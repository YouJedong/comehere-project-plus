package com.bitcamp.testproject.service;

import java.util.List;
import java.util.Map;
import com.bitcamp.testproject.vo.Board;
import com.bitcamp.testproject.vo.Criteria;
import com.bitcamp.testproject.vo.Search;

// 비즈니스 로직을 수행하는 객체의 사용규칙(호출규칙)
//
public interface BoardService {

  List<Map<String, Object>> bestList() throws Exception;

  List<Map<String, Object>> clgList() throws Exception;

  int addAndReturnBoardNo(Board board) throws Exception;

  List<Map<String, Object>> list(Criteria cri) throws Exception;

  List<Map<String, Object>> listWithSearch(Criteria cri, Search search);

  int countTotalBoard(int no) throws Exception;

  int countTotalBoardWithSearch(int no, Search search);

  void increaseViews(int no);

  Board get(int no) throws Exception;

  boolean update(Board board) throws Exception;

  boolean delete(int no) throws Exception;

  //은지
  List<Board> findByMyPost(Map<String, Object> paramMap) throws Exception;

  int countMyPost(int memberNo) throws Exception;

}








