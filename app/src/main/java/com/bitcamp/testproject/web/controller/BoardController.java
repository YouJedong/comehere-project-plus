package com.bitcamp.testproject.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.bitcamp.testproject.service.BoardCommentService;
import com.bitcamp.testproject.service.BoardService;
import com.bitcamp.testproject.vo.Board;
import com.bitcamp.testproject.vo.BoardCategory;
import com.bitcamp.testproject.vo.Criteria;
import com.bitcamp.testproject.vo.Member;
import com.bitcamp.testproject.vo.PageMaker;
import com.bitcamp.testproject.vo.Search;

@Controller
@RequestMapping("/board/")
public class BoardController {

  @Autowired
  ServletContext sc;

  @Autowired
  BoardService boardService;
  @Autowired
  BoardCommentService boardCommentService;

  @GetMapping("main")
  public Model main(Model model) {

    // 인기 게시글 가져오기
    List<Map<String,Object>> bestList = boardService.bestList();

    // 챌린지 게시글 가져오기
    List<Map<String,Object>> clgList = boardService.clgList();

    model.addAttribute("bestList", bestList);
    model.addAttribute("clgList", clgList);

    return model;
  }

  //  제동 메서드 추가 
  @PostMapping("add") 
  public String add(Board board, int cateno, Part file, HttpSession session) throws Exception {
    // 카테고리 번호, 파일경로넣기, 등록회원 정보 넣기
    if (board.getCateno() == 3) {
      board.setThumbnail(saveAttachedFile(file));
    }
    board.setCateno(cateno);
    board.setWriter((Member) session.getAttribute("loginMember"));

    boardService.add(board);
    return "redirect:list?no=" + cateno;
  }

  private String saveAttachedFile(Part file) throws IOException, ServletException {
    String dirPath = sc.getRealPath("/board/files");
    // 첨부파일이 있다면 실행
    if (file.getSize() != 0) {
      String filename = UUID.randomUUID().toString();
      file.write(dirPath + "/" + filename);
      return filename;
    }
    return null;
  }

  @GetMapping("form")
  public Model form(int cateno, Model model) throws Exception {
    BoardCategory boardCategory = boardService.getCategory(cateno);
    return model.addAttribute("category", boardCategory);
  }

  @GetMapping("detail")
  public Model detail(int no, Model model, HttpServletRequest request, HttpServletResponse response, Criteria cri) throws Exception {

    // 페이징하기 위한 연산 
    PageMaker pageMaker = new PageMaker();
    cri.setPerPageNum(5);
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(boardCommentService.countCommentListTotal(no));


    // 조회수 증가     
    viewCountUp(no, request, response);

    // 게시글 꺼내기
    Board board = boardService.get(no);
    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }
    model.addAttribute("board", board);
    model.addAttribute("pageMaker", pageMaker);

    return model;
  }

  // 조회수 증가 
  private void viewCountUp(int no, HttpServletRequest request, HttpServletResponse response) {

    Cookie oldCookie = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("postView")) {
          oldCookie = cookie;
        }
      }
    }
    if (oldCookie != null) {
      if (!oldCookie.getValue().contains("[" + no + "]")) {
        boardService.increaseViews(no);
        oldCookie.setValue(oldCookie.getValue() + "_[" + no + "]");
        oldCookie.setPath("/");
        oldCookie.setMaxAge(60 * 60 * 24);
        response.addCookie(oldCookie);
      }
    } else {
      boardService.increaseViews(no);
      Cookie newCookie = new Cookie("postView","[" + no + "]");
      newCookie.setPath("/");
      newCookie.setMaxAge(60 * 60 * 24);
      response.addCookie(newCookie);
    }
  }

  @GetMapping("list")
  public ModelAndView list(Criteria cri, int no, Search search) throws Exception {
    ModelAndView mav;
    if (no == 3) {
      // 챌린지 게시판일 경우 (listOfClg.html을 실행하고 게시글 9개만 출력)
      mav = new ModelAndView("board/listOfClg");
      cri.setPerPageNum(10);
    } else {
      mav = new ModelAndView("board/list");
      cri.setPerPageNum(10);
    }

    cri.setCatenoToPage(no); // 특정 게시판을 목록을 출력하기위한 설정

    // 검색 기능인지 단지 목록 출력인지에 따라 다른 메서드 호출 
    List<Map<String,Object>> list;
    int boardTotalCount;
    if (search.getKeyword() == null || search.getKeyword() == "") {
      list = boardService.list(cri);
      boardTotalCount = boardService.countTotalBoard(no);
    } else {
      list = boardService.listWithKeyword(cri, search);
      boardTotalCount = boardService.countTotalBoardWithSearch(no, search);
    }

    // 페이징하기 위한 연산 
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(boardTotalCount);

    // 게시글 카테고리 번호 Board객체에 담아서 map객체에 담을 준비 
    Board catenoInBoard = new Board();
    catenoInBoard.setCateno(no);

    mav.addObject("list", list);
    mav.addObject("pageMaker", pageMaker);
    mav.addObject("catenoInBoard", catenoInBoard);
    mav.addObject("search", search);

    return mav;
  }

  @GetMapping("delete")
  public String delete(int no, HttpSession session) throws Exception {

    // 삭제 후 돌아갈 게시판카테고리 넘버 찾기
    int cateno = boardService.get(no).getCateno();
    // 회원 유효성 체크(삭제할 회원이 같이 회원인지)
    checkOwner(no, session);

    if (!boardService.delete(no)) {
      throw new Exception("게시글을 삭제할 수 없습니다.");
    }

    return "redirect:list?no=" + cateno;
  }


  @GetMapping("updateForm")
  public Model updateForm(int no, Model model) throws Exception {
    Board board = boardService.get(no);

    if (board == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }

    return model.addAttribute("board", board);
  }

  @PostMapping("update")
  public String update(Board board, Part file, HttpSession session) throws Exception {
    if (board.getCateno() == 3) {
      board.setThumbnail(saveAttachedFile(file));
    }
    checkOwner(board.getNo(), session);

    if (!boardService.update(board)) {
      throw new Exception("게시글을 변경할 수 없습니다!");
    }

    return "redirect:detail?no=" + board.getNo();
  }

  @GetMapping("fileDelete")
  public String boardFileDelete(int no, HttpSession session) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");
    Board board = boardService.get(no);

    if (board.getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }

    if (boardService.deleteThumbnail(no) == 0) {
      throw new Exception("게시글 첨부파일을 삭제할 수 없습니다.");
    }

    return "redirect:updateForm?no=" + board.getNo();
  }

  private void checkOwner(int boardNo, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    if (boardService.get(boardNo).getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }
  }




}