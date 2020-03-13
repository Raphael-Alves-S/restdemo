package br.com.api.restdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pessoa")
public class PessoaEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_id_pessoa")
	@SequenceGenerator(name="seq_id_pessoa", sequenceName="seq_id_pessoa", initialValue=1, allocationSize=1)  	
	@Column(name="id_pessoa")
	private long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, length = 11)
	private String cpf;
	
	@Column(nullable = true)
	private int idade;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	
}
