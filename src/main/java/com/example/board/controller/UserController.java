package com.example.board.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import com.example.board.dto.BoardDTO;
import com.example.board.dto.PageDTO;
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
	public ModelAndView join(UserDTO userDTO) throws Exception {
		
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
		boolean result = userService.login(userDTO);
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("user/login");
		
		if(result) {
			userDTO = userService.loginUserInfo(userDTO.getUserId());
			session.setAttribute("userId", userDTO.getUserId());
			session.setAttribute("nickName", userDTO.getNickName());
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
	
	@GetMapping("/info")
	public ModelAndView update(HttpSession session) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		log.info("유저 회원정보 페이지");
		
		if(session.getAttribute("userId") != null) {
			String userId = session.getAttribute("userId").toString();
			UserDTO userDTO = userService.loginUserInfo(userId);
			
			mv.setViewName("user/info");
			mv.addObject("userDTO", userDTO);
		} else {
			mv.setViewName("user/info");
		}
		
		return mv;
	}
	
	@PostMapping("/changePw")
	@ResponseBody
	public Map<String, Object> changePw(HttpSession session, String curPw, String newPw) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(session.getAttribute("userId") != null) {
			String userId = session.getAttribute("userId").toString();
			UserDTO userDTO = new UserDTO();
			userDTO.setUserId(userId);
			userDTO.setUserPw(curPw);

			boolean result = userService.login(userDTO);
			
			if(result) {
				userDTO.setUserPw(newPw);
				userService.changePw(userDTO);
				map.put("message", "OK");
			} else {
				map.put("message","현재 비밀번호가 다릅니다. 다시 입력해 주세요.");
			}
		}
		return map;
	}

	@PostMapping("/changeNickName")
	@ResponseBody
	public Map<String, Object> changeNickName(HttpSession session, String changeNickName) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(session.getAttribute("userId") != null) {
			boolean result = (userService.nickNameCheck(changeNickName) == 0) ? true : false;

			if(result) {
				String userId = session.getAttribute("userId").toString();
				UserDTO userDTO = new UserDTO();
				userDTO.setUserId(userId);
				userDTO.setNickName(changeNickName);
				
				userService.changeNickName(userDTO);
				
				map.put("message", "OK");
			} else {
				map.put("message","이미 사용중인 닉네임입니다.");
			}
		}
		return map;
	}
	
	@PostMapping("/deleteUser")
	@ResponseBody
	public Map<String, Object> deleteUser(HttpSession session, String userPw) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();

		if(session.getAttribute("userId") != null) {
			String userId = session.getAttribute("userId").toString();
			UserDTO userDTO = new UserDTO();
			userDTO.setUserId(userId);
			userDTO.setUserPw(userPw);

			boolean result = userService.login(userDTO);
			
			if(result) {
				userService.deleteUser(userId);
				userService.logout(session);
				map.put("message", "OK");
			} else {
				map.put("message","비밀번호를 다시 확인해주세요.");
			}
		}
		return map;
	}
	
	
	@GetMapping("/boardList")
	public ModelAndView boardList(@RequestParam(value="p", defaultValue="1") int curPage, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("유저 작성 게시글 페이지");
		
		if(session.getAttribute("userId") != null) {
			String userId = session.getAttribute("userId").toString();

			int total = userService.total(userId);
			PageDTO pageDTO = new PageDTO(userId, total, curPage);
			List<BoardDTO> list = userService.list(new PageDTO(userId, total, curPage));
			LocalDate today = LocalDate.now();
			
			mv.setViewName("user/boardList");
			mv.addObject("pageDTO", pageDTO);
			mv.addObject("list", list);
			mv.addObject("today", today);
		} else {
			mv.setViewName("user/info");
		}
		
		return mv;
	}

	@GetMapping("/replyList")
	public ModelAndView replyList(@RequestParam(value="p", defaultValue="1") int curPage, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		log.info("유저 작성 댓글 페이지");
		
		if(session.getAttribute("userId") != null) {
			String userId = session.getAttribute("userId").toString();
			
			int total = userService.total(userId);
			PageDTO pageDTO = new PageDTO(userId, total, curPage);
			List<BoardDTO> list = userService.list(new PageDTO(userId, total, curPage));
			LocalDate today = LocalDate.now();
			
			log.info("pageDTO: {}", pageDTO);
			
			mv.setViewName("user/replyList");
			mv.addObject("pageDTO", pageDTO);
			mv.addObject("list", list);
			mv.addObject("today", today);
		} else {
			mv.setViewName("user/info");
		}
		
		return mv;
	}
	
}
