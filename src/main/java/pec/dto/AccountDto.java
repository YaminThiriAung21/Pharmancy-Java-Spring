package pec.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Login Dto
 * 25/8/2020
 * @author Su Su Lin
 */
@Data
public class AccountDto {
	
	private Integer id;
	@NotEmpty
	@Size(max = 25)
	private String name;
	private String role;
	private String password;


}
