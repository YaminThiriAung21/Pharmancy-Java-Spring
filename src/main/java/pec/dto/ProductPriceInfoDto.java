package pec.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class ProductPriceInfoDto {
	
	private Integer product_price_info_id;
	@NotEmpty
	@Size(max = 25)
	private Integer product_info_id;
	private String old_sell_price ;
	private String old_buy_price;
	private float new_sell_price;
	private float new_buy_price;
	private Integer delete_flag;
	private Integer office_info_id;
	private String insert_user;
	private Date insert_date;
	private String update_user;
	private Date update_date ;
	private Date submit_date ;

}
