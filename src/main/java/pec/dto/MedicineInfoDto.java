package pec.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
/**
 * MedicindeInfo Dto
 * 25/8/2020
 * @author Khin Pyae Pyae Phyo
 */
@Data
public class MedicineInfoDto{
	
	
		
	private Integer medicine_info_id;
	private Integer delete_flag;
	
	@NotEmpty
	@Size(max = 45)
	private String medicine_code;
	private String medicine_type;
	private String insert_user;
	private String update_user;
	private Date insert_date;
	private Date update_date;
		
	}


