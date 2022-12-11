package com.bitcamp.testproject.dao;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardCommentReportDao {

  int insertReport(Map<String, Object> reportMap);

  int deleteReportOfComment(int commentNo);

}
