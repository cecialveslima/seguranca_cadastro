package br.com.seg.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.seg.dto.FuncionalidadeDTO;
import br.com.seg.entity.Funcionalidade;
import br.com.seg.repository.FuncionalidadeRepository;
import br.com.seg.service.exception.ResourceNotFoundException;
import br.com.seg.service.exception.ResourceNotFound;

@Service
public class FuncionalidadeService {

	@Autowired
	private FuncionalidadeRepository func;
	
	
	
	@Transactional
	public ResponseEntity<Funcionalidade> findbyId(@Valid String id) {
		Funcionalidade funcs = func.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não existem registros que cumpram a condição"));
		return ResponseEntity.ok(funcs);
	}

	@Transactional
	public ResponseEntity<List<Funcionalidade>> findAll() {
		List<Funcionalidade> funcs = func.findAll();
		
		
		if(funcs.isEmpty() || funcs == null) {
			throw new ResourceNotFoundException("Não existem registros na base");
		}
		return new ResponseEntity<List<Funcionalidade>>(funcs, HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<FuncionalidadeDTO> save(FuncionalidadeDTO funcs) {
		if (funcs.getFunId() == null) {
			throw new ResourceNotFound("O ID do módulo não pode ser nulo");
		}
		
		List<Funcionalidade> listaFunc  = func.findByfunId(funcs.getFunId());
		
		for (Funcionalidade lista: listaFunc) {
			throw new ResourceNotFoundException("O ID: " + lista.getFunId() + " já existe" );
		}
		
		Funcionalidade  funcionalidade = new Funcionalidade();
		validaDados(funcs,funcionalidade,"INS");
		func.save(funcionalidade);
		return new ResponseEntity<FuncionalidadeDTO>(HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<FuncionalidadeDTO> update(FuncionalidadeDTO funcionalidade) {
		if(funcionalidade.getFunId() == null) {
			 throw new ResourceNotFoundException("O ID não pode ser nulo");
		}
		String id = funcionalidade.getFunId();
		
		Funcionalidade fun = func.findById(id)
				.orElseThrow(() -> new ResourceNotFound("ID não encontrado"));
		
		validaDados(funcionalidade,fun,"UPD");	
		func.save(fun);
		return new ResponseEntity<FuncionalidadeDTO>(HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<Object> delete(@Valid String id) {
		Funcionalidade funcs = func.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado"));
		func.delete(funcs);
		return ResponseEntity.noContent().build();
		
	}


	private void validaDados(FuncionalidadeDTO funcionalidade,Funcionalidade newFunc, String Mode) {

		newFunc.setFunId(funcionalidade.getFunId());
		if (Mode == "INS" && funcionalidade.isFunStatus() == false) {
			throw new ResourceNotFound("O status da funcionalidade não pode iniciar inativo");
		} else {
			newFunc.setFunStatus(funcionalidade.isFunStatus());
		}
		
		if(funcionalidade.getFunName() == null) {
			if (Mode == "UPD") {
				newFunc.setFunName(newFunc.getFunName());
			} else  {
				throw new ResourceNotFound("A descrição da funcionalidade deve ser informada");
			}
		} else {
			newFunc.setFunName(funcionalidade.getFunName());
		}
		
		if (funcionalidade.getFunPathIconAble() == null) {
			if (Mode == "UPD") {
				newFunc.setFunPathIconAble(newFunc.getFunPathIconAble());
			} else {
				throw new ResourceNotFound("O ícone para funcionalidade ativa deve ser informado");
			}
		} else {
			newFunc.setFunPathIconAble(funcionalidade.getFunPathIconAble());
		}
		
		if (funcionalidade.getFunPathIconDisable() == null) {
			if (Mode == "UPD") {
				newFunc.setFunPathIconDisable(newFunc.getFunPathIconDisable());
			} else {
				throw new ResourceNotFound("O ícone para funcionalidade inativa deve ser informado");
			}
		} else {
			newFunc.setFunPathIconDisable(funcionalidade.getFunPathIconDisable());
		}
		
	}
}
