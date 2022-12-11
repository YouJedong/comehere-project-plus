package com.bitcamp.testproject.service;

import java.util.List;
import java.util.Map;
import com.bitcamp.testproject.vo.Comment;

public interface BoardCommentService {

  int insert(Comment comment);

  List<Comment> getComments(Map<String, Object> map);

  int countTotalComment(int boardNo);


  Comment getComment(int commentNo);

  int delete(int boardNo);

  int update(Comment comment);



  int deleteAll(int no);

  int countTotalCommentOfMember(int no);

  List<Comment> getCommentsOfMember(Map<String, Object> paramMap);


}
