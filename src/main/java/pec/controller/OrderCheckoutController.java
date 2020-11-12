package pec.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pec.datacode.UserPageInfo;
import pec.dto.CustomerDetailOrderInfoDto;
import pec.dto.CustomerInfoDto;
import pec.dto.CustomerOrderInfoDto;
import pec.dto.EmployeeInfoDto;
import pec.dto.LoginUserInfoDto;
import pec.service.UserService;


/**
 * Order Checkout Controller
 * 25/8/2020
 * @author Aung Htet Lwin
 */

@Controller
@RequestMapping("/user/orderCheckout")

public class OrderCheckoutController {
	@Autowired
	UserService service;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return UserPageInfo.Order_Checkout.redirectUrl;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String initialize(Model model) throws IOException {
		model = service.msgProperty(model);
		//List<CustomerDetailOrderInfoDto> checkout=getList();
		//CustomerOrderInfoDto cardtotal=new CustomerOrderInfoDto();
		//cardtotal.setTotal_price(1000.00);
		List<CustomerDetailOrderInfoDto> checkout =(List<CustomerDetailOrderInfoDto>) session.getAttribute("customerDetailOrderList");
		CustomerOrderInfoDto cardtotal = (CustomerOrderInfoDto) session.getAttribute("customerOrderInfo");
		
		model.addAttribute("checkout",checkout);
		model.addAttribute("cardtotal",cardtotal);
		model.addAttribute("userLoginDto", new LoginUserInfoDto());
		model.addAttribute("userDto", new CustomerInfoDto());
		return UserPageInfo.Order_Checkout.template;
	}

	/**
	* Customer Login Form 9/9/2020
	*
	* @author Nyi Min Aye
	*/

	@SuppressWarnings("null")
	@RequestMapping(params="login_success", method=RequestMethod.POST)
	public String login(Model model, LoginUserInfoDto userLoginDto, BindingResult res, RedirectAttributes attr) throws IOException {
	/*if(res.hasErrors()) {
	model.addAttribute("logErrMsg","<p style='color:red'>Please Input All.</p>");
	return UserPageInfo.Order_Checkout.template;
	}*/
	Properties prop = new Properties();
	prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"),
	StandardCharsets.UTF_8));
	CustomerInfoDto empDto = service.getLoginEmployeeInfo(userLoginDto.getLog_email_address());

	if(empDto == null) {
		CustomerInfoDto userDto = service.getLoginCustomerInfo(userLoginDto.getLog_email_address());
		if(userDto == null) {
			attr.addFlashAttribute("errorMsg", prop.getProperty("E000115"));
	
		} else {
			//String pass_encode = new BCryptPasswordEncoder().encode();
			if(new BCryptPasswordEncoder().matches(userLoginDto.getLog_password().trim(), userDto.getPassword())) {
			attr.addFlashAttribute("logMsg", "Customer Login Process Successful.");
			session.setAttribute("loginUserDto", userDto);
		} else {
			attr.addFlashAttribute("pwdMsg", prop.getProperty("E000114"));
			}
		}

	} else {
	//String customer_pass_encode = new BCryptPasswordEncoder().encode(userLoginDto.getLog_password().trim());
	if(new BCryptPasswordEncoder().matches(userLoginDto.getLog_password().trim(), empDto.getPassword())) {
		attr.addFlashAttribute("logMsg", "Customer Login Process Successful.");
		session.setAttribute("loginUserDto", empDto);
	} else {
		attr.addFlashAttribute("pwdMsg", prop.getProperty("E000114"));
		}
	}
	return UserPageInfo.Order_Checkout.redirectUrl;
	}
	
	/**
	 * Customer Register Form 9/9/2020
	 * 
	 * @author Htet Wai Yan Aung
	 */	

	@RequestMapping(params = "signup", method = RequestMethod.POST)
	public String registered(Model model, @Valid @ModelAttribute("userDto") CustomerInfoDto userDto, BindingResult res,
			RedirectAttributes attr) throws IOException {

		int count = service.getDuplicate(userDto.getEmail_address());
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"),
				StandardCharsets.UTF_8));

		if (count != 0) {
			model = service.msgProperty(model);
			model.addAttribute("dupMsg", prop.getProperty("E000009"));
			List<CustomerDetailOrderInfoDto> checkout =(List<CustomerDetailOrderInfoDto>) session.getAttribute("customerDetailOrderList");
			CustomerOrderInfoDto cardtotal = (CustomerOrderInfoDto) session.getAttribute("customerOrderInfo");
			
			model.addAttribute("checkout",checkout);
			model.addAttribute("cardtotal",cardtotal);
			model.addAttribute("userLoginDto", new LoginUserInfoDto());
			return UserPageInfo.Order_Checkout.template;
		}
		
		//EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		
		Date date = new Date();
		userDto.setRole("USER");
		userDto.setDelete_flag(0);
		userDto.setInsert_user(userDto.getCustomer_name());
		userDto.setInsert_date(date);
		userDto.setUpdate_user(userDto.getCustomer_name());
		userDto.setUpdate_date(date);
		userDto.setOffice_info_id((Integer) session.getAttribute("officeId"));
		service.loginUser(userDto);
		
		int loginId = service.getMaxLogin_user_id(userDto);
		userDto.setLogin_user_info_id(loginId);
		service.signup(userDto);
		attr.addFlashAttribute("regMsg", "Customer Register Process Successful.");
		return UserPageInfo.Order_Checkout.redirectUrl;
	}
	
	
	/*@RequestMapping(method=RequestMethod.GET)
	public String initOrderCheckout(Model model) {
		List<CustomerDetailOrderInfoDto> checkout=getList();
		CustomerOrderInfoDto cardtotal=new CustomerOrderInfoDto();
		cardtotal.setTotal_price(1000.00);
		model.addAttribute("checkout",checkout);
		model.addAttribute("cardtotal",cardtotal);
		return UserPageInfo.Order_Checkout.template;
	}*/
	
	
	public List<CustomerDetailOrderInfoDto> getList(){
		List<CustomerDetailOrderInfoDto> cdoDto = new ArrayList<CustomerDetailOrderInfoDto>();
		CustomerDetailOrderInfoDto dto1 = new CustomerDetailOrderInfoDto();
		CustomerDetailOrderInfoDto dto2 = new CustomerDetailOrderInfoDto();
		
		dto1.setProduct_info_id(1);
		dto1.setProduct_name("Ibuprfen");
		dto1.setProduct_price(50.00);
		dto1.setProduct_quantity(2);
		dto1.setProduct_total_price(dto1.getProduct_price()*dto1.getProduct_quantity());
		
		
		dto2.setProduct_info_id(2);
		dto2.setProduct_name("Bioderma");
		dto2.setProduct_price(50.00);
		dto2.setProduct_quantity(2);
		dto2.setProduct_total_price(dto2.getProduct_price()*dto2.getProduct_quantity());
		
		cdoDto.add(dto1);
		cdoDto.add(dto2);
		return cdoDto;
	}
		
}
