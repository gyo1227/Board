package com.example.board.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageDTO;
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
	
	// 게시글 전체 조회
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value="p", defaultValue="1") int curPage, @RequestParam(defaultValue = "") String option, @RequestParam(defaultValue = "") String query) throws Exception {
		
		int total = boardService.total(new PageDTO(option, query));
		PageDTO pageDTO = new PageDTO(total, curPage, option, query);
		List<BoardDTO> list = boardService.list(pageDTO);
		LocalDate today = LocalDate.now();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/list");
		mv.addObject("total", total);
		mv.addObject("pageDTO", pageDTO);
		mv.addObject("list", list);
		mv.addObject("today", today);
		if(curPage > pageDTO.getEndPage()) {
			mv.addObject("total", 0);
			mv.addObject("list", null);
		}
		
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
	public String write(BoardDTO boardDTO, HttpSession session) throws Exception {
		
		boardDTO.setUserId(session.getAttribute("userId").toString());
		boardService.create(boardDTO);
		
		log.info("게시판 작성 처리");
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/view/{boardNum}")
	public ModelAndView view(@PathVariable("boardNum") int boardNum) throws Exception {
		BoardDTO boardDTO = boardService.read(boardNum);
		
		ModelAndView mv = new ModelAndView();
		log.info("게시판 상세보기 페이지");
		
		mv.setViewName("board/view");
		mv.addObject("boardDTO", boardDTO);
		
		return mv;
	}

	@GetMapping("/update/{boardNum}")
	public ModelAndView update(@PathVariable("boardNum") int boardNum) throws Exception {
		
		BoardDTO boardDTO = boardService.read(boardNum);
		
		ModelAndView mv = new ModelAndView();
		log.info("게시판 수정 페이지");
		
		mv.setViewName("board/update");
		mv.addObject("boardDTO", boardDTO);
		
		return mv;
	}
	
	@PostMapping("/update/{boardNum}")
	public String update(HttpSession session, BoardDTO boardDTO, HttpServletRequest request) throws Exception {
		
		if(session.getAttribute("userId") == null) {
			request.setAttribute("msg", "로그인이 필요합니다.");
			request.setAttribute("url", "/user/login");
			
			return "includes/alert";
		}
		
		boardService.update(session, boardDTO);
		
		log.info("게시판 수정 처리");
		
		return "redirect:/board/view/" + boardDTO.getBoardNum();
	}
	
	@GetMapping("/delete/{boardNum}")
	public String delete(@PathVariable("boardNum") int boardNum) throws Exception {
		
		boardService.delete(boardNum);
		log.info("게시판 삭제 처리");
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/chkDelete")
	@ResponseBody
	public void chkDelete(@RequestParam (required = false, value = "chkArr[]") List<String> chkArr) throws Exception {
		log.info("게시판 선택 삭제 처리");
		for(int i = 0; i < chkArr.size(); i++) {
			boardService.delete(Integer.parseInt(chkArr.get(i)));
		}
	}
}
