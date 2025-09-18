package com.ygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@EntityScan("com.ygo.model")
@SpringBootApplication
@ConfigurationPropertiesScan
public class YgoApplication {

	public static void main(String[] args) {
		SpringApplication.run(YgoApplication.class, args);
	}

}
