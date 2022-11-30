package com.bitcamp.testproject.service;

import java.util.List;
import java.util.Map;
import com.bitcamp.testproject.vo.PartyMember;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
//
public interface PartyMemberService {

  int countMyPartyMember(int partyNo) throws Exception;

  List<PartyMember> findByMyPartyList(Map<String, Object> paramMap) throws Exception;

  int partyMemberCheck(int memberNo, int partyNo) throws Exception;

  boolean partyMemberOk(int status, int memberNo, int partyNo) throws Exception;

  boolean partyMemberNo(int status, int memberNo, int partyNo) throws Exception;

  boolean partyMemberSecession(int status, int memberNo, int partyNo) throws Exception;

  int countPartyMember(int partyNo) throws Exception;

}








