package br.com.seg.entity;

/*
 * Cadastro de funcionalidades dos módulos
 * Exemplo: Incluir, alterar, excluir, visualizar CSV, PDF..
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Funcionalidade")
public class Funcionalidade {
	
	@Id
	@Column(name = "fun_id", length= 30)
	private String FunId;
	
	@Column(name = "fun_name", length = 50)
	private String FunName;
	
	@Column(name = "fun_sts")
	private boolean FunStatus;
	
	@Column(name="path_icon_able", length=60)
	private String FunPathIconAble; //Caminho da imagem para ativos
	
	@Column(name ="path_icon_disable", length=60)
	private String FunPathIconDisable; //Caminho da imagem para inativos

}
