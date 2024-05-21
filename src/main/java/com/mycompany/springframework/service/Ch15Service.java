package com.mycompany.springframework.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mycompany.springframework.dao.mybatis.Ch13AccountDao;
import com.mycompany.springframework.dto.Ch15Account;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch15Service {
	@Autowired
	private Ch13AccountDao accountDao;
	
	
	
	public List<Ch15Account> getAccountList(){
		List<Ch15Account> accountList = accountDao.selectAll();
		return accountList;
	
	}
	@Transactional //예외발생시 모두 실행을 금지 시켜야 한다.
	public void transfer(int fromAno, int toAno, int amount) {
		//출금 작업
		Ch15Account fromAccount = accountDao.selectByAno(fromAno); //번호로 작업할 계좌로 조회해서 가져온다.
		if(fromAccount == null) {
			throw new RuntimeException("출금 계좌 없음"); //계좌가 없으면 예외를 발생시킨다.
		}
		fromAccount.setBalance(fromAccount.getBalance() - amount);//기존 금액에서 출금금액을 빼고 다시 밸런스를 세팅한다.
		accountDao.updateBalance(fromAccount); //뺀 금액을 DB에 업데이트 한다.
		
		//입금작업
		Ch15Account toAccount = accountDao.selectByAno(toAno); //번호로 작업할 계좌로 조회해서 가져온다.
		if(toAccount == null) {
			throw new RuntimeException("입금 계좌 없음"); //계좌가 없으면 예외를 발생시킨다.
		}
		toAccount.setBalance(toAccount.getBalance() + amount);//기존 금액에서 입금금액을 더하고 다시 밸런스를 세팅한다.
		accountDao.updateBalance(toAccount); //더한 금액을 DB에 업데이트 한다.
	}
}
