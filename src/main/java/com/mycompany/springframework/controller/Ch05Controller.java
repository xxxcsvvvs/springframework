package com.mycompany.springframework.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch05")
public class Ch05Controller {
	/*@GetMapping("/header")
	public String header(Model model, 
			@RequestHeader("user-Agent") String userAgent) {
		// @RequestHeader("원하는 헤더 이름") -> 요청 헤더에서 값을 받을 수 있음
		log.info("실행");
		log.info("user-Agent: " + userAgent);
		
		if(userAgent.contains("Edg")) {
			model.addAttribute("browser","Edge");
		} else {
			model.addAttribute("browser","chrome");
		}
		
		model.addAttribute("chNum","ch05");
		return"ch05/header";
	}*/
	
	@GetMapping("/header")
	public String header(Model model, 
			HttpServletRequest request) {
		// HttpServletRequest 여기 요청 헤더 값이 들어가 있음
		
		String userAgent = request.getHeader("User-Agent");
		// 이런 방식으로도 User-Agent를 받아 올 수 있음
		
		if(userAgent.contains("Edg")) {
			model.addAttribute("browser","Edge");
		} else {
			model.addAttribute("browser","chrome");
		}
		
		// 브라우저가 실행하는 컴퓨터의 IP 주소
		String clientIp = request.getRemoteAddr();
		model.addAttribute("clientIp", clientIp);
		
		model.addAttribute("chNum","ch05");
		return"ch05/header";
	}
	
	@GetMapping("/createCookie")
	public String createCookie(Model model, HttpServletResponse response) {
		//Cookie 생성
		Cookie cookie = new Cookie("useremail", "summer@naver.com");
		
		//Cookie를 응답 HTTP에 포함시키기
		response.addCookie(cookie);
		
		return "redirect:/";
	}
	
	@GetMapping("/readCookie")
	public String readCookie(@CookieValue("useremail") String userEmail, Model model) {
		// 쿠키 이름이 달라서 useremail != userEmail @CookieValue를 적어줘야함
		log.info("useremail: " + userEmail);
		
		model.addAttribute("userEmail", userEmail);
		model.addAttribute("chNum","ch05");
		return "ch05/cookie";
	}

}