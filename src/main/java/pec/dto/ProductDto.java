package pec.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import oracle.sql.BLOB;

@Data
public class ProductDto {
	@NotEmpty
	private BLOB product_image;
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
}
