package com.bitcamp.testproject.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.testproject.vo.Board;
import com.bitcamp.testproject.vo.BoardCategory;
import com.bitcamp.testproject.vo.Criteria;

@Mapper
public interface BoardDao {

  int insert(Board board);

  int insertFiles(Board board);

  Board findByNo(int no);

  int deleteFiles(int boardNo);

  int delete(int no);

  int update(Board board);

  int deleteFile(int fileNo);

  List<Map<String, Object>> findAll(Criteria cri);

  int findListTotalCount(int no);

  List<Map<String, Object>> findBestList();

  List<Map<String, Object>> findClgList();

  void increaseViews(int no);

  BoardCategory findCategoryByNo(int no);

  int daleteThumbnailByNo(int no);

  String getThumbnailByBoardNo(int no);

  List<Map<String, Object>> findByKeyword(Map<String,Object> searchObj);

  int findListTotalCountWithSearch(Map<String, Object> countObj);

  List<Board> findBoardsOfScrap(Map<String, Object> paramMap);

  //

  //  int insert(Board board);
  //
  //  Board findByNo(int no);
  //
  //  int update(Board board);
  //
  //  int delete(int no);
  //
  //  int deleteByMember(int memberNo);
  //
  //  List<Board> findAll();
  //
  //  int insertFiles(Board board);
  //
  //  BoardAttachedFile findFileByNo(int fileNo);
  //
  //  List<BoardAttachedFile> findFilesByBoard(int boardNo);
  //
  //  int deleteFile(int fileNo);
  //
  //  int deleteFiles(int boardNo);
  //
  //  int deleteFilesByMemberBoards(int memberNo);

  //은지
  List<Board> findByMyPost(Map<String, Object> paramMap);

  int countMyPost(int memberNo);


}














