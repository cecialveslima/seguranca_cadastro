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

import br.com.seg.dto.UserDTO;
import br.com.seg.service.UserService;
import br.com.seg.utils.MediaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/usuario/v1")
//@Tag(description = "Manutenção de Usuários" , name = "Manutenção de Usuários")
@Api(value = "Manutenção de Usuários", tags= {"Manutenção de Usuários/Logins"})
public class UserController {
	
	@Autowired
	private UserService myUser;
	

	@GetMapping(value ="/{id}" , 
			produces = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
	@Operation(summary ="Localiza usuário por ID",
		responses = {
				@ApiResponse(description ="Sucesso na requisição" , responseCode = "200", content = @Content),
				@ApiResponse(description ="Credenciais inválidas" , responseCode = "401", content = @Content),
				@ApiResponse(description ="Acesso não permitido" ,  responseCode = "403", content = @Content),
				@ApiResponse(description ="Não encontrado" , 		responseCode = "404", content = @Content)})
	public ResponseEntity<UserDTO> findById(@PathVariable @Valid @ApiParam(name ="Id do Usuário", example = "123Login" ,  value = "Identificador") String id) 
			throws Exception {
		return myUser.findById(id);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML})
	@Operation(summary = "Lista todos os usuários cadastrados" ,
			responses = {
					@ApiResponse(description = "Sucesso na requisição", responseCode = "200", content = @Content),
					@ApiResponse(description = "Credenciais inválidas", responseCode = "401", content = @Content),
					@ApiResponse(description = "Acesso não permitido",  responseCode = "403", content = @Content),
					@ApiResponse(description = "Não encontrado",		responseCode = "404", content = @Content)})	
	
	public ResponseEntity<List<UserDTO>> findAll() throws Exception{
		List<UserDTO> list = myUser.findAll();
		return ResponseEntity.ok(list);
	}
	
	@PostMapping(
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML} ,
			consumes = {MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
	@Operation(summary = "Cria novo usuário",
			responses = {
					@ApiResponse(description = "Sucesso na criação", 	 responseCode = "200", content = @Content),
					@ApiResponse(description = "Atualizado com sucesso", responseCode = "200", content = @Content),
					@ApiResponse(description = "Credenciais inválidas",  responseCode = "401", content = @Content),
					@ApiResponse(description = "Acesso não permitido",   responseCode = "403", content = @Content),
					@ApiResponse(description = "Não encontrado",		 responseCode = "404", content = @Content)})
	public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO usuario) throws Exception {
		return myUser.create(usuario);
	}
	
	@PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
				consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Operation(summary = "Realiza atualização no cadastro de usuário",
	 responses = {
			@ApiResponse(description = "Sucesso na requisição", responseCode = "200", content = @Content),
			@ApiResponse(description = "Atualizado com sucesso", 	responseCode = "201" , content =  @Content),					
			@ApiResponse(description = "Requisição mal formada", 	responseCode = "400" , content =  @Content),
			@ApiResponse(description = "Você não tem permissão para acessar este recurso", responseCode = "401" , content =  @Content),
			@ApiResponse(description = "Acesso não permitido",		responseCode = "403" , content =  @Content),
			@ApiResponse(description = "Não encontrado", 			responseCode = "404" , content =  @Content),
			@ApiResponse(description = "Foi gerada uma exceção", 	responseCode = "500" , content =  @Content)})
	public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO usuario) throws Exception {
		myUser.update(usuario);
		return ResponseEntity.ok().build();
				
	}
	
	@DeleteMapping(value ="/{id}" , 
					produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
					consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Operation(summary = "Remove um usuário", 
	responses = {
			@ApiResponse(description = "Sucesso na requisição", 	responseCode = "204" , content =  @Content),
			@ApiResponse(description = "Requisição mal formada", 	responseCode = "400" , content =  @Content),
			@ApiResponse(description = "Você não tem permissão para acessar este recurso", responseCode = "401" , content =  @Content),
			@ApiResponse(description = "Não autorizado", 			responseCode = "403" , content =  @Content),
			@ApiResponse(description = "Foi gerada uma exceção", 	responseCode = "500" , content =  @Content)})		
	public ResponseEntity<UserDTO> delete(@PathVariable @Valid @ApiParam(name ="Id do Usuário", example = "123Login" ,  value = "Identificador") String id)
			throws Exception  {
		myUser.delete(id);
		return ResponseEntity.noContent().build();
		
	}

	
}
