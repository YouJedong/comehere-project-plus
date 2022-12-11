package com.bitcamp.testproject.service;

import java.util.List;
import java.util.Map;
import com.bitcamp.testproject.vo.Comment;

public interface BoardCommentService {

  int insert(Comment comment);

  List<Comment> getCommentsOfBoard(Map<String, Object> map);

  int countTotalCommentOfBoard(int boardNo);

  int update(Comment comment);

  int delete(int commentNo);

  List<Comment> getCommentsOfMember(Map<String, Object> paramMap);

  int countTotalCommentOfMember(int memberNo);

}
