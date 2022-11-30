package com.bitcamp.testproject.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.bitcamp.testproject.service.PartyService;
import com.bitcamp.testproject.service.ReviewService;
import com.bitcamp.testproject.vo.AttachedFile;
import com.bitcamp.testproject.vo.Member;
import com.bitcamp.testproject.vo.Party;
import com.bitcamp.testproject.vo.Review;

@Controller
@RequestMapping("/review/")
public class ReviewController {

  @Autowired
  ServletContext sc;

  @Autowired
  ReviewService reviewService;
  @Autowired
  PartyService partyService;

  @GetMapping("review-detail-form") 
  public Map reviewDetailForm(int no) throws Exception {
    Party party = partyService.get(no);
    if (party == null) {
      throw new Exception("해당 번호의 모임이 없습니다!");
    }
    Map map = new HashMap();
    map.put("party", party);
    return map;
  }

  @GetMapping("review-form") 
  public void reviewForm(int no, Model model) throws Exception {
    Party party = partyService.get(no);
    if (party == null) {
      throw new Exception("해당 번호의 모임이 없습니다!");
    }
    model.addAttribute("party", party);
  }


  @PostMapping("review-add") 
  public String add(
      int no,
      double star,
      MultipartFile[] files,
      String title,
      String content,
      Review review,
      HttpSession session) throws Exception {
    System.out.println("add 도착?");
    Member member = (Member) session.getAttribute("loginMember");

    review.setPartyNo(no);
    review.setStar(star);
    review.setAttachedFiles(saveAttachedFiles(files));
    review.setTitle(title);
    review.setContent(content);
    review.setWriter(member);

    reviewService.add(review);


    return "redirect:../mypage/end-party";
  }


  private List<AttachedFile> saveAttachedFiles(MultipartFile[] files)
      throws IOException, ServletException {
    List<AttachedFile> attachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/review/files");

    for (MultipartFile part : files) {
      if (part.isEmpty()) {
        continue;
      }

      String filename = UUID.randomUUID().toString();
      part.transferTo(new File(dirPath + "/" + filename));
      attachedFiles.add(new AttachedFile(filename));
    }
    return attachedFiles;
  }


}






