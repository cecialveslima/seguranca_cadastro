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

	public GroupModuleFuncionalityEmb() {
		
	}
	
	public GroupModuleFuncionalityEmb(long forId, String groupForId, String moduleId, String funId) {
		super();
		ForId = forId;
		GroupForId = groupForId;
		ModuleId = moduleId;
		FunId = funId;
	}

	public long getForId() {
		return ForId;
	}

	public void setForId(long forId) {
		ForId = forId;
	}

	public String getGroupForId() {
		return GroupForId;
	}

	public void setGroupForId(String groupForId) {
		GroupForId = groupForId;
	}

	public String getModuleId() {
		return ModuleId;
	}

	public void setModuleId(String moduleId) {
		ModuleId = moduleId;
	}

	public String getFunId() {
		return FunId;
	}

	public void setFunId(String funId) {
		FunId = funId;
	}
	
	
	
}
