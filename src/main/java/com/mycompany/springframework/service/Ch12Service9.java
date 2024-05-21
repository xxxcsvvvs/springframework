package com.mycompany.springframework.service;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.Ch12Dao3;
import com.mycompany.springframework.dao.Ch12Dao4;
import com.mycompany.springframework.dao.Ch12Dao5;
import com.mycompany.springframework.dao.Ch12DaoInterface;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch12Service9 {
   //Field 주입
	//@AutoWired private Ch12Dao3 ch12Dao3;
   @Autowired @Qualifier("ch12Dao6") private Ch12DaoInterface field1; //@Qulifier("ch12Dao6")
   
   @Resource(name="ch12Dao7") private Ch12DaoInterface field2; // (name = "이름") 이 이름을 가지고 있는 객체를 주입해
   
   @Autowired
   public Ch12Service9(@Qualifier("ch12Dao6") Ch12DaoInterface field1) {
	   
   }
   
   @Resource(name="ch12Dao7")
	public void setField2(Ch12DaoInterface field2) {
		this.field2 = field2;
	}
}