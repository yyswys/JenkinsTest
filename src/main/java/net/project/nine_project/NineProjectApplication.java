package net.project.nine_project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("net.project.nine_project.mapper")
public class NineProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(NineProjectApplication.class, args);
	}

}
