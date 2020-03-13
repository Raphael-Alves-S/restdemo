package br.com.api.restdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.restdemo.entity.EnderecoEntity;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long>{

}
