package pec.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pec.dto.ProductLocDto;
import pec.service.ProductLocService;
import pec.datacode.AdminPageInfo;

/**
 * Product Location List Delete Controller 
 * 6/9/2020
 * @author Thet Mon Hnin
 */
@Controller
@RequestMapping("/admin/productloclistDelete")
public class ProductLocRegDeltController {

	@Autowired
	ProductLocService productlocService;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.PRODUCT_LOCATION_LIST_DELETE.redirectUrl;
	}
	
	

	@RequestMapping(method = RequestMethod.GET)
	public String initialize(@RequestParam("row-check") List<Integer> id,Model model) throws IOException {
		for(int i=0;i<id.size();i++) {
			productlocService.delete(id.get(i));	
		}
		List<ProductLocDto> productlocList = productlocService.getProductLocRegList();
		
		model.addAttribute("productlocList",productlocList);
		return AdminPageInfo.PRODUCT_LOCATION_LIST_DELETE.template;
	}

}
	