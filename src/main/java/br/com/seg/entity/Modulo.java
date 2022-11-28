package br.com.seg.entity;

/*
 * Cadastro de módulos 
 *  Hierarquia - Modulos >> Funcionalidades
 */


import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "modulo")
public class Modulo {
	
	@Id
	@Column(name = "module_id", length= 20)
	private String moduleId;
	
	@Column(name = "mod_name", length=60, nullable= false)
	private String moduleName;
	
	@Column(name = "mod_sts", nullable = false)
	private boolean moduleStatus;
	
	//Cria a tabela de associação USER_GROUP
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "moduleFuncionality", joinColumns = {
			@JoinColumn(name = "module_id", nullable = false)
	},
	inverseJoinColumns = {
			@JoinColumn(name = "module_funid", nullable = false)
	})
	private List<Funcionalidade> user_group; //Coloquei o mesmo nome *opcional

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public boolean isModuleStatus() {
		return moduleStatus;
	}

	public void setModuleStatus(boolean moduleStatus) {
		this.moduleStatus = moduleStatus;
	}

	public List<Funcionalidade> getUser_group() {
		return user_group;
	}

	public void setUser_group(List<Funcionalidade> user_group) {
		this.user_group = user_group;
	}

	
}

/*
@Transactional(isolation = Isolation.READ_COMMITTED)
Os tipos possíveis para o isolation são:

ISOLATION_DEFAULT: Nível de isolação padrão.
ISOLATION_READ_COMMITTED: Previne apenas “dirty reads”.
ISOLATION_READ_UNCOMMITED: Indica que “dirty reads”, “non-repeatable reads” e “phatom reads” podem ocorrer, ou seja, não serão impedidos.
ISOLATION_REPEATABLE_READ: Previne apenas “dirty reads” e “non-repeatable reads”.
ISOLATION_SERIALIZABLE: Previne “dirty reads”, “non-repeatable reads” e “phatom reads”.*/
