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
import com.bitcamp.testproject.service.PartyCommentService;
import com.bitcamp.testproject.vo.Comment;
import com.bitcamp.testproject.vo.Criteria;

@Controller
@RequestMapping("/partyComment/")
public class PartyCommentController {

  @Autowired
  ServletContext sc;

  @Autowired
  PartyCommentService partyCommentService;


  @PostMapping("insertAndReplay")
  @ResponseBody
  public int insertAndReplay(String content, int partyNo, int memberNo) {

    // ajax가 보낸 데이터 댓글 vo에 넣기
    Comment comment = new Comment(content, partyNo, memberNo);

    return partyCommentService.insert(comment);
  }

  @GetMapping("list")
  @ResponseBody
  public Object list(int pageNo, int partyNo) {
    System.out.println(">>>>>>" + pageNo);

    // 몇번 페이지인지 기록하고 넘기기
    Criteria cri = new Criteria();
    cri.setPerPageNum(15);
    if (pageNo != 0) {
      cri.setPage(pageNo);
    }

    Map<String, Object> map = new HashMap<>();
    map.put("partyNo", partyNo);
    map.put("cri", cri);

    List<Comment> list = partyCommentService.getComments(map);

    return list;
  }

  @PostMapping("delete/{no}")
  @ResponseBody
  public int delete(@PathVariable int no) {
    return partyCommentService.delete(no);
  }


  @PostMapping("update")
  @ResponseBody
  public int update(Comment comment) {

    int result = partyCommentService.update(comment);

    return result;
  }

}






