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
	private String GroupId;
	
	//Muitos Grupos para um fornecedor- criando ao FK 
	//(Caso remova: Could not determine recommended JdbcType for `br.com.seg.entity.Fornecedor`)
	@ManyToOne 
	@JoinColumn(name = "for_id")
	private Fornecedor CliFornecedor;
	
	@Column
	private String GroupDsc;
	
	@Column
	private boolean GroupSts;

	public String getGroupId() {
		return GroupId;
	}
}
