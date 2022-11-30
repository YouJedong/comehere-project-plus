package com.bitcamp.testproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.testproject.dao.BoardCommentDao;
import com.bitcamp.testproject.dao.BoardDao;
import com.bitcamp.testproject.dao.ScrapDao;
import com.bitcamp.testproject.vo.Board;
import com.bitcamp.testproject.vo.BoardCategory;
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
  //
  //  @Transactional
  //  @Override
  //  public void add(Board board) throws Exception {
  //    // 1) 게시글 등록
  //    if (boardDao.insert(board) == 0) {
  //      throw new Exception("게시글 등록 실패!");
  //    }
  //
  //    // 2) 첨부파일 등록
  //    if (board.getAttachedFiles().size() > 0) {
  //      boardDao.insertFiles(board);
  //    }
  //  }
  //
  //  @Transactional
  //  @Override
  //  public boolean update(Board board) throws Exception {
  //    // 1) 게시글 변경
  //    if (boardDao.update(board) == 0) {
  //      return false;
  //    }
  //
  //    // 2) 첨부파일 추가
  //    if (board.getAttachedFiles().size() > 0) {
  //      boardDao.insertFiles(board);
  //    }
  //
  //    return true;
  //  }
  //
  //  @Override
  //  public Board get(int no) throws Exception {
  //    return boardDao.findByNo(no); // 첨부파일 데이터까지 조인하여 select를 한 번만 실행한다.
  //  }
  //
  //  @Transactional
  //  @Override
  //  public boolean delete(int no) throws Exception {
  //    // 1) 첨부파일 삭제
  //    boardDao.deleteFiles(no);
  //
  //    // 2) 게시글 삭제
  //    return boardDao.delete(no) > 0;
  //  }
  //
  //  @Override
  //  public List<Board> list() throws Exception {
  //    return boardDao.findAll();
  //  }
  //
  //  @Override
  //  public BoardAttachedFile getAttachedFile(int fileNo) throws Exception {
  //    return boardDao.findFileByNo(fileNo);
  //  }
  //
  //  @Override
  //  public boolean deleteAttachedFile(int fileNo) throws Exception {
  //    return boardDao.deleteFile(fileNo) > 0;
  //  }



  //  제동 소스
  @Override
  public BoardCategory getCategory(int no) {
    return boardDao.findCategoryByNo(no);
  }

  @Override
  public List<Map<String, Object>> bestList() {
    return boardDao.findBestList();
  }

  @Override
  public List<Map<String, Object>> clgList() {
    return boardDao.findClgList();
  }


  @Transactional
  @Override
  public void add(Board board) throws Exception {
    //  1) 게시판 등록
    if (boardDao.insert(board) == 0) {
      throw new Exception("게시글 등록 실패!");
    }
  }

  @Override
  public Board get(int no) throws Exception {
    return boardDao.findByNo(no); 
  }

  @Override
  public void increaseViews(int no) {
    // 조회수 증가시키기
    boardDao.increaseViews(no);
  }

  @Override
  public List<Map<String, Object>> list(Criteria cri) throws Exception {
    return boardDao.findAll(cri);
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

  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
    // 게시글에 댓글 삭제하기 
    boardCommentDao.deleteAll(no);
    // 스크랩 삭제
    scrapDao.deleteAll(no);

    return boardDao.delete(no) > 0;
  }

  @Transactional
  @Override
  public boolean update(Board board) throws Exception {

    // 썸네일을 변경하지 않았을 때 원래 파일이름을 넣어준다.
    if (board.getThumbnail() == null) {
      String originThumb = boardDao.getThumbnailByBoardNo(board.getNo());
      board.setThumbnail(originThumb);
    }

    // 1) 게시글 변경
    if (boardDao.update(board) == 0) {
      return false;
    }
    // 2) 첨부파일 추가
    //    if (board.getAttachedFiles().size() > 0) {
    //      boardDao.insertFiles(board);
    //    }

    return true;
  }

  @Override
  public int deleteThumbnail(int no) {
    return boardDao.daleteThumbnailByNo(no);
  }

  @Override
  public List<Map<String, Object>> listWithKeyword(Criteria cri, Search search) {
    System.out.println("bsercice" + cri);
    // 값들을 Map에 담아서 보내기
    Map<String, Object> searchObj = new HashMap<>(); 
    searchObj.put("search", search);
    searchObj.put("cri", cri);

    return boardDao.findByKeyword(searchObj);
  }

  //////////
  // 은지
  // 작성글
  @Override
  public List<Board> findByMyPost(Map<String, Object> paramMap) throws Exception {
    return boardDao.findByMyPost(paramMap);
  }

  @Override
  public int countMyPost(int memberNo) throws Exception {
    return boardDao.countMyPost(memberNo);
  }

}








