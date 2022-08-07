package com.example.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.board.dto.BoardDTO;
import com.example.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	@GetMapping("/list")
	public ModelAndView list() throws Exception {
		
		List<BoardDTO> list = boardService.listAll();
		
		ModelAndView mv = new ModelAndView();
		log.info("게시판 리스트 페이지");
		log.info("{}", list);
//		log.info("{}", list.get(0).getRegDate());
//		log.info("{}", list.get(0).getRegDate().getClass().getName());
		mv.setViewName("board/list");
		mv.addObject("list", list);
		
		return mv;
	}
	
	@GetMapping("/write")
	public ModelAndView write() {
		ModelAndView mv = new ModelAndView();
		log.info("게시판 작성 페이지");
		
		mv.setViewName("board/write");
		
		return mv;
	}
	
	@PostMapping("/write")
	public String write(BoardDTO boardDTO) throws Exception {
		
		boardService.create(boardDTO);
		
		log.info("게시판 작성 처리 - {}", boardDTO.toString());
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/view/{boardNum}")
	public ModelAndView view(@PathVariable("boardNum") int boardNum) throws Exception {
		
		BoardDTO boardDTO = boardService.read(boardNum);
		
		ModelAndView mv = new ModelAndView();
		log.info("게시판 상세보기 페이지");
		log.info("{}", boardDTO);
		
		mv.setViewName("board/view");
		mv.addObject("dto", boardDTO);
		
		return mv;
	}
}
