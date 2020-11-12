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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pec.dto.AccountDto;
import pec.dto.EmployeeInfoDto;
import pec.dto.ProductLocDto;
import pec.service.ProductLocService;
import pec.datacode.AdminPageInfo;

/**
 * Product Location Register Controller
 * 25/8/2020
 * @author Thet Mon Hnin
 */
@Controller
@RequestMapping("/admin/prodcutlocRegister")
public class ProductLocRegisterController {
	
	@Autowired
    private ProductLocService productlocService;
	
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.PRODUCT_LOCATION_INSERT.redirectUrl;
	}
		
	@RequestMapping(method=RequestMethod.GET)
	public String initialize(Model model) throws IOException {
		model= productlocService.messageProperty(model);
		model.addAttribute("productlocDto", new ProductLocDto());
		return AdminPageInfo.PRODUCT_LOCATION_INSERT.template;
	}
	
	@RequestMapping(params="register", method=RequestMethod.POST)
	public String register(Model model, @Valid @ModelAttribute("productlocDto") ProductLocDto productlocDto, BindingResult res, RedirectAttributes attr) throws IOException {
	
		List<ProductLocDto>locList = productlocService.getDuplicateRegister(); 
		
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		
		if(res.hasErrors()) {
			model.addAttribute("msg","<p style='color:red'>Please Input Location .</p>");
			return AdminPageInfo.PRODUCT_LOCATION_INSERT.template;		
		}
		
		for(int i=0;i<locList.size();i++) {
			if(productlocDto.getLocation_code().equals(locList.get(i).getLocation_code())) {
				model= productlocService.messageProperty(model);
				model.addAttribute("errormsg", prop.getProperty("E000084"));
				return AdminPageInfo.PRODUCT_LOCATION_INSERT.template;
			}
			else if(productlocDto.getProduct_location_place().equals(locList.get(i).getProduct_location_place())) {
				model= productlocService.messageProperty(model);
				model.addAttribute("errormsg", prop.getProperty("E000085"));
				return AdminPageInfo.PRODUCT_LOCATION_INSERT.template;
			}
		}
		
		productlocService.registerProductLoc(productlocDto);
		attr.addFlashAttribute("msg", "Product Location Register Successful.");
		return AdminPageInfo.PRODUCT_LOCATION_INSERT_LIST.redirectUrl;
	}

}


