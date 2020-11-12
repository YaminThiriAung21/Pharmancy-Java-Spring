package pec.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pec.datacode.UserPageInfo;
import pec.dto.ProductDetailDto;
import pec.service.ProductListService;

/**
 * ProductList Controller
 * 28/8/2020
 * @author Mi Phue Phue Myint
 */
@Controller
@RequestMapping("/user/productList")
public class ProductListController {

	@Autowired
	private ProductListService service;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(method=RequestMethod.GET)
	public String productList(Model model, HttpServletRequest req) {
		Map<String, String[]> reqMap = req.getParameterMap();
		reqMap.forEach((key,value)-> {
			model.addAttribute(key, value[0]);
		});
		Integer office_id = (Integer) session.getAttribute("officeId");
		System.out.print(office_id);
		if (office_id == null) {
			return UserPageInfo.USER_Home.redirectUrl;
		} else {
			List<ProductDetailDto> prods = service.getProductList(office_id);
			model.addAttribute("prod",new ProductDetailDto());	
			model.addAttribute("prods",prods);
			return UserPageInfo.Product_list.template;
		}	
	}
	
	@RequestMapping(params="search",method = RequestMethod.GET)
	public String productSearch(Model model,ProductDetailDto prod,RedirectAttributes attr) {
		Integer office_id = (Integer) session.getAttribute("officeId");
	    List<ProductDetailDto> prods = service.searchProductByName(office_id,prod.getProduct_name());
	    if(prods.size()<1) {
	    	model.addAttribute("prod",new ProductDetailDto());
		    model.addAttribute("prods",prods);
		    model.addAttribute("displayMessage", "No Product Name Found!!!");
	    	return UserPageInfo.Product_list.template;
	    }else {
	    	model.addAttribute("prod",new ProductDetailDto());
		    model.addAttribute("prods",prods);
			return UserPageInfo.Product_list.template;
	    }    
	}
	
	@RequestMapping(value="/medicineTypeSearch/{medicine_info_id}",method = RequestMethod.GET)
	public String medicineTypeSearch(Model model,@PathVariable("medicine_info_id") int medicine_info_id) {
		Integer office_id = (Integer) session.getAttribute("officeId"); 
		List<ProductDetailDto> prods = service.searchProductByMedicineType(office_id,medicine_info_id);
		ProductDetailDto prod = new ProductDetailDto();	
		prod.setMedicine_info_id(medicine_info_id);
		
		model.addAttribute("prod",prod);
		
	    model.addAttribute("prods",prods);
		
		return UserPageInfo.Product_list.template;	
		
	}
	
	@RequestMapping(value="/categorySearch/{category_info_id}",method = RequestMethod.GET)
	public String categorySearch(Model model,@PathVariable("category_info_id") int category_info_id) {
		Integer office_id = (Integer) session.getAttribute("officeId");
		List<ProductDetailDto> prods = service.searchProductByCategory(office_id,category_info_id);
		ProductDetailDto prod = new ProductDetailDto();	
		/*prods=service.getMedicineTypeById(medicine_info_id);*/
		if(category_info_id != 0) {
			prod.setCategory_info_id(category_info_id);
		}
		
		model.addAttribute("prod",prod);
	    model.addAttribute("prods",prods);

		return UserPageInfo.Product_list.template;
	}
	
	
	@ModelAttribute("pplist")
	public List<ProductDetailDto> getCategoryList() {
		Integer office_id = (Integer) session.getAttribute("officeId");
		List<ProductDetailDto> res = new ArrayList<ProductDetailDto>();
		if (office_id != null) {
			res = service.getCategoryList(office_id);
		} 
		return res;
}
	@ModelAttribute("mtlist")
	public List<ProductDetailDto> getMedicineTypeList() {
		Integer office_id = (Integer) session.getAttribute("officeId");
		List<ProductDetailDto> res = new ArrayList<ProductDetailDto>();
		if (office_id != null) {
			res=service.getMedicineTypeList(office_id);
		}
		System.out.println("ressss : "+res);
		return res;
	}
	
	
	@RequestMapping(params="medicineName",method=RequestMethod.GET)
	public String medicineName(Model model, HttpServletRequest req) {
		Map<String, String[]> reqMap = req.getParameterMap();
		reqMap.forEach((key,value)-> {
			model.addAttribute(key, value[0]);
		});
		int office_id = (int) session.getAttribute("officeId");
		List<ProductDetailDto> prods = service.getMedicineNameList(office_id);
		model.addAttribute("prod",new ProductDetailDto());
		model.addAttribute("prods",prods);
		return UserPageInfo.Product_list.template;
	}
	
	
}
