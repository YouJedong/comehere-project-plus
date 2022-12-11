package com.bitcamp.testproject.dao;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardReportDao {


  int insertReport(Map<String, Object> reportMap);

  int deleteReportOfBoard(int boardNo);

}














