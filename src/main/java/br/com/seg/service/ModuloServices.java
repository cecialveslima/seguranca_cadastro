package br.com.seg.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

//import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.seg.dto.ModuloDTO;
import br.com.seg.entity.Modulo;
import br.com.seg.repository.ModuleRepository;
import br.com.seg.service.exception.ResourceNotFoundException;
import br.com.seg.service.exception.ResourceNotFound;

@Service
public class ModuloServices {

	@Autowired
	private ModuleRepository modulo;
	
	//private Logger logger = Logger.getLogger(ModuloServices.class.getName());

	public ResponseEntity<ModuloDTO> findById(@Valid String id) {
		Modulo module = modulo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Módulo não encontrado com esse id"));
		ModuloDTO mod = new ModuloDTO(module);
		return ResponseEntity.ok(mod);
	}

	public List<ModuloDTO> findAll() {
		List<Modulo> list = modulo.findAll();
		return list.stream().map(x -> new ModuloDTO(x)).collect(Collectors.toList());
	}

	@Transactional
	public ResponseEntity<ModuloDTO> create(ModuloDTO mod) {
		if (mod.getModuleId() == null ) {
			throw new ResourceNotFound("O ID do módulo não pode ser nulo");
		}
		
		List<Modulo> modExist = modulo.findBymoduleId(mod.getModuleId());
	
		for (Modulo modulox : modExist) {
			if (!modulox.getModuleId().isEmpty()) {
				throw new ResourceNotFound("O ID " + modulox.getModuleId() + " já existe na base");
			}
		}
		Modulo newModulo = new Modulo();
		newModulo.setModuleId(mod.getModuleId());
		newModulo.setModuleName(mod.getModuleName());
		newModulo.setModuleStatus(mod.isModuleStatus());
		
		validaDados(mod, newModulo,"INS");
		
		modulo.save(newModulo);
		return new ResponseEntity<ModuloDTO>(HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<ModuloDTO> update(ModuloDTO mod) {
		if (mod.getModuleId() == null ) {
			throw new ResourceNotFound("O ID do módulo não pode ser nulo");
		}
		
		String id = mod.getModuleId();
		Modulo module = modulo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Módulo não encontrado para atualização"));
		
		validaDados(mod, module,"UPD");
		
		module.setModuleId(mod.getModuleId());
		modulo.save(module);
		ModuloDTO modret = new ModuloDTO(module);
		return new ResponseEntity<>(modret, HttpStatus.OK);

	}

	@Transactional
	public void delete(@Valid String id) {
		
		Modulo module = modulo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Módulo não encontrado"));

		modulo.delete(module);

	}

	private void validaDados(ModuloDTO mod , Modulo modNew, String Mode) {

		if(Mode == "INS" && mod.isModuleStatus() == false) {
			throw new ResourceNotFound("O status do módulo não pode iniciar com status desabilitado");
		} else {
			modNew.setModuleStatus(mod.isModuleStatus());
		}

			
		modNew.setModuleName(modNew.getModuleName());
		if (mod.getModuleName() == null) {
			if (Mode == "INS") {
				throw new ResourceNotFound("O nome do módulo não pode ser nulo");
			} else {
				modNew.setModuleName(modNew.getModuleName());
			}
		} else {
			modNew.setModuleName(mod.getModuleName());

			if (mod.getModuleId() == null) {
				throw new ResourceNotFound("O ID do módulo não pode ser nulo");
			} else {
				if (mod.getModuleName().isEmpty()) {
					throw new ResourceNotFound("O nome do módulo deve ser informado");
				} else {
					if (mod.getModuleId().isEmpty()) {
						throw new ResourceNotFound("O ID do módulo deve ser informado");
					}
				}
			}
		}
	}
}
