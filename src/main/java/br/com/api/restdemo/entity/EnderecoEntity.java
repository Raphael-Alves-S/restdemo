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
@Table(name="tb_endereco", schema = "public")
public class EnderecoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_endereco")
	@SequenceGenerator(name="seq_id_endereco", sequenceName="seq_id_endereco", initialValue=1, allocationSize=1) 
	@Column(name = "id_endereco")
	private long id;
	
	@Column(nullable = false)
	private String logradouro;
	
	@Column(nullable = false, length = 9)
	private String cep;
	
	@Column(nullable = false)
	private String bairro;
	
	@Column(nullable = false)
	private String cidade;
	
	@Column(nullable = false, length = 2)
	private String UF;
		
	
	@JoinTable(name = "TB_PESSOA_X_TB_ENDERECO", 
	    	joinColumns = {@JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")}, 
			inverseJoinColumns = {@JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")})
	
	        @ManyToOne(cascade ={  CascadeType.PERSIST, CascadeType.MERGE })
			private PessoaEntity pessoas;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public PessoaEntity getPessoas() {
		return pessoas;
	}

	public void setPessoas(PessoaEntity l) {
		this.pessoas = l;
	}
	 
	 
	
}
