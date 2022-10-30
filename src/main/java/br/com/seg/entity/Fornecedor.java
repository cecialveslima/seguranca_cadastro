package br.com.seg.entity;

/*
 * Cadastro de empresas/fornecedores
 * Base para o restante do cadastramento
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "Fornecedor")
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "for_id")
	private long ForId; //Campo auto-incremento
	
	
	@Column(name ="for_nom" , length = 60 , nullable = false)
	private String NameFor;
	
	@Column(name = "for_sts", nullable= false)
	private boolean StatusFor;
	
	@Column(name = "for_mail", unique = true, nullable = false)
	private String email;

}
