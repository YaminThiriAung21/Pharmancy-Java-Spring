package pec.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Packaging Info Dto
 * 27/8/2020
 * @author Thet Hnin Su
 */

@Data
public class PackagingInfoDto {

	private Integer packaging_info_id;
	private Integer delete_flag;
	@NotEmpty
	@Size(max = 45)
	private String packaging_type;
	private String insert_user;
	private Date insert_date;
	private String update_user;
	private Date update_date;
	
}
