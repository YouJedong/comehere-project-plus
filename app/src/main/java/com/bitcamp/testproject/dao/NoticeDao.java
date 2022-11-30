package com.bitcamp.testproject.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.testproject.vo.Notice;

@Mapper
public interface NoticeDao {

  int insert(Notice notice);

  List<Notice> findAll(Map<String, Object> paramMap);

  int update();

  int delete();

  int countNotice();

  Notice get(int no);

  void increaseViews(int no);

  int update(Notice notice);

  String getThumbnailByNoticeNo(int no);

  int delete(int no);


}
