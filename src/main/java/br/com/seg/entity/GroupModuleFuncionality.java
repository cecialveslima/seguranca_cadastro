package br.com.seg.entity;

/*
 * Grupos x Módulos x Funcionalidades
 * Agrupamento de permissões
 */

import java.io.Serializable;

import br.com.seg.embeddeable.GroupModuleFuncionalityEmb;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


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
