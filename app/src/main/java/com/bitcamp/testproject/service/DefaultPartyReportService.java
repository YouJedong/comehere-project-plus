package com.bitcamp.testproject.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.testproject.dao.PartyCommentReportDao;
import com.bitcamp.testproject.dao.MemberReportDao;

@Service
public class DefaultPartyReportService implements PartyReportService {

  @Autowired 
  MemberReportDao memberReportDao;
  @Autowired 
  PartyCommentReportDao commentReportDao;

  @Override
  public void addMemberReport(Map<String, Object> reportMap) throws Exception {
    if (memberReportDao.insertReport(reportMap) == 0) {
      throw new Exception("신고 처리가 되지 않았습니다.");
    }
  }

  @Override
  public void addCommentReport(Map<String, Object> reportMap) throws Exception {
    if (commentReportDao.insertReport(reportMap) == 0) {
      throw new Exception("신고 처리가 되지 않았습니다.");
    }
  }


}








