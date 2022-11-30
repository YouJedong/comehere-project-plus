package com.bitcamp.testproject.service;

import java.util.List;
import java.util.Map;
import com.bitcamp.testproject.vo.Notice;

public interface NoticeService {

  public List<Notice> findAll(Map<String, Object> paramMap);

  public int countNotice();

  public int add(Notice notice);

  public Notice get(int no);

  public void increaseViews(int no);

  public boolean update(Notice notice);

  public boolean delete(int no);



}
