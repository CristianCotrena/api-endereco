package com.api.endereco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ApiEnderecoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiEnderecoApplication.class, args);
	}
	@GetMapping("/")
	public String helloWord() {
		return "Hello word";
	}
}
