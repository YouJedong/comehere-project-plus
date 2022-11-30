package com.bitcamp.testproject.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitcamp.testproject.dao.PartyCommentDao;
import com.bitcamp.testproject.vo.Comment;

@Service
public class DefaultPartyCommentService implements PartyCommentService {

  @Autowired 
  PartyCommentDao partyCommentDao;

  //  @Override
  //  public List<Comment> list(int boardNo, int pageNo) {
  //    return boardCommentDao.findByBoardNo(boardNo, pageNo);
  //  }

  @Override
  public int insert(Comment comment) {
    return partyCommentDao.insertComment(comment);
  }

  @Override
  public List<Comment> getComments(Map<String, Object> map) {
    return partyCommentDao.findByPartyNo(map);
  }

  @Override
  public Comment getComment(int commentNo) {
    return partyCommentDao.findCommentByNo(commentNo);
  }

  @Override
  public int delete(int partyNo) {
    return partyCommentDao.deleteComment(partyNo);
  }

  @Override
  public int deleteAll(int partyNo) {
    return partyCommentDao.deleteCommentAll(partyNo);
  }

  @Override
  public int update(Comment comment) {
    return partyCommentDao.updateComment(comment);
  }

  @Override
  public int countCommentListTotal(int partyNo) {
    return partyCommentDao.findCommentsCount(partyNo);
  }
}








