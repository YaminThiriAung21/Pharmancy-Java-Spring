package pec.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;


/** 
 * login user info dto 
 * @author nyi min aye
 *
 */

@Data
public class LoginUserInfoDto {
	
	private Integer login_user_info_id;
	
	@NotEmpty
	@Size(max = 45)
	private String log_email_address;
	
	@NotEmpty
	@Size(max = 45)
	private String log_password;
	
	@NotEmpty
	@Size(max = 45)
	private String role;
	
	@NotEmpty
	@Size(max = 11)
	private Integer office_info_id;
	
	@NotEmpty
	@Size(max = 11)
	private Integer delete_flag;
	
	@NotEmpty
	@Size(max = 45)
	private String insert_user;
	
	@NotEmpty
	private Date insert_date;
	
	@NotEmpty
	@Size(max = 45)
	private String update_user;
	
	@NotEmpty
	private Date update_date;
	
}
