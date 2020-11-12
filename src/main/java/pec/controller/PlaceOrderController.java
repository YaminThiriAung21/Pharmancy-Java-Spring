package pec.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pec.datacode.UserPageInfo;
import pec.dto.CustomerDetailOrderInfoDto;
import pec.dto.CustomerInfoDto;
import pec.dto.CustomerOrderInfoDto;
import pec.service.InventoryService;
import pec.service.OrderCheckoutService;

@Controller
@RequestMapping("/user/placeOrder")
public class PlaceOrderController {

	@Autowired
	private OrderCheckoutService ordercheckoutService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	InventoryService inventoryService;
	
	@RequestMapping(params="placeorder",method=RequestMethod.POST)
	public String initOrderCheckout(Model model ,CustomerOrderInfoDto customerorderinfo) {	
		
		List<CustomerDetailOrderInfoDto> checkout =(List<CustomerDetailOrderInfoDto>) session.getAttribute("customerDetailOrderList");
		CustomerOrderInfoDto cardtotal = (CustomerOrderInfoDto) session.getAttribute("customerOrderInfo");	
		CustomerInfoDto userDto = (CustomerInfoDto) session.getAttribute("loginUserDto");	
		customerorderinfo.setLogin_user_info_id(userDto.getLogin_user_info_id());
		customerorderinfo.setCustomer_order_date(new Date());
		customerorderinfo.setTotal_price(cardtotal.getTotal_price());
		customerorderinfo.setOffice_info_id((Integer) session.getAttribute("officeId"));
		customerorderinfo.setDelete_flag(0);
		customerorderinfo.setInsert_date(new Date());
		customerorderinfo.setInsert_user("ahl");
		customerorderinfo.setUpdate_date(new Date());
		customerorderinfo.setUpdate_user("ath");
		
		ordercheckoutService.placeOrder(customerorderinfo);
		
		for (int i=0; i< checkout.size(); i++) {
			checkout.get(i).setCustomer_order_info_id(customerorderinfo.getCustomer_order_info_id());
			checkout.get(i).setDelete_flag(0);
			checkout.get(i).setInsert_user("ahl");
			checkout.get(i).setInsert_date(new Date());
			checkout.get(i).setUpdate_user("ath");
			checkout.get(i).setUpdate_date(new Date());
			ordercheckoutService.checkoutOrder(checkout.get(i));
			inventoryService.subtractInventory(checkout.get(i).getProduct_quantity(), checkout.get(i).getProduct_info_id(),(Integer) session.getAttribute("officeId"));
			
		}			
		session.removeAttribute("customerDetailOrderList");
		session.removeAttribute("customerOrderInfo");
		session.removeAttribute("loginUserDto");
		session.setAttribute("customerOrderSize",0);
		return UserPageInfo.Place_Order.template;
	}
	
}
	
	/*@RequestMapping(params="placeorder",method=RequestMethod.POST)
	public String initOrderCheckout(Model model ,CustomerOrderInfoDto customerorderinfo) {
		
		System.out.println("Hi");
		//CustomerOrderInfoDto customerorderinfo=new CustomerOrderInfoDto();
		
		customerorderinfo.setCustomer_info_id(1);
		customerorderinfo.setCustomer_order_date(new Date());
		customerorderinfo.setTotal_price(1000.00);
		customerorderinfo.setOffice_info_id(1);
		customerorderinfo.setDelete_flag(1);
		customerorderinfo.setInsert_date(new Date());
		customerorderinfo.setInsert_user("ahl");
		customerorderinfo.setUpdate_date(new Date());
		customerorderinfo.setUpdate_user("ath");
		
		System.out.println("ahl : "+customerorderinfo);
		//model.addAttribute("cardtotal",cardtotal);
		
		
		List<CustomerDetailOrderInfoDto> customerDetailOrderDto = new ArrayList<CustomerDetailOrderInfoDto>();
		CustomerDetailOrderInfoDto detailDto1 = new CustomerDetailOrderInfoDto();
		CustomerDetailOrderInfoDto detailDto2 = new CustomerDetailOrderInfoDto();
		
		detailDto1.setCustomer_order_info_id(2);
		
		detailDto1.setProduct_info_id(1);
		detailDto1.setProduct_quantity(3);
		detailDto1.setDelete_flag(1);
		detailDto1.setInsert_user("ahl");
		detailDto1.setInsert_date(new Date());
		detailDto1.setUpdate_user("ath");
		detailDto1.setUpdate_date(new Date());
		detailDto1.setProduct_price(100.00);
		detailDto1.setProduct_total_price(300.00);
		
		//detailDto1.setProduct_name("Ibuprfen");
		
		
		detailDto2.setCustomer_order_info_id(2);
		detailDto2.setProduct_info_id(1);
		detailDto2.setProduct_quantity(2);
		detailDto2.setDelete_flag(1);
		detailDto2.setInsert_user("ahl");
		detailDto2.setInsert_date(new Date());
		detailDto2.setUpdate_user("ath");
		detailDto2.setUpdate_date(new Date());
		detailDto2.setProduct_price(100.00);
		detailDto2.setProduct_total_price(200.00);
		
		customerDetailOrderDto.add(detailDto1);
		customerDetailOrderDto.add(detailDto2);
		
		ordercheckoutService.placeOrder(customerorderinfo);
		
		for (int i = 0; i <customerDetailOrderDto.size(); i++) {
			ordercheckoutService.checkoutOrder(customerDetailOrderDto.get(i));
		}
		
		return UserPageInfo.Place_Order.template;
	}
	
}*/
