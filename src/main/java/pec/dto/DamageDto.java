package pec.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 
 * DamageDto
 * 
 * @author Kyaw Kyaw Khaing
 *
 */
@Data
public class DamageDto {

	@NotEmpty
	private int damage_info_id;
	private int product_info_id;
	private int office_info_id;
	private Date damage_remove_date;
	private int damage_product_quantity;
	private int delete_flag;
	@Size(max = 45)
	private String insert_user;
	private Date insert_date;
	@Size(max = 45)
	private String update_user;
	private Date update_date;
}
