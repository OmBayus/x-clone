package com.osntus.xserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XserverApplication {
	public static void main(String[] args) {
		SpringApplication.run(XserverApplication.class, args);
		System.out.println("Here we go!");
	}

}
