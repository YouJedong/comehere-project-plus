package com.bitcamp.testproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.testproject.dao.BoardCommentDao;
import com.bitcamp.testproject.dao.BoardDao;
import com.bitcamp.testproject.dao.BoardReportDao;
import com.bitcamp.testproject.dao.ScrapDao;
import com.bitcamp.testproject.vo.Board;
import com.bitcamp.testproject.vo.Criteria;
import com.bitcamp.testproject.vo.Search;

@Service
public class DefaultBoardService implements BoardService {

  @Autowired 
  BoardDao boardDao;
  @Autowired
  BoardCommentDao boardCommentDao;
  @Autowired
  ScrapDao scrapDao;
  @Autowired
  BoardReportDao boardReportDao;

  @Override
  public List<Map<String, Object>> bestList() throws Exception {
    return boardDao.findBestList();
  }

  @Override
  public List<Map<String, Object>> clgList() throws Exception {
    return boardDao.findClgList();
  }

  @Override
  public int addAndReturnBoardNo(Board board) throws Exception {
    if (boardDao.insert(board) == 0) {
      throw new Exception("게시글 등록 실패!");
    } else {
      return boardDao.findRecentBoardNo(board.getWriter().getNo());
    }
  }

  @Override
  public List<Map<String, Object>> list(Criteria cri) throws Exception {
    return boardDao.findList(cri);
  }

  @Override
  public List<Map<String, Object>> listWithSearch(Criteria cri, Search search) {
    Map<String, Object> objToSearch = new HashMap<>(); 
    objToSearch.put("search", search);
    objToSearch.put("cri", cri);
    return boardDao.findListByKeyword(objToSearch);
  }

  @Override
  public int countTotalBoard(int no) throws Exception {
    return boardDao.findListTotalCount(no);
  }

  @Override
  public int countTotalBoardWithSearch(int no, Search search) {
    // 값들을 Map에 담아서 보내기
    Map<String, Object> countObj = new HashMap<>(); 
    countObj.put("search", search);
    countObj.put("cateno", no);

    return boardDao.findListTotalCountWithSearch(countObj);
  }

  @Override
  public void increaseViews(int no) {
    boardDao.increaseViews(no);
  }

  @Override
  public Board get(int no) throws Exception {
    return boardDao.findByBoardNo(no); 
  }

  @Transactional
  @Override
  public boolean update(Board board) throws Exception {

    // 챌린지 게시글에서 썸네일을 변경하지 않았을 때 원래 파일이름을 넣어준다.
    if (board.getThumbnail() == null && board.getCateno() == 3) {
      String originThumb = boardDao.findByBoardNo(board.getNo()).getThumbnail();
      board.setThumbnail(originThumb);
    }

    if (boardDao.update(board) == 0) {
      return false;
    }
    return true;
  }

  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
    // 게시글에 댓글 삭제하기 
    boardCommentDao.deleteAll(no);
    // 스크랩 삭제
    scrapDao.deleteAll(no);
    // 받은 신고 삭제
    boardReportDao.deleteReportOfBoard(no);

    return boardDao.delete(no) > 0;
  }

  // 은지
  @Override
  public List<Board> findByMyPost(Map<String, Object> paramMap) throws Exception {
    return boardDao.findByMyPost(paramMap);
  }

  @Override
  public int countMyPost(int memberNo) throws Exception {
    return boardDao.countMyPost(memberNo);
  }

}








