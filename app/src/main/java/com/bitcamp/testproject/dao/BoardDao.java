package com.bitcamp.testproject.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.testproject.vo.Board;
import com.bitcamp.testproject.vo.Criteria;

@Mapper
public interface BoardDao {

  List<Map<String, Object>> findBestList();

  List<Map<String, Object>> findClgList();

  int insert(Board board);

  int findRecentBoardNo(int boardNo);

  List<Map<String, Object>> findList(Criteria cri);

  List<Map<String, Object>> findListByKeyword(Map<String,Object> objToSearch);

  int findListTotalCount(int no);

  int findListTotalCountWithSearch(Map<String, Object> countObj);

  void increaseViews(int no);

  Board findByBoardNo(int no);

  int update(Board board);

  int delete(int no);

  List<Board> findBoardsOfScrap(Map<String, Object> paramMap);

  //은지
  List<Board> findByMyPost(Map<String, Object> paramMap);

  int countMyPost(int memberNo);



}














