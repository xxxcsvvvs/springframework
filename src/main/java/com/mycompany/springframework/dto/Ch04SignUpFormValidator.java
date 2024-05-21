package com.mycompany.springframework.dto;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch04SignUpFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		log.info("실행");
		return Ch04SignUpForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//강제 변환
		Ch04SignUpForm signUpForm = (Ch04SignUpForm)target;
		
		//아이디 검사
		String mid = signUpForm.getMid();
		if(mid == null || mid.equals("")) {
			errors.rejectValue("mid", "errors.mid.required", "아이디를 입력해.");//에러가 등록되면 에러메세지 출력
		}else if(mid.length() < 6 || mid.length() > 12) {
			errors.rejectValue("mid", null,"아디는 6자 이상, 12자 이하로 입력해.");
		}
		//비밀번호 검사
		String mpassword = signUpForm.getMpassword();
		String patternPassword = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}";
		if(mpassword == null || mpassword.equals("")) {
			errors.rejectValue("mpassword", null, "비번을 입력해.");
		}else if(mpassword.length() <= 8 || mpassword.length() >= 15) {
			errors.rejectValue("mpassword", null,"비번은 8자 이상, 15자 이하로 입력해.");
		}else if(!Pattern.matches(patternPassword, mpassword)){
			errors.rejectValue("mpassword", null, "비밀번호는 알파벳 대소문자 및 숫자를 입력해.");
		}
		//이름 검사
		String mname = signUpForm.getMname();
		String patternName = "^[가-힣]{1,5}$";
		if(mname == null || mname.equals("")) {
			errors.rejectValue("mname", null, "이름을 입력해.");
		}else if(mname.length() <= 1 || mname.length() >= 5) {
			errors.rejectValue("mname", null,"이름은 1자 이상, 5자 이하로 입력해.");
		}else if(!Pattern.matches(patternName, mname)){
			errors.rejectValue("mname", null, "이름은 한글만 입력해.");
		}
		//이메일 검사
		String memail = signUpForm.getMemail();
		String patternEmail = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		if(memail == null || memail.equals("")) {
			errors.rejectValue("memail", null, "이메일을 입력해.");
		}else if(!Pattern.matches(patternEmail, memail)){
			errors.rejectValue("memail", null, "이메일 형식에 맞게 입력해.");
		}
		//전화번호 검사
		String mphoneNumber = signUpForm.getMphoneNumber();
		String patternPhoneNumber = "^\\\\d{3}-\\\\d{3,4}-\\\\d{4}$";
		if(mphoneNumber == null || mphoneNumber.equals("")) {
			errors.rejectValue("mname", null, "전화번호를 입력해.");
		}else if(!Pattern.matches(patternPhoneNumber, mphoneNumber)){
			errors.rejectValue("mphoneNumber", null, "전화번호 형식에 맞게 입력해.");
		}
	}
	
	
	
}
