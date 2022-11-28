package br.com.seg.controller;

import java.util.List;

import javax.validation.Valid;

//import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.seg.entity.Fornecedor;
import br.com.seg.repository.FornecedorRepository;
import br.com.seg.service.FornecedorService;
import br.com.seg.utils.MediaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/fornecedor/v1")
//@Tag(name = "Manutenção básica")
@Api(value = "Manutenção de Fornecedor",  tags = {"Fornecedor"})
public class FornecedorController {
	
	@Autowired
	private FornecedorService fornecedor;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;

	public long Max() {
		return fornecedorRepository.maxId();
	}
	
	//private Logger logger = Logger.getLogger(FornecedorController.class.getName());

	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Operation(summary = "Pesquisar fornecedor por id",
		responses = {
			@ApiResponse(description = "Sucesso na requisição", 	responseCode = "200" , content =  @Content),
			@ApiResponse(description = "Credenciais inválidas", 	responseCode = "401" , content =  @Content),
			@ApiResponse(description = "Acesso não permitido",		responseCode = "403" , content =  @Content),
			@ApiResponse(description = "Não encontrado", 			responseCode = "404" , content =  @Content)})			

	public ResponseEntity<Fornecedor> findbyId(@PathVariable @ApiParam(name = "id", example = "1", value ="ID do Fornecedor") long id) 
			throws Exception {
		return fornecedor.findbyId(id);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
	@Operation(summary = "Listar todos os fornecedores",
			responses = {
					@ApiResponse(description = "Sucesso na requisição", 	responseCode = "200" , content =  @Content),
					@ApiResponse(description = "Credenciais inválidas", 	responseCode = "401" , content =  @Content),
					@ApiResponse(description = "Acesso não permitido",		responseCode = "403" , content =  @Content),
					@ApiResponse(description = "Não encontrado", 			responseCode = "404" , content =  @Content)})	
	public ResponseEntity<List<Fornecedor>> findAll() throws Exception{
		return fornecedor.findAll();		
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML}, 
				 produces = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
	@Operation(summary = "Criar um novo fornecedor",
			responses = {
			@ApiResponse(description = "Sucesso na requisição", 	responseCode = "200" , content =  @Content),
			@ApiResponse(description = "Atualizado com sucesso", 	responseCode = "201" , content =  @Content),					
			@ApiResponse(description = "Credenciais inválidas", 	responseCode = "401" , content =  @Content),
			@ApiResponse(description = "Acesso não permitido",		responseCode = "403" , content =  @Content),
			@ApiResponse(description = "Não encontrado", 			responseCode = "404" , content =  @Content)})	
	public Fornecedor create(@RequestBody @Valid Fornecedor myForn) throws Exception {
		long novo_id = Max() + 1 ;
		myForn.setForId(novo_id);
		return fornecedor.create(myForn);
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML}, 
			 produces = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
	@Operation(summary = "Atualizar fornecedor", 
			responses = {
					@ApiResponse(description = "Sucesso na requisição", 	responseCode = "200" , content =  @Content),
					@ApiResponse(description = "Atualizado com sucesso", 	responseCode = "201" , content =  @Content),					
					@ApiResponse(description = "Requisição mal formada", 	responseCode = "400" , content =  @Content),
					@ApiResponse(description = "Você não tem permissão para acessar este recurso", responseCode = "401" , content =  @Content),
					@ApiResponse(description = "Acesso não permitido",		responseCode = "403" , content =  @Content),
					@ApiResponse(description = "Não encontrado", 			responseCode = "404" , content =  @Content),
					@ApiResponse(description = "Foi gerada uma exceção", 	responseCode = "500" , content =  @Content)})	
	public ResponseEntity<FornecedorService> update(@RequestBody @Valid Fornecedor myForn) throws Exception {
		
		//logger.info(myForn.getNameFor());
		
		fornecedor.update(myForn);
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping(consumes = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML}, 
			 produces = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
	@Operation(summary = "Atualizar fornecedor, campos não informados serão mantidos os valores já gravados", 
			responses = {
					@ApiResponse(description = "Sucesso na requisição", 	responseCode = "200" , content =  @Content),
					@ApiResponse(description = "O servidor atendeu à solicitação com êxito ", 	responseCode = "204" , content =  @Content),
					@ApiResponse(description = "Requisição mal formada", 	responseCode = "400" , content =  @Content),
					@ApiResponse(description = "Você não tem permissão para acessar este recurso", responseCode = "401" , content =  @Content),
					@ApiResponse(description = "Não encontrado", 			responseCode = "404" , content =  @Content),
					@ApiResponse(description = "Acesso não permitido",		responseCode = "403" , content =  @Content),
					@ApiResponse(description = "Foi gerada uma exceção", 	responseCode = "500" , content =  @Content)})	
	public ResponseEntity<FornecedorService> updateParcial(@RequestBody @Valid Fornecedor myForn) throws Exception {
		
		//logger.info(myForn.getNameFor());
		
		fornecedor.updateParcial(myForn);
		return ResponseEntity.ok().build();
	}	
	
	@DeleteMapping()
	@Operation(summary = "Remove fornecedor", 
			responses = {
					@ApiResponse(description = "Sucesso na requisição", 	responseCode = "204" , content =  @Content),
					@ApiResponse(description = "Requisição mal formada", 	responseCode = "400" , content =  @Content),
					@ApiResponse(description = "Você não tem permissão para acessar este recurso", responseCode = "401" , content =  @Content),
					@ApiResponse(description = "Não autorizado", 			responseCode = "403" , content =  @Content),
					@ApiResponse(description = "Foi gerada uma exceção", 	responseCode = "500" , content =  @Content)})	
	public ResponseEntity<FornecedorService> delete(@RequestBody @Valid Fornecedor myForn) throws Exception {
		
		//logger.info(myForn.getNameFor());
		
		fornecedor.delete(myForn.getForId());
		return ResponseEntity.ok().build();
	}		
}
