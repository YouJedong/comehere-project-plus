package com.bitcamp.testproject.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.testproject.dao.BoardCommentReportDao;
import com.bitcamp.testproject.dao.BoardReportDao;

@Service
public class DefaultBoardReportService implements BoardReportService {

  @Autowired 
  BoardReportDao boardReportDao;
  @Autowired 
  BoardCommentReportDao boardCommentReportDao;

  @Override
  public void addBoardReport(Map<String, Object> reportMap) throws Exception {
    if (boardReportDao.insertReport(reportMap) == 0) {
      throw new Exception("신고 처리가 되지 않았습니다.");
    }
  }

  @Override
  public void addCommentReport(Map<String, Object> reportMap) throws Exception {
    if (boardCommentReportDao.insertReport(reportMap) == 0) {
      throw new Exception("신고 처리가 되지 않았습니다.");
    }
  }
  //////////

}








