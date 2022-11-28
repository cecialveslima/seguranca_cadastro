package br.com.seg.entity;

/*
 * Cadastro de empresas/fornecedores
 * Base para o restante do cadastramento
 * 
 * Podemos usar @Email @Cpf @Cnpj (não precisaria de rotina
 */


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.seg.utils.ValidaDocumento;
import io.swagger.annotations.ApiModelProperty;



@Entity
@Table(name = "fornecedor")
//@SequenceGenerator(name = "seq", allocationSize = 1) //nome e sequencia de incremento, quando Strategy ==sequence
@JsonPropertyOrder( {"identificacao","nome","mail","status","cnpj","telefone"})
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "for_id")
	private long ForId; 
	
	@Column(name ="for_nom" , length = 60 , nullable = false)
	//@Size(min = 2 ,max = 60 ,  message = "O nome deve conter no máximo 60 caracteres")
	private String NameFor;
	
	
	@Column(name = "for_sts", nullable= false)
	private boolean StatusFor;
	
	
	@Column(name = "for_mail", unique = true, nullable = false)
	private String forEmail;
	
	@Column(name = "for_cnpj", unique = true, nullable = false)
	private String forCnpj;

	@Column(name = "telefone", unique = true, nullable = false)
	private String forTelefone;
	
	/*Getters e Setters*/
	
	@JsonProperty("identificacao")
	@ApiModelProperty(notes = "ID do fornecedor", example = "id do fornecedor")
	public long getForId() {
		return ForId;
	}

	public void setForId(long forId) {
		ForId = forId;
	}

	@JsonProperty("nome")	
	@ApiModelProperty(notes = "Nome do fornecedor, informar a Razão Social completa", example = "Nome do fornecedor, informar a Razão Social")
	public String getNameFor() {
		return NameFor ;
	}

	public void setNameFor(String nameFor) {
		NameFor = nameFor;
	}

	@JsonProperty("status")
	@AssertTrue
	@ApiModelProperty(notes = "Situação do cadastro, true = ativo false =inativo", example = "true")
	public boolean isStatusFor() {
		return StatusFor;
	}

	public void setStatusFor(boolean statusFor) {
		StatusFor = statusFor;
	}

	@JsonProperty("mail")
	@ApiModelProperty(notes = "e-mail de contato do fornecedor email@email.com.br", example = "e-mail de contato do fornecedor, informar apenas 1 (um)")
	public String getForEmail() {
		return forEmail;
	}

	public void setForEmail(String forEmail) {
		this.forEmail = forEmail;
	}

	@JsonProperty("cnpj")
	@ApiModelProperty(notes = "CNPJ do fornecedor, podendo informar .-/", example = "CNPJ do fornecedor, podendo informar .-/")
	public String getForCnpj() {
		if (forCnpj != null && forCnpj.length() > 14) {
			return forCnpj  = ValidaDocumento.LimpaDoc(forCnpj,"CNPJ");
		}

		return forCnpj;
	}

	@JsonProperty("telefone")
	@ApiModelProperty(notes = "Telefone do fornecedor,formato 55DDNNNNNNNNN", example = "Telefone do fornecedor,formato permitido 55DDNNNNNNNNN")
	public String getForTelefone() {
		return forTelefone;
	}

	public void setForTelefone(String forTelefone) {
		this.forTelefone = forTelefone;
	}

	public void setForCnpj(String forCnpj) {
		this.forCnpj = forCnpj;
	}

	public Fornecedor() {}

	public Fornecedor(long forId, String nameFor, boolean statusFor, String forEmail, String forCnpj,
			String forTelefone) {
		super();
		ForId = forId;
		NameFor = nameFor;
		StatusFor = statusFor;
		this.forEmail = forEmail;
		this.forCnpj = forCnpj;
		this.forTelefone = forTelefone;
	}

	
	public Fornecedor(long forId) {
		ForId = forId;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(ForId, NameFor, StatusFor, forCnpj, forEmail, forTelefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		return ForId == other.ForId && Objects.equals(NameFor, other.NameFor) && StatusFor == other.StatusFor
				&& Objects.equals(forCnpj, other.forCnpj) && Objects.equals(forEmail, other.forEmail)
				&& Objects.equals(forTelefone, other.forTelefone);
	}
	
	
}

/* 
 * usando @JsonIgnoreProperties
 * Se uma classe de modelo estiver sendo criada para representar o JSON em Java, 
a classe pode ser anotada como @JsonIgnoreProperties (ignoreUnknown = true) 
para ignorar qualquer campo desconhecido. Isso significa que se um novo campo 
for adicionado posteriormente no JSON que representa este modelo, Jackson não 
lançará UnrecognizedPropertyException ao analisar JSON em Java. Essa abordagem 
não apenas ignora propriedades desconhecidas para essa classe de modelo, 
mas também fornece mais controle.
 */

/*
 * Uso do identity = Não vou usar, notei que pega 1 numero e queima sempre que dá erro.
 * 
 */
//@GeneratedValue(strategy = GenerationType.IDENTITY)	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
