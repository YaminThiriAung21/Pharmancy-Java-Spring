package pec.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

/**
 * Supplier Detail Order Info Dto
 * 26/8/2020
 * @author Wutt Yee Tun
 */
@Data
public class SupplierDetailOrderInfoDto {
	

	private Integer supplier_detail_order_info_id;
	private Integer product_info_id;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expire_date;
	private String expireDate;
	private Integer expire_flag;
	private Integer product_price;
	private Integer product_quantity;
	private Integer product_total_price;
	private Integer supplier_order_info_id;
	private Integer delete_flag;
	private String insert_user;
	private Date insert_date;
	private String update_user;
	private Date update_date;
	private Integer product_location_id;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date manufacture_date;
	private String manufactureDate;
	private String product_code;
	private String product_name;
	private String medicine_type;
	private String category_name;
	private String packaging_type;	
	private MultipartFile product_image;
	private String product_image_name;
	private Integer rowIndex;
	
}
