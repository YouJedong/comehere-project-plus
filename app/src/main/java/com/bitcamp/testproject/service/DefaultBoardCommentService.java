package com.bitcamp.testproject.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.testproject.dao.BoardCommentDao;
import com.bitcamp.testproject.vo.Comment;

@Service
public class DefaultBoardCommentService implements BoardCommentService {

  @Autowired 
  BoardCommentDao boardCommentDao;

  //  @Override
  //  public List<Comment> list(int boardNo, int pageNo) {
  //    return boardCommentDao.findByBoardNo(boardNo, pageNo);
  //  }

  @Override
  public int insert(Comment comment) {
    return boardCommentDao.insertComment(comment);
  }

  @Override
  public List<Comment> getComments(Map<String, Object> map) {
    return boardCommentDao.findByBoardNo(map);
  }
  @Override
  public List<Comment> getCommentsOfMember(Map<String, Object> paramMap) {
    return boardCommentDao.findCommentsByMemberNo(paramMap);
  }

  @Override
  public Comment getComment(int commentNo) {
    return boardCommentDao.findCommentByNo(commentNo);
  }

  @Override
  public int delete(int boardNo) {
    return boardCommentDao.deleteComment(boardNo);
  }

  @Override
  public int update(Comment comment) {
    return boardCommentDao.updateComment(comment);
  }

  @Override
  public int countCommentListTotal(int boardNo) {
    return boardCommentDao.findCommentsCount(boardNo);
  }

  @Override
  public int deleteAll(int boardNo) {
    return boardCommentDao.deleteAll(boardNo);
  }

  @Override
  public int countTotalCommentOfMember(int MemberNo) {
    return boardCommentDao.findTotalCommentOfMember(MemberNo);
  }
}








