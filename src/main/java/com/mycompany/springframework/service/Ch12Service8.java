package com.mycompany.springframework.service;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.Ch12Dao3;
import com.mycompany.springframework.dao.Ch12Dao4;
import com.mycompany.springframework.dao.Ch12Dao5;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch12Service8 {
   //Field 주입
	//@AutoWired private Ch12Dao3 ch12Dao3;
   @Autowired
   private Ch12Dao3 ch12Dao3;
   private Ch12Dao4 ch12Dao4;
   private Ch12Dao5 ch12Dao5;
   
   //Constrictor 주입
   @Autowired
   public Ch12Service8(Ch12Dao4 ch12Dao4) {
	   this.ch12Dao4 = ch12Dao4;
   }
   //Setter 주입
   //@Autowired
   @Resource
   public void setCh12Dao5(Ch12Dao5 ch12Dao5) {
	   log.info("실행");
	   this.ch12Dao5 = ch12Dao5;
   }
   
   
}