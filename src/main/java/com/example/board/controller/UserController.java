package com.example.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@GetMapping("/login")
	public ModelAndView login() {
		
		ModelAndView mv = new ModelAndView();
		log.info("유저 로그인 페이지");
		
		mv.setViewName("user/login");
		mv.addObject("", "");
		
		return mv;
	}
	
	@GetMapping("/join")
	public ModelAndView join() {
		
		ModelAndView mv = new ModelAndView();
		log.info("유저 회원가입 페이지");
		
		mv.setViewName("user/join");
		mv.addObject("", "");
		
		return mv;
	}
}
