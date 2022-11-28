package br.com.seg.entity;

import java.util.Objects;

/*
 * Cadastro de funcionalidades dos módulos
 * Exemplo: Incluir, alterar, excluir, visualizar CSV, PDF..
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "funcionalidade")
public class Funcionalidade {
	
	@Id
	@Column(name = "fun_id", length= 30)
	private String funId;
	
	@Column(name = "fun_name", length = 50)
	private String funName;
	
	@Column(name = "fun_sts")
	private boolean funStatus;
	
	@Column(name="path_icon_able", length=60)
	private String funPathIconAble; //Caminho da imagem para ativos
	
	@Column(name ="path_icon_disable", length=60)
	private String funPathIconDisable; //Caminho da imagem para inativos

	public String getFunId() {
		return funId;
	}

	public void setFunId(String funId) {
		this.funId = funId;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public boolean isFunStatus() {
		return funStatus;
	}

	public void setFunStatus(boolean funStatus) {
		this.funStatus = funStatus;
	}

	public String getFunPathIconAble() {
		return funPathIconAble;
	}

	public void setFunPathIconAble(String funPathIconAble) {
		this.funPathIconAble = funPathIconAble;
	}

	public String getFunPathIconDisable() {
		return funPathIconDisable;
	}

	public void setFunPathIconDisable(String funPathIconDisable) {
		this.funPathIconDisable = funPathIconDisable;
	}

	public Funcionalidade() {

	}

	public Funcionalidade(String funId, String funName, boolean funStatus, String funPathIconAble,
			String funPathIconDisable) {
		super();
		this.funId = funId;
		this.funName = funName;
		this.funStatus = funStatus;
		this.funPathIconAble = funPathIconAble;
		this.funPathIconDisable = funPathIconDisable;
	}

	@Override
	public int hashCode() {
		return Objects.hash(funId, funName, funPathIconAble, funPathIconDisable, funStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionalidade other = (Funcionalidade) obj;
		return Objects.equals(funId, other.funId) && Objects.equals(funName, other.funName)
				&& Objects.equals(funPathIconAble, other.funPathIconAble)
				&& Objects.equals(funPathIconDisable, other.funPathIconDisable) && funStatus == other.funStatus;
	}

	
}
