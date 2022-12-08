package com.bitcamp.testproject.web.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
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
  public Model listMain(Model model) throws Exception {
    // 인기 게시글 가져오기
    List<Map<String,Object>> bestList = boardService.bestList();
    // 챌린지 게시글 가져오기
    List<Map<String,Object>> clgList = boardService.clgList();

    model.addAttribute("bestList", bestList);
    model.addAttribute("clgList", clgList);
    return model;
  }

  @GetMapping("form")
  public Model form(int cateno, Model model) throws Exception {
    return model.addAttribute("cateno", cateno);
  }

  @PostMapping("add") 
  public String add(Board board, Part file, HttpSession session) throws Exception {
    int cateno = board.getCateno();
    if (cateno == 3) { // 챌린지 게시판일 때 썸네일 이미지 저장
      board.setThumbnail(saveAttachedFile(file));
    }
    board.setWriter((Member) session.getAttribute("loginMember"));
    // 등록 후 상세조회 페이지로 이동하기 위한 게시글 번호 가지고오기 
    int boardNo = boardService.addAndReturnBoardNo(board);

    return "redirect:detail?no=" + boardNo;
  }

  @GetMapping("list")
  public ModelAndView list(Criteria cri, int no, Search search, ModelAndView mav) throws Exception {
    // 게시판 종류에 따라 다른 view 셋팅
    if (no == 3) {
      mav = new ModelAndView("board/listOfClg");
    } else {
      mav = new ModelAndView("board/list");
    }

    cri.setPerPageNum(10); // 한 페이지의 게시글 수 설정
    cri.setCatenoToPage(no); // 특정 게시판의 목록을 출력하기위한 설정

    List<Map<String,Object>> list;
    int boardTotalCount;
    // 검색결과 목록인지 단순 목록인지 확인 후 특정 목록 출력과 전체 게시글 수 찾기
    if (search.getKeyword() == null || search.getKeyword() == "") {
      list = boardService.list(cri);
      boardTotalCount = boardService.countTotalBoard(no);
    } else {
      list = boardService.listWithSearch(cri, search);
      boardTotalCount = boardService.countTotalBoardWithSearch(no, search);
    }

    // 페이징 연산자 준비
    PageMaker pageMaker = readyPageMaker(cri, boardTotalCount);;

    mav.addObject("list", list);
    mav.addObject("pageMaker", pageMaker);
    mav.addObject("cateno", no);
    mav.addObject("search", search); // 검색 결과 문구를 위해 보내줌
    return mav;
  }

  @GetMapping("detail")
  public Model detail(int no, Model model, HttpServletRequest request, HttpServletResponse response, Criteria cri) throws Exception {

    // 페이징하기 위한 연산 
    cri.setPerPageNum(5);
    PageMaker pageMaker = readyPageMaker(cri, boardCommentService.countTotalComment(no));

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
    if (board.getCateno() == 3) { // 챌린지 게시판일 때 썸네일 이미지 저장
      board.setThumbnail(saveAttachedFile(file));
    }
    checkOwner(board.getNo(), session);

    if (!boardService.update(board)) {
      throw new Exception("게시글을 변경할 수 없습니다!");
    }

    return "redirect:detail?no=" + board.getNo();
  }


  @GetMapping("delete")
  public String delete(int no, HttpSession session) throws Exception {

    // 삭제 후 돌아갈 게시판카테고리 넘버 찾기
    int cateno = boardService.get(no).getCateno();
    // 회원 유효성 체크(삭제할 회원이 같은 회원인지)
    checkOwner(no, session);

    if (!boardService.delete(no)) {
      throw new Exception("게시글을 삭제할 수 없습니다.");
    }

    return "redirect:list?no=" + cateno;
  }

  private String saveAttachedFile(Part file) throws Exception {
    String dirPath = sc.getRealPath("/board/files");
    // 첨부파일이 있다면 실행
    if (file.getSize() != 0) {
      String filename = UUID.randomUUID().toString(); // 고유한 이름 생성
      file.write(dirPath + "/" + filename);
      return filename;
    }
    return null;
  }

  private void checkOwner(int boardNo, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    if (boardService.get(boardNo).getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }
  }

  private PageMaker readyPageMaker(Criteria cri, int totalCount) {
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(totalCount);

    return pageMaker;
  }

  private void viewCountUp(int no, HttpServletRequest request, HttpServletResponse response) {
    Cookie oldCookie = null;
    Cookie[] cookies = request.getCookies();
    // 쿠키에 저장된 조회수리스트 찾기
    if (cookies != null) { 
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("boardView")) {
          oldCookie = cookie;
        }
      }
    } 
    // 조회수리스트가 존재한다면 실행 (처음 조회하는 게시판이라면 조회수 증가)    
    if (oldCookie != null) { 
      if (!oldCookie.getValue().contains("[" + no + "]")) {
        boardService.increaseViews(no);
        oldCookie.setValue(oldCookie.getValue() + "_[" + no + "]");
        oldCookie.setPath("/");
        oldCookie.setMaxAge(60 * 60 * 24);
        response.addCookie(oldCookie);
      }
      // 조회수리스트가 존재하지 않는다면 실행
    } else { 
      boardService.increaseViews(no);
      Cookie newCookie = new Cookie("boardView","[" + no + "]");
      newCookie.setPath("/");
      newCookie.setMaxAge(60 * 60 * 24);
      response.addCookie(newCookie);
    }
  }

}