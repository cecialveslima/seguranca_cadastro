package br.com.seg.embeddeable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GroupModuleFuncionalityEmb implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "for_id")
	private long ForId;
	
	@Column(name = "group_id")
	private String GroupForId;
	
	@Column(name = "module_id")
	private String ModuleId;

	@Column(name = "fun_id")
	private String FunId;
}
