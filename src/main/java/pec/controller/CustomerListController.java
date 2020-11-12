package pec.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pec.service.CustomerInfoService;
import pec.datacode.AdminPageInfo;
import pec.dto.CustomerInfoDto;

/**
 * Customer List Controller 27/8/2020
 * 
 * @author Htet Wai Yan Aung
 */

@Controller
@RequestMapping("/admin/customerList")
public class CustomerListController {

	@Autowired
	private CustomerInfoService service;

	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.CUSTOMER_LIST.redirectUrl;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String accList(Model model, HttpServletRequest req) {

		Map<String, String[]> reqMap = req.getParameterMap();
		reqMap.forEach((key, value) -> {
			model.addAttribute(key, value[0]);
		});
		List<CustomerInfoDto> customerList = service.getCustomerList();
		model.addAttribute("customerList", customerList);
		return AdminPageInfo.CUSTOMER_LIST.template;
	}

}
