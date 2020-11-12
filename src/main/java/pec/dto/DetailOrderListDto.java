package pec.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Product Location Dto
 * 26/8/2020
 * @author Yamin Thiri Aung
 */
@Data
public class DetailOrderListDto {
	
	private Integer customer_detail_order_info;
	private Integer product_info_id;
	private Integer customer_info_id;
	private Integer office_info_id;
	private Integer customer_order_info_id;
	private Date customer_order_date;
	private Integer product_quantity;
	private Integer product_price;
	private Integer product_total_price;
	private String customer_name;
	private String product_name;
	private String office_name;
	private MultipartFile product_image;
	private String product_image_name;


}
