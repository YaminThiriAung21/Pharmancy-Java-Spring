package pec.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


@Data
public class ExpiredItemDto {

	@NotEmpty
	@Size(max = 50)
	private String product_code;
	private MultipartFile product_image;
	private String product_name;
	private Date expire_date;
	private String update_user;
	private Date update_date;
}
