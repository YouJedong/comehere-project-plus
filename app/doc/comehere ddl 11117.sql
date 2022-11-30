-- 회원
DROP TABLE IF EXISTS member RESTRICT;

-- 게시글
DROP TABLE IF EXISTS board RESTRICT;

-- 운동 분류
DROP TABLE IF EXISTS sports RESTRICT;

-- 운동 지역
DROP TABLE IF EXISTS region RESTRICT;

-- 모임
DROP TABLE IF EXISTS party RESTRICT;

-- 모임후기
DROP TABLE IF EXISTS party_review RESTRICT;

-- 댓글
DROP TABLE IF EXISTS board_comment RESTRICT;

-- 공지사항
DROP TABLE IF EXISTS notice RESTRICT;

-- 1:1 문의
DROP TABLE IF EXISTS qna RESTRICT;

-- 관리자
DROP TABLE IF EXISTS admin RESTRICT;

-- 관심지역
DROP TABLE IF EXISTS favorite_region RESTRICT;

-- 관심 운동
DROP TABLE IF EXISTS favorite_sports RESTRICT;

-- 모임참여명단
DROP TABLE IF EXISTS party_members RESTRICT;

-- 모임후기 첨부파일
DROP TABLE IF EXISTS party_review_file RESTRICT;

-- 모임 댓글
DROP TABLE IF EXISTS party_comment RESTRICT;

-- 알림
DROP TABLE IF EXISTS alert RESTRICT;

-- 게시글신고
DROP TABLE IF EXISTS board_tatlle RESTRICT;

-- 스크랩
DROP TABLE IF EXISTS scrap RESTRICT;

-- 회원신고
DROP TABLE IF EXISTS member_tatlle RESTRICT;

-- 게시글카테고리
DROP TABLE IF EXISTS board_category RESTRICT;

-- 게시글 댓글신고
DROP TABLE IF EXISTS board_comment_tatlle RESTRICT;

-- 신고사유 카테고리
DROP TABLE IF EXISTS tatlle_reason RESTRICT;

-- 신청처리
DROP TABLE IF EXISTS party_status RESTRICT;

-- 모임 댓글신고
DROP TABLE IF EXISTS party_comment_tatlle RESTRICT;

-- 회원
CREATE TABLE member (
  mno      INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  name     VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  id       VARCHAR(20)  NOT NULL COMMENT '아이디', -- 아이디
  email    VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  pwd      VARCHAR(100) NOT NULL COMMENT '암호', -- 암호
  nick     VARCHAR(255) NOT NULL COMMENT '닉네임', -- 닉네임
  tel      VARCHAR(20)  NOT NULL COMMENT '휴대전화', -- 휴대전화
  bday     DATE         NOT NULL COMMENT '생년월일', -- 생년월일
  sex      BOOLEAN      NOT NULL COMMENT '성별', -- 성별
  filepath VARCHAR(255) NULL     COMMENT '파일경로', -- 파일경로
  cdt      DATETIME     NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  act      BOOLEAN      NOT NULL DEFAULT 1 COMMENT '활성화여부', -- 활성화여부
  black    BOOLEAN      NOT NULL DEFAULT 0 COMMENT '블랙여부' -- 블랙여부
)
COMMENT '회원';

-- 회원
ALTER TABLE member
  ADD CONSTRAINT PK_member -- 회원 기본키
    PRIMARY KEY (
      mno -- 회원번호
    );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_member
  ON member ( -- 회원
    email ASC -- 이메일
  );

-- 회원 유니크 인덱스2
CREATE UNIQUE INDEX UIX_member2
  ON member ( -- 회원
    nick ASC -- 닉네임
  );

-- 회원 유니크 인덱스3
CREATE UNIQUE INDEX UIX_member3
  ON member ( -- 회원
    id ASC -- 아이디
  );

-- 회원 인덱스
CREATE INDEX IX_member
  ON member( -- 회원
    name ASC -- 이름
  );

ALTER TABLE member
  MODIFY COLUMN mno INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 게시글
CREATE TABLE board (
  bno       INTEGER      NOT NULL COMMENT '게시글번호', -- 게시글번호
  cateno    INTEGER      NOT NULL COMMENT '게시글카테고리번호', -- 게시글카테고리번호
  mno       INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  title     VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  act       BOOLEAN      NOT NULL DEFAULT 1 COMMENT '활성화여부', -- 활성화여부
  cont      MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  cdt       DATETIME     NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  vw_cnt    INTEGER      NOT NULL DEFAULT 0 COMMENT '조회수', -- 조회수
  thumbnail VARCHAR(255) NULL     COMMENT '썸네일' -- 썸네일
)
COMMENT '게시글';

-- 게시글
ALTER TABLE board
  ADD CONSTRAINT PK_board -- 게시글 기본키
    PRIMARY KEY (
      bno -- 게시글번호
    );

ALTER TABLE board
  MODIFY COLUMN bno INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시글번호';

-- 운동 분류
CREATE TABLE sports (
  sno  INTEGER     NOT NULL COMMENT '운동일련번호', -- 운동일련번호
  name VARCHAR(50) NOT NULL COMMENT '운동 명' -- 운동 명
)
COMMENT '운동 분류';

-- 운동 분류
ALTER TABLE sports
  ADD CONSTRAINT PK_sports -- 운동 분류 기본키
    PRIMARY KEY (
      sno -- 운동일련번호
    );

ALTER TABLE sports
  MODIFY COLUMN sno INTEGER NOT NULL AUTO_INCREMENT COMMENT '운동일련번호';

-- 운동 지역
CREATE TABLE region (
  rno  INTEGER      NOT NULL COMMENT '지역일련번호', -- 지역일련번호
  sido VARCHAR(255) NOT NULL COMMENT '시/도', -- 시/도
  sigu VARCHAR(255) NOT NULL COMMENT '시/군/구' -- 시/군/구
)
COMMENT '운동 지역';

-- 운동 지역
ALTER TABLE region
  ADD CONSTRAINT PK_region -- 운동 지역 기본키
    PRIMARY KEY (
      rno -- 지역일련번호
    );

-- 운동 지역 인덱스
CREATE INDEX IX_region
  ON region( -- 운동 지역
    sigu ASC -- 시/군/구
  );

ALTER TABLE region
  MODIFY COLUMN rno INTEGER NOT NULL AUTO_INCREMENT COMMENT '지역일련번호';

-- 모임
CREATE TABLE party (
  pno        INTEGER      NOT NULL COMMENT '모임 일련번호', -- 모임 일련번호
  rno        INTEGER      NOT NULL COMMENT '지역일련번호', -- 지역일련번호
  sno        INTEGER      NOT NULL COMMENT '운동일련번호', -- 운동일련번호
  title      VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  act        BOOLEAN      NOT NULL DEFAULT 1 COMMENT '활성화여부', -- 활성화여부
  cont       MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  cdt        DATETIME     NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  headcnt    INTEGER      NOT NULL COMMENT '정원', -- 정원
  ptime      TIME         NOT NULL COMMENT '모임시간', -- 모임시간
  pdate      DATE         NOT NULL COMMENT '모임일', -- 모임일
  mapName    VARCHAR(255) NOT NULL COMMENT '장소명', -- 장소명
  mapAddress VARCHAR(255) NOT NULL COMMENT '장소주소', -- 장소주소
  lat        FLOAT(24)    NOT NULL COMMENT '위도', -- 위도
  lng        FLOAT(24)    NOT NULL COMMENT '경도', -- 경도
  thumbnail  VARCHAR(255) NULL     COMMENT '썸네일' -- 썸네일
)
COMMENT '모임';

-- 모임
ALTER TABLE party
  ADD CONSTRAINT PK_party -- 모임 기본키
    PRIMARY KEY (
      pno -- 모임 일련번호
    );

ALTER TABLE party
  MODIFY COLUMN pno INTEGER NOT NULL AUTO_INCREMENT COMMENT '모임 일련번호';

-- 모임후기
CREATE TABLE party_review (
  prno  INTEGER      NOT NULL COMMENT '후기 일련번호', -- 후기 일련번호
  mno   INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  pno   INTEGER      NOT NULL COMMENT '모임 일련번호', -- 모임 일련번호
  act   BOOLEAN      NOT NULL DEFAULT 1 COMMENT '활성화여부', -- 활성화여부
  title VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  cont  MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  star  DEC(3,2)     NOT NULL COMMENT '별점', -- 별점
  cdt   DATETIME     NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '모임후기';

-- 모임후기
ALTER TABLE party_review
  ADD CONSTRAINT PK_party_review -- 모임후기 기본키
    PRIMARY KEY (
      prno -- 후기 일련번호
    );

ALTER TABLE party_review
  MODIFY COLUMN prno INTEGER NOT NULL AUTO_INCREMENT COMMENT '후기 일련번호';

-- 댓글
CREATE TABLE board_comment (
  bcno INTEGER    NOT NULL COMMENT '댓글번호', -- 댓글번호
  bno  INTEGER    NOT NULL COMMENT '게시글번호', -- 게시글번호
  mno  INTEGER    NOT NULL COMMENT '회원번호', -- 회원번호
  cont MEDIUMTEXT NOT NULL COMMENT '내용', -- 내용
  act  BOOLEAN    NOT NULL DEFAULT 1 COMMENT '활성화여부', -- 활성화여부
  cdt  DATETIME   NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '댓글';

-- 댓글
ALTER TABLE board_comment
  ADD CONSTRAINT PK_board_comment -- 댓글 기본키
    PRIMARY KEY (
      bcno -- 댓글번호
    );

ALTER TABLE board_comment
  MODIFY COLUMN bcno INTEGER NOT NULL AUTO_INCREMENT COMMENT '댓글번호';

-- 공지사항
CREATE TABLE notice (
  nno       INTEGER      NOT NULL COMMENT '게시글번호', -- 게시글번호
  title     VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  cont      MEDIUMTEXT   NOT NULL COMMENT '내용', -- 내용
  cdt       DATETIME     NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  vw_cnt    INTEGER      NOT NULL DEFAULT 0 COMMENT '조회수', -- 조회수
  thumbnail VARCHAR(255) NULL     COMMENT '썸네일' -- 썸네일
)
COMMENT '공지사항';

-- 공지사항
ALTER TABLE notice
  ADD CONSTRAINT PK_notice -- 공지사항 기본키
    PRIMARY KEY (
      nno -- 게시글번호
    );

ALTER TABLE notice
  MODIFY COLUMN nno INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시글번호';

-- 1:1 문의
CREATE TABLE qna (
  qno   INTEGER      NOT NULL COMMENT '문의질문번호', -- 문의질문번호
  mno   INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  title VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  act   BOOLEAN      NOT NULL DEFAULT 0 COMMENT '답변여부', -- 답변여부
  cont  MEDIUMTEXT   NOT NULL COMMENT '질문', -- 질문
  cdt   DATETIME     NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  acont MEDIUMTEXT   NULL     COMMENT '답변', -- 답변
  adt   DATETIME     NULL     COMMENT '답변일' -- 답변일
)
COMMENT '1:1 문의';

-- 1:1 문의
ALTER TABLE qna
  ADD CONSTRAINT PK_qna -- 1:1 문의 기본키
    PRIMARY KEY (
      qno -- 문의질문번호
    );

ALTER TABLE qna
  MODIFY COLUMN qno INTEGER NOT NULL AUTO_INCREMENT COMMENT '문의질문번호';

-- 관리자
CREATE TABLE admin (
  adno  INTEGER      NOT NULL COMMENT '관리자번호', -- 관리자번호
  name  VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  id    VARCHAR(20)  NOT NULL COMMENT '아이디', -- 아이디
  email VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  pwd   VARCHAR(100) NOT NULL COMMENT '암호', -- 암호
  cdt   DATETIME     NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '관리자';

-- 관리자
ALTER TABLE admin
  ADD CONSTRAINT PK_admin -- 관리자 기본키
    PRIMARY KEY (
      adno -- 관리자번호
    );

-- 관리자 유니크 인덱스
CREATE UNIQUE INDEX UIX_admin
  ON admin ( -- 관리자
    email ASC -- 이메일
  );

-- 관리자 유니크 인덱스2
CREATE UNIQUE INDEX UIX_admin2
  ON admin ( -- 관리자
    id ASC -- 아이디
  );

-- 관리자 인덱스
CREATE INDEX IX_admin
  ON admin( -- 관리자
    name ASC -- 이름
  );

ALTER TABLE admin
  MODIFY COLUMN adno INTEGER NOT NULL AUTO_INCREMENT COMMENT '관리자번호';

-- 관심지역
CREATE TABLE favorite_region (
  rno INTEGER NOT NULL COMMENT '지역일련번호', -- 지역일련번호
  mno INTEGER NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '관심지역';

-- 관심지역
ALTER TABLE favorite_region
  ADD CONSTRAINT PK_favorite_region -- 관심지역 기본키
    PRIMARY KEY (
      rno, -- 지역일련번호
      mno  -- 회원번호
    );

-- 관심 운동
CREATE TABLE favorite_sports (
  sno INTEGER NOT NULL COMMENT '운동일련번호', -- 운동일련번호
  mno INTEGER NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '관심 운동';

-- 관심 운동
ALTER TABLE favorite_sports
  ADD CONSTRAINT PK_favorite_sports -- 관심 운동 기본키
    PRIMARY KEY (
      sno, -- 운동일련번호
      mno  -- 회원번호
    );

-- 모임참여명단
CREATE TABLE party_members (
  mno  INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  pno  INTEGER NOT NULL COMMENT '모임 일련번호', -- 모임 일련번호
  psno INTEGER NOT NULL COMMENT '신청처리번호', -- 신청처리번호
  auth BOOLEAN NOT NULL COMMENT '권한' -- 권한
)
COMMENT '모임참여명단';

-- 모임참여명단
ALTER TABLE party_members
  ADD CONSTRAINT PK_party_members -- 모임참여명단 기본키
    PRIMARY KEY (
      mno, -- 회원번호
      pno  -- 모임 일련번호
    );

-- 모임후기 첨부파일
CREATE TABLE party_review_file (
  prfno    INTEGER      NOT NULL COMMENT '모임후기첨부번호', -- 모임후기첨부번호
  filepath VARCHAR(255) NOT NULL COMMENT '파일경로', -- 파일경로
  prno     INTEGER      NOT NULL COMMENT '후기 일련번호' -- 후기 일련번호
)
COMMENT '모임후기 첨부파일';

-- 모임후기 첨부파일
ALTER TABLE party_review_file
  ADD CONSTRAINT PK_party_review_file -- 모임후기 첨부파일 기본키
    PRIMARY KEY (
      prfno -- 모임후기첨부번호
    );

ALTER TABLE party_review_file
  MODIFY COLUMN prfno INTEGER NOT NULL AUTO_INCREMENT COMMENT '모임후기첨부번호';

-- 모임 댓글
CREATE TABLE party_comment (
  pcno INTEGER    NOT NULL COMMENT '모임댓글번호', -- 모임댓글번호
  mno  INTEGER    NOT NULL COMMENT '회원번호', -- 회원번호
  pno  INTEGER    NOT NULL COMMENT '모임 일련번호', -- 모임 일련번호
  cont MEDIUMTEXT NOT NULL COMMENT '내용', -- 내용
  act  BOOLEAN    NOT NULL DEFAULT 1 COMMENT '활성화여부', -- 활성화여부
  cdt  DATETIME   NOT NULL DEFAULT now() COMMENT '등록일' -- 등록일
)
COMMENT '모임 댓글';

-- 모임 댓글
ALTER TABLE party_comment
  ADD CONSTRAINT PK_party_comment -- 모임 댓글 기본키
    PRIMARY KEY (
      pcno -- 모임댓글번호
    );

ALTER TABLE party_comment
  MODIFY COLUMN pcno INTEGER NOT NULL AUTO_INCREMENT COMMENT '모임댓글번호';

-- 알림
CREATE TABLE alert (
  alno    INTEGER    NOT NULL COMMENT '알림번호', -- 알림번호
  mno     INTEGER    NOT NULL COMMENT '회원번호', -- 회원번호
  cont    MEDIUMTEXT NOT NULL COMMENT '내용', -- 내용
  aldt    DATETIME   NOT NULL DEFAULT now() COMMENT '알림시간', -- 알림시간
  alcheck BOOLEAN    NOT NULL DEFAULT 0 COMMENT '읽음표시' -- 읽음표시
)
COMMENT '알림';

-- 알림
ALTER TABLE alert
  ADD CONSTRAINT PK_alert -- 알림 기본키
    PRIMARY KEY (
      alno -- 알림번호
    );

ALTER TABLE alert
  MODIFY COLUMN alno INTEGER NOT NULL AUTO_INCREMENT COMMENT '알림번호';

-- 게시글신고
CREATE TABLE board_tatlle (
  btno   INTEGER    NOT NULL COMMENT '신고번호', -- 신고번호
  mno    INTEGER    NOT NULL COMMENT '신고자', -- 신고자
  decbno INTEGER    NOT NULL COMMENT '신고대상글', -- 신고대상글
  trno   INTEGER    NOT NULL COMMENT '신고사유번호', -- 신고사유번호
  tdt    DATETIME   NOT NULL DEFAULT now() COMMENT '신고일', -- 신고일
  cont   MEDIUMTEXT NULL     COMMENT '처리내용', -- 처리내용
  comdt  DATETIME   NULL     COMMENT '처리일' -- 처리일
)
COMMENT '게시글신고';

-- 게시글신고
ALTER TABLE board_tatlle
  ADD CONSTRAINT PK_board_tatlle -- 게시글신고 기본키
    PRIMARY KEY (
      btno -- 신고번호
    );

ALTER TABLE board_tatlle
  MODIFY COLUMN btno INTEGER NOT NULL AUTO_INCREMENT COMMENT '신고번호';

-- 스크랩
CREATE TABLE scrap (
  mno INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  bno INTEGER NOT NULL COMMENT '게시글번호' -- 게시글번호
)
COMMENT '스크랩';

-- 스크랩
ALTER TABLE scrap
  ADD CONSTRAINT PK_scrap -- 스크랩 기본키
    PRIMARY KEY (
      mno, -- 회원번호
      bno  -- 게시글번호
    );

-- 회원신고
CREATE TABLE member_tatlle (
  mtno  INTEGER    NOT NULL COMMENT '신고번호', -- 신고번호
  mnoee INTEGER    NOT NULL COMMENT '신고자회원번호', -- 신고자회원번호
  mnoer INTEGER    NOT NULL COMMENT '피신고자회원번호', -- 피신고자회원번호
  trno  INTEGER    NOT NULL COMMENT '신고사유번호', -- 신고사유번호
  tdt   DATETIME   NOT NULL DEFAULT now() COMMENT '신고일', -- 신고일
  cont  MEDIUMTEXT NULL     COMMENT '처리내용', -- 처리내용
  comdt DATETIME   NULL     COMMENT '처리일' -- 처리일
)
COMMENT '회원신고';

-- 회원신고
ALTER TABLE member_tatlle
  ADD CONSTRAINT PK_member_tatlle -- 회원신고 기본키
    PRIMARY KEY (
      mtno -- 신고번호
    );

ALTER TABLE member_tatlle
  MODIFY COLUMN mtno INTEGER NOT NULL AUTO_INCREMENT COMMENT '신고번호';

-- 게시글카테고리
CREATE TABLE board_category (
  cateno INTEGER      NOT NULL COMMENT '게시글카테고리번호', -- 게시글카테고리번호
  name   VARCHAR(255) NOT NULL COMMENT '게시글카테고리명' -- 게시글카테고리명
)
COMMENT '게시글카테고리';

-- 게시글카테고리
ALTER TABLE board_category
  ADD CONSTRAINT PK_board_category -- 게시글카테고리 기본키
    PRIMARY KEY (
      cateno -- 게시글카테고리번호
    );

ALTER TABLE board_category
  MODIFY COLUMN cateno INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시글카테고리번호';

-- 게시글 댓글신고
CREATE TABLE board_comment_tatlle (
  bctno INTEGER    NOT NULL COMMENT '신고번호', -- 신고번호
  mno   INTEGER    NOT NULL COMMENT '신고자', -- 신고자
  bcno  INTEGER    NOT NULL COMMENT '댓글번호', -- 댓글번호
  trno  INTEGER    NOT NULL COMMENT '신고사유번호', -- 신고사유번호
  tdt   DATETIME   NOT NULL DEFAULT now() COMMENT '신고일', -- 신고일
  cont  MEDIUMTEXT NULL     COMMENT '처리내용', -- 처리내용
  comdt DATETIME   NULL     COMMENT '처리일' -- 처리일
)
COMMENT '게시글 댓글신고';

-- 게시글 댓글신고
ALTER TABLE board_comment_tatlle
  ADD CONSTRAINT PK_board_comment_tatlle -- 게시글 댓글신고 기본키
    PRIMARY KEY (
      bctno -- 신고번호
    );

ALTER TABLE board_comment_tatlle
  MODIFY COLUMN bctno INTEGER NOT NULL AUTO_INCREMENT COMMENT '신고번호';

-- 신고사유 카테고리
CREATE TABLE tatlle_reason (
  trno   INTEGER      NOT NULL COMMENT '신고사유번호', -- 신고사유번호
  reason VARCHAR(255) NOT NULL COMMENT '신고사유' -- 신고사유
)
COMMENT '신고사유 카테고리';

-- 신고사유 카테고리
ALTER TABLE tatlle_reason
  ADD CONSTRAINT PK_tatlle_reason -- 신고사유 카테고리 기본키
    PRIMARY KEY (
      trno -- 신고사유번호
    );

-- 신청처리
CREATE TABLE party_status (
  psno   INTEGER      NOT NULL COMMENT '신청처리번호', -- 신청처리번호
  status VARCHAR(255) NOT NULL COMMENT '처리상태' -- 처리상태
)
COMMENT '신청처리';

-- 신청처리
ALTER TABLE party_status
  ADD CONSTRAINT PK_party_status -- 신청처리 기본키
    PRIMARY KEY (
      psno -- 신청처리번호
    );

-- 모임 댓글신고
CREATE TABLE party_comment_tatlle (
  pctno INTEGER    NOT NULL COMMENT '신고번호', -- 신고번호
  mno   INTEGER    NOT NULL COMMENT '신고자', -- 신고자
  pcno  INTEGER    NOT NULL COMMENT '모임댓글번호', -- 모임댓글번호
  trno  INTEGER    NOT NULL COMMENT '신고사유번호', -- 신고사유번호
  tdt   DATETIME   NOT NULL DEFAULT now() COMMENT '신고일', -- 신고일
  cont  MEDIUMTEXT NULL     COMMENT '처리내용', -- 처리내용
  comdt DATETIME   NULL     COMMENT '처리일' -- 처리일
)
COMMENT '모임 댓글신고';

-- 모임 댓글신고
ALTER TABLE party_comment_tatlle
  ADD CONSTRAINT PK_party_comment_tatlle -- 모임 댓글신고 기본키
    PRIMARY KEY (
      pctno -- 신고번호
    );

ALTER TABLE party_comment_tatlle
  MODIFY COLUMN pctno INTEGER NOT NULL AUTO_INCREMENT COMMENT '신고번호';

-- 게시글
ALTER TABLE board
  ADD CONSTRAINT FK_member_TO_board -- 회원 -> 게시글
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 게시글
ALTER TABLE board
  ADD CONSTRAINT FK_board_category_TO_board -- 게시글카테고리 -> 게시글
    FOREIGN KEY (
      cateno -- 게시글카테고리번호
    )
    REFERENCES board_category ( -- 게시글카테고리
      cateno -- 게시글카테고리번호
    );

-- 모임
ALTER TABLE party
  ADD CONSTRAINT FK_region_TO_party -- 운동 지역 -> 모임
    FOREIGN KEY (
      rno -- 지역일련번호
    )
    REFERENCES region ( -- 운동 지역
      rno -- 지역일련번호
    );

-- 모임
ALTER TABLE party
  ADD CONSTRAINT FK_sports_TO_party -- 운동 분류 -> 모임
    FOREIGN KEY (
      sno -- 운동일련번호
    )
    REFERENCES sports ( -- 운동 분류
      sno -- 운동일련번호
    );

-- 모임후기
ALTER TABLE party_review
  ADD CONSTRAINT FK_party_members_TO_party_review -- 모임참여명단 -> 모임후기
    FOREIGN KEY (
      mno, -- 회원번호
      pno  -- 모임 일련번호
    )
    REFERENCES party_members ( -- 모임참여명단
      mno, -- 회원번호
      pno  -- 모임 일련번호
    );

-- 댓글
ALTER TABLE board_comment
  ADD CONSTRAINT FK_board_TO_board_comment -- 게시글 -> 댓글
    FOREIGN KEY (
      bno -- 게시글번호
    )
    REFERENCES board ( -- 게시글
      bno -- 게시글번호
    );

-- 댓글
ALTER TABLE board_comment
  ADD CONSTRAINT FK_member_TO_board_comment -- 회원 -> 댓글
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 1:1 문의
ALTER TABLE qna
  ADD CONSTRAINT FK_member_TO_qna -- 회원 -> 1:1 문의
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 관심지역
ALTER TABLE favorite_region
  ADD CONSTRAINT 
    FOREIGN KEY (
      rno -- 지역일련번호
    )
    REFERENCES region ( -- 운동 지역
      rno -- 지역일련번호
    );

-- 관심지역
ALTER TABLE favorite_region
  ADD CONSTRAINT FK_member_TO_favorite_region -- 회원 -> 관심지역
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 관심 운동
ALTER TABLE favorite_sports
  ADD CONSTRAINT 
    FOREIGN KEY (
      sno -- 운동일련번호
    )
    REFERENCES sports ( -- 운동 분류
      sno -- 운동일련번호
    );

-- 관심 운동
ALTER TABLE favorite_sports
  ADD CONSTRAINT FK_member_TO_favorite_sports -- 회원 -> 관심 운동
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 모임참여명단
ALTER TABLE party_members
  ADD CONSTRAINT FK_member_TO_party_members -- 회원 -> 모임참여명단
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 모임참여명단
ALTER TABLE party_members
  ADD CONSTRAINT FK_party_TO_party_members -- 모임 -> 모임참여명단
    FOREIGN KEY (
      pno -- 모임 일련번호
    )
    REFERENCES party ( -- 모임
      pno -- 모임 일련번호
    );

-- 모임참여명단
ALTER TABLE party_members
  ADD CONSTRAINT FK_party_status_TO_party_members -- 신청처리 -> 모임참여명단
    FOREIGN KEY (
      psno -- 신청처리번호
    )
    REFERENCES party_status ( -- 신청처리
      psno -- 신청처리번호
    );

-- 모임후기 첨부파일
ALTER TABLE party_review_file
  ADD CONSTRAINT FK_party_review_TO_party_review_file -- 모임후기 -> 모임후기 첨부파일
    FOREIGN KEY (
      prno -- 후기 일련번호
    )
    REFERENCES party_review ( -- 모임후기
      prno -- 후기 일련번호
    );

-- 모임 댓글
ALTER TABLE party_comment
  ADD CONSTRAINT FK_party_TO_party_comment -- 모임 -> 모임 댓글
    FOREIGN KEY (
      pno -- 모임 일련번호
    )
    REFERENCES party ( -- 모임
      pno -- 모임 일련번호
    );

-- 모임 댓글
ALTER TABLE party_comment
  ADD CONSTRAINT FK_member_TO_party_comment -- 회원 -> 모임 댓글
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 알림
ALTER TABLE alert
  ADD CONSTRAINT FK_member_TO_alert -- 회원 -> 알림
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 게시글신고
ALTER TABLE board_tatlle
  ADD CONSTRAINT FK_member_TO_board_tatlle -- 회원 -> 게시글신고
    FOREIGN KEY (
      mno -- 신고자
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 게시글신고
ALTER TABLE board_tatlle
  ADD CONSTRAINT FK_board_TO_board_tatlle -- 게시글 -> 게시글신고
    FOREIGN KEY (
      decbno -- 신고대상글
    )
    REFERENCES board ( -- 게시글
      bno -- 게시글번호
    );

-- 게시글신고
ALTER TABLE board_tatlle
  ADD CONSTRAINT FK_tatlle_reason_TO_board_tatlle -- 신고사유 카테고리 -> 게시글신고
    FOREIGN KEY (
      trno -- 신고사유번호
    )
    REFERENCES tatlle_reason ( -- 신고사유 카테고리
      trno -- 신고사유번호
    );

-- 스크랩
ALTER TABLE scrap
  ADD CONSTRAINT FK_member_TO_scrap -- 회원 -> 스크랩
    FOREIGN KEY (
      mno -- 회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 스크랩
ALTER TABLE scrap
  ADD CONSTRAINT FK_board_TO_scrap -- 게시글 -> 스크랩
    FOREIGN KEY (
      bno -- 게시글번호
    )
    REFERENCES board ( -- 게시글
      bno -- 게시글번호
    );

-- 회원신고
ALTER TABLE member_tatlle
  ADD CONSTRAINT FK_member_TO_member_tatlle -- 회원 -> 회원신고
    FOREIGN KEY (
      mnoee -- 신고자회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 회원신고
ALTER TABLE member_tatlle
  ADD CONSTRAINT FK_member_TO_member_tatlle2 -- 회원 -> 회원신고2
    FOREIGN KEY (
      mnoer -- 피신고자회원번호
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 회원신고
ALTER TABLE member_tatlle
  ADD CONSTRAINT FK_tatlle_reason_TO_member_tatlle -- 신고사유 카테고리 -> 회원신고
    FOREIGN KEY (
      trno -- 신고사유번호
    )
    REFERENCES tatlle_reason ( -- 신고사유 카테고리
      trno -- 신고사유번호
    );

-- 게시글 댓글신고
ALTER TABLE board_comment_tatlle
  ADD CONSTRAINT FK_member_TO_board_comment_tatlle -- 회원 -> 게시글 댓글신고
    FOREIGN KEY (
      mno -- 신고자
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );

-- 게시글 댓글신고
ALTER TABLE board_comment_tatlle
  ADD CONSTRAINT FK_board_comment_TO_board_comment_tatlle -- 댓글 -> 게시글 댓글신고
    FOREIGN KEY (
      bcno -- 댓글번호
    )
    REFERENCES board_comment ( -- 댓글
      bcno -- 댓글번호
    );

-- 게시글 댓글신고
ALTER TABLE board_comment_tatlle
  ADD CONSTRAINT FK_tatlle_reason_TO_board_comment_tatlle -- 신고사유 카테고리 -> 게시글 댓글신고
    FOREIGN KEY (
      trno -- 신고사유번호
    )
    REFERENCES tatlle_reason ( -- 신고사유 카테고리
      trno -- 신고사유번호
    );

-- 모임 댓글신고
ALTER TABLE party_comment_tatlle
  ADD CONSTRAINT FK_party_comment_TO_party_comment_tatlle -- 모임 댓글 -> 모임 댓글신고
    FOREIGN KEY (
      pcno -- 모임댓글번호
    )
    REFERENCES party_comment ( -- 모임 댓글
      pcno -- 모임댓글번호
    );

-- 모임 댓글신고
ALTER TABLE party_comment_tatlle
  ADD CONSTRAINT FK_tatlle_reason_TO_party_comment_tatlle -- 신고사유 카테고리 -> 모임 댓글신고
    FOREIGN KEY (
      trno -- 신고사유번호
    )
    REFERENCES tatlle_reason ( -- 신고사유 카테고리
      trno -- 신고사유번호
    );

-- 모임 댓글신고
ALTER TABLE party_comment_tatlle
  ADD CONSTRAINT FK_member_TO_party_comment_tatlle -- 회원 -> 모임 댓글신고
    FOREIGN KEY (
      mno -- 신고자
    )
    REFERENCES member ( -- 회원
      mno -- 회원번호
    );