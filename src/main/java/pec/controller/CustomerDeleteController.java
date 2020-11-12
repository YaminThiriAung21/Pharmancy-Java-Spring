package pec.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pec.service.CustomerInfoService;
import pec.datacode.AdminPageInfo;
import pec.dto.CustomerInfoDto;

/**
 * Customer Info Service
 * 28/8/2020
 * @author Htet Wai Yan Aung
 */

@Controller
@RequestMapping("/admin/customerDelete")
public class CustomerDeleteController {
	
	@Autowired
	private CustomerInfoService service;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.CUSTOMER_LIST.redirectUrl;
	}
		
	@RequestMapping(method = RequestMethod.GET)
	public String delete(Model model, @RequestParam("row-check") List<Integer> id, RedirectAttributes attr) {
		
		/*service.delete(id);
		CustomerInfoDto info = new CustomerInfoDto();
		info = service.getDelete(id);
		service.deleteId(info);
		model.addAttribute("info",info);*/
		
		CustomerInfoDto info = new CustomerInfoDto();
		
		for(int i=0; i<id.size();i++) {
			service.delete(id.get(i));
			info = service.getDelete(id.get(i)); 
			service.deleteId(info);
		}
		model.addAttribute("info",info);
		attr.addFlashAttribute("msg", "Selected User Deleted Successfully.");
		return AdminPageInfo.CUSTOMER_LIST.redirectUrl;
	}
	
}

