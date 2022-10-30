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
	private String ModuleId;
	
	@Column(name = "mod_name", length=60, nullable= false)
	private String ModuleName;
	
	@Column(name = "mod_sts", nullable = false)
	private boolean ModuleStatus;
	
	//Cria a tabela de associação USER_GROUP
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ModuleFuncionality", joinColumns = {
			@JoinColumn(name = "module_id", nullable = false)
	},
	inverseJoinColumns = {
			@JoinColumn(name = "module_funid", nullable = false)
	})
	private List<Funcionalidade> user_group; //Coloquei o mesmo nome *opcional

}
