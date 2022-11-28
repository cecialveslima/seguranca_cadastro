package br.com.seg;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class MinhasApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinhasApisApplication.class, args);
	}
}
