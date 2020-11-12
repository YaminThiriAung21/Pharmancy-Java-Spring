package pec.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDetailDto {
	private MultipartFile image;
	private Integer product_info_id;
	private byte[] product_image;
	private String product_image_name;
	private String product_name;
	private String product_code;
	private Double sell_price;
	private Integer total_quantity;
	private String description;
	private String medicine_type;
	private String medicine_code;
	private String ingredient;
	private String category_name;
	private String manufacture;
	private Integer category_info_id;
	private Integer medicine_info_id;
}
