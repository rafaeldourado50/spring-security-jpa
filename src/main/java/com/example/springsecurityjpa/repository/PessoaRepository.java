package com.example.springsecurityjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsecurityjpa.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
