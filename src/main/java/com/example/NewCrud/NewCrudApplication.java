package com.example.NewCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class NewCrudApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(NewCrudApplication.class, args);
	}


}
