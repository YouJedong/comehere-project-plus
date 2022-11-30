package com.bitcamp.testproject.web.controller;

import java.io.IOException;
import java.util.HashMap;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bitcamp.testproject.service.NoticeService;
import com.bitcamp.testproject.vo.Criteria;
import com.bitcamp.testproject.vo.Notice;
import com.bitcamp.testproject.vo.PageMaker;
import com.bitcamp.testproject.vo.Search;

@Controller
@RequestMapping("/notice/")
public class NoticeController {

  @Autowired
  ServletContext sc;

  @Autowired
  NoticeService noticeService;

  @GetMapping("list")
  public String notiList(Model model, Criteria cri, Search search) {
    System.out.println(search);
    // 페이징하기 위한 연산
    PageMaker pageMaker = new PageMaker();
    cri.setPerPageNum(10);
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(noticeService.countNotice());

    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("pagesStart", cri.getPagesStart());
    paramMap.put("perPageNum", cri.getPerPageNum());
    paramMap.put("search", search);
    // Search [keyword=이용, type=T, typeArr=[T]]
    model.addAttribute("pageMaker", pageMaker);
    model.addAttribute("search", search);
    model.addAttribute("noticeList", noticeService.findAll(paramMap));

    return "/notice/list";
  }

  @GetMapping("form")
  public String form(Model model) throws Exception {
    return "/notice/form";
  }

  @Transactional
  @PostMapping("add")
  public String add(Notice notice, Part file, HttpSession session) throws Exception {
    // 파일경로넣기, 등록회원 정보 넣기
    notice.setThumbnail(saveAttachedFile(file));

    noticeService.add(notice);
    return "redirect:detail?no=" + notice.getNo();

  }

  @GetMapping("detail")
  public Model detail(int no, Model model, HttpServletRequest request, HttpServletResponse response,
      Criteria cri) throws Exception {

    // 조회수 증가
    viewCountUp(no, request, response);

    // 게시글 꺼내기
    Notice notice = noticeService.get(no);

    if (notice == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }

    return model.addAttribute("notice", notice);
  }


  @GetMapping("updateForm")
  public Model updateForm(int no, Model model) throws Exception {
    Notice notice = noticeService.get(no);

    if (notice == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }

    return model.addAttribute("notice", notice);
  }

  @PostMapping("update")
  public String update(Notice notice, Part file, HttpSession session) throws Exception {

    System.out.println(notice + "\n 노티스 넘어오았냐?!!?!?!?!?");
    notice.setThumbnail(saveAttachedFile(file));
    System.out.println(file + "\n 파일 넘어오았냐?!!?!?!?!?");
    if (!noticeService.update(notice)) { 
      throw new Exception("게시글을 변경할 수 없습니다!"); 
    }

    return "redirect:detail?no=" + notice.getNo();
  }

  @GetMapping("delete")
  public String delete(int no, HttpSession session) throws Exception {

    if (!noticeService.delete(no)) {
      throw new Exception("게시글을 삭제할 수 없습니다.");
    }

    return "redirect:list";
  }


  private String saveAttachedFile(Part file) throws IOException, ServletException {
    String dirPath = sc.getRealPath("/notice/files");
    // 첨부파일이 있다면 실행
    if (file.getSize() != 0) {
      String filename = UUID.randomUUID().toString();
      file.write(dirPath + "/" + filename);
      return filename;
    }
    return null;
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
        noticeService.increaseViews(no);
        oldCookie.setValue(oldCookie.getValue() + "_[" + no + "]");
        oldCookie.setPath("/");
        oldCookie.setMaxAge(60 * 60 * 24);
        response.addCookie(oldCookie);
      }
    } else {
      noticeService.increaseViews(no);
      Cookie newCookie = new Cookie("postView", "[" + no + "]");
      newCookie.setPath("/");
      newCookie.setMaxAge(60 * 60 * 24);
      response.addCookie(newCookie);
    }
  }
}
