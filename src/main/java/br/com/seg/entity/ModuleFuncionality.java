package br.com.seg.entity;

/*
 * Associação de módulos e funcionalidades
 */

import javax.persistence.EmbeddedId;

import br.com.seg.embeddeable.ModuleFuncionalityEmb;
import jakarta.persistence.Column;

public class ModuleFuncionality {

	@EmbeddedId
	private ModuleFuncionalityEmb moduleFuncionality;
	
	//@ManyToOne
	@Column(name = "module_funid")
	private String ModuleFunId;
	
	//@ManyToOne
	@Column(name = "fun_id")
	private String FunId;
}
