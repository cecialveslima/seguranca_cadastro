package br.com.seg.entity;


/*
 * Criação de agrupamento de modulos e funcionalidades
 * para associação aos grupos
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name ="grupos")
public class Group {

	@Id
	@Column(name = "group_id")	
	private String groupId;
	
	//Muitos Grupos para um fornecedor- criando ao FK 
	//(Caso remova: Could not determine recommended JdbcType for `br.com.seg.entity.Fornecedor`)
	@ManyToOne 
	@JoinColumn(name = "for_id")
	private Fornecedor cliFornecedor;
	
	@Column
	private String groupDsc;
	
	@Column
	private boolean groupSts;

	public Group() {
		
	}
	public Group(String groupId, Fornecedor cliFornecedor, String groupDsc, boolean groupSts) {
		super();
		this.groupId = groupId;
		this.cliFornecedor = cliFornecedor;
		this.groupDsc = groupDsc;
		this.groupSts = groupSts;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Fornecedor getCliFornecedor() {
		return cliFornecedor;
	}

	public void setCliFornecedor(Fornecedor cliFornecedor) {
		this.cliFornecedor = cliFornecedor;
	}

	public String getGroupDsc() {
		return groupDsc;
	}

	public void setGroupDsc(String groupDsc) {
		this.groupDsc = groupDsc;
	}

	public boolean isGroupSts() {
		return groupSts;
	}

	public void setGroupSts(boolean groupSts) {
		this.groupSts = groupSts;
	}


}
