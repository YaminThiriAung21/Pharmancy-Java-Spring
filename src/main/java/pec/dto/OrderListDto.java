package pec.dto;

import java.sql.Date;

//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Order List Dto
 * 26/8/2020
 * @author Yamin Thiri Aung
 */
@Data
public class OrderListDto {
	
	private Integer customer_order_info_id;
	private Integer customer_info_id;
	private Date customer_order_date;
	private Integer total_price;
	private Integer office_info_id;
	private String customer_name;
	private String office_name;
	
	

}
