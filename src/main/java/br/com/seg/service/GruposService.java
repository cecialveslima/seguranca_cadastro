package br.com.seg.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.seg.dto.GroupDTO;
import br.com.seg.entity.Fornecedor;
import br.com.seg.entity.Group;
import br.com.seg.repository.FornecedorRepository;
import br.com.seg.repository.GroupRepository;
import br.com.seg.service.exception.ObjetoNulo;
import br.com.seg.service.exception.ResourceNotFoundException;
import br.com.seg.service.exception.ResourceNotFound;

@Service
public class GruposService {

	@Autowired
	GroupRepository groupRepository;
	
	@Autowired
	FornecedorRepository fornecedor;
	
	@Transactional
	public List<GroupDTO> findAll() {
		 List<Group> grupos = groupRepository.findAll();
		return grupos.stream().map(x -> new GroupDTO(x)).collect(Collectors.toList());
	}

	@Transactional
	public ResponseEntity<GroupDTO> findById(String id) {
		Group grupos = groupRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("ID: " + id  + " não encontrado"));
		
		GroupDTO dto = new GroupDTO(grupos);
		return ResponseEntity.ok(dto);
	}

	@Transactional
	public ResponseEntity<GroupDTO> create(GroupDTO grupo) {
		Group newGroup = new Group();
		
		List<Group> lists = groupRepository.findBygroupId(grupo.getGroupId());
		
		for(Group lista:lists) {
			if(lista.getGroupId().equals(grupo.getGroupId())) {
				throw new ResourceNotFoundException("ID já existe na base");
			}
		}
		
		validaDados(grupo, newGroup, "INS");
		
		GroupDTO dto = new GroupDTO(newGroup);
		groupRepository.save(newGroup);
		return new ResponseEntity<GroupDTO>(dto, HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<GroupDTO>  atualizar(GroupDTO grupo) {
		if(grupo.getGroupId() == null ) {
			throw new ObjetoNulo("ID do grupo deve ser informado");
		}
		
		Group newGroup = groupRepository.findById(grupo.getGroupId())
				.orElseThrow(() -> new ResourceNotFoundException("ID não encontrado para atualizar"));
		
		validaDados(grupo, newGroup, "UPD");
		GroupDTO dto = new GroupDTO(newGroup);
		groupRepository.save(newGroup);
		return new ResponseEntity<GroupDTO>(dto, HttpStatus.OK);

	}
	
	@Transactional
	public void deletar(String id) {
		Group grupos = groupRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("ID: " + id  + " não encontrado"));
		
		groupRepository.delete(grupos);
	}

	private void validaDados(GroupDTO grupo , Group newGroup, String Mode) {
		//ID, fornecedor, descrição e status
		
		Fornecedor forn = fornecedor.findById(grupo.getCliFornecedor())
		.orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado na base"));
		
		newGroup.setCliFornecedor(forn);
		newGroup.setGroupSts(grupo.isGroupSts());
		
		if (grupo.getGroupId() == null) {
			if (Mode == "INS") {
				throw new ObjetoNulo("Obrigatório informar o ID do Grupo");
			} else  {
				newGroup.setGroupId(newGroup.getGroupId());
			}
		} else  {
			newGroup.setGroupId(grupo.getGroupId());
		}
		
		if(grupo.getGroupDsc() == null) {
			if (Mode =="INS") {
				throw new ObjetoNulo("A descrição do módulo não pode ser vazia");
			} else {
				newGroup.setGroupDsc(newGroup.getGroupDsc());
			}
		} else  {
			newGroup.setGroupDsc(grupo.getGroupDsc());
		}
	}

}
