package pec.controller;

import java.io.IOException;
import java.util.List;
//import java.io.IOException;
//import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pec.dto.CategoryDto;
import pec.service.CategoryService;
//import pec.service.CategoryService;
import pec.datacode.AdminPageInfo;

/**
 * category Update Controller
 * 27/8/2020
 * @author Khin Thant Sin
 */
@Controller
@RequestMapping("/admin/categoryUpdate")
public class CategoryUpdateController {
	
	@Autowired
    private CategoryService categoryService;
	
	
	@RequestMapping(value="/{category_info_id}", method = RequestMethod.GET)
	public String getCategoryDto(Model model,@PathVariable("category_info_id") Integer id,HttpServletRequest req) throws IOException{
		
		
		
	/*	Map<String, String[]> reqMap= req.getParameterMap();
		reqMap.forEach((key, value)-> {
			model.addAttribute(key, value[0]);});*/
	
	
	
		List<CategoryDto> categoryList = categoryService.getCategoryList();
		model= categoryService.messageProperty(model);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("categoryDto",new CategoryDto());
		model.addAttribute("data_tb","save");
		CategoryDto categoryDto=categoryService.getCategoryDto(id);
		
		model.addAttribute("categoryDto",categoryDto);
		
		return AdminPageInfo.CATEGORY_UPDATE.template;
	}
	
	
	
	@RequestMapping(params="update",method=RequestMethod.POST)
	public String updateCategory(@Valid CategoryDto categoryDto,BindingResult res,RedirectAttributes attr) { 
	/*{if(res.hasErrors()) {
		model.addAttribute("msg", "<p style='color:red'>User input is invalid. </p>");
		return AdminPageInfo.CATEGORY_UPDATE.template;	
	}*/

     categoryService.updateCategory(categoryDto);
	attr.addFlashAttribute("msg","Category Update Successful");
	return AdminPageInfo.CATEGORY_INSERT.redirectUrl;
	}
}