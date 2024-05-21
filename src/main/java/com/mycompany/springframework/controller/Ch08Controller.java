package com.mycompany.springframework.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mycompany.springframework.dto.Ch08CartItem;
import com.mycompany.springframework.dto.Ch08Product;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch08")
public class Ch08Controller {
	
	
	@RequestMapping("/productList")
	public String productList(Model model) {
		
		//상품 데이터 생성
		List<Ch08Product> productList = new ArrayList<>();
		for(int i = 1; i <= 5; i++) {
			productList.add(new Ch08Product(i, "상품"+i, i*10000));
		}
		//request 범위에 저장
		model.addAttribute("productList", productList);
		
		return "ch08/productList";
	}
	@RequestMapping("/detailView")
	public String detailView(int pno, Model model) {
		//상품 상제 정보 얻기
		Ch08Product product = new Ch08Product(pno, "상품" + pno, pno * 10000);
		 model.addAttribute("product", product);
		 model.addAttribute("ch08", "ch08");
		return "ch08/detailView";
	}
	
	@RequestMapping("/cartView")
	public String cartView(HttpSession session) {
		//장바구니를 세션에서 가져오기
		List<Ch08CartItem> cart =(List<Ch08CartItem>)session.getAttribute("cart");
		//가져온 장바구니가 없을 경우 새로 장바구니를 생성해서 session 범위에 저장
		if(cart == null) {
			cart = new ArrayList<Ch08CartItem>();
			session.setAttribute("cart", cart);
		}
		
		return "ch08/cartView";
		
	}
	
	@RequestMapping("/addCartItem")
	public String addCartItem(int pno, int amount, HttpSession session) {
		
		log.info("pno: " + pno);
		log.info("amont: " + amount);
		
		
		
		//장바구니를 세션에서 가져오기
		List<Ch08CartItem> cart =(List<Ch08CartItem>)session.getAttribute("cart");
		if(cart == null) {
			cart = new ArrayList<Ch08CartItem>();
			session.setAttribute("cart", cart);
		}
		
		boolean result = false;
		for( Ch08CartItem cartItem : cart) {
			if(cartItem.getProduct().getPno() == pno) {
				cartItem.setAmount(cartItem.getAmount() + amount);
				result = true;
			}
		}
		
		//장바구니에 장바구니 아이템을 추가
		if(!result) {
		Ch08Product product = new Ch08Product(pno, "상품" + pno, pno * 1000);
		//장바구니 아이템 생성
		Ch08CartItem cartItem = new Ch08CartItem();
		cartItem.setProduct(product);
		cartItem.setAmount(amount);
		cart.add(cartItem);
		}
		
		return "redirect:/ch08/cartView";
	}
	
	
	@RequestMapping(value="/updateCartItem", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String updateCartItem(int pno, int amount, @SessionAttribute("cart") List<Ch08CartItem> args) {//HttpSession 변수를 선언해서 
																														//session.getAttribute를 할 필요가 없음
		//장바구니 가져오기
		
		//pno와 같은 cartItem찾기
		for(Ch08CartItem item : args) {
			if(item.getProduct().getPno() == pno) {
				//cartItem의 amount를 수정
				item.setAmount(amount);
			}
		}
		//수정 결과를 응답 생성
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result","success");
		
		
		return jsonObject.toString();
	}
	
	@RequestMapping("/removeCartItem")
	public String removeCartItem(int pno, @SessionAttribute List<Ch08CartItem> cart) {
		//장바구니를 
		//List<Ch08CartItem> cart =(List<Ch08CartItem>)session.getAttribute("cart");  @SessionAttribute를 쓰면 session에서 .getAttribute를 할 필요가 없음
		Iterator<Ch08CartItem> iterator = cart.iterator();
		while(iterator.hasNext()) {
			Ch08CartItem cartItem = iterator.next();
			if(cartItem.getProduct().getPno() == pno) {
				iterator.remove();
			}
		}
		return "redirect:/ch08/cartView";
	}
}

