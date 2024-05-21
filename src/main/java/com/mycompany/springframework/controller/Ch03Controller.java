package com.mycompany.springframework.controller;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.springframework.dto.Ch03Dto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch03")
public class Ch03Controller {
	@RequestMapping("/receiveParamData")
	public String receiveParamData( // 아코디언 때문에 받아야함
			String param1, String param2, String param3, String param4, String param5, String param6, Model model) {

		log.info("실행");
		log.info("param1: " + param1);
		log.info("param2: " + param2);
		log.info("param3: " + param3);
		log.info("param4: " + param4);
		log.info("param5: " + param5);
		log.info("param6: " + param6);

		//model.addAttribute("chNum", chNum); // 원래는 클라이언트에서 받아서 사용해야하는데 강제로 넣어서도 사용가능함
		model.addAttribute("chNum", "ch03"); // 이렇게 하면 menu에서 chNum=ch03을 안 받아와도 됨
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		model.addAttribute("param3", param3);
		model.addAttribute("param4", param4);
		model.addAttribute("param5", param5);
		model.addAttribute("param6", param6);

		return "ch03/receiveParamData";
	}

	@GetMapping("/postMethodForm")
	public String postMethodForm(String chNum, Model model) {
		
		log.info("실행");
		model.addAttribute("chNum", "ch03");
		return "ch03/postMethodForm";
	}
	
	@RequestMapping("/requestParamAnnotation")
	public String requestParamAnnotation( 
			@RequestParam("param1") String arg1,
			@RequestParam("param2") String arg2,
			@RequestParam("param3") String arg3,
			@RequestParam("param4") String arg4,
			@RequestParam("param5") String arg5, 
			Model model) {

		log.info("실행");
		log.info("param1: " + arg1);
		log.info("param2: " + arg2);
		log.info("param3: " + arg3);
		log.info("param4: " + arg4);
		log.info("param5: " + arg5);
		

		//model.addAttribute("chNum", chNum); // 원래는 클라이언트에서 받아서 사용해야하는데 강제로 넣어서도 사용가능함
		model.addAttribute("chNum", "ch03"); // 이렇게 하면 menu에서 chNum=ch03을 안 받아와도 됨
		model.addAttribute("param1", arg1);
		model.addAttribute("param2", arg2);
		model.addAttribute("param3", arg3);
		model.addAttribute("param4", arg4);
		model.addAttribute("param5", arg5);
		

		return "ch03/receiveParamData";
	}
	
	
	
	@RequestMapping("/requestParamAnnotationRequired")
	public String requestParamAnnotationRequired( 
			@RequestParam (required=true)String param1,
			@RequestParam (required=true)String param2,
			String param3,
			String param4,
			String param5,
			String param6,
			Model model) {

		log.info("실행");
		log.info("param1: " + param1);
		log.info("param2: " + param2);
		log.info("param3: " + param3);
		log.info("param4: " + param4);
		log.info("param5: " + param5);
		log.info("param6: " + param6);

		//model.addAttribute("chNum", chNum); // 원래는 클라이언트에서 받아서 사용해야하는데 강제로 넣어서도 사용가능함
		model.addAttribute("chNum", "ch03"); // 이렇게 하면 menu에서 chNum=ch03을 안 받아와도 됨
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		model.addAttribute("param3", param3);
		model.addAttribute("param4", param4);
		model.addAttribute("param5", param5);
		model.addAttribute("param6", param6);

		return "ch03/receiveParamData";
	}
	
	@RequestMapping("/requestParamAnnotationDefaultValue")
	public String requestParamAnnotationDefaultValue( 
			// @RequestParam 붙으면 required 가 true로 지정되나봄
			String param1,
			String param2,
			String param3,
			String param4,
			String param5,
			@RequestParam (defaultValue="기본값")String param6,
			Model model) {
   
		log.info("실행");
		log.info("param1: " + param1);
		log.info("param2: " + param2);
		log.info("param3: " + param3);
		log.info("param4: " + param4);
		log.info("param5: " + param5);
		log.info("param6: " + param6);

		//model.addAttribute("chNum", chNum); // 원래는 클라이언트에서 받아서 사용해야하는데 강제로 넣어서도 사용가능함
		model.addAttribute("chNum", "ch03"); // 이렇게 하면 menu에서 chNum=ch03을 안 받아와도 됨
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		model.addAttribute("param3", param3);
		model.addAttribute("param4", param4);
		model.addAttribute("param5", param5);
		model.addAttribute("param6", param6);

		return "ch03/receiveParamData";
	}
	
	@RequestMapping("/typeChange")
	public String typeChange( 
			// @RequestParam 붙으면 required 가 true로 지정되나봄
			String param1,
			int param2,
			double param3,
			boolean param4,
			@DateTimeFormat(pattern="yyyy-MM-dd") Date param5,
			@RequestParam(defaultValue="0") int param6,
			// 값이 넘어오지 않는 경우에 int로 사용하고 싶으면 디폴트 값음 0으로 하면됨
//			int param6,
			Model model) {
		// 모든 값이 String으로 넘어오지만 변환이 가능한 값들은 변환된 값으로 받을 수 있음
		// Date의 경우는 바로 변경이 안되서 @DateTimeFormat을 적어줘야함
		// 값이 들어오지 않는데 int로 변경할려고 하면 500 에러남 null을 인트로 변경할 수 없음 
		// String으로 받을 경우에는 상관 없음
		
		//model.addAttribute("chNum", chNum); // 원래는 클라이언트에서 받아서 사용해야하는데 강제로 넣어서도 사용가능함
		model.addAttribute("chNum", "ch03"); // 이렇게 하면 menu에서 chNum=ch03을 안 받아와도 됨
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		model.addAttribute("param3", param3);
		model.addAttribute("param4", param4);
		model.addAttribute("param5", param5);
		model.addAttribute("param6", param6);
		// jsp => ${} 안에 들어감

		return "ch03/receiveParamData";
	}
	
	@RequestMapping("/getDto")
	public String getDto(@ModelAttribute ("dto")Ch03Dto dto, Model model) {
		// DTO로 받으면 훨씬 심플해짐 매개변수로 전체 받을 필요 없음 속도도 같음 DTO 쓰자
		// @ModelAttribute ("dto") 여기에 이름을 넣으면 ("dto")로 jsp에서 사용 가능
		// @ModelAttribute ("dto") 이거 사용하지 않으면 클래스의 처음이 소문자로 ch03Dto로 고정됨
		// DTO 사용할 경우 따로 model로 넘겨주지 않아도 jsp에서 사용 가능함
		
		log.info("실행");
		log.info("param1: " + dto.getParam1());
		log.info("param2: " + dto.getParam2());
		log.info("param3: " + dto.getParam3());
		log.info("param4: " + dto.isParam4());
		log.info("param5: " + dto.getParam5());
		
		model.addAttribute("chNum", "ch03");
		
		return "ch03/getDto";
	}
	
	@GetMapping("/ajax")
	public String ajax(Model model) {
		log.info("실행");
		model.addAttribute("chNum", "ch03");
		return "ch03/ajax";
	}
	
	@PostMapping(value="/getAjaxParams", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String getAjaxParams(Ch03Dto dto) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("param1", dto.getParam1());
		jsonObject.put("param2", dto.getParam2());
		jsonObject.put("param3", dto.getParam3());
		jsonObject.put("param4", dto.isParam4());
		jsonObject.put("param5", dto.getParam5());
		
		return jsonObject.toString();
	}
	
	
	

}