package com.bitcamp.testproject.dao;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardReportDao {


  int insertReport(Map<String, Object> reportMap);


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


}














