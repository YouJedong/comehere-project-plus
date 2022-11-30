package com.bitcamp.testproject.service;

import java.util.List;
import com.bitcamp.testproject.vo.Member;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
//
public interface MemberService {

  void add(Member member) throws Exception;

  boolean update(Member member) throws Exception;

  Member get(int no) throws Exception;

  Member get(String id, String password) throws Exception;

  Member getByPassword(String id, String email, String SecCode) throws Exception;

  Member getId(String name, String email) throws Exception;

  boolean delete(int no) throws Exception;

  List<Member> list() throws Exception;

  int idCheck(String id) throws Exception;

  int verificationPw(String password, int no) throws Exception;

  int nickCheck(String nickname) throws Exception;

  int emailCheck(String email) throws Exception;

  boolean updatePW(String password, String email, String id) throws Exception;

  Member idEmailCheck(String id, String email) throws Exception;

  Member idPasswordCheck(String id, String password) throws Exception;

  Member findIdCheck(String name, String email) throws Exception;
}








