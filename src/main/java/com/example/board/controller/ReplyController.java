package com.example.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.board.dto.ReplyDTO;
import com.example.board.service.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyController {

	private static final Logger log = LoggerFactory.getLogger(ReplyController.class);
	
	@Autowired
	private ReplyService replyService;

	public void setBoardService(ReplyService replyService) {
		this.replyService = replyService;
	}

	@PostMapping("/write")
	@ResponseBody
	public int write(ReplyDTO replyDTO, HttpSession session) {
		log.info("댓글 작성");
		
		replyDTO.setUserId(session.getAttribute("userId").toString());
		int result = replyService.writeReply(replyDTO);

		return result;
	}

	@GetMapping("/replyList/{boardNum}")
	@ResponseBody
	public ModelAndView replyList(@PathVariable int boardNum) {
		log.info("댓글 목록");
		
		ModelAndView mv = new ModelAndView();
		List<ReplyDTO> list = replyService.list(boardNum);
		int replyCnt = replyService.replyCnt(boardNum);
		
		mv.addObject("replyCnt", replyCnt);
		mv.addObject("list", list);
		mv.setViewName("ajax/replyAjax");
		return mv;
	}
	
	@PostMapping("/update")
	@ResponseBody
	public int update(ReplyDTO replyDTO, HttpSession session) {
		log.info("댓글 작성");
		
		replyDTO.setUserId(session.getAttribute("userId").toString());
		log.info("{}",replyDTO);
		int result = replyService.updateReply(replyDTO);
		
		return result;
	}

	@PostMapping("/delete")
	@ResponseBody
	public int delete(ReplyDTO replyDTO, HttpSession session) {
		log.info("댓글 작성");
		
		replyDTO.setUserId(session.getAttribute("userId").toString());
		log.info("{}",replyDTO);
		
		if(replyDTO.getDepth() == 0) {
			return replyService.deleteUpdate(replyDTO.getReplyNum());
		}
		
		return replyService.deleteReply(replyDTO.getReplyNum());
	}
	
	@PostMapping("/chkDelete")
	@ResponseBody
	public void chkDelete(@RequestParam (required = false, value = "chkArr[]") List<String> chkArr, @RequestParam (required = false, value = "depthArr[]") List<String> depthArr) throws Exception {
		log.info("게시판 선택 삭제 처리");
		for(int i = 0; i < chkArr.size(); i++) {
			if(Integer.parseInt(depthArr.get(i)) == 0) {
				replyService.deleteUpdate(Integer.parseInt(chkArr.get(i)));
			} else {
				replyService.deleteReply(Integer.parseInt(chkArr.get(i)));
			}
		}
	}
	
}
