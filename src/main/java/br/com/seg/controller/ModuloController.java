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
import org.springframework.web.bind.annotation.RequestBody; //Monta JSON na requisição
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.seg.dto.ModuloDTO;
import br.com.seg.service.ModuloServices;
import br.com.seg.utils.MediaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/modulo/v1")
@Api(value = "Manutenção dos módulos" , tags = {"Manutenção de módulo"})
public class ModuloController {

	@Autowired
	private ModuloServices modService;
	
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML})
	@Operation(summary = "Lista todos os módulos cadastrados" ,
	responses = {
			@ApiResponse(description = "Sucesso na requisição", responseCode = "200", content = @Content),
			@ApiResponse(description = "Credenciais inválidas", responseCode = "401", content = @Content),
			@ApiResponse(description = "Acesso não permitido",  responseCode = "403", content = @Content),
			@ApiResponse(description = "Não encontrado",		responseCode = "404", content = @Content)})	
	public ResponseEntity<List<ModuloDTO>> findAll() throws Exception {
		List<ModuloDTO> modulos = modService.findAll();
		return ResponseEntity.ok(modulos);
		
	}
	
	@GetMapping(value = "/{id}" , produces = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
	@Operation(summary ="Localiza módulo por ID",
	responses = {
			@ApiResponse(description ="Sucesso na requisição" , responseCode = "200", content = @Content),
			@ApiResponse(description ="Credenciais inválidas" , responseCode = "401", content = @Content),
			@ApiResponse(description ="Acesso não permitido" ,  responseCode = "403", content = @Content),
			@ApiResponse(description ="Não encontrado" , 		responseCode = "404", content = @Content)})
	public ResponseEntity<ModuloDTO> findbyId(@PathVariable @Valid 
			@ApiParam(name = "ID do módulo" ,  example = "CADUSER" , value = "caduser") String id) throws Exception {
		return modService.findById(id);
		
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} ,
				 consumes = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
	@Operation(summary = "Cria novo módulo",
			responses = {
					@ApiResponse(description = "Sucesso na criação", 	 responseCode = "200", content = @Content),
					@ApiResponse(description = "Atualizado com sucesso", responseCode = "200", content = @Content),
					@ApiResponse(description = "Credenciais inválidas",  responseCode = "401", content = @Content),
					@ApiResponse(description = "Acesso não permitido",   responseCode = "403", content = @Content),
					@ApiResponse(description = "Não encontrado",		 responseCode = "404", content = @Content)})
	public ResponseEntity<ModuloDTO> create(@RequestBody @Valid ModuloDTO modulo) throws Exception {
		return modService.create(modulo);
		
	}

	@PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Operation(summary = "Realiza atualização no cadastro de módulos",
	responses = {
		@ApiResponse(description = "Sucesso na requisição", responseCode = "200", content = @Content),
		@ApiResponse(description = "Atualizado com sucesso", 	responseCode = "201" , content =  @Content),					
		@ApiResponse(description = "Requisição mal formada", 	responseCode = "400" , content =  @Content),
		@ApiResponse(description = "Você não tem permissão para acessar este recurso", responseCode = "401" , content =  @Content),
		@ApiResponse(description = "Acesso não permitido",		responseCode = "403" , content =  @Content),
		@ApiResponse(description = "Não encontrado", 			responseCode = "404" , content =  @Content),
		@ApiResponse(description = "Foi gerada uma exceção", 	responseCode = "500" , content =  @Content)})
	public ResponseEntity<ModuloDTO> update(@RequestBody @Valid ModuloDTO modulo) throws Exception {
		modService.update(modulo);
		return ResponseEntity.ok().build();
		
	}	
	
	@DeleteMapping(value ="/{id}" , 
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Operation(summary = "Remove um módulo", 
	responses = {
		@ApiResponse(description = "Sucesso na requisição", 	responseCode = "204" , content =  @Content),
		@ApiResponse(description = "Requisição mal formada", 	responseCode = "400" , content =  @Content),
		@ApiResponse(description = "Você não tem permissão para acessar este recurso", responseCode = "401" , content =  @Content),
		@ApiResponse(description = "Não autorizado", 			responseCode = "403" , content =  @Content),
		@ApiResponse(description = "Foi gerada uma exceção", 	responseCode = "500" , content =  @Content)})	
		public ResponseEntity<ModuloDTO> delete(@PathVariable @Valid 
			@ApiParam(name = "ID do módulo" ,  example = "CADUSER" , value = "caduser")  String id) throws Exception {
		modService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
}
