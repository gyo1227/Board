package com.example.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.board.dto.UserDTO;
import com.example.board.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

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
	
	@PostMapping("/join")
	public String join(UserDTO userDTO) throws Exception {
		
		userService.join(userDTO);
		
		log.info("유저 회원가입 처리 - {}", userDTO.toString());
		
		return "redirect:/user/login";
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public int idCheck(@RequestParam("userId") String userId) throws Exception {
		log.info("userId : {}" , userId);
		int cnt = userService.idCheck(userId);
		
		log.info("cnt : {}" , cnt);
		
		return cnt;
	}
}
