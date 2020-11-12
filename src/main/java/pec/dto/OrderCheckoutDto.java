package pec.dto;

import java.util.List;

import lombok.Data;
import pec.dto.LoginUserInfoDto;

/**
 * Customer Info DTO
 *
 * @author Aung Htet Lwin
 */
@Data
public class OrderCheckoutDto {

	private LoginUserInfoDto loginUserInfoDto;
	private CustomerInfoDto customerInfoDto;
	private List<CustomerDetailOrderInfoDto> customerDetailOrderDto;
	private CustomerOrderInfoDto customerOrderDto;
	private ProductInfoDto productInfoDto;

	
}
