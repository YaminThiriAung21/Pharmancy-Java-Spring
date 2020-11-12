package pec.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pec.dto.InventoryInfoDto;
import pec.datacode.UserPageInfo;
import pec.dto.CustomerCartDto;
import pec.dto.CustomerDetailOrderInfoDto;
import pec.dto.CustomerOrderDetailWrapperDto;
import pec.dto.CustomerOrderInfoDto;
import pec.service.CustomerCartService;

/**
 * Customer Cart Controller
 * 28/8/2020
 * @author Aung Thiha
 */

@Controller
@RequestMapping("/user/CustomerCart")
public class CustomerCartController {


	@Autowired
	private CustomerCartService service;
	
	@Autowired
	HttpSession session;
	

	@RequestMapping(method=RequestMethod.GET)
	public String initCustomerCart(Model model) {
		List<CustomerDetailOrderInfoDto> customerList = (List<CustomerDetailOrderInfoDto>) session.getAttribute("customerDetailOrderList");
		if(customerList  == null){
			CustomerOrderDetailWrapperDto customerDetailWarpper = new CustomerOrderDetailWrapperDto();
			model.addAttribute("customerOrderDto", new CustomerOrderInfoDto());
			model.addAttribute("customerDetailWarpper", customerDetailWarpper);
			model.addAttribute("orderListSize", 0);
			session.setAttribute("customerOrderSize",0);			
			return UserPageInfo.CustomerOrder.template;
			
		} else {
			CustomerOrderDetailWrapperDto customerDetailWarpper = new CustomerOrderDetailWrapperDto();
			//List<CustomerDetailOrderInfoDto> cartList = CustomerCartDto.getList();
			customerDetailWarpper.setCartList(customerList);
			model.addAttribute("customerOrderDto", new CustomerOrderInfoDto());
			model.addAttribute("customerDetailWarpper", customerDetailWarpper);
			model.addAttribute("orderListSize", customerList.size());
			session.setAttribute("customerOrderSize",customerList.size());
			return UserPageInfo.CustomerOrder.template;	
		}
		
	}
	/*public String cartList(Model model,RedirectAttributes attr, HttpServletRequest req) {
		List<ProductInfoDto> cartList = CustomerCartDto.getList();
		model.addAttribute("cartList", cartList);
		return UserPageInfo.CustomerOrder.template;
	}*/
	
	
	@RequestMapping(params="remove", method=RequestMethod.POST)
	public String removeOrder(Model model,CustomerOrderDetailWrapperDto customerDetailWarpper, CustomerOrderInfoDto customerOrderInfoDto) {
		int id = customerOrderInfoDto.getRowIndex();
		List<CustomerDetailOrderInfoDto>  del = customerDetailWarpper.getCartList();
		del.remove(id);
		customerDetailWarpper.setCartList(del);
		model.addAttribute("customerOrderDto", customerOrderInfoDto);
		model.addAttribute("customerDetailWarpper", customerDetailWarpper);
		model.addAttribute("orderListSize", del.size());
		session.setAttribute("customerDetailOrderList",customerDetailWarpper.getCartList());
		session.setAttribute("customerOrderInfo",customerOrderInfoDto);
		session.setAttribute("customerOrderSize",del.size());
		return UserPageInfo.CustomerOrder.template;
	}
	
	@RequestMapping(params="checkOut", method=RequestMethod.POST)
	public String checkOutOrder(Model model, CustomerOrderDetailWrapperDto customerDetailWarpper, CustomerOrderInfoDto customerOrderInfoDto) {	
		System.out.print(customerDetailWarpper.getCartList().get(0).getProduct_info_id());
		
		/*for(int i=0;i<customerDetailWarpper.getCartList().size();i++) {
			int product_qty = customerDetailWarpper.getCartList().get(i).getProduct_quantity();
			int product_info_id = customerDetailWarpper.getCartList().get(0).getProduct_info_id();
			Integer product_info = product_info_id;
			InventoryInfoDto dto = service.getQuantity(product_info);
			Integer total_qty = dto.getTotal_quantity();
			if (product_qty > total_qty) {
				model.addAttribute("msg","<p style='color:red'> Quantity not enough.</p>");
			}
			
			
		}*/
		 session.setAttribute("customerDetailOrderList",customerDetailWarpper.getCartList());
		 session.setAttribute("customerOrderInfo",customerOrderInfoDto);
		//List<CustomerDetailOrderInfoDto>  del = CustomerCartDto.getList();
		 
		 return UserPageInfo.Order_Checkout.redirectUrl;
		
	}
	
}
