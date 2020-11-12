package pec.dao;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pec.dto.CustomerDetailOrderInfoDto;
import pec.dto.CustomerOrderInfoDto;
import pec.dto.OrderCheckoutDto;
import pec.mapper.OrderCheckoutMapper;

@Repository
public class OrderCheckoutDao {

	@Autowired 
	private OrderCheckoutMapper mapper;
	
	public void placeOrder(CustomerOrderInfoDto customerorderinfoDto) {
		mapper.placeOrder(customerorderinfoDto);
		System.out.println("Daoahl");
		
	}

	public void checkoutOrder(CustomerDetailOrderInfoDto customerDetailOrderDto) {
		mapper.checkoutOrder(customerDetailOrderDto);
		
	}

}
