package com.tutorial.apidemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class SpringbootTutorialApplicationTests {

	@Test
	void contextLoads() throws Exception{
		double price;
		int foodClass = 1;
		double atribute = 20.0;
		String importDay = "12/10/2021";
		String expiry = "12/2/2022";
		LocalDate dImportDay = LocalDate.parse(importDay);
//		Date dExpiry = formatter1.parse(expiry);
		long monthBetween = ChronoUnit.MONTHS.between(dImportDay,LocalDate.now());
		if (foodClass == 1){
			price = atribute*20000*monthBetween;
		}
		else {
			price = atribute*10000*monthBetween;
		}
		System.out.println(price);
	}

}
