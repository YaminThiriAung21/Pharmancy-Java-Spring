package pec.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * Productinfo Dto
 * 25/8/2020
 * @author Thet Hnin Su
 */


@Data
public class ProductInfoDto {

	private Integer product_info_id;
	private Integer delete_flag;
	@NotEmpty
	@Size(max = 25)
	private String product_code;
	private String product_name;
	private String description;
	private String ingredient;
	private String manufacture;
	private String medicine_code;
	private Integer medicine_info_id;
	private String category_name;
	private Integer category_info_id;
	private Integer sell_price;
	private Integer buy_price;
	private String insert_user;
	private Date insert_date;
	private String update_user;
	private Date update_date;
	private MultipartFile productimage;
	private byte[] product_image;
	private String product_image_name;
	private Integer office_info_id;
	private String packaging_type;
	private Integer packaging_info_id;
	private Integer Total_price;
	private Integer Sub_total;
    private Integer total_quantity;
	
}
