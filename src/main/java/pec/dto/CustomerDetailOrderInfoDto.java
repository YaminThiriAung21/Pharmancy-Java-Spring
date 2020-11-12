package pec.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerDetailOrderInfoDto {

	private Integer customer_detail_order_info_id;
	private Integer product_info_id;
	private String product_name;
	private Integer product_quantity;
	private Integer customer_order_info_id;
	private Integer delete_flag;
	private String insert_user;
	private Date insert_date;
	private String update_user;
	private Date update_date;
	private Double product_price;
	private Double product_total_price;
	private String product_image_name;
	private Integer product_total_quantity;
}