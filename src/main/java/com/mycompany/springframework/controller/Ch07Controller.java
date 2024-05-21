package com.mycompany.springframework.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.springframework.dto.Ch07Board;
import com.mycompany.springframework.dto.Ch07JoinForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch07")
public class Ch07Controller {
   
   @GetMapping("/request1")
   public ModelAndView request1(Model model) {
      
      // 데이터 생성
      List<Ch07Board> boardList = new ArrayList<>();
      
      for(int i=1; i<=10; i++) {
         boardList.add(new Ch07Board(i,"제목"+i,"봄날이여~"+i,"글쓴이"+i, 0, new Date()));
      }
      
      ModelAndView modelAndView = new ModelAndView();
      //request 범위 저장에 boardList 저장
      
      modelAndView.addObject("boardList", boardList);
      modelAndView.addObject("chNum", "ch07");
      
      modelAndView.setViewName("ch07/requestData");
      
      return modelAndView;
   }
   
   @ModelAttribute("jobList") //한번 정의하면 jsp에서 사용가능
   public List<String> getJobList(){
	   List<String> jobList = new ArrayList<>();
	      jobList.add("개발자");
	      jobList.add("공무원");
	      jobList.add("은행원");
	      
	   return jobList;
   }
   @ModelAttribute("cityList") //한번 정의하면 jsp에서 사용가능
   public List<String> getCityList(){
	   List<String> cityList = new ArrayList<>();
	   cityList.add("서울");
	   cityList.add("대전");
	   cityList.add("제주");
	      
	   return cityList;
   }
   
   @GetMapping("/request2")
   public String request2(Model model) {
      model.addAttribute("chNum","ch07");
      /*List<String> jobList = new ArrayList<>();
      jobList.add("개발자");
      jobList.add("공무원");
      jobList.add("은행원");
      model.addAttribute("jobList", jobList);*/
      //model.addAttribute("defaultJob", "개발자");
      
      
      
      model.addAttribute("chNum","ch07");
      /*List<String> cityList = new ArrayList<>();
      cityList.add("서울");
      cityList.add("대전");
      cityList.add("제주");
      model.addAttribute("cityList", cityList)*/;
      //model.addAttribute("defaultCity","대전");
      
      Ch07JoinForm joinForm = new Ch07JoinForm();
      joinForm.setMid("dlskw");
      joinForm.setMname("태환");
      joinForm.setMjob("공무원");
      joinForm.setMcity("제주");
      model.addAttribute("joinForm", joinForm);
      
      return "ch07/joinForm";
   }
   @PostMapping("/request2")
   public String PostRequest2(Model model,@ModelAttribute("joinForm") Ch07JoinForm joinForm) {
	   model.addAttribute("chNum","ch07");
	   //model.addAttribute("ch07JoinForm", joinForm); 할필요없이 자동저장
	   
	   return "ch07/memberInfo";
   }
   	
   
   @GetMapping("/sessionLoginForm")
   public String sessionLoginForm(Model model) {
      model.addAttribute("chNum","ch07");
      return "ch07/loginForm";
   }
   @PostMapping("/sessionLogin")
   public String sessionLogin(Model model, HttpSession session) {
      session.setAttribute("login", "success");
      return "redirect:/ch07/sessionLoginForm";
   }
   @GetMapping("/sessionLogout")
   public String sessionLogout(Model model, HttpSession session) {
      session.removeAttribute("login");
      return "redirect:/ch07/sessionLoginForm";
   }
   
   @GetMapping("/application")
   public String application(Model model, HttpServletRequest request) {
	  
      model.addAttribute("chNum","ch07");
      ServletContext application = request.getServletContext();
      //application 범위에서 counter 이름의 값(객체)를 가져오기
      Integer counter = (Integer)application.getAttribute("counter");
      
      //값(객체) 존재 유뮤에 따라 처리
      if(counter == null){
    	  application.setAttribute("counter", 1);
      } else {
    	  application.setAttribute("counter", 1 + counter);
      }
      return "ch07/applicationData";
   }

}