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

import br.com.api.restdemo.entity.PessoaEntity;
import br.com.api.restdemo.repository.PessoaRepository;

@RestController
public class PessoaController {

	@Autowired
	private PessoaRepository _pessoaRepository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/pessoa")
	public List<PessoaEntity> Get(){
		return _pessoaRepository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/pessoa/{id}")
	public ResponseEntity<PessoaEntity> GetById(@PathVariable(value = "id")long id){
		Optional<PessoaEntity> pessoa = _pessoaRepository.findById(id);
		if(pessoa.isPresent()) {
			return new ResponseEntity<PessoaEntity>(pessoa.get(), HttpStatus.OK);
		}
		else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value = "/pessoa")
	public PessoaEntity Post(@Valid @RequestBody PessoaEntity pessoa) {
		return _pessoaRepository.save(pessoa);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping(value = "/pessoa/{id}")
	public ResponseEntity<PessoaEntity> Put(@PathVariable(value = "id")long id, @Valid @RequestBody PessoaEntity newPessoaEntity){
		Optional<PessoaEntity> oldPessoa = _pessoaRepository.findById(id);
		if(oldPessoa.isPresent()) {
			PessoaEntity pessoa = oldPessoa.get();
			pessoa.setNome(newPessoaEntity.getNome());
			pessoa.setCpf(newPessoaEntity.getCpf());
			pessoa.setIdade(newPessoaEntity.getIdade());
			_pessoaRepository.save(pessoa);
			return new ResponseEntity<PessoaEntity>(pessoa, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<PessoaEntity>(HttpStatus.NOT_FOUND);
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping(value = "pessoa/delete/{id}")
	public ResponseEntity<Object> Delete(@PathVariable(value = "id")long id){
		Optional<PessoaEntity> pessoa = _pessoaRepository.findById(id);
		if(pessoa.isPresent()) {
			_pessoaRepository.delete(pessoa.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
