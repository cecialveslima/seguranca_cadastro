package br.com.seg.entity;

/*
 * Associação de grupos e usuários 
 * 
 */

import java.io.Serializable;

import br.com.seg.embeddeable.UserGroupEmbeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UserGroup")
public class UserGroup implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserGroupEmbeddable UserGroup;
	
	@ManyToOne
	@JoinColumn(name = "group_id", insertable = false, updatable = false)
	private Group GroupId;
	
	@ManyToOne 
	@JoinColumn(name = "for_id" , insertable = false, updatable = false)
	private Fornecedor CliFornecedor; 
	
	@ManyToOne 
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;
	
	
}
