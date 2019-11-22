package com.baixin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private static MongoTemplate mongoTemplate;

	@Test
	public void saveFileTest() throws  Exception{
//		FileInputStream file = new FileInputStream("/Users/liqingzheng/Job_work/Idea_Work/springboot-manage/src/main/resources/static/img/qq.jpg");

	}





}
