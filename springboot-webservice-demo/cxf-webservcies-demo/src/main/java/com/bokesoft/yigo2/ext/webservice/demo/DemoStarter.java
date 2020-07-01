package com.bokesoft.yigo2.ext.webservice.demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoStarter {
	public static void main(String[] args) throws IOException {
		SpringApplication app = new SpringApplication(DemoStarter.class);		
		app.run(args);
    }
}
