package pec.dto;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
/**
 * category Dto
 * 26/8/2020
 * @author Khin Thant Sin
 */
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class CategoryDto {
	private Integer category_info_id;
	@NotEmpty
	@Size(max = 25)
	private String category_code;
	private String category_name;
	private int delete_flag;
	private String insert_user;
	private Date insert_date;
	private String update_user;
	private Date update_date;
}


