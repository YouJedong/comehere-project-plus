package com.bitcamp.testproject.service;

import com.bitcamp.testproject.vo.Member;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
//
public interface AdminService {

  //  void add(Member member) throws Exception;
  //
  //  boolean update(Member member) throws Exception;
  //
  //  Member get(int no) throws Exception;
  //
  //  Member get(String id, String password) throws Exception;
  //
  //  Member getByPassword(String id, String email, String SecCode) throws Exception;
  //
  //  Member getId(String name, String email) throws Exception;
  //
  //  boolean delete(int no) throws Exception;
  //
  //  List<Member> list() throws Exception;
  //
  //  boolean updatePW(String password, String email, String id) throws Exception;

  Member getAdmin(Member idPwd);

}








