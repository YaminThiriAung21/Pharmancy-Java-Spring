package pec.dto;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 
 * EmployeeInfoDto
 * 
 * @author Thiha  
 *
 */

@Data

public class EmployeeInfoDto {
	
	private Integer employee_info_id;
	@NotEmpty
	@Size(max = 45)
	private String employee_code;
	@NotEmpty
	@Size(max = 45)
	private String employee_name;
	@NotEmpty
	private String email_address;
	@NotEmpty
	@Size(max = 150)
	private String password;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birth_date;
	@Size(max = 45)
	private String gender;
	@NotEmpty
	@Size(max = 20)
	private String permit_order;
	@NotEmpty
	@Size(max = 20)
	private String phone_number;
	@NotEmpty
	@Size(max = 200)
	private String employee_address;
	@NotEmpty
	@Size(max = 45)
	private String employee_education;
	private Integer work_experience;
	@Size(max = 200)
	private String remark;
	@NotEmpty
	@Size(max = 45)
	private String job_status;
	private Integer delete_flag;
	
	@Size(max = 45)
	private String insert_user;
	
	private Date insert_date;
	
	@Size(max = 45)
	private String update_user;
	
	private Date update_date;
	
	private int office_info_id;
	
	private String office_name;
	
	private String role;
	private Integer login_user_info_id;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date job_start_date;
}

