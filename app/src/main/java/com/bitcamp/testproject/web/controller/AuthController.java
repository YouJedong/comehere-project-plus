package com.bitcamp.testproject.web.controller;

import java.util.Random;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.bitcamp.testproject.service.EmailService;
import com.bitcamp.testproject.service.MemberService;
import com.bitcamp.testproject.vo.Mail;
import com.bitcamp.testproject.vo.Member;

@Controller
@RequestMapping("/auth/")
public class AuthController {

  @Autowired
  EmailService emailService;

  @Autowired
  MemberService memberService;

  public AuthController(MemberService memberService) {
    System.out.println("AuthController() 호출됨!");
    this.memberService = memberService;
  }

  // 헌식
  @GetMapping("form")
  public String form(@CookieValue(name = "id", defaultValue = "") String id, Model model, HttpServletRequest request)
      throws Exception {

    String referer = request.getHeader("Referer");
    request.getSession().setAttribute("redirectURI", referer);

    model.addAttribute("id", id);
    return "auth/form";
  }


  @PostMapping("login")
  public ModelAndView login(String id, String password, HttpServletResponse response,
      HttpSession session, String beforePageURL) throws Exception {

    Member member = memberService.get(id, password);

    String[] url = beforePageURL.split("app/");
    if (url.length > 1) {
      String[] url2 = url[1].split("/");
      if (url2[0].equals("auth")) {
        ModelAndView mv = new ModelAndView("redirect:../");
        System.out.println("도착함 ");
        session.setAttribute("loginMember", member);
        return mv;    
      }
    }

    if (member != null) {
      session.setAttribute("loginMember", member);
    }

    Cookie cookie = new Cookie("id", id);
    if (id == null) {
      cookie.setMaxAge(0);
    } else {
      cookie.setMaxAge(60 * 60 * 24 * 7); // 7일
    }
    response.addCookie(cookie);

    if(member != null) {
      ModelAndView mv = new ModelAndView("redirect:../");

      mv.addObject("member", member);
      return mv;
    } else {
      ModelAndView mv = new ModelAndView("redirect:form");
      return mv;
    }

  }

  @GetMapping("find-id")
  public String findId() {
    return "auth/find-id";
  }

  @GetMapping("find-password")
  public String findIdPassword() {
    return "auth/find-password";
  }


  @GetMapping("sendMail")
  public String sendMail() {
    return "auth/sendMail";
  }

  @GetMapping("findById")
  public ModelAndView findById(String name, String email, HttpServletResponse response,
      HttpSession session) throws Exception {

    Member member = memberService.getId(name, email);

    if (member != null) {
      session.setAttribute("findId", member);
    }

    ModelAndView mv = new ModelAndView("auth/form");
    mv.addObject("member", member);
    return mv;
  }




  @PostMapping("mail/send")
  @ResponseBody
  public String send(String email) throws Exception {

    Random random = new Random();
    int SecCode = random.nextInt(888888) + 111111;

    Mail mail = new Mail();
    mail.setAddress(email);
    mail.setTitle("[여기모여] 이메일 계정 인증");
    mail.setCheckNum(SecCode);
    mail.setTemplate("emailCode");
    emailService.sendTemplateMessage(mail);

    return Integer.toString(SecCode);
  }


  @GetMapping("findByPassword")
  public ModelAndView findByPassword(String id, String email, String secCode,
      HttpServletResponse response, HttpSession session) throws Exception {

    Member member = memberService.getByPassword(id, email, secCode);

    if (member != null) {
      session.setAttribute("findByPassword", member);
    }

    ModelAndView mv = new ModelAndView("auth/newPassword");
    mv.addObject("member", member);
    return mv;
  }

  @GetMapping("new-password")
  public String newPassword(String email, String id, Model model) {
    model.addAttribute("email", email);
    model.addAttribute("id", id);
    return "auth/new-password";
  }

  @GetMapping("logout")
  public String logout(HttpSession session, HttpServletRequest request) throws Exception {

    session.invalidate();
    return "redirect:../";
  }


  @PostMapping("updatePW")
  public String updatePW(String password, String email, String id, HttpSession session) throws Exception {
    boolean result = memberService.updatePW(password, email, id);

    if (result != false) {
      System.out.println("변경 실패");
    } 
    return "redirect:form";
  }

  @PostMapping("idEmailCheck")
  @ResponseBody
  public String idEmailCheck( String id,String email , HttpSession session) throws Exception {
    Member result = memberService.idEmailCheck(id,email);
    System.out.println(result);

    if (result == null) {
      System.out.println("회원 없음");
      return "false";
    } 
    return "true";
  }

  @PostMapping("idPasswordCheck")
  @ResponseBody
  public String idPasswordCheck( String id,String password , HttpSession session) throws Exception {
    Member result = memberService.idPasswordCheck(id,password);
    System.out.println(result);

    if (result == null) {
      System.out.println("회원 없음");
      return "false";
    }
    System.out.println(result.getActive());

    if (result.getActive() == 0) {
      System.out.println("탈퇴한 회원");
      return "active";
    }
    return "true";
  }

  @GetMapping("findIdCheck")
  @ResponseBody
  public String findIdCheck( String name,String email , HttpSession session) throws Exception {
    Member result = memberService.findIdCheck(name,email);
    System.out.println("name" + name);
    System.out.println("email" + email);
    System.out.println("result" + result);
    if (result == null) {
      System.out.println("회원 없음");
      return "false";
    }
    return result.getId();
  }


  // 헌식 끝

}


