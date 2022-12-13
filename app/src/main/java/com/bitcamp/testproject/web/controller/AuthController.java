package com.bitcamp.testproject.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

  @GetMapping("kakaoLogin")
  public ModelAndView kakaoLogin(String code, HttpSession session, HttpServletResponse res, ModelAndView mv) {

    // key=value 데이터 요청(카카오로)
    RestTemplate rt = new RestTemplate();

    // HttpHeader 오브젝트 생성
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    // HttpBody 오브젝트 생성
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", "3e127c745fa2928767e1e53af087b50f");
    params.add("redirect_uri", "http://localhost:8888/app/auth/kakaoLogin");
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
      System.out.println("oauthToken" + oauthToken);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    // 카카오 유저 정보 가져오기
    KakaoProfile profile = getProfile(oauthToken);

    // 카카오 id와 일치하는 member 가져오기
    Member member = memberService.getWithKakao(profile.id);

    if (member != null) { // 일치하는 member가 있다면 로그인
      session.setAttribute("loginMember", member);
      session.setAttribute("access_token", oauthToken.getAccess_token());
      mv = new ModelAndView("redirect:/");
      return mv;
    } else {
      // 카카오 이메일과 일치하는 member 찾기
      member = memberService.matcheKakaoEmail(profile.kakao_account.email);
      if (member != null) { // kakaoId는 없지만 카카오 email과 같은 email을 가진 member가 있다면 
        session.setAttribute("kakaoId", profile.id);
        session.setAttribute("member", member);
        mv = new ModelAndView("redirect:kakaoLink");
        return mv;
      } else {
        mv = new ModelAndView("redirect:form");
        return mv;
      }
    }

  }
  @GetMapping("kakaoLogout")
  public String kakaoLogout(HttpSession session) throws Exception {
    kakaoLogoutProcess((String)session.getAttribute("access_token"));
    session.invalidate();
    return "redirect:../";
  }

  // 로그아웃
  private void kakaoLogoutProcess(String access_Token) {
    String reqURL = "https://kapi.kakao.com/v1/user/unlink";
    try {
      URL url = new URL(reqURL);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Authorization", "Bearer " + access_Token);

      int responseCode = conn.getResponseCode();
      System.out.println("responseCode : " + responseCode);

      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

      String result = "";
      String line = "";

      while ((line = br.readLine()) != null) {
        result += line;
      }
      System.out.println(result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // 회원 탈퇴 시 카카오 아이디 연결끊기
  private void kakaoUnlinkProcess(String access_Token) {
    System.out.println("뭐야???" + access_Token);
    String reqURL = "https://kapi.kakao.com/v1/user/unlink";
    try {
      URL url = new URL(reqURL);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Authorization", "Bearer " + access_Token);

      int responseCode = conn.getResponseCode();
      System.out.println("responseCode : " + responseCode);

      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

      String result = "";
      String line = "";

      while ((line = br.readLine()) != null) {
        result += line;
      }
      System.out.println(result);
    } catch (IOException e) {
      e.printStackTrace();
    }
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
    try {
      profile = objectMapper.readValue(response.getBody(), KakaoProfile.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    System.out.println(profile);

    return profile;
  }

  @GetMapping("kakaoLink")
  public void kakaoLink() {
  }

}


