package com.bitcamp.testproject.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bitcamp.testproject.dao.NoticeDao;
import com.bitcamp.testproject.vo.Notice;

@Service
public class DefaultNoticeService implements NoticeService {

  @Autowired
  NoticeDao noticeDao;

  @Override
  public List<Notice> findAll(Map<String, Object> paramMap) {
    return noticeDao.findAll(paramMap);
  }

  @Override
  public int countNotice() {
    return noticeDao.countNotice();
  }

  @Override
  public int add(Notice notice) {
    return noticeDao.insert(notice);
  }

  @Override
  public Notice get(int no) {
    return noticeDao.get(no);
  }

  @Override
  public void increaseViews(int no) {
    noticeDao.increaseViews(no);
  }

  @Transactional
  @Override
  public boolean update(Notice notice) {

    // 썸네일을 변경하지 않았을 때 원래 파일이름을 넣어준다.
    if (notice.getThumbnail() == null) {
      String originThumb = noticeDao.getThumbnailByNoticeNo(notice.getNo());
      notice.setThumbnail(originThumb);
    }

    // 1) 게시글 변경
    if (noticeDao.update(notice) == 0) {
      return false;
    }
    // 2) 첨부파일 추가
    //    if (board.getAttachedFiles().size() > 0) {
    //      boardDao.insertFiles(board);
    //    }

    return true;
  }

  @Override
  public boolean delete(int no) {

    return noticeDao.delete(no) > 0;
  }
}
