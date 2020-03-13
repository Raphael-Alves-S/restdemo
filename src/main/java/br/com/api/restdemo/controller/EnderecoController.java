package br.com.api.restdemo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.restdemo.entity.EnderecoEntity;
import br.com.api.restdemo.entity.PessoaEntity;
import br.com.api.restdemo.repository.EnderecoRepository;
import br.com.api.restdemo.repository.PessoaRepository;

@RestController
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository _enderecoRepository;
	
	@Autowired
	private PessoaRepository _pessoaRepository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/endereco")
	public List<EnderecoEntity> Get(){
		return _enderecoRepository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "endereco/{id}")
	public ResponseEntity<EnderecoEntity> GetById(@PathVariable(value = "id") long id){
		Optional<EnderecoEntity> endereco = _enderecoRepository.findById(id);
		if(endereco.isPresent()) {
			return new ResponseEntity<EnderecoEntity>(endereco.get(), HttpStatus.OK);
		}
		if(!(endereco.isPresent())){
			return new ResponseEntity<EnderecoEntity>(HttpStatus.NOT_FOUND);
		}
		
		else{
			return new ResponseEntity<EnderecoEntity>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/endereco")
	public EnderecoEntity Post(@Valid @RequestBody EnderecoEntity endereco) {
		 _pessoaRepository.flush();
		return _enderecoRepository.save(endereco);
		
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value = "/endereco/{id}")
	public ResponseEntity<EnderecoEntity> Put(@PathVariable(value = "id")long id, @Valid @RequestBody EnderecoEntity newEnderecoEntity){
		Optional<EnderecoEntity> oldEndereco = _enderecoRepository.findById(id);
		PessoaEntity pessoaEntity = new PessoaEntity();
		Optional<PessoaEntity> oldPessoa = _pessoaRepository.findById(pessoaEntity.getId());
		if(oldEndereco.isPresent()){
			EnderecoEntity endereco = oldEndereco.get();
			endereco.setLogradouro(newEnderecoEntity.getLogradouro());
			endereco.setBairro(newEnderecoEntity.getBairro());
			endereco.setCidade(newEnderecoEntity.getCidade());
			endereco.setCep(newEnderecoEntity.getCep());
			endereco.setUF(newEnderecoEntity.getUF());
			endereco.setPessoas(oldPessoa.get());
			_enderecoRepository.save(endereco);
			return new ResponseEntity<EnderecoEntity>(endereco, HttpStatus.OK);	
		}
		
		else {
			return new ResponseEntity<EnderecoEntity>(HttpStatus.NOT_FOUND);
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value = "endereco/delete/{id}")
	public ResponseEntity<Object> Delete(@PathVariable(value = "id")long id){
		Optional<EnderecoEntity> endereco = _enderecoRepository.findById(id);
		if(endereco.isPresent()) {
			_enderecoRepository.delete(endereco.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
