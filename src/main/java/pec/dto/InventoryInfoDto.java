package pec.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class InventoryInfoDto {
	
	
	@NotEmpty
	private int inventory_info_id;
	private int product_info_id;
	private int office_info_id;
	private int total_quantity;
	private int total_damage_quantity;
	private int total_expired_quantity;
	private int delete_flag;
	@Size(max = 45)
	private String insert_user;
	private Date insert_date;
	@Size(max = 45)
	private String update_user;
	private Date update_date;

	
}
