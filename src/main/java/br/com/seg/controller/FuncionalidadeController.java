package br.com.seg.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.jboss.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody; //Monta JSON na requisição

import br.com.seg.dto.FuncionalidadeDTO;
import br.com.seg.entity.Funcionalidade;
import br.com.seg.service.FuncionalidadeService;
import br.com.seg.utils.MediaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/api/funcionalidade/v1")
@Api(value = "Manutenção de funcionalidades" ,  tags = {"Funcionalidade"})
public class FuncionalidadeController {

	@Autowired
	private FuncionalidadeService funcionalidade;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
	@Operation(summary = "Lista todos as funcionalidades cadastradas" ,
	responses = {
			@ApiResponse(description = "Sucesso na requisição", responseCode = "200", content = @Content),
			@ApiResponse(description = "Credenciais inválidas", responseCode = "401", content = @Content),
			@ApiResponse(description = "Acesso não permitido",  responseCode = "403", content = @Content),
			@ApiResponse(description = "Não encontrado",		responseCode = "404", content = @Content)})	
	public ResponseEntity<List<Funcionalidade>> findAll() throws Exception {
		return funcionalidade.findAll();
	}
	
	@GetMapping(value  = "/{id}", produces = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
	@Operation(summary ="Localiza funcionalidade por ID",
	responses = {
			@ApiResponse(description ="Sucesso na requisição" , responseCode = "200", content = @Content),
			@ApiResponse(description ="Credenciais inválidas" , responseCode = "401", content = @Content),
			@ApiResponse(description ="Acesso não permitido" ,  responseCode = "403", content = @Content),
			@ApiResponse(description ="Não encontrado" , 		responseCode = "404", content = @Content)})	
	public ResponseEntity<Funcionalidade> findbyId(@PathVariable @Valid 
			@ApiParam(name = "id da funcionalidade", example = "PDF" ,  value="PDF") String id) {
		return funcionalidade.findbyId(id);
		
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML},
				 consumes = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
	@Operation(summary = "Cria nova funcionalidade",
	responses = {
			@ApiResponse(description = "Sucesso na criação", 	 responseCode = "200", content = @Content),
			@ApiResponse(description = "Atualizado com sucesso", responseCode = "200", content = @Content),
			@ApiResponse(description = "Credenciais inválidas",  responseCode = "401", content = @Content),
			@ApiResponse(description = "Acesso não permitido",   responseCode = "403", content = @Content),
			@ApiResponse(description = "Não encontrado",		 responseCode = "404", content = @Content)})	
	public ResponseEntity<FuncionalidadeDTO> create(@RequestBody FuncionalidadeDTO func) {
		
		return funcionalidade.save(func);
		
	}
	
	@PutMapping
	@Operation(summary = "Realiza atualização no cadastro de funcionalidades",
	responses = {
		@ApiResponse(description = "Sucesso na requisição", responseCode = "200", content = @Content),
		@ApiResponse(description = "Atualizado com sucesso", 	responseCode = "201" , content =  @Content),					
		@ApiResponse(description = "Requisição mal formada", 	responseCode = "400" , content =  @Content),
		@ApiResponse(description = "Você não tem permissão para acessar este recurso", responseCode = "401" , content =  @Content),
		@ApiResponse(description = "Acesso não permitido",		responseCode = "403" , content =  @Content),
		@ApiResponse(description = "Não encontrado", 			responseCode = "404" , content =  @Content),
		@ApiResponse(description = "Foi gerada uma exceção", 	responseCode = "500" , content =  @Content)})
		public ResponseEntity<FuncionalidadeDTO> update(@RequestBody FuncionalidadeDTO func) {
			funcionalidade.update(func);
			return ResponseEntity.ok().build();
			
		}
	
	@DeleteMapping(value = "/{id}", 
			 produces = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML},
			 consumes = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
	public ResponseEntity<FuncionalidadeDTO> delete(@PathVariable @Valid 
			@ApiParam(name = "id da funcionalidade", example = "PDF", value = "PDF") String id) {
		
		funcionalidade.delete(id);
		return ResponseEntity.noContent().build();
		
	}
}
