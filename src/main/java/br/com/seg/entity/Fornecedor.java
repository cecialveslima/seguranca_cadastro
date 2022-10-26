package br.com.seg.entity;

/*
 * Cadastro de empresas/fornecedores
 * Base para o restante do cadastramento
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



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

	/*@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "UserGroup" , joinColumns = {
			@JoinColumn(name = "for_id", nullable = false)
	},
	inverseJoinColumns = {
			@JoinColumn(name = "user_id", nullable = false)
	})
	private List<User> users;
*/
}
