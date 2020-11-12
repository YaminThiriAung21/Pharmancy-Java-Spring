package pec.dto;

import java.sql.Date;
import lombok.Data;
 
/**
 * Expire Dto
 * 28/8/2020
 * @author Khin Me Me Latt
 */
@Data
public class ExpireDto {

	private int SUPPLIER_DETAIL_ORDER_INFO_ID;
	private String product_code;
	private String product_image_name;
	private String product_name;
	private Date expire_date;
	private String update_user;
	private Date update_date;
	
}
