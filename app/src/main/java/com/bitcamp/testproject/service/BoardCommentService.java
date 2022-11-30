package com.bitcamp.testproject.service;

import java.util.List;
import java.util.Map;
import com.bitcamp.testproject.vo.Comment;

public interface BoardCommentService {

  //  List<Comment> list(int boardNo, int pageNo);

  int insert(Comment comment);

  Comment getComment(int commentNo);

  int delete(int boardNo);

  int update(Comment comment);

  int countCommentListTotal(int boardNo);

  List<Comment> getComments(Map<String, Object> map);

  int deleteAll(int no);

  int countTotalCommentOfMember(int no);

  List<Comment> getCommentsOfMember(Map<String, Object> paramMap);


}
