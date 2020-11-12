package pec.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pec.dto.ProductLocDto;

import pec.service.ProductLocService;
import pec.datacode.AdminPageInfo;

/**
 * Controller
 * 31/8/2020
 * @author Yin Min Oo
 */
@Controller
@RequestMapping("/admin/productLocUpdate")
public class ProductLocUpdateController {
	
	@Autowired
	private ProductLocService productlocService;
	
	@ModelAttribute("accdropdown")
	public List<String> getAccountDropdown(){
		List<String> res = new ArrayList<String>();
		res.add("ADMIN");
		res.add("USER");
		return res;		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String initFromMenu() {
		return AdminPageInfo.PRODUCT_LOCATION_UPDATE.redirectUrl;
	}
		
	@RequestMapping(value = "/{loc_info_id}",method=RequestMethod.GET)
	public String initialize(Model model,@PathVariable("loc_info_id")int id) throws IOException {
		
		ProductLocDto productlocDto = new ProductLocDto();
		productlocDto = productlocService.getUpdate(id);
		model= productlocService.messageProperty(model);
		model.addAttribute("productlocDto", productlocDto);
		return AdminPageInfo.PRODUCT_LOCATION_UPDATE.template;
		
	}
	
	@RequestMapping(params="updating", method=RequestMethod.POST)
	public String updating(Model model, @Valid @ModelAttribute("productlocDto")ProductLocDto productlocDto, BindingResult res, RedirectAttributes attr) throws IOException{
		
		List<ProductLocDto>locList = productlocService.getDuplicate(productlocDto.getProduct_location_info_id()); 
		
		
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		
		if(res.hasErrors()) {
			
			model.addAttribute("msg","<p style='color:red'>Please Input Valid Data.</p>");
			return AdminPageInfo.PRODUCT_LOCATION_UPDATE.template;	
		}
		for(int i=0;i<locList.size();i++) {
			if(productlocDto.getLocation_code().equals(locList.get(i).getLocation_code())) {
				model= productlocService.messageProperty(model);
				model.addAttribute("errormsg", prop.getProperty("E000084"));
				return AdminPageInfo.PRODUCT_LOCATION_UPDATE.template;
			}
			else if(productlocDto.getProduct_location_place().equals(locList.get(i).getProduct_location_place())) {
				model= productlocService.messageProperty(model);
				model.addAttribute("errormsg", prop.getProperty("E000085"));
				return AdminPageInfo.PRODUCT_LOCATION_UPDATE.template;
			}
		}
		
		
		productlocService.updateProductLoc(productlocDto);
		
		attr.addFlashAttribute("msg", "Product Location Update Successful.");
		return AdminPageInfo.PRODUCT_LOCATION_INSERT_LIST.redirectUrl;
	}
	
			
		

}