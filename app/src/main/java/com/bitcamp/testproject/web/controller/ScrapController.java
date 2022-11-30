package com.bitcamp.testproject.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bitcamp.testproject.service.ScrapService;
import com.bitcamp.testproject.vo.Scrap;

@Controller
@RequestMapping("/scrap/")
public class ScrapController {

  @Autowired
  ServletContext sc;
  @Autowired
  ScrapService scrapService;

  @PostMapping("add")
  @ResponseBody
  public int add(Scrap scrap, HttpSession session) throws Exception {
    return scrapService.insert(scrap);
  }

  @PostMapping("delete")
  @ResponseBody
  public int delete(Scrap scrap, HttpSession session) throws Exception {
    return scrapService.delete(scrap);
  }
}










