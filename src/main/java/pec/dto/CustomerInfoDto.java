package pec.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Customer Info DTO
 *
 * @author Htet Wai Yan Aung
 */

@Data
public class CustomerInfoDto {
	
	private Integer customer_info_id;
	@NotEmpty
	@Size(max = 45)
	private String customer_name;
	@NotEmpty
	private String phone_number;
	@NotEmpty
	@Size(max = 200)
	private String customer_address;
	private Integer login_user_info_id;
	@NotEmpty
	@Size(max = 45)
    //@Pattern(message = "Email is invalid",regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
	private String email_address;
	@Size(max = 45)
	private String password;
	@Size(max = 45)
	private String role;
	private Integer office_info_id;
	private Integer delete_flag;
	@Size(max = 45)	
	private String insert_user;
	private Date insert_date;
	@Size(max = 45)	
	private String update_user;
	private Date update_date;
	
}

