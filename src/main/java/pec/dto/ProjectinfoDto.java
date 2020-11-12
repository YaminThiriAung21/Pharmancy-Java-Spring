package pec.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProjectinfoDto {
	private Integer product_info_id;
	private String delete_flag;
	@NotEmpty
	@Size(max = 25)
	private String product_code;
	private String product_name;
	private String description;
	private String ingredient;
	private String manufacture;
	private String medicine_info_id;
	private String category_info_id;
	private String sell_price;
	private String buy_price;
	private String insert_user;
	private Integer insert_date;
	private String update_user;
	private Integer update_date;
	private MultipartFile product_image;
	private byte[] sign;
	private String product_image_name;
	private String office_info_id;
	private String packaging_info_id;
}
