package br.com.seg.entity;

/*
 * Cadastro de Usuários do sistema
 * Sempre iniciar cadastrando um fornecedor e 
 * apartir dele criar os usuários associados
 * O manyToOne lê-se: muitos usuários para um fornecedor
 */



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id", length = 20)
	private String UserId;

	@ManyToOne 
	@JoinColumn(name = "for_id")
	private Fornecedor fornecedor;

	@Column(name = "user_name", nullable = false, length = 60)
	private String UserName;

	@Column(name = "user_mail", length = 60, unique = true)
	private String Usermail;

	@Column(name = "user_sts")
	private boolean Status;

	@Column(name = "cod_ven", unique = true, nullable = false)
	private int CodVendedor;

	@Column(name = "cod_loj", nullable = false)
	private int loja;


	
	/*
	 * 
	 * EAGER o carregamento de coleções significa que elas são buscadas
	 * completamente no momento em que seu pai é buscado LAZY por outro lado,
	 * significa que o conteúdo do Listarquivo é obtido somente quando você tenta
	 * acessá-lo
	 * 
	 * 
	 * //Monta a tsbela de Usuários x Grupos //PERSIST- Salva o filho quando salva o
	 * pai /MERGE - Atualiza filhos quando atualiza o pai, somente se já estiver
	 * persisitido
	 * 
	 * @OneToMany(fetch = FetchType.LAZY , cascade = {CascadeType.PERSIST,
	 * CascadeType.MERGE})
	 * 
	 * @JoinTable(name ="UserGroup", joinColumns = @JoinColumn(name = "UserId"),
	 * inverseJoinColumns = @JoinColumn(name = "GroupId") ) private List<Group>
	 * groups;
	 */
	
//	@OneToMany(mappedBy = "User", fetch = FetchType.LAZY)
	//private List<UserGroup> userGroups;
}
