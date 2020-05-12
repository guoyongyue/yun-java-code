package com.yun;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();
		list.add("");
		list.add("");
		list.add("");
		list.add("1");
		list.add("2");
		list.add("3");

		String messageKey = FastJsonUtils.convertObjectToJSON(list);
		List<String> messageKeys = FastJsonUtils.toList(messageKey, String.class);
		System.out.println(messageKeys);


		System.out.println(Double.valueOf("12.12312412412512").intValue());
//		SpringApplication.run(Application.class, args);
	}

}
