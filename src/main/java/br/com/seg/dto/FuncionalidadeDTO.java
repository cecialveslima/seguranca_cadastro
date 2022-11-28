package br.com.seg.dto;




import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.seg.entity.Funcionalidade;
import io.swagger.annotations.ApiModelProperty;


@JsonPropertyOrder({"funId","funNome","funStatus","funPathIconAble","funPathIconDisable"})
public class FuncionalidadeDTO {

	private String funId;
	private String funName;
	private boolean funStatus;
	private String funPathIconAble; //Caminho da imagem para ativos
	private String funPathIconDisable; //Caminho da imagem para inativos

	
	@JsonProperty("funId")
	@ApiModelProperty(name = "Identificador da funcionalidade", example = "CSV")
	public String getFunId() {
		return funId;
	}

	public void setFunId(String funId) {
		this.funId = funId;
	}
	
	@JsonProperty("funName")
	@ApiModelProperty(name = "Nome da funcionalidade", example = "PDF")
	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}
	
	@JsonProperty("funStatus")
	@ApiModelProperty(name = "Status da funcionalidade true/false", example = "true")
	public boolean isFunStatus() {
		return funStatus;
	}

	public void setFunStatus(boolean funStatus) {
		this.funStatus = funStatus;
	}
	
	@JsonProperty("funPathIconAble")
	@ApiModelProperty(name = "Imagem para botões ativos", example = "/mnt/ativos/imagema.png")
	public String getFunPathIconAble() {
		return funPathIconAble;
	}

	public void setFunPathIconAble(String funPathIconAble) {
		this.funPathIconAble = funPathIconAble;
	}
	
	@JsonProperty("funPathIconDisable")
	@ApiModelProperty(name = "Imagem para botões inativos" , example = "/usr/mnt/imagem.png")
	public String getFunPathIconDisable() {
		return funPathIconDisable;
	}

	public void setFunPathIconDisable(String funPathIconDisable) {
		this.funPathIconDisable = funPathIconDisable;
	}

	public FuncionalidadeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FuncionalidadeDTO(String funId, String funName, boolean funStatus, String funPathIconAble,
			String funPathIconDisable) {
		super();
		this.funId = funId;
		this.funName = funName;
		this.funStatus = funStatus;
		this.funPathIconAble = funPathIconAble;
		this.funPathIconDisable = funPathIconDisable;
	}
	
	public FuncionalidadeDTO(Funcionalidade func) {
		funId 				= func.getFunId();
		funName 			= func.getFunId();
		funStatus 			= func.isFunStatus();
		funPathIconAble 	= func.getFunPathIconAble();
		funPathIconDisable 	= func.getFunPathIconAble();
	}
	
}
