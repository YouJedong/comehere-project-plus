package com.bitcamp.testproject.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bitcamp.testproject.service.BoardReportService;
import com.bitcamp.testproject.vo.Member;

@Controller
@RequestMapping("/boardReport/")
public class BoardReportController {

  @Autowired
  ServletContext sc;
  @Autowired
  BoardReportService boardReportService;


  @PostMapping("add")
  public String add(int tatlleNo, int boardNo, int value, String name, HttpSession session) throws Exception {
    Map<String, Object> reportMap = new HashMap<>();

    // 신고사유, 신고한 회원, 신고 대상번호 데이터를 담아 놓는다.
    reportMap.put("trno", tatlleNo);
    reportMap.put("mno", ((Member) session.getAttribute("loginMember")).getNo());
    reportMap.put("decbno", value);

    if (name.equals("댓글")) {
      boardReportService.addCommentReport(reportMap);

    }
    if (name.equals("게시글")) {
      boardReportService.addBoardReport(reportMap);
    }
    return"redirect:../board/detail?no=" + boardNo;
  }

}
