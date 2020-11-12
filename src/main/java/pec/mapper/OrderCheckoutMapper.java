package pec.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pec.dto.CustomerDetailOrderInfoDto;
import pec.dto.CustomerOrderInfoDto;

@Mapper
public interface OrderCheckoutMapper {

	public void placeOrder(CustomerOrderInfoDto customerorderinfoDto);

	public void checkoutOrder(CustomerDetailOrderInfoDto customerDetailOrderDto);
}
  