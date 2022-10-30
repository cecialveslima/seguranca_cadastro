package br.com.seg.embeddeable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/*
 * @Embeddable (tradução: incorporável) - Utilizado para agrupar propriedades, neste exemplo
 * Fornecedor + Usuário e Grupo - formando UserGroup
 * Aplica-se neste caso o conceito de composição, compondo uma classe a partir de outras.
 * O JPA pode ser usado para mapear com eficiência chaves compostas e consultá-las por meio de consultas derivadas.
 */

@Embeddable
public class UserGroupEmbeddable implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "group_id")
	private String GroupId;
	
	@Column(name = "for_id")
	private long ForId;
	
	@Column(name = "user_id")
	private String UserId;

}
