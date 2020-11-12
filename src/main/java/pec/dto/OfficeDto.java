package pec.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
/**
 * 
 * OfficeDto
 * @author Khin Me Me Latt       
 *
 */
@Data
public class OfficeDto {

	
	private int office_info_id;
	@NotEmpty
	@Size(max = 45)
	private String office_code;
	@NotEmpty
	@Size(max = 45)
	private String office_name;
	@NotEmpty
	@Size(max = 45)
	private String  office_email_address;
	@NotEmpty
	@Size(max = 45)
	private String office_city;
	@NotEmpty
	private String office_phone_number;
	@NotEmpty
	@Size(max = 200)
	private String office_address;
	private int delete_flag;
	@Size(max = 45)
	private String insert_user;
	private Date insert_date;
	@Size(max = 45)
	private String update_user;
	private Date update_date;
	private MultipartFile officeimage;
	private byte[] office_image;
	@Size(max = 45)
	private String office_image_name; 
	
}
