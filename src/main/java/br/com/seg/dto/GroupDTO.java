package br.com.seg.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.seg.entity.Group;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({"groupId", "forId", "groupDsc", "groupStatus"})
public class GroupDTO {

	private String groupId;
	private Long cliFornecedor;
	private String groupDsc;
	private boolean groupSts;
	
	public GroupDTO(String groupId, Long cliFornecedor, String groupDsc, boolean groupSts) {
		super();
		this.groupId = groupId;
		this.cliFornecedor = cliFornecedor;
		this.groupDsc = groupDsc;
		this.groupSts = groupSts;
	}
	
	public GroupDTO() {
		super();
	}
	
	public GroupDTO(Group grupo) {
		this.groupId = grupo.getGroupId();
		this.cliFornecedor = grupo.getCliFornecedor().getForId();
		this.groupDsc = grupo.getGroupDsc();
		this.groupSts = grupo.isGroupSts();
	}
	
	@JsonProperty("groupId")
	@ApiModelProperty(name = "ID do grupo", example = "SISADMIN")
	public String getGroupId() {
		return groupId;
	}
	
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@JsonProperty("codFornecedor")
	@ApiModelProperty(name = "Id do Fornecedor", example = "1")
	public Long getCliFornecedor() {
		return cliFornecedor;
	}
	
	public void setCliFornecedor(Long cliFornecedor) {
		this.cliFornecedor = cliFornecedor;
	}
	
	@JsonProperty("groupDsc")
	@ApiModelProperty(name = "Descrição do Grupo" , example = "Administrador")
	public String getGroupDsc() {
		return groupDsc;
	}
	
	public void setGroupDsc(String groupDsc) {
		this.groupDsc = groupDsc;
	}
	
	@JsonProperty("groupStatus")
	@ApiModelProperty(name = "Status do Grupo" , example = "true")	
	public boolean isGroupSts() {
		return groupSts;
	}
	
	public void setGroupSts(boolean groupSts) {
		this.groupSts = groupSts;
	}

}
