package com.everis.account.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel( description = "All about account" )
@JsonFilter("accountRequest")
public class AccountRequest {
	
	private Integer id;
	
	@Size( min = 5, max = 50 )
	@NotBlank( message = "{account.name.empty} " )
	@NotNull( message = "{account.name.null}" )
	@Pattern( regexp="^[A-Z ]*$" )
	@ApiModelProperty( notes = "This is account's name" )
	private String name;
	
	@JsonIgnore
	private Date startDate;
}
