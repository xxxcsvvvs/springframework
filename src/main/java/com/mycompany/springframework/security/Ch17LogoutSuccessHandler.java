package com.mycompany.springframework.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Ch17LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	@Override
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException {
			log.info("실행");
			//로그 아웃 성공시 리다이렉트로 이동할 경로 설정
			/*setDefaultTargetUrl("/ch17/loginForm");*/
			
			//기타 기본 설정을 적응하기 위해 부모 메소드 호출
			super.onLogoutSuccess(request, response, authentication);
		}
}
