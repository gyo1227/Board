package com.example.board.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
	@ResponseBody
	public Map<String, Object> join(UserDTO userDTO) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		log.info("유저 회원가입 처리");
		
		if(userService.join(userDTO)) {
			map.put("message", "회원가입을 축하합니다.");
		}
		
		return map; 
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public Map<String, Object> idCheck(@RequestParam("userId") String userId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		log.info("userId 검증");
		String pattern = "^[a-zA-Z0-9]*$";
		boolean check = Pattern.matches(pattern, userId);
		
		if(check && !(userId.length() < 4 || userId.length() > 20)) {
			int cnt = userService.idCheck(userId);
			
			if(cnt == 0) {
				map.put("message", "OK");
			} else {
				map.put("message", "EXIST");
			}
			
		} else {
			map.put("message", "ERROR");
		}
		
		return map;
	}
	
	@PostMapping("/nickNameCheck")
	@ResponseBody
	public Map<String, Object> nickNameCheck(@RequestParam("nickName") String nickName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		log.info("nickName 검증");
		String pattern = "^[a-zA-Z0-9가-힣ㄱ-ㅎ]*$";
		boolean check = Pattern.matches(pattern, nickName);

		if(!check) {
			map.put("message", "닉네임은 한글, 영문, 숫자를 사용한 2 ~ 8자리만 입력가능합니다.");
			return map;
		}
		if(!(nickName.length() >= 2 && nickName.length() < 9)) {
			map.put("message", "닉네임은 한글, 영문, 숫자를 사용한 2 ~ 8자리만 입력가능합니다.");
			return map;
		}
		
		int cnt = userService.nickNameCheck(nickName);

		if(cnt == 0) {
			map.put("message", "OK");
		} else {
			map.put("message", "이미 사용중인 닉네임 입니다.");
		}
		
		return map;
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
	
	@PostMapping("/nickName")
	@ResponseBody
	public Map<String, Object> nickName(HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String nickName = null;
		if(session.getAttribute("userId") != null) {
			String userId = session.getAttribute("userId").toString();
			nickName = userService.loginUserInfo(userId).getNickName();
		}
		
		log.info("nickName: {}", nickName);
		
		map.put("nickName", nickName);
		
		return map;
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
		
		String pattern = "^[\\S]*$";
		boolean check = Pattern.matches(pattern, newPw);
		
		log.info("{}", check);
		log.info("{}", newPw.length());
		
		if(check && (newPw.length() >= 8 && newPw.length() < 17)) {
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
		} else { 
			map.put("message", "영문, 숫자, 특수문자를 사용한 8 ~ 16자리여야 합니다.");
		}
		
		return map;
	}

	@PostMapping("/changeNickName")
	@ResponseBody
	public Map<String, Object> changeNickName(HttpSession session, String changeNickName) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(session.getAttribute("userId") != null) {
			
			String pattern = "^[a-zA-Z0-9가-힣ㄱ-ㅎ]*$";
			boolean check = Pattern.matches(pattern, changeNickName);

			if(!check) {
				map.put("message", "닉네임은 한글, 영문, 숫자를 사용한 2 ~ 8자리만 입력가능합니다.");
				return map;
			}
			if(!(changeNickName.length() >= 2 && changeNickName.length() < 9)) {
				map.put("message", "닉네임은 한글, 영문, 숫자를 사용한 2 ~ 8자리만 입력가능합니다.");
				return map;
			}
			
			int cnt = userService.nickNameCheck(changeNickName);

			if(cnt == 0) {
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

			int total = userService.boardTotal(userId);
			PageDTO pageDTO = new PageDTO(userId, total, curPage);
			List<BoardDTO> list = userService.boardList(new PageDTO(userId, total, curPage));
			LocalDate today = LocalDate.now();

			log.info("pageDTO: {}", pageDTO);
			
			mv.setViewName("user/boardList");
			mv.addObject("pageDTO", pageDTO);
			mv.addObject("total", total);
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
			
			int total = userService.replyTotal(userId);
			PageDTO pageDTO = new PageDTO(userId, total, curPage);
			List<BoardDTO> list = userService.replyList(new PageDTO(userId, total, curPage));
			
			log.info("pageDTO: {}", pageDTO);
			
			mv.setViewName("user/replyList");
			mv.addObject("pageDTO", pageDTO);
			mv.addObject("list", list);
			mv.addObject("total", total);
		} else {
			mv.setViewName("user/info");
		}
		
		return mv;
	}
	
}
