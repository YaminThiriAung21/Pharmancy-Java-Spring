package pec.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pec.datacode.UserPageInfo;
import pec.dto.CustomerDetailOrderInfoDto;
import pec.dto.ProductDetailDto;
import pec.service.ProductDetailService;

@Controller
@RequestMapping("/user/AddtoCart")
public class AddToCartController {
	@Autowired
	HttpSession session;
	
	@Autowired
	private ProductDetailService productDetailService;
	
	@RequestMapping(params="addToCart", method=RequestMethod.POST)
	public String selectedProduct(Model md,HttpServletRequest request,@ModelAttribute("prod")ProductDetailDto productDetailDto,RedirectAttributes attr) {
		System.out.println("Data in");
		String qty = request.getParameter("quantity");
		String id = request.getParameter("id");
		ProductDetailDto dto = productDetailService.getProductById(Integer.parseInt(id));
		Integer totalqty = dto.getTotal_quantity();
		Integer qty1 = Integer.parseInt(qty);
		
		CustomerDetailOrderInfoDto customerDetailDto = new CustomerDetailOrderInfoDto();
		customerDetailDto.setProduct_info_id(Integer.parseInt(request.getParameter("id")));
		customerDetailDto.setProduct_image_name(dto.getProduct_image_name());
		customerDetailDto.setProduct_name(dto.getProduct_name());
		customerDetailDto.setProduct_price(dto.getSell_price());
		customerDetailDto.setProduct_quantity(Integer.parseInt(request.getParameter("quantity")));
		customerDetailDto.setProduct_total_price(customerDetailDto.getProduct_price()*customerDetailDto.getProduct_quantity());
		
		if (qty1 > totalqty) {
			attr.addFlashAttribute("displayMessage","Quantity Not Enough. Please Choose Other Product.");
			return UserPageInfo.Product_list.redirectUrl;
		}
		
		List<CustomerDetailOrderInfoDto> customerList = (List<CustomerDetailOrderInfoDto>) session.getAttribute("customerDetailOrderList");
		if(customerList  == null){
		List<CustomerDetailOrderInfoDto> customerDetailOrderList = new ArrayList<CustomerDetailOrderInfoDto>();
		customerDetailOrderList.add(customerDetailDto);
		session.setAttribute("customerDetailOrderList",customerDetailOrderList);
		} else {
		customerList.add(customerDetailDto);
		session.setAttribute("customerDetailOrderList",customerList);
		}
		/*md.addAttribute("prod", dto );
		md.addAttribute("quantity", qty );*/
		return UserPageInfo.CustomerOrder.redirectUrl;
	} 
}
