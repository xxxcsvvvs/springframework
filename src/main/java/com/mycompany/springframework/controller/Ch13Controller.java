package com.mycompany.springframework.controller;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Pager;
import com.mycompany.springframework.service.Ch13Service;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j // 로깅
@RequestMapping("/ch13")
public class Ch13Controller {

	@Autowired
	private Ch13Service service;

	@GetMapping("/writeBoardForm")
	public String writeBoardForm() {
		return "ch13/writeBoardForm";
	}

	@PostMapping("/writeBoard")
	public String writeBoard(Ch13Board board) {
		// 요청 데이터의 유효성 검사
		log.info("original filename: " + board.getBattach().getOriginalFilename());
		log.info("filetype:" + board.getBattach().getContentType());

		// 첨부 파일이 있는 여부 조사
		if (board.getBattach() != null && !board.getBattach().isEmpty()) {

			// DTO 추가 설정
			board.setBattachoname(board.getBattach().getOriginalFilename());
			board.setBattachtype(board.getBattach().getContentType());

			try {
				board.setBattachdata(board.getBattach().getBytes());
			} catch (Exception e) {

			}
		}
		// 로그인된 사용자 아이디 설정
		board.setMid("user");
		// 비지니스 로직 처리를 서비스로 위임
		service.writeBoard(board);
		return "redirect:/ch13/boardList";
	}

	/*
	 * board.xml 설정 내용 <insert id="insert"
	 * parameterType="com.company.springframework.dto.Ch13Board"> insert into
	 * board(bno, btitle, bcontent, bdate, mid, bhitcount) values(seq_bno.nextval,
	 * #{btitle}, #{bcontent}, sysdate, #{mid}, 0 ) <!-- #{btitle} => btitle의 필드값을
	 * #{}에 넣어준다는 의미--> </insert>
	 */

	@GetMapping("/boardList")
	public String boardList(String pageNo, Model model, HttpSession session) {
		if (pageNo == null) {
			// pageNo를 받지 못했을 경우
			pageNo = (String) session.getAttribute("pageNo");
			if (pageNo == null) {
				// 세션에 저장되어 있지 않을 경우 "1"로 강제 세팅
				pageNo = "1";
			}
		}
		// 세션에 PageNo 저장
		session.setAttribute("pageNo", pageNo);
		// 문자열을 정수로 저장
		int intPageNo = Integer.parseInt(pageNo);

		// Pager 객체 생성
		int rowsPagingTarget = service.getTotalRow();
		Ch13Pager pager = new Ch13Pager(10, 10, rowsPagingTarget, intPageNo);
		List<Ch13Board> boardList = service.getBoardList(pager);

		// JSP에서 사용할 수 있도록 설정
		model.addAttribute("pager", pager);
		model.addAttribute("boardList", boardList);
		return "ch13/boardList";
	}

	@GetMapping("/detailBoard")
	public String detailBoard(int bno, Model model) throws Exception {
		Ch13Board board = service.getBoard(bno);
		model.addAttribute("board", board);
		return "ch13/detailBoard";
	}

	@GetMapping("/attachDownload")
	public void attachDownload(int bno, HttpServletResponse response) throws Exception {
		// 다운로드 할 데이터를 준비
		Ch13Board board = service.getBoard(bno);
		byte[] data = service.getAttachData(bno);
		// 응답 헤더 구성
		response.setContentType(board.getBattachtype());
		String fileName = new String(board.getBattachoname().getBytes("UTF-8"), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		// 응답 본문에 파일 데이터 출력
		OutputStream os = response.getOutputStream();
		os.write(data);
		os.flush();
		os.close();

	}

	@GetMapping("/updateBoardForm")
	public String updateBoardForm(int bno, Model model) {
		Ch13Board board = service.getBoard(bno);
		model.addAttribute("board", board);
		return "ch13/updateBoardForm";
	}

	@PostMapping("/updateBoard")
	public String updateBoard(Ch13Board board) {

		// 첨부 파일이 있는 여부 조사
		if (board.getBattach() != null && !board.getBattach().isEmpty()) {

			// DTO 추가 설정
			board.setBattachoname(board.getBattach().getOriginalFilename());
			board.setBattachtype(board.getBattach().getContentType());

			try {
				board.setBattachdata(board.getBattach().getBytes());
			} catch (Exception e) {

			}
		}
		// 비지니스 로직 처리를 서비스로 위임
		service.updateBoard(board);
		return "redirect:/ch13/detailBoard?bno="+ board.getBno();
		
	}
	@GetMapping("/deleteBoard")
	public String deleteBoard(int bno) {
		service.removeBoard(bno);
		return "ch13/updateBoardForm";
	}
}