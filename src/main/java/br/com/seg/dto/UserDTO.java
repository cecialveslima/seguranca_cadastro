package br.com.seg.dto;

import javax.validation.constraints.AssertTrue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.seg.entity.User;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({"codFornecedor","nome","cpfUsuario", "email", "status","codVendedor","numeroLoja"})
public class UserDTO  {
	
	private String 	UserId;
	private long 	ForId;
	private String 	UserName;
	private String 	Usermail;
	private boolean Status;
	private int 	CodVendedor;
	private int 	loja;
	private String 	cpfUsuario;
	
	
	public UserDTO(String userId, long forId, String userName, String usermail, boolean status, int codVendedor,
			int loja, String cpfUsuario) {
		super();
		UserId = userId;
		ForId = forId;
		UserName = userName;
		Usermail = usermail;
		Status = status;
		CodVendedor = codVendedor;
		this.loja = loja;
		this.cpfUsuario = cpfUsuario;
	}
	

	public UserDTO() {
		super();
	}
	
	//Construtor que receba a minha entidade User
	public UserDTO(User user) {
		UserId 		= user.getUserId();
		ForId 		= user.getFornecedor().getForId();
		UserName 	= user.getUserName();
		Usermail 	= user.getUsermail();
		Status 		= user.isStatus();
		CodVendedor = user.getCodVendedor();
		loja 		= user.getLoja();
		cpfUsuario 	= user.getCpfUsuario();
	}

	
	@JsonProperty("userId")
	@ApiModelProperty(name = "ID único do usuário" , example = "078MARIA")
	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}
	
	@JsonProperty("nome")
	@ApiModelProperty(name = "Nome completo do usuário, espaços superiores a 1 serão removidos", example = "Nome completo do usuário")
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	@JsonProperty("email")
	@ApiModelProperty(name= "e-mail para contato", example = "email@email.com.br")
	public String getUsermail() {
		return Usermail;
	}

	public void setUsermail(String usermail) {
		Usermail = usermail;
	}

	@AssertTrue
	@JsonProperty("status")
	@ApiModelProperty(name = "Situação do cadastro", example = "true")
	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	@JsonProperty("codVendedor")
	@ApiModelProperty(name = "Código do vendedor", example = "852")
	public int getCodVendedor() {
		return CodVendedor;
	}

	public void setCodVendedor(int codVendedor) {
		CodVendedor = codVendedor;
	}

	@JsonProperty("numeroLoja")
	@ApiModelProperty(name = "Numero da Loja" , example = "897")
	public int getLoja() {
		return loja;
	}

	public void setLoja(int loja) {
		this.loja = loja;
	}

	
	@ApiModelProperty(name = "CPF do usuário, permitido inserir com e sem pontuação", example = "123.456.789-88")
	@JsonProperty("cpfUsuario")
	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

	@ApiModelProperty(name = "Código do fornecedor", example = "258")
	@JsonProperty("codFornecedor")
	public long getForId() {
		return ForId;
	}

	public void setForId(long forId) {
		this.ForId = forId;
	}
}
