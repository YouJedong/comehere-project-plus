package com.bitcamp.testproject.service;

import java.util.List;
import java.util.Map;
import com.bitcamp.testproject.vo.Comment;

public interface PartyCommentService {

  //  List<Comment> list(int boardNo, int pageNo);

  int insert(Comment comment);

  Comment getComment(int commentNo);

  int delete(int partyNo);

  int deleteAll(int partyNo);

  int update(Comment comment);

  int countCommentListTotal(int partyNo);

  List<Comment> getComments(Map<String, Object> map);


}
