package com.ygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.ygo.model")
@SpringBootApplication
public class YgoApplication {

	public static void main(String[] args) {
		SpringApplication.run(YgoApplication.class, args);
	}

}
