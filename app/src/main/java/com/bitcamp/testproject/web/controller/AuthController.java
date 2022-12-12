package com.bitcamp.testproject.web.controller;

import java.util.Random;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.bitcamp.testproject.service.EmailService;
import com.bitcamp.testproject.service.MemberService;
import com.bitcamp.testproject.vo.KakaoProfile;
import com.bitcamp.testproject.vo.Mail;
import com.bitcamp.testproject.vo.Member;
import com.bitcamp.testproject.vo.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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


  @GetMapping("kakao")
  public ModelAndView kakao(String code, HttpSession session, HttpServletResponse res, ModelAndView mv) {

    // Post 방식으로 key=value 데이터 요청(카카오로)
    RestTemplate rt = new RestTemplate();

    // HttpHeader 오브젝트 생성
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

    // HttpBody 오브젝트 생성
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", "3e127c745fa2928767e1e53af087b50f");
    params.add("redirect_uri", "http://localhost:8888/app/auth/kakao");
    params.add("code", code);

    // HttpHeader와 HttpBody를 하나의 오브젝트에 넣기
    HttpEntity<MultiValueMap<String, String>> kakaoRequest = new HttpEntity<>(params, headers);

    // Http요청하기(post 방식으로) 그 후 response 변수의 응답을 받음
    ResponseEntity<String> response = rt.exchange(
        "https://kauth.kakao.com/oauth/token",
        HttpMethod.POST,
        kakaoRequest,
        String.class
        );

    // Gson Library, JSON SIMPLE LIBRARY, OBJECT MAPPER 사용
    // 카카오에 요청 후 보내준 데이터(토큰 등)을 OAuthToken에 담기 
    ObjectMapper objectMapper = new ObjectMapper();
    OAuthToken oauthToken = null;
    try {
      oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    // 카카오 유저 정보 가지고 오기
    KakaoProfile profile = getProfile(oauthToken);

    Member member = memberService.getWithKakao(profile.id);

    if (member != null) {
      session.setAttribute("loginMember", member);
      mv = new ModelAndView("redirect:/");
      mv.addObject("member", member);
      return mv;
    }

    //    String id = member.getId();
    //    Cookie cookie = new Cookie("id", id);
    //    if (id == null) {
    //      cookie.setMaxAge(0);
    //    } else {
    //      cookie.setMaxAge(60 * 60 * 24 * 7); // 7일
    //    }
    //    res.addCookie(cookie);

    else {
      mv = new ModelAndView("redirect:form");
      return mv;
    }



    // 로그인 쿠키 삭제

  }

  private KakaoProfile getProfile(OAuthToken oauthToken) {
    RestTemplate rt = new RestTemplate(); // http 요청을 간단하게 해줄 수 있는 클래스

    //HttpHeader 오브젝트 생성
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer "+ oauthToken.getAccess_token());
    headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

    HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

    //Http 요청하기 - POST 방식으로 - 그리고 response 변수의 응답을 받음.
    ResponseEntity<String> response = rt.exchange(
        "https://kapi.kakao.com/v2/user/me",
        HttpMethod.POST,
        kakaoProfileRequest,
        String.class
        );

    ObjectMapper objectMapper = new ObjectMapper();
    KakaoProfile profile  = null;
    //Model과 다르게 되있으면 그리고 getter setter가 없으면 오류가 날 것이다.
    try {
      profile = objectMapper.readValue(response.getBody(), KakaoProfile.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    System.out.println(profile);

    return profile;
  }

}


