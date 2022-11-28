package br.com.seg.controller;

import java.util.List;

import javax.validation.Valid;

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

import br.com.seg.dto.GroupDTO;
import br.com.seg.service.GruposService;
import br.com.seg.utils.MediaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/grupos/v1")
@Api(value = "Manutenção de Grupos", tags = {"Manutenção de grupos"})
public class GroupController {
	
	@Autowired
	private GruposService grupos;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML})
	@Operation(summary = "Lista todos os grupos cadastrados" ,
	responses = {
			@ApiResponse(description = "Sucesso na requisição", responseCode = "200", content = @Content),
			@ApiResponse(description = "Credenciais inválidas", responseCode = "401", content = @Content),
			@ApiResponse(description = "Acesso não permitido",  responseCode = "403", content = @Content),
			@ApiResponse(description = "Não encontrado",		responseCode = "404", content = @Content)})	
	public ResponseEntity<List<GroupDTO>> findAll() throws Exception {
		List<GroupDTO> grupo = grupos.findAll();
		return ResponseEntity.ok(grupo);
	}
	
	@GetMapping(value = "/{id}" , produces = {MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML})
	@Operation(summary ="Localiza grupo por ID",
	responses = {
			@ApiResponse(description ="Sucesso na requisição" , responseCode = "200", content = @Content),
			@ApiResponse(description ="Credenciais inválidas" , responseCode = "401", content = @Content),
			@ApiResponse(description ="Acesso não permitido" ,  responseCode = "403", content = @Content),
			@ApiResponse(description ="Não encontrado" , 		responseCode = "404", content = @Content)})	
	public ResponseEntity<GroupDTO> findById(@PathVariable @Valid @ApiParam(name = "ID do grupo" ,  example = "CTB" , value = "CTB") String id) throws Exception {
		return grupos.findById(id);
	}
	 
	@PostMapping(produces = {MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML}, 
				 consumes = {MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML})
	@Operation(summary = "Cria novo grupo",
	responses = {
			@ApiResponse(description = "Sucesso na criação", 	 responseCode = "200", content = @Content),
			@ApiResponse(description = "Atualizado com sucesso", responseCode = "200", content = @Content),
			@ApiResponse(description = "Credenciais inválidas",  responseCode = "401", content = @Content),
			@ApiResponse(description = "Acesso não permitido",   responseCode = "403", content = @Content),
			@ApiResponse(description = "Não encontrado",		 responseCode = "404", content = @Content)})	
	public ResponseEntity<GroupDTO> create(@RequestBody GroupDTO grupo) throws Exception {
		return grupos.create(grupo);
	} 
	
	@PutMapping(produces = {MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML}, 
				consumes = {MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML})
	@Operation(summary = "Realiza atualização no cadastro de grupos",
	responses = {
		@ApiResponse(description = "Sucesso na requisição", responseCode = "200", content = @Content),
		@ApiResponse(description = "Atualizado com sucesso", 	responseCode = "201" , content =  @Content),					
		@ApiResponse(description = "Requisição mal formada", 	responseCode = "400" , content =  @Content),
		@ApiResponse(description = "Você não tem permissão para acessar este recurso", responseCode = "401" , content =  @Content),
		@ApiResponse(description = "Acesso não permitido",		responseCode = "403" , content =  @Content),
		@ApiResponse(description = "Não encontrado", 			responseCode = "404" , content =  @Content),
		@ApiResponse(description = "Foi gerada uma exceção", 	responseCode = "500" , content =  @Content)})	
	public ResponseEntity<GroupDTO> atualizar(@RequestBody GroupDTO grupo) throws Exception {
		grupos.atualizar(grupo);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Remove um grupo", 
	responses = {
		@ApiResponse(description = "Sucesso na requisição", 	responseCode = "204" , content =  @Content),
		@ApiResponse(description = "Requisição mal formada", 	responseCode = "400" , content =  @Content),
		@ApiResponse(description = "Você não tem permissão para acessar este recurso", responseCode = "401" , content =  @Content),
		@ApiResponse(description = "Não autorizado", 			responseCode = "403" , content =  @Content),
		@ApiResponse(description = "Metodo não autorizado", 	responseCode = "404" , content =  @Content),
		@ApiResponse(description = "Foi gerada uma exceção", 	responseCode = "500" , content =  @Content)})		
	public ResponseEntity<GroupDTO> deletar(@PathVariable @Valid @ApiParam(name = "ID do grupo" ,  example = "CTB" , value = "CTB") String id) throws Exception  {
		grupos.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
