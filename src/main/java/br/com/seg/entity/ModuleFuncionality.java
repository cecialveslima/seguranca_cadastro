package br.com.seg.entity;

/*
 * Associação de módulos e funcionalidades
 */

import javax.persistence.EmbeddedId;

import br.com.seg.embeddeable.ModuleFuncionalityEmb;
import javax.persistence.Column;

public class ModuleFuncionality {

	@EmbeddedId
	private ModuleFuncionalityEmb moduleFuncionality;
	
	//@ManyToOne
	@Column(name = "module_funid")
	private String ModuleFunId;
	
	//@ManyToOne
	@Column(name = "fun_id")
	private String FunId;

	
	public ModuleFuncionality() {
		
	}
	public ModuleFuncionality(ModuleFuncionalityEmb moduleFuncionality, String moduleFunId, String funId) {
		super();
		this.moduleFuncionality = moduleFuncionality;
		ModuleFunId = moduleFunId;
		FunId = funId;
	}

	public ModuleFuncionalityEmb getModuleFuncionality() {
		return moduleFuncionality;
	}

	public void setModuleFuncionality(ModuleFuncionalityEmb moduleFuncionality) {
		this.moduleFuncionality = moduleFuncionality;
	}

	public String getModuleFunId() {
		return ModuleFunId;
	}

	public void setModuleFunId(String moduleFunId) {
		ModuleFunId = moduleFunId;
	}

	public String getFunId() {
		return FunId;
	}

	public void setFunId(String funId) {
		FunId = funId;
	}
	
	
}
