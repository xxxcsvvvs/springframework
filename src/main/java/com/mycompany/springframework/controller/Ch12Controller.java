package com.mycompany.springframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch12")
public class Ch12Controller {
	@GetMapping("/createBeanByXml")
	public String createBeanByXml(Model model) {
		log.info("실행");
		model.addAttribute("chNum", "ch12");
		return "ch12/createBeanByXml";
	}
	
	@GetMapping("/diByXml")
	public String diByXml(Model model) {
		log.info("실행");
		model.addAttribute("chNum", "ch12");
		return "ch12/diByXml";
	}
}
