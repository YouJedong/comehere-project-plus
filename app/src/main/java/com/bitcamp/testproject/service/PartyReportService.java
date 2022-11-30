package com.bitcamp.testproject.service;

import java.util.Map;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
//
public interface PartyReportService {

  void addCommentReport(Map<String, Object> reportMap) throws Exception;

  void addMemberReport(Map<String, Object> reportMap) throws Exception;

}








