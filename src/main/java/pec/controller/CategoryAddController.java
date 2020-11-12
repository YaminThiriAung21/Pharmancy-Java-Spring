package pec.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
import java.util.List;
//import java.util.Map;
//import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import pec.dto.AccountDto;
import pec.dto.CategoryDto;
//import pec.service.AccountService;
import pec.service.CategoryService;
import pec.datacode.AdminPageInfo;
/**
 * category Add Controller
 * 26/8/2020
 * @author Khin Thant Sin
 */
@Controller
@RequestMapping("/admin/categoryAdd")
public class CategoryAddController {
	@Autowired
	CategoryService categoryService;
	
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.CATEGORY_INSERT.redirectUrl;
	}
		
	@RequestMapping(method=RequestMethod.GET)
	public String initialize(Model model,HttpServletRequest req) throws IOException {


		//Map<String, String[]> reqMap= req.getParameterMap();
		//reqMap.forEach((key, value)-> {model.addAttribute(key, value[0]);});
		
		//model.addAttribute("categoryDto",new CategoryDto());
		List<CategoryDto> categoryList = categoryService.getCategoryList();
		
		
		model= categoryService.messageProperty(model);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("categoryDto", new CategoryDto());
		model.addAttribute("data_tb","save");
		return AdminPageInfo.CATEGORY_INSERT.template;
	}
	
	@RequestMapping(params="save", method=RequestMethod.POST)
	public String add(Model model, @Valid @ModelAttribute("categoryDto") CategoryDto categoryDto, BindingResult res, RedirectAttributes attr) throws IOException{
int count = categoryService.getDuplicate(categoryDto.getCategory_code());
		
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		
		if(count != 0) {
			
			model.addAttribute("errormsg", prop.getProperty("E000138"));
			List<CategoryDto> categoryList = categoryService.getCategoryList();
			model.addAttribute("categoryList",categoryList);
			return AdminPageInfo.CATEGORY_INSERT.template;
		}
		else{
			categoryService.addCategory(categoryDto);
		model.addAttribute("msg", "Category Add Successful.");
		}
		
		List<CategoryDto> categoryList = categoryService.getCategoryList();
		
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("categoryDto", new CategoryDto());
		
		return AdminPageInfo.CATEGORY_INSERT.template;
	}
	
	}


