package br.com.seg.embeddeable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class GroupModuleFuncionalityEmb {
	@Column(name = "for_id")
	private long ForId;
	
	@Column(name = "group_id")
	private String GroupForId;
	
	@Column(name = "module_id")
	private String ModuleId;

	@Column(name = "fun_id")
	private String FunId;
}
