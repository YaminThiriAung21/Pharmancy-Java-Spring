package pec.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pec.service.ProductInfoService;
import pec.datacode.AdminPageInfo;

@Controller
@RequestMapping("/admin/productDelete")
public class ProductDeleteController {
	
	@Autowired
	private ProductInfoService productInfoService;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.PRODUCT_LIST.redirectUrl;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("row-check") List<Integer> id, RedirectAttributes attr) {
		for(int i=0;i<id.size();i++) {
			productInfoService.deleteProduct(id.get(i));	
			attr.addFlashAttribute("msg","Deleted Successfully!");
		}
		return AdminPageInfo.PRODUCT_LIST.redirectUrl;
	}
}