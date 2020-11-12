package pec.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerOrderInfoDto {
	
	private Integer customer_order_info_id;
	private Integer login_user_info_id;
	private Integer customer_info_id;
	private Date customer_order_date;
	private Double total_price;
	private Integer office_info_id;
	private Integer delete_flag;
	private String insert_user;
	private Date insert_date;
	private String update_user;
	private Date update_date;
	private int rowIndex;

}
