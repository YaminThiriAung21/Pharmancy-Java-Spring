package pec.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pec.datacode.UserPageInfo;
import pec.dto.HomeDto;
import pec.service.HomeService;

/**
 * Home Controller
 * 8/9/2020
 * @author Htet Tayzar Oo
 */

@Controller
@RequestMapping("/user/pharmacyHome")
public class HomeController {
	
	@Autowired
	private HomeService service;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		session.removeAttribute("officeId");
		session.removeAttribute("customerDetailOrderList");
		session.removeAttribute("customerOrderInfo");
		session.removeAttribute("customerOrderSize");
		return UserPageInfo.USER_Home.redirectUrl;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String initPharmacyHome(Model model, HttpServletRequest req) throws IOException {		
		Map<String, String[]> reqMap = req.getParameterMap();
		reqMap.forEach((key,value)-> {
			model.addAttribute(key, value[0]);
		});		
		
		List<HomeDto> home = service.getOfficeNameList();
		model.addAttribute("officeSearch", home);
		return UserPageInfo.USER_Home.template;
	}
	
	@RequestMapping(value="/{office_id}",method = RequestMethod.GET)
	public String officeSelectByImg(Model model,@PathVariable("office_id") Integer office_id,RedirectAttributes attr) {
		session.setAttribute("officeId",office_id);
		session.setAttribute("customerOrderSize",0);
		return UserPageInfo.Product_list.redirectUrl;
	 
	}
	
	@RequestMapping(value="/selectOffice/{office_id}",method = RequestMethod.GET)
	public String officeSelectBySelectBox(Model model,@PathVariable("office_id") Integer office_id,RedirectAttributes attr) {
		
		session.setAttribute("officeId",office_id);
		session.setAttribute("customerOrderSize",0);
		return UserPageInfo.Product_list.redirectUrl;
	  
	}
	
}
