package br.com.seg.entity;

/*
 * Cadastro de módulos 
 *  Hierarquia - Modulos >> Funcionalidades
 */


import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

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
