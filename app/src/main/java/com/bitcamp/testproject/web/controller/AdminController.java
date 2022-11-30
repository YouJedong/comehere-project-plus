package com.bitcamp.testproject.web.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.bitcamp.testproject.service.AdminService;
import com.bitcamp.testproject.vo.Member;

@Controller
@RequestMapping("/admin/")
public class AdminController {

  @Autowired
  AdminService adminService;

  @GetMapping("form")
  public void form() throws Exception {
  }

  @PostMapping("login")
  public String login(Model model, Member idPwd, HttpSession session, RedirectAttributes re) throws Exception {

    // 관리자 정보가 맞다면 login
    Member admin = adminService.getAdmin(idPwd);
    if (admin != null) {
      session.setAttribute("loginMember", admin);
      return "redirect:../";
    } else {
      String loginFail = "fail";
      re.addFlashAttribute("loginFail", loginFail);
      return "redirect:/admin";
    }


    //    if(member != null) {
    //      ModelAndView mv = new ModelAndView("redirect:" + beforePageURL);
    //      mv.addObject("member", member);
    //      return mv;
    //    }
    //
    //    ModelAndView mv = new ModelAndView("auth/loginResult");
    //    mv.addObject("member", member);
    //    return mv;

  }

  @GetMapping("mngment")
  public void mngment() throws Exception {
  }


  //  
  //  @GetMapping("findId")
  //  public String findId() {
  //    return "auth/findId";
  //  }
  //
  //  @GetMapping("findPassword")
  //  public String findIdPassword() {
  //    return "auth/findPassword";
  //  }
  //
  //
  //  @GetMapping("sendMail")
  //  public String sendMail() {
  //    return "auth/sendMail";
  //  }
  //
  //  @GetMapping("findById")
  //  public ModelAndView findById(String name, String email, HttpServletResponse response,
  //      HttpSession session) throws Exception {
  //
  //    Member member = memberService.getId(name, email);
  //
  //    if (member != null) {
  //      session.setAttribute("findId", member);
  //    }
  //
  //    ModelAndView mv = new ModelAndView("auth/findIdResult");
  //    mv.addObject("member", member);
  //    return mv;
  //  }
  //
  //
  //
  //
  //  @PostMapping("mail/send")
  //  @ResponseBody
  //  public String send(String email) {
  //
  //    Random random = new Random();
  //    int SecCode = random.nextInt(888888) + 111111;
  //
  //    emailService.sendSimpleMessage(email, SecCode);
  //    return Integer.toString(SecCode);
  //  }
  //
  //
  //  @GetMapping("findByPassword")
  //  public ModelAndView findByPassword(String id, String email, String secCode,
  //      HttpServletResponse response, HttpSession session) throws Exception {
  //
  //    Member member = memberService.getByPassword(id, email, secCode);
  //
  //    if (member != null) {
  //      session.setAttribute("findByPassword", member);
  //    }
  //
  //    ModelAndView mv = new ModelAndView("auth/newPassword");
  //    mv.addObject("member", member);
  //    return mv;
  //  }
  //
  //  @GetMapping("newPassword")
  //  public String newPassword(String email, String id, Model model) {
  //    model.addAttribute("email", email);
  //    model.addAttribute("id", id);
  //    return "auth/newPassword";
  //  }
  //
  //  @GetMapping("logout")
  //  public String logout(HttpSession session, HttpServletRequest request) throws Exception {
  //
  //    String beforePageURL = request.getHeader("Referer");
  //    request.getSession().setAttribute("redirectURI", beforePageURL);
  //
  //
  //    session.invalidate();
  //    //    return "redirect:../";
  //    return "redirect:" + beforePageURL;
  //  }
  //
  //
  //  @PostMapping("updatePW")
  //  public String updatePW(String password, String email, String id, HttpSession session) throws Exception {
  //    boolean result = memberService.updatePW(password, email, id);
  //
  //    if (result != false) {
  //    } 
  //    return "redirect:form";
  //  }
  //
  //  // 헌식 끝
  //
  //  // // 아이디 중복 체크 확인
  //  // @PostMapping("id-check")
  //  // @ResponseBody
  //  // public String idCheck(Member member) throws Exception {
  //  // return memberService.checkId(member);
  //  // }
  //
  //  // 은지
  //  @GetMapping("join")
  //  public String form(Model model) throws Exception {
  //    model.addAttribute("data", "join page");
  //    return "auth/join";
  //  }
  //
  //  @PostMapping("add")
  //  public String add(Member member) throws Exception {
  //    memberService.add(member);
  //    return "redirect:join";
  //  }
  //
  //  @GetMapping("mypage-member")
  //  public String myPageMember(Member member) {
  //
  //    return "myPageMember";
  //  }
  //
  //

}


