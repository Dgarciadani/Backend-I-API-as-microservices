package com.clinic.mspatientservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class MsPatientServiceApplication {

	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(MsPatientServiceApplication.class, args);
	}

}
