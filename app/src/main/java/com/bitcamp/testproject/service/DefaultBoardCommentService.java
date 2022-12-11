package com.bitcamp.testproject.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.testproject.dao.BoardCommentDao;
import com.bitcamp.testproject.dao.BoardCommentReportDao;
import com.bitcamp.testproject.vo.Comment;

@Service
public class DefaultBoardCommentService implements BoardCommentService {

  @Autowired 
  BoardCommentDao boardCommentDao;
  @Autowired 
  BoardCommentReportDao boardCommentReportDao;

  @Override
  public int insert(Comment comment) {
    return boardCommentDao.insertComment(comment);
  }

  @Override
  public List<Comment> getCommentsOfBoard(Map<String, Object> map) {
    return boardCommentDao.findCommentsByBoardNo(map);
  }

  @Override
  public int countTotalCommentOfBoard(int boardNo) {
    return boardCommentDao.findTotalCommentOfBoard(boardNo);
  }

  @Override
  public int update(Comment comment) {
    return boardCommentDao.updateComment(comment);
  }

  @Override
  public int delete(int commentNo) {

    // 받은 신고 삭제
    boardCommentReportDao.deleteReportOfComment(commentNo);

    return boardCommentDao.deleteComment(commentNo);
  }

  @Override
  public List<Comment> getCommentsOfMember(Map<String, Object> paramMap) {
    return boardCommentDao.findCommentsByMemberNo(paramMap);
  }

  @Override
  public int countTotalCommentOfMember(int MemberNo) {
    return boardCommentDao.findTotalCommentOfMember(MemberNo);
  }
}








