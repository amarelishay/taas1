package com.jb.taas;

import com.jb.taas.security.Information;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication // @Configuration + @ComponentScan(basePackages = "com.jb.taas") + @EnableAutoConfiguration
@EnableScheduling
public class TaasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaasApplication.class, args);
	}


}
