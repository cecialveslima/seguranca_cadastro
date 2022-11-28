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
public class GroupModuleFuncionality  implements Serializable {

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

	public GroupModuleFuncionality() {
		
	}
	public GroupModuleFuncionality(GroupModuleFuncionalityEmb groupModuleFuncionality, Fornecedor forId, Group groupId,
			Modulo moduleId, Funcionalidade funId) {
		super();
		GroupModuleFuncionality = groupModuleFuncionality;
		ForId = forId;
		GroupId = groupId;
		ModuleId = moduleId;
		FunId = funId;
	}
	public GroupModuleFuncionalityEmb getGroupModuleFuncionality() {
		return GroupModuleFuncionality;
	}
	public void setGroupModuleFuncionality(GroupModuleFuncionalityEmb groupModuleFuncionality) {
		GroupModuleFuncionality = groupModuleFuncionality;
	}
	public Fornecedor getForId() {
		return ForId;
	}
	public void setForId(Fornecedor forId) {
		ForId = forId;
	}
	public Group getGroupId() {
		return GroupId;
	}
	public void setGroupId(Group groupId) {
		GroupId = groupId;
	}
	public Modulo getModuleId() {
		return ModuleId;
	}
	public void setModuleId(Modulo moduleId) {
		ModuleId = moduleId;
	}
	public Funcionalidade getFunId() {
		return FunId;
	}
	public void setFunId(Funcionalidade funId) {
		FunId = funId;
	}
	
	


}
