package com.mycompany.springframework.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.springframework.dto.Ch02FileInfo;
import com.mycompany.springframework.interceptor.Auth;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch02") 
public class Ch02Controller {

	@RequestMapping("/getMethod")
	public String getMethod(String bkind, String bno, Model model) {
		// 요청 처리 코드
		log.info("getMethod() 실행");
		
		log.info("bkind: " + bkind);
		log.info("bno: " + bno);

		model.addAttribute("chNum", "ch02"); 
		
		// view에서 사용하는 model을 컨트롤러에서 제공
		return "ch02/getMethod";
	}

	@RequestMapping("/getMethodAjax")
	public String getMethodAjax(String bkind, String bno, Model model) {
		// 요청 처리 코드
		log.info("getMethodAjax() 실행");
		
		log.info("bkind: " + bkind);
		log.info("bno: " + bno);

		return "ch02/getMethodAjax";
	}

	@RequestMapping("/postMethod")
	public String postMethod(String mid, String mpassword, Model model) {
		// 요청 처리 코드
		log.info("postMethod() 실행");
	
		log.info("mid: " + mid);
		log.info("mpassword: " + mpassword);
		model.addAttribute("chNum", "ch02");
		return "ch02/postMethod";
	}

	@RequestMapping("/postMethodAjax") // 이런식으로 get 방식으로도 만들 수 있음.
	public void postMethodAjax(String mid, String mpassword, HttpServletResponse response) throws IOException {
		// 요청 처리 코드
		log.info("postMethodAjax() 실행");
		log.info("mid: " + mid);
		log.info("mpassword: " + mpassword);

		Map<String, String> map = new HashMap<>();
		map.put("spring", "12345");
		map.put("summer", "67890");

		JSONObject result = new JSONObject();

		if (map.containsKey(mid)) {
			if (map.get(mid).equals(mpassword)) {
				// 로그인 성공
				result.put("result", "success");
			} else {
				// 비빌번호가 틀린 경우
				result.put("result", "fail");
				result.put("reason", "wrongMpassword");
			}

		} else {
			// 아이디가 존재하지 않는 경우
			result.put("result", "fail");
			result.put("reason", "wrongMid");
		}
		// 직접 응답을 생성해서 브라우저로 보냄
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json; charset=UTF-8");
		pw.println(result.toString());
		pw.flush();
		pw.close();
	}

	@GetMapping("/modelAndViewReturn")
	public ModelAndView modelAndViewReturn() {

		log.info("modelAndViewReturn() 실행");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("chNum", "ch02");
		modelAndView.addObject("login", true);
		modelAndView.addObject("userName", "감자바");
		// 전달하는 개수만큼 적어서 전달해야함

		modelAndView.setViewName("ch02/modelAndViewReturn");
		// model이랑 비슷한 역할
		return modelAndView;
	}

	@GetMapping("/voidReturn")
	public void imageDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 다운로드할 이미지 파일 선택
		String fileName = "photo1.jpg";

		// 실제 파일의 경로 얻기
		String filePath = "/resources/image/photos/photo1.jpg";
		String fileSystemPath = request.getServletContext().getRealPath(filePath);
		//실제 파일이 위치한 곳을 찾아서 알려줌

		// 실제로 어디에 저장되어 있는지 알 수 있음
		log.info("fileSystemPath: " + fileSystemPath);
		// 파일의 MIME 타입 알기?
		String mimeType = request.getServletContext().getMimeType(fileName);
		log.info("mimeType: " + mimeType);

		// 파일 데이터를 읽는 입력 스트림을 얻기
		InputStream is = new FileInputStream(fileSystemPath);

		// 응답 헤더에 Content-Type 지정하기 (image/jpeg)
		response.setContentType("image/jpeg");

		// 응답 본문에 데이터를 얻는 출력 스트림을 얻기
		OutputStream os = response.getOutputStream();

		// 응답 본문에 데이터 보내기
		// 입력 스트림에서 데이터를 읽고 -> 출력 스트림으로 데이터를 쓰기

		// 방법1
//		byte[] bytes = new byte[1024];
//		
//		while(true) {
//			int readNum = is.read(bytes);
//			if(readNum == -1) break;
//			os.write(bytes, 0, readNum);
//		}

		// 방법2
		FileCopyUtils.copy(is, os);

		os.flush();
		is.close();
		os.close();
	}

	@GetMapping("/objectReturn")
	public String objectReturn( Model model) {

		model.addAttribute("chNum", "ch02");
		return "ch02/objectReturn";
	}

	@GetMapping(value = "/objectReturnJson1", produces = "application/json; charset=UTF-8")
	// 두개 적을 떄는 value, produces를 꼭 적어야함
	@ResponseBody // 리턴된 객체를 JSON으로 해석하고 응답 본문에 넣겠다는 의미
					// -> String이면 원래 jsp 이름이 들어감
	public String objectReturnJson1() {
		log.info("objectReturnJson1() 실행");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo1.jpg");
		// return {"fileName" : "photo1.jpg"}
		return jsonObject.toString();
		// 응답 내용
	}

	@GetMapping(value = "/objectReturnJson2", produces = "application/json; charset=UTF-8")
	@ResponseBody // 리턴된 객체를 JSON으로 변환하고 응답 본문에 넣겠다.
	public Ch02FileInfo objectReturnJson2() {
		log.info("objectReturnJson2() 실행");
		Ch02FileInfo fileInfo = new Ch02FileInfo();
		fileInfo.setFileName("photo2.jpg");
		fileInfo.setInfo("아름다운 풍경 사진");
		return fileInfo; // 객체를 JSON으로 전달 or JSON을 객체로 변환할 때 내부적으로 사용 
	}

	@GetMapping("/testAuthInterceptor")
	public String testAuthInterceptor( Model model) {
		log.info("testAuthInterceptor() 실행");
		model.addAttribute("chNum","ch02");
		return "ch02/testAuthInterceptor";
	}

	@GetMapping("/testAuthInterceptor2")
	@Auth
	public String testAuthInterceptor2( Model model) {
		log.info("testAuthInterceptor2() 실행");
		model.addAttribute("chNum","ch02");
		return "ch02/testAuthInterceptor2";
	}

}