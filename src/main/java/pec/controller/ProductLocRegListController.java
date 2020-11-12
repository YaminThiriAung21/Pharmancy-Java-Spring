package pec.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pec.dto.ProductLocDto;
import pec.service.ProductLocService;
import pec.datacode.AdminPageInfo;

/**
 * Product Location Register List Controller
 * 25/8/2020
 * @author Thet Mon Hnin
 */

@Controller
@RequestMapping("/admin/prodcutlocRegisterList")
public class ProductLocRegListController {
	@Autowired
	private ProductLocService productlocService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String productlocList(Model model,HttpServletRequest req,RedirectAttributes attr ) {
		Map<String, String[]> reqMap=req.getParameterMap();
		
		reqMap.forEach((key,value)->{
			model.addAttribute(key,value[0]);
			});
		List<ProductLocDto> productlocList = productlocService.getProductLocRegList();
	
		model.addAttribute("productlocList",productlocList);
		return AdminPageInfo.PRODUCT_LOCATION_INSERT_LIST.template;		 
		
	}
	
}
