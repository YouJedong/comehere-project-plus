package com.bitcamp.testproject.dao;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardCommentReportDao {

  //  List<Map<String, Object>> findAll(int boardNo);

  int insertReport(Map<String, Object> reportMap);

}
