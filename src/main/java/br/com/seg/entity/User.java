package br.com.seg.entity;

/*
 * Cadastro de Usuários do sistema
 * Sempre iniciar cadastrando um fornecedor e 
 * apartir dele criar os usuários associados
 * O manyToOne lê-se: muitos usuários para um fornecedor
 * 
 * Passada toda a resposabilidade para o UserDTO, este ficou apenas como entidade
 */



import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import java.util.logging.Logger;

@Entity
@Table(name = "usuario")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

//	private Logger logger = Logger.getLogger(User.class.getName());
	@Id
	@Column(name = "user_id", length = 20)
	private String userId;

	@ManyToOne( fetch = FetchType.LAZY) 
	@JoinColumn(name = "for_id")
	private Fornecedor fornecedor;

	@Column(name = "user_name", nullable = false, length = 60)
	private String userName;

	@Column(name = "user_mail", length = 64, unique = true)
	private String userMail;

	@Column(name = "user_sts")
	private boolean status;

	@Column(name = "cod_ven", unique = true, nullable = false)
	private int codVendedor;

	@Column(name = "cod_loj", nullable = false)
	private int loja;
	
	@Column(name = "user_cpf", nullable = false)
	private String cpfUsuario;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor; 
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUsermail() {
		return userMail;
	}

	public void setUsermail(String userMail) {
		this.userMail = userMail;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getCodVendedor() {
		return codVendedor;
	}

	public void setCodVendedor(int codVendedor) {
		this.codVendedor = codVendedor;
	}

	public int getLoja() {
		return loja;
	}

	public void setLoja(int loja) {
		this.loja = loja;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String userId, Fornecedor fornecedor, String userName, String userMail, boolean status, int codVendedor,
			int loja, String cpfUsuario) {
		super();
		this.userId 		= userId;
		this.fornecedor 	= fornecedor;
		this.userName 		= userName;
		this.userMail 		= userMail;
		this.status 		= status;
		this.codVendedor 	= codVendedor;
		this.loja 			= loja;
		this.cpfUsuario 	= cpfUsuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codVendedor, status, userId, userName, userMail, cpfUsuario, fornecedor, loja);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return codVendedor == other.codVendedor && status == other.status && Objects.equals(userId, other.userId)
				&& Objects.equals(userName, other.userName) && Objects.equals(userMail, other.userMail)
				&& Objects.equals(cpfUsuario, other.cpfUsuario) && Objects.equals(fornecedor, other.fornecedor)
				&& loja == other.loja;
	}
}


	
	
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

