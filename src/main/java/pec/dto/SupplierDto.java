package pec.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * SupplierDto
 * 26/8/2020
 * @author Lin Lae Win Wah
 */
@Data
public class SupplierDto {
	
	private Integer supplier_info_id;
	@NotEmpty
	@Size(max = 25)	
	private String supplier_code;
	private String supplier_name;
	private String supplier_city;
	private String supplier_address;
	private String supplier_email_address;
	private String supplier_phone_no;
	private Integer delete_flag;
	private String insert_user;
	private Date insert_date;
	private String update_user;
	private Date update_date;
	
	
	
}