package br.com.seg.embeddeable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ModuleFuncionalityEmb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "module_funid")
	private String ModuleFunId;
	
	@Column(name = "fun_id")
	private String FunId;

}
