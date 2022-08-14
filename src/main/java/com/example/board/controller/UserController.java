package com.example.board.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@GetMapping("/join")
	public ModelAndView join() {
		
		ModelAndView mv = new ModelAndView();
		log.info("유저 회원가입 페이지");
		
		mv.setViewName("user/join");
		
		return mv;
	}
	
	@PostMapping("/join")
	public ModelAndView join(UserDTO userDTO, Model model) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("유저 회원가입 처리 - {}", userDTO.toString());
		
		mv.setViewName("user/join");
		if(userService.join(userDTO)) {
			mv.addObject("message", "회원가입을 축하합니다.");
		}
		
		return mv; 
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public String idCheck(@RequestParam("userId") String userId) throws Exception {
		log.info("userId 중복 검사");
		int cnt = userService.idCheck(userId);
		
		String result = Integer.toString(cnt);
		
		return result;
	}
	
	@PostMapping("/nickNameCheck")
	@ResponseBody
	public String nickNameCheck(@RequestParam("nickName") String nickName) throws Exception {
		log.info("nickName 중복 검사");
		int cnt = userService.nickNameCheck(nickName);
		
		String result = Integer.toString(cnt);
		
		return result;
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		
		ModelAndView mv = new ModelAndView();
		log.info("유저 로그인 페이지");
		
		mv.setViewName("user/login");
		
		return mv;
	}
	
	@PostMapping("/login")
	@ResponseBody
	public ModelAndView login(UserDTO userDTO, HttpSession session) throws Exception {
		
		boolean result = userService.login(userDTO, session);
		
		ModelAndView mv = new ModelAndView();
		log.info("유저 로그인 처리");
		
		mv.setViewName("user/login");
		
		if(result == true) {
		} else {
			mv.addObject("userId", userDTO.getUserId().toString());
			mv.addObject("message", "fail");
		}
		return mv;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		log.info("유저 로그아웃 처리");
		userService.logout(session);
		
		return "redirect:/board/list";
	}
	
}
