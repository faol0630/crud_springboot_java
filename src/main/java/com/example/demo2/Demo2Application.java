package com.example.demo2;

import com.example.demo2.dao.RepoCursoInt;
import com.example.demo2.dao.RepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@ComponentScan("com.example.demo2")
public class Demo2Application  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	@Override
	public void run(String... args)  {
		System.out.println("Aplicacion iniciada exitosamente!");

	}
}
