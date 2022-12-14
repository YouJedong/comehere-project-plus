
# **๐์ฌ๊ธฐ๋ชจ์ฌ - ํ ํ๋ก์ ํธ**

<img width="700" alt="image" src="https://user-images.githubusercontent.com/108327853/209047716-23f7abfa-2c41-4f62-83b0-6e51d777009f.png">
<p>

### **์ ์ ๋์ ๊ตฌํ ๊ธฐ๋ฅ ๋ฐ ์์ค**
1. **์ปค๋ฎค๋ํฐ ๊ธฐ๋ฅ ์ ์ฒด ๊ตฌํ** (๊ฒ์๊ธ, ๋๊ธ, ์คํฌ๋ฉ, ๊ฒ์, ํ์ด์ง)
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
  
- resources/templates/board/ .html <br><br>

2. **์นด์นด์ค ๋ก๊ทธ์ธ API ๊ตฌํ**
- web/controller/ <br>
  AuthController.java (kakao ๋ถ๋ถ)

- vo/ <br>
  KakaoProfile.java, OAuthToken.java
  
- resources/templates/auth/ <br>
  kakaoLinkForm.html, join.html

**๋ฐํ์์**
https://youtu.be/VcFEVTZOpzg?t=1395

## **ํ๋ก์ ํธ ์๊ฐ**
**'์ฌ๊ธฐ๋ชจ์ฌ' ์ฌ์ดํธ๋?**

>์ด๋๋ชจ์์ ๋น ๋ฅด ๊ฐํธํ๊ฒ ๋งค์นญ์์ผ์ฃผ๋ ๋ชจ์์ฐพ๊ธฐ ๊ธฐ๋ฅ๊ณผ <br>
ํ์๋ค์ด ์๋ก ์ํตํ๊ณ  ์ ๋ณด๋ฅผ ๊ณต์ ํ  ์ ์๋ ์ปค๋ฎค๋ํฐ ๊ธฐ๋ฅ์ ๊ฐ์ง <br>
**์ขํฉ์ด๋์ฌ์ดํธ**

**ํํฉ ๋ฐ ๋ฌธ์ ์ **

>๋ํธํ ๊ฐ์ ์ ์ ๊ธฐ์ ์ธ ๋ชจ์ ์ฐธ์ฌ๋ฅผ ์๊ตฌ, ๋จ๊ธฐ ๋ชจ์์ ๋ถ์ฌ <br>

**ํด๊ฒฐ๋ฐฉ์ ๋ฐ ์ฌ์ฉ์ ์ด์ **

>๋น ๋ฅด๊ณ  ๊ฐํธํ ์ด๋ **๋ชจ์ ๊ฐ์ค** ๋ฐ **๋ชจ์ ์ฐธ์ฌ** <br>
๋ชจ์์ ๋ฆฌ๋ทฐ์ **ํ์ ** ๋์, ์ฌ์ฉ์๊ฐ ์ํ๋ ์ ๋ขฐ์ฑ์ ๊ฐ์ง ๊ฑด๊ฐํ ์ ๋ณด๋ฅผ ์ ๊ณต <br>
๋๊ฐ์ฑ ๊ด๊ณ ๊ฐ ํฌํจ๋ ์ ๋ณด๊ฐ ์๋ ์ฌ์ฉ์๊ฐ ์ค์  ์ด์ฉ ๋ฐ ์ฌ์ฉ ํ๊ธฐ๋ฅผ ๋ฐํ์ผ๋ก ํ **์ปค๋ฎค๋ํฐ** ์ ๊ณต

<br>

## **์ฌ์ฉ ๊ธฐ์  ๋ฐ ๋๊ตฌ**
- **BackEnd** : Spring Boot, Undertow, mybatis, MariaDB, Thymeleaf
- **FrontEnd** : HTML5, CSS3, Java Script, jQuery, AJAX, BootStrap SweetAlert
- **Programming Tool** : eclipse, Visual Studio Code, Gradle, Github, GitKraken, Sequel Pro

<br>

## **์ฃผ์๊ธฐ๋ฅ**

### **1. ๋ชจ์ ๊ฐ์ค ๋ฐ ์ฐธ๊ฐ**

<img width="600" alt="image" src="https://user-images.githubusercontent.com/108327853/209033155-37048394-0cd0-4d12-8a0b-bf7775661f2e.png">
 
- ๋ค์ํ **๊ฒ์์กฐ๊ฑด**(์๊ฐ, ์ด๋์ข๋ฅ, ์ฅ์)์ผ๋ก ์ํ๋ ๋ชจ์์ ์ ํํ์ฌ ์ฐธ๊ฐ

<br>
  
<img width="600" alt="image" src="https://user-images.githubusercontent.com/108327853/209035903-cf3607c6-c599-49c9-8cff-1a90bdc748fd.png">

- **์นด์นด์ค Map**์ ํ์ฉํ์ฌ ์ฅ์๋ฅผ ์ ํํ์ฌ ๋ชจ์์ ๊ฐ์ค

<br>
 
### **2. ์ปค๋ฎค๋ํฐ ๊ธฐ๋ฅ**
 
<img width="600" alt="image" src="https://user-images.githubusercontent.com/108327853/209039851-9ccab7ce-036c-44a5-8ccb-2f8ed2db1e31.png">
 
- ์ธ๊ธฐ๊ธ, ์ต๊ทผ ์ฑ๋ฆฐ์ง ์ธ์ฆ๊ธ, ๋ค์ํ ๊ฒ์ํ๋ค์ ํตํด ๊ธ์ ์์ฑํ๊ณ  ์กฐํ

<br>
 
<img width="600" alt="image" src="https://user-images.githubusercontent.com/108327853/209040193-375b54f5-8356-4dc9-8b95-8368d763715d.png">

- ์ํ๋ ๊ฒ์๊ธ์ **์คํฌ๋ฉ**ํ์ฌ ๋ง์ดํ์ด์ง๋ฅผ ํตํด ํ์ธ

<br>

### **3. ์ ๊ณ  ๊ธฐ๋ฅ**

<img width="600" alt="image" src="https://user-images.githubusercontent.com/108327853/209040762-3a00eae6-bfc8-48a0-97f5-caf0ebc5d5ad.png">

- ๋ถ๊ฑด์ ํ ๋ชจ์, ๊ฒ์๊ธ, ๋๊ธ์ **์ ๊ณ **ํ  ์ ์๋ ๊ธฐ๋ฅ 

<br>

### **4. ์นด์นด์ค ๋ก๊ทธ์ธ ๊ธฐ๋ฅ**

<img width="600" alt="image" src="https://user-images.githubusercontent.com/108327853/209041027-b1b3116f-4806-4e5e-a885-3bf28a878c33.png">

- ์นด์นด์ค ๋ก๊ทธ์ธ API๋ฅผ ํ์ฉํ์ฌ **๋ก๊ทธ์ธ, ํ์๊ฐ์, ์์ด๋ ์ฐ๋, ๋ก๊ทธ์์**์ ํ  ์ ์์

<br>
