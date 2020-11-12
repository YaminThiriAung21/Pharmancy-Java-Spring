package pec.dto;

import java.util.List;

import lombok.Data;

@Data
public class CustomerOrderDetailWrapperDto {
	
	List<CustomerDetailOrderInfoDto> cartList;

}
