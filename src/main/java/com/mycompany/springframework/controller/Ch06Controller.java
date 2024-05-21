package com.mycompany.springframework.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch06Cart;
import com.mycompany.springframework.dto.Ch06Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch06")
public class Ch06Controller {
	
	@RequestMapping("/forward")
	public String forward(Model model, HttpServletRequest request) {
		
		// 데이터 생성 -> jsp까지만 사용하고 싶으면 request 범위까지만 사용하면 됨
		// forward 방식을 하면  request 범위임
		Ch06Member member = new Ch06Member();
		member.setMid("spring");
		member.setMname("난벚꽃");
		member.setMemail("kim@naver.com");
		
		// request 범위에 객체 생성------------
		// forward할 때 까지 계속 사용할 수 있는 범위
		// 응답이 전송되면 request 범위는 사라짐
		// -------------------------------
		model.addAttribute("chNum", "ch06");
		model.addAttribute("member1", member);
		request.setAttribute("member2", member);
		// 둘다 request 범위의 값을 저장하는 것임 -> forward 방식
		// 대신 jsp에 응답이 가면 값이 사라짐
		
		return "ch06/forward";
	}
	// 이런식으로 JSP로 이동하는게 forward방식
	// 컨트롤러와 jsp에서 변수를 사용할 수 있는게 request 범위에서 사용하는 것
	
	@GetMapping("/redirect")
	public String redirect(HttpSession session) {
		log.info("실행");
		
		//데이터 생성
		List<String> items = new ArrayList<>();
		items.add("상품1");
		items.add("상품2");
		items.add("상품3");
		Ch06Cart cart = new Ch06Cart();
		cart.setItems(items);
		
		// Session 범위에 저장
		// 동일한 client(브라우저)가 사용할 수 있는 범위
		session.setAttribute("cart", cart);
		
		return "redirect:/ch06/sessionData";
		// 리다이렉트 한다고 하면 응답시 request와 response는 날라감  밑에서는 새로운 request와 response가 생성
	}
	
	@GetMapping("/sessionData")
	public String sessionData(HttpSession session, Model model) {
		log.info("실행");
		
		//session 범위에 저장된 객체 가져오기
		Ch06Cart cart = (Ch06Cart) session.getAttribute("cart");
		if(cart != null) {
			for(String item : cart.getItems()) {
				log.info(item);
		}
	}
		model.addAttribute("chNum", "ch06");
		return "ch06/sessionData";
	}

}