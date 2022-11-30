package com.bitcamp.testproject.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bitcamp.testproject.service.BoardCommentService;
import com.bitcamp.testproject.service.BoardService;
import com.bitcamp.testproject.service.MemberService;
import com.bitcamp.testproject.service.PartyMemberService;
import com.bitcamp.testproject.service.PartyService;
import com.bitcamp.testproject.service.ScrapService;
import com.bitcamp.testproject.vo.Criteria;
import com.bitcamp.testproject.vo.Member;
import com.bitcamp.testproject.vo.PageMaker;
import com.bitcamp.testproject.vo.Party;

@Controller
@RequestMapping("/mypage/")
public class MypageController {

  @Autowired
  ServletContext sc;
  @Autowired
  BoardService boardService;
  @Autowired
  BoardCommentService boardCommentService;
  @Autowired
  PartyService partyService;
  @Autowired
  MemberService memberService;
  @Autowired
  ScrapService scrapService;
  @Autowired
  PartyMemberService partyMemberService;

  @GetMapping("my-post")
  public String myPost(HttpSession session, Model model, Criteria cri) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");

    // 페이징하기 위한 연산 
    PageMaker pageMaker = new PageMaker();
    cri.setPerPageNum(10);
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(boardService.countMyPost(loginMember.getNo()));

    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("memberNo", loginMember.getNo());
    paramMap.put("pagesStart", cri.getPagesStart());
    paramMap.put("perPageNum", cri.getPerPageNum());

    model.addAttribute("myPostList", boardService.findByMyPost(paramMap));
    model.addAttribute("pageMaker", pageMaker);

    return "mypage/my-post";
  }

  @GetMapping("my-party")
  public String myParty(HttpSession session, Model model, Criteria cri) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");

    // 페이징하기 위한 연산 
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(partyService.countMyParty(loginMember.getNo()));

    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("memberNo", loginMember.getNo());
    paramMap.put("pagesStart", cri.getPagesStart());
    paramMap.put("perPageNum", cri.getPerPageNum());

    model.addAttribute("myPartys", partyService.findByMyParty(paramMap));
    model.addAttribute("pageMaker", pageMaker);

    return "mypage/my-party";
  }

  @GetMapping("my-comment")
  public String myComment(HttpSession session, Model model, Criteria cri) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");
    // 페이징하기 위한 연산 
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(boardCommentService.countTotalCommentOfMember(loginMember.getNo()));

    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("memberNo", loginMember.getNo());
    paramMap.put("cri", cri);

    model.addAttribute("myCommentList", boardCommentService.getCommentsOfMember(paramMap));
    model.addAttribute("pageMaker", pageMaker);

    return "mypage/my-comment";
  }

  @GetMapping("my-scrap")
  public String myScrap(HttpSession session, Model model, Criteria cri) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");
    // 페이징하기 위한 연산 
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(scrapService.countTotalScrapOfMember(loginMember.getNo()));

    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("memberNo", loginMember.getNo());
    paramMap.put("cri", cri);

    model.addAttribute("myScrapList", scrapService.getScrapsOfMember(paramMap));
    model.addAttribute("pageMaker", pageMaker);

    return "mypage/my-scrap";
  }

  @GetMapping("join-party")
  public String joinParty(HttpSession session, Model model, Criteria cri) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");

    // 페이징하기 위한 연산 
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(partyService.countJoinParty(loginMember.getNo()));

    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("memberNo", loginMember.getNo());
    paramMap.put("pagesStart", cri.getPagesStart());
    paramMap.put("perPageNum", cri.getPerPageNum());


    model.addAttribute("joinPartys", partyService.findByJoinParty(paramMap));
    model.addAttribute("pageMaker", pageMaker);

    return "mypage/join-party";
  }


  @GetMapping("end-party")
  public String endParty(HttpSession session, Model model, Criteria cri) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");

    // 페이징하기 위한 연산 
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(partyService.countEndParty(loginMember.getNo()));

    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("memberNo", loginMember.getNo());
    paramMap.put("pagesStart", cri.getPagesStart());
    paramMap.put("perPageNum", cri.getPerPageNum());

    int pageNum = cri.getPage() - 1;
    List<Party> endPartyListTotal = partyService.findByEndParty(paramMap);
    List<Party> endPartyListTotal2 = new ArrayList<>();
    if (partyService.countEndParty(loginMember.getNo()) != 0) {
      int count = ((pageNum + 1) * 10) - 10;
      System.out.println(count);
      int listt = partyService.countEndParty(loginMember.getNo());
      // 페이징 처리 (해당 개수만, 페이지 넘기거나 앞으로 하면 적용)
      if (partyService.countEndParty(loginMember.getNo()) >= 10) {
        for (int i=0 + count; i<cri.getPerPageNum() + count; i++) {
          System.out.println("dded");
          endPartyListTotal2.add(endPartyListTotal.get(i));
          System.out.println("dded");
        }
      } else {
        for (int i=0 + count; i< listt; i++) {
          System.out.println("dded");
          endPartyListTotal2.add(endPartyListTotal.get(i));
          System.out.println("dded");
        }      }
    }
    model.addAttribute("endPartys", endPartyListTotal2);
    model.addAttribute("pageMaker", pageMaker);

    return "mypage/end-party";
  }




  @GetMapping("my-party-detail")
  public String myPartyDetail(int no, Model model, Criteria cri, HttpSession session) throws Exception {

    Party party = partyService.get(no);

    // 페이징하기 위한 연산 
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(partyMemberService.countMyPartyMember(no));

    Map<String, Object> paramMap = new HashMap<String, Object>();
    paramMap.put("partyNo", party.getNo());
    paramMap.put("pagesStart", cri.getPagesStart());
    paramMap.put("perPageNum", cri.getPerPageNum());

    model.addAttribute("party", party);
    model.addAttribute("myPartyMembers", partyMemberService.findByMyPartyList(paramMap));
    model.addAttribute("pageMaker", pageMaker);
    return "mypage/my-party-detail";
  }

  @GetMapping("partymember-update")
  public String partymemberUpdate(int status, int memberNo, int partyNo) throws Exception {
    if (status == 2) {
      if (!partyMemberService.partyMemberOk(status, memberNo, partyNo)) {
      }    
    }
    if (status == 3) {
      if (!partyMemberService.partyMemberNo(status, memberNo, partyNo)) {
      }   
    } 
    return "redirect:my-party-detail?no=" + partyNo;
  }

  @GetMapping("check-headcount-ajax")
  @ResponseBody
  public String checkHeadcout(int partyNo) throws Exception {
    Party party = partyService.get(partyNo);
    if (party.getHeadCount() > partyMemberService.countPartyMember(partyNo)) {
      return "1";
    } else {
      return "0";
    }
  }
}




