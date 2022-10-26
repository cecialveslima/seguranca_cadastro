package br.com.seg.entity;

/*
 * Grupos x Módulos x Funcionalidades
 * Agrupamento de permissões
 */

import java.io.Serializable;

import br.com.seg.embeddeable.GroupModuleFuncionalityEmb;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "GroupModuleFuncionality")
public class GroupModuleFuncionality implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GroupModuleFuncionalityEmb GroupModuleFuncionality;
	
	@ManyToOne //Fornecedor
	@JoinColumn(name = "for_id", insertable = false, updatable = false)
	private Fornecedor ForId;

	@ManyToOne //Grupo
	@JoinColumn(name = "group_id", insertable = false, updatable = false)
	private Group GroupId;

	@ManyToOne //Modulo
	@JoinColumn(name = "module_id", insertable = false, updatable = false)
	private Modulo ModuleId;
	
	@ManyToOne //Funcionalidade
	@JoinColumn(name = "fun_id", insertable = false, updatable = false)
	private Funcionalidade FunId;


}
