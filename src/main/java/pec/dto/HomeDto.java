package pec.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class HomeDto {
		
		private Integer office_info_id;
		@NotEmpty
		@Size(max = 25)
		private String office_name;
		@NotEmpty
		@Size(max = 25)
		private String office_image_name;
		
}
