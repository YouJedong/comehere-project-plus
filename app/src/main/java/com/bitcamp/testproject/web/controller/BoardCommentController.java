package com.bitcamp.testproject.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bitcamp.testproject.service.BoardCommentService;
import com.bitcamp.testproject.service.BoardService;
import com.bitcamp.testproject.vo.Comment;
import com.bitcamp.testproject.vo.Criteria;

@Controller
@RequestMapping("/boardComment/")
public class BoardCommentController {

  @Autowired
  ServletContext sc;
  @Autowired
  BoardService boardService;
  @Autowired
  BoardCommentService boardCommentService;


  @PostMapping("add")
  @ResponseBody
  public int add(String content, int boardNo, int memberNo) {
    Comment comment = new Comment(content, boardNo, memberNo);
    return boardCommentService.insert(comment);
  }

  @GetMapping("list")
  @ResponseBody
  public Object list(int pageNo, int boardNo) {
    // 댓글의 몇 페이지인지 저장하기
    Criteria cri = new Criteria();
    cri.setPerPageNum(5);
    if (pageNo != 0) {
      cri.setPage(pageNo);
    }

    // 출력할 게시글 번호와 페이지 번호를 Map에 담아서 보내기
    Map<String, Object> map = new HashMap<>();
    map.put("boardNo", boardNo);
    map.put("cri", cri);

    List<Comment> list = boardCommentService.getCommentsOfBoard(map);

    return list;
  }

  @PostMapping("update")
  @ResponseBody
  public int update(Comment comment) { 
    return boardCommentService.update(comment);
  }

  @PostMapping("delete/{no}")
  @ResponseBody
  public int delete(@PathVariable int no) {
    return boardCommentService.delete(no);
  }

}






