package pec.controller;

import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pec.dto.CustomerInfoDto;
import pec.service.CustomerInfoService;
import pec.datacode.AdminPageInfo;

/**
 * Customer Info Service
 * 28/8/2020
 * @author Htet Wai Yan Aung
 */

@Controller
@RequestMapping("/admin/customerUpdate")
public class CustomerEditController {
	
	@Autowired
	private CustomerInfoService service;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.CUSTOMER_UPDATE.redirectUrl;
	}

	@RequestMapping(value="/{customerId}", method = RequestMethod.GET)
	public String updateList(Model model, @PathVariable("customerId") int customer_info_id) throws IOException {
		
		CustomerInfoDto info = new CustomerInfoDto();
		info = service.getUpdate(customer_info_id); 
		model= service.messageProperty(model);
		model.addAttribute("info",info);
		return AdminPageInfo.CUSTOMER_UPDATE.template;
	}
	
	@RequestMapping(params="update", method = RequestMethod.POST)
	public String update(Model model,@Valid CustomerInfoDto customerDto, BindingResult res, RedirectAttributes attr) {
		
		/*if(res.hasErrors()) {
			model.addAttribute("msg","<p style='color:red'>Please Input All.</p>");
			return AdminPageInfo.CUSTOMER_UPDATE.template;
		}*/
		service.update(customerDto);
		attr.addFlashAttribute("msg", "User Updated Successfully.");
		return AdminPageInfo.CUSTOMER_LIST.redirectUrl;
	}
	
}

