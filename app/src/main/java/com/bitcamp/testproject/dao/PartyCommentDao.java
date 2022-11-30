package com.bitcamp.testproject.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.bitcamp.testproject.vo.Comment;

@Mapper
public interface PartyCommentDao {

  //  List<Map<String, Object>> findAll(int boardNo);

  int insertComment(Comment comment);

  List<Comment> findByPartyNo(Map<String, Object> map);

  Comment findCommentByNo(int commentNo);

  int deleteComment(int partyNo);

  int deleteCommentAll(int partyNo);

  int updateComment(Comment comment);

  int findCommentsCount(int partyNo);

}
