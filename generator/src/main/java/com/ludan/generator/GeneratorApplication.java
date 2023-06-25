package com.ludan.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableDiscoveryClient
//@EnableTransactionManagement
//@EnableFeignClients(basePackages = {"com.central.common.feign", "com.ludan.demo"})
@SpringBootApplication
public class GeneratorApplication {

	public static void main(String[] args) {

		SpringApplication.run(GeneratorApplication.class, args);
	}

}
