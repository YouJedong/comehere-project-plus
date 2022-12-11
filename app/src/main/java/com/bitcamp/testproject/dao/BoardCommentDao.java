package com.bitcamp.testproject.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.testproject.vo.Comment;

@Mapper
public interface BoardCommentDao {

  int insertComment(Comment comment);

  int updateComment(Comment comment);

  int deleteComment(int boardNo);

  List<Comment> findCommentsByBoardNo(Map<String, Object> map);

  int findTotalCommentOfBoard(int boardNo);

  List<Comment> findCommentsByMemberNo(Map<String, Object> paramMap);

  int findTotalCommentOfMember(int memberNo);

  int deleteAll(int boardNo);

}
