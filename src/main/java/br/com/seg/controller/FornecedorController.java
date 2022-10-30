package br.com.seg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.seg.entity.Fornecedor;
import br.com.seg.service.FornecedorService;
import br.com.seg.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/fornecedor/v1")
@Tag(name = "Manutenção básica")
public class FornecedorController {
	
	@Autowired
	private FornecedorService fornecedor;

	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Operation(summary = "Pesquisar fornecedor por id")
	public Fornecedor findbyId(@PathVariable(value ="/id") long id) throws Exception {
		return fornecedor.findbyId(id);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
	@Operation(summary = "Listar todos os fornecedores")
	public List<Fornecedor> findAll(){
		return fornecedor.findAll();		
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML}, 
				 produces = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
	@Operation(summary = "Criar um novo fornecedor")
	public Fornecedor create(@RequestBody Fornecedor myForn) throws Exception {
		return fornecedor.create(myForn);
	}
	
	@PutMapping
	@Operation(summary = "Atualizar fornecedor")
	public Fornecedor update(@RequestBody Fornecedor myForn) throws Exception {
		return fornecedor.update(myForn);
	}
	
	@DeleteMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML})
	@Operation(summary = "Realizando a exclusão de um fornecedor")
	public ResponseEntity<?> delete(@PathVariable(value="id") long id) throws Exception {
		fornecedor.delete(id);
		return ResponseEntity.noContent().build();
	}
}
