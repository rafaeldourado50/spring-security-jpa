package com.example.springsecurityjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.springsecurityjpa.repository.PessoaRepository;
import com.example.springsecurityjpa.repository.UsuarioRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {UsuarioRepository.class, PessoaRepository.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
