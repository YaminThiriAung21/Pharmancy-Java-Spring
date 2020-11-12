package pec.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pec.dao.OrderCheckoutDao;
import pec.dto.CustomerDetailOrderInfoDto;
import pec.dto.CustomerOrderInfoDto;
import pec.dto.OrderCheckoutDto;

@Service
@Transactional
public class OrderCheckoutService {

	@Autowired 
	private OrderCheckoutDao dao;
	
	public void placeOrder(CustomerOrderInfoDto customerorderinfoDto) {
		dao.placeOrder(customerorderinfoDto);
		System.out.println("serviceahl");
	}

	public void checkoutOrder(CustomerDetailOrderInfoDto customerDetailOrderDto) {
		dao.checkoutOrder(customerDetailOrderDto);
		
	}

}
