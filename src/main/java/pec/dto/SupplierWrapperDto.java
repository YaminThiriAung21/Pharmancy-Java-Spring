package pec.dto;



import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SupplierWrapperDto {
	private int supplier_info_id;
	private int supplier_detail_order_info_id;
	private int product_info_id;
	private int supplier_order_info_id;
	
	@NotEmpty
	@Size(max = 25)
	private String supplier_name;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date order_date;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date order_receive_date;
	private String product_code;
	private String product_name;
	private String medicine_type;
	private String category_name;
	private String packaging_type;
	private Integer product_price;
	private Integer product_quantity;
	private Integer buy_price;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date manufacture_date;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expire_date;
	private Integer product_total_price;
	private Integer expire_flag;
	private Integer delete_flag;
	private String insert_user;
	private Date insert_date;
	private String update_user;
	private Date update_date;
	/*private SupplierDto supplierDto;
	private List<SupplierDetailOrderInfoDto> supplierDetailOrderInfoDto;
	private List<ProductInfoDto> productInfoDto;*/
}
