package pec.controller;

import java.io.IOException;
/**
 * ProductDetailController
 * 28/8/2020
 * @author Kyaw Min Khant, Zin Nwe Htet
 */
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
import pec.datacode.UserPageInfo;
import pec.dto.ProductDetailDto;
import pec.service.ProductDetailService;

@Controller
@RequestMapping("/user/ProductDetail")
public class ProductDetailController {
	
	@Autowired
	private ProductDetailService productdetailService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(method=RequestMethod.GET)
	public String initProductDetail(Model model, HttpServletRequest req) {
		Map<String, String[]> reqMap = req.getParameterMap();
		reqMap.forEach((key,value)-> {
			model.addAttribute(key, value[0]);
		});
		return UserPageInfo.Product_list.redirectUrl;
	
	}
	
	@RequestMapping(value="/{product_info_id}",method=RequestMethod.GET)
	public String initProductDetail(Model model,@PathVariable("product_info_id") int product_info_id) throws IOException {		
		model = productdetailService.messageProperty(model);
		ProductDetailDto prod =  productdetailService.getProductById(product_info_id);
		model.addAttribute("prod", prod );
		System.out.println("product_info_id ...... "+prod.getProduct_info_id());
		return UserPageInfo.Product_Detail.template;
	}
}
