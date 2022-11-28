package br.com.seg.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.seg.entity.Modulo;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({"moduloId","moduloName","status" })
public class ModuloDTO {

	private String moduleId;
	private String moduleName;
	private boolean moduleStatus;

	@JsonProperty("moduloId")
	@ApiModelProperty(name = "Identificador do módulo", example = "CADUSER")
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@JsonProperty("moduloName")
	@ApiModelProperty(name = "Título do módulo", example = "Cadastro de Usuários")
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	//@AssertTrue //não estava permitindo update para false
	@JsonProperty("status")
	@ApiModelProperty(name = "Status ativo/inativo", example = "true")
	public boolean isModuleStatus() {
		return moduleStatus;
	}

	public void setModuleStatus(boolean moduleStatus) {
		this.moduleStatus = moduleStatus;
	}

	public ModuloDTO(Modulo module) {
		moduleId = module.getModuleId();
		moduleName = module.getModuleName();
		moduleStatus = module.isModuleStatus();
	}

	public ModuloDTO(String moduleId, String moduleName, boolean moduleStatus) {
		super();
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.moduleStatus = moduleStatus;
	}

	public ModuloDTO() {

	}
}
