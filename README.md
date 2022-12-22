
# **👟여기모여 - 팀 프로젝트**

<img width="700" alt="image" src="https://user-images.githubusercontent.com/108327853/209030791-63f8899c-7bda-46af-90ba-607a8a5281a4.png">
<p>

### **유제동의 구현 기능 및 소스**
1. **커뮤니티 기능 전체 구현** (게시글, 댓글, 스크랩, 검색, 페이징)
- web/controller/ <br>
  BoardController.java, BoardCommentController.java, ScrapController.java

- service/ <br>
  BoardService.java, BoardCommentService.java, ScrapService.java

- dao/ <br>
  BoardDao.java, BoardCommentDao.java, ScrapDao.java

- resources/com/bitcamp/testproject/dao/ <br>
  BoardDao.xml, BoardCommentDao.xml, ScrapDao.xml

- vo/ <br>
  Board.java, Comment.java, Criteria.java, PageMaker.java, Scrap.java, Search.java
  
- resources/templates/board/ .html <br>

2. **카카오 로그인 API 구현**
- web/controller/ <br>
  AuthController.java (kakao 부분)

- vo/ <br>
  KakaoProfile.java, OAuthToken.java
  
- resources/templates/auth/ <br>
  kakaoLinkForm.html, join.html

**발표영상**
https://youtu.be/VcFEVTZOpzg?t=1395

## **프로젝트 소개**
**'여기모여' 사이트란?**

>운동모임을 빠르 간편하게 매칭시켜주는 모임찾기 기능과 <br>
회원들이 서로 소통하고 정보를 공유할 수 있는 커뮤니티 기능을 가진 <br>
**종합운동사이트**

**현황 및 문제점**

>동호회 가입 시 정기적인 모임 참여를 요구, 단기 모임의 부재 <br>

**해결방안 및 사용자 이점**

>빠르고 간편한 운동 **모임 개설** 및 **모임 참여** <br>
모임에 리뷰와 **평점** 도입, 사용자가 원하는 신뢰성을 가진 건강한 정보를 제공 <br>
대가성 광고가 포함된 정보가 아닌 사용자가 실제 이용 밒 사용 후기를 바탕으로 한 **커뮤니티** 제공

<br>

## **사용 기술 및 도구**
- **BackEnd** : Spring Boot, Undertow, mybatis, MariaDB, Thymeleaf
- **FrontEnd** : HTML5, CSS3, Java Script, jQuery, AJAX, BootStrap SweetAlert
- **Programming Tool** : eclipse, Visual Studio Code, Gradle, Github, GitKraken, Sequel Pro

<br>

## **주요기능**

### **1. 모임 개설 및 참가**

<img width="600" alt="image" src="https://user-images.githubusercontent.com/108327853/209033155-37048394-0cd0-4d12-8a0b-bf7775661f2e.png">
 
- 다양한 **검색조건**(시간, 운동종류, 장소)으로 원하는 모임을 선택하여 참가

<br>
  
<img width="600" alt="image" src="https://user-images.githubusercontent.com/108327853/209035903-cf3607c6-c599-49c9-8cff-1a90bdc748fd.png">

- **카카오 Map**을 활용하여 장소를 선택하여 모임을 개설

<br>
 
### **2. 커뮤니티 기능**
 
<img width="600" alt="image" src="https://user-images.githubusercontent.com/108327853/209039851-9ccab7ce-036c-44a5-8ccb-2f8ed2db1e31.png">
 
- 인기글, 최근 챌린지 인증글, 다양한 게시판들을 통해 글을 작성하고 조회

<br>
 
<img width="600" alt="image" src="https://user-images.githubusercontent.com/108327853/209040193-375b54f5-8356-4dc9-8b95-8368d763715d.png">

- 원하는 게시글을 **스크랩**하여 마이페이지를 통해 확인

<br>

### **3. 신고 기능**

<img width="600" alt="image" src="https://user-images.githubusercontent.com/108327853/209040762-3a00eae6-bfc8-48a0-97f5-caf0ebc5d5ad.png">

- 불건전한 모임, 게시글, 댓글을 **신고**할 수 있는 기능 

<br>

### **4. 카카오 로그인 기능**

<img width="600" alt="image" src="https://user-images.githubusercontent.com/108327853/209041027-b1b3116f-4806-4e5e-a885-3bf28a878c33.png">

- 카카오 로그인 API를 활용하여 **로그인, 회원가입, 아이디 연동, 로그아웃**을 할 수 있음

<br>
