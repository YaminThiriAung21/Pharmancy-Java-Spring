package pec.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Product Location Dto
 * 26/8/2020
 * @author Yin Min Oo+Thet Mon Hnin
 */
@Data
public class ProductLocDto {
	
	private Integer product_location_info_id;
	@NotEmpty
	@Size(max = 25)
	private String location_code;
	private String product_location_place ; 
	private String delete_flag;
	private String insert_user;
	private Date insert_date;
	private String update_user;
	private Date update_date;
	
	
	

}
