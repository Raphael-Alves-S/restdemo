package br.com.api.restdemo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
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
	
	@JoinTable(name = "TB_PESSOA_X_TB_ENDERECO", 
	    	joinColumns = {@JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")}, 
			inverseJoinColumns = {@JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")})
	
	        @ManyToOne(cascade ={  CascadeType.PERSIST, CascadeType.MERGE })
			private EnderecoEntity endereco;

	public EnderecoEntity getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoEntity endereco) {
		this.endereco = endereco;
	}

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
