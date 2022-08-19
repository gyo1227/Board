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
import org.springframework.web.bind.annotation.ResponseBody;

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
		replyDTO.setReplyUserId(session.getAttribute("userId").toString());
		int result = replyService.create(replyDTO);
		return result;
	}
	
	@GetMapping("/replyList/{boardNum}")
	@ResponseBody
	public List<ReplyDTO> replyList(@PathVariable int boardNum) {
		log.info("{}", boardNum);
		List<ReplyDTO> list = replyService.list(boardNum);
		log.info("{}", list);
		return list;
	}
	
	
	
	
	

	
}
