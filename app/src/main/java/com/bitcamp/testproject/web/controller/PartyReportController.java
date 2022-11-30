package com.bitcamp.testproject.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bitcamp.testproject.service.PartyReportService;
import com.bitcamp.testproject.vo.Member;

@Controller
@RequestMapping("/partyReport/")
public class PartyReportController {

  @Autowired
  ServletContext sc;
  @Autowired
  PartyReportService partyReportService;


  @PostMapping("add")
  public String add(int partyNo, int objectNo, int value, String name, HttpSession session) throws Exception {
    Map<String, Object> reportMap = new HashMap<>();
    reportMap.put("trno", value);
    reportMap.put("mno", ((Member) session.getAttribute("loginMember")).getNo());
    reportMap.put("mnoer", objectNo);

    if (name.equals("댓글")) {
      partyReportService.addCommentReport(reportMap);

    }
    if (name.equals("파티")) {
      partyReportService.addMemberReport(reportMap);
    }
    return"redirect:../party/detail?no=" + partyNo;
  }



}
