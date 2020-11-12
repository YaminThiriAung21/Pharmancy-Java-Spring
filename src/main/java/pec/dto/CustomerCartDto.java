package pec.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomerCartDto {

	public static List<CustomerDetailOrderInfoDto> getList(){
		List<CustomerDetailOrderInfoDto> CusDto = new ArrayList<CustomerDetailOrderInfoDto>();
		CustomerDetailOrderInfoDto dto1 = new CustomerDetailOrderInfoDto();
		dto1.setProduct_info_id(1);
		dto1.setProduct_image_name("product_01");
		dto1.setProduct_name("Ibuprfen");
		dto1.setProduct_price(55.00);
		dto1.setProduct_quantity(2);
		//dto1.setImage_name(null);
		//dto1.setProduct_image(null);
		dto1.setProduct_total_price(dto1.getProduct_price()*dto1.getProduct_quantity());
		
		CustomerDetailOrderInfoDto dto2 = new CustomerDetailOrderInfoDto();
		dto2.setProduct_info_id(2);
		dto2.setProduct_image_name("product_02");
		dto2.setProduct_name("Bioderma");
		dto2.setProduct_price(49.00);
		dto2.setProduct_quantity(3);
		//dto2.setImage_name(null);
		//dto2.setProduct_image(null);
		dto2.setProduct_total_price(dto2.getProduct_price()*dto2.getProduct_quantity());
		CusDto.add(dto1);
		CusDto.add(dto2);
		return CusDto;
	}
	
	}
