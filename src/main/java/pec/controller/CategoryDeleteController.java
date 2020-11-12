package pec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pec.service.CategoryService;
import pec.datacode.AdminPageInfo;
@Controller
@RequestMapping("/admin/categoryDelete")

public class CategoryDeleteController {
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(method=RequestMethod.GET,params="top")
	public String initFromMenu() {
		return AdminPageInfo.CATEGORY_INSERT.redirectUrl;
	}
	@RequestMapping( method = RequestMethod.GET)
	public String initialize(@RequestParam("row-check") List<Integer> id,RedirectAttributes attr) {
		for(int i=0;i<id.size();i++) {
			
			categoryService.deleteCategory(id.get(i));
			attr.addFlashAttribute("msg","Category Delete Successful.");
		}
		return AdminPageInfo.CATEGORY_INSERT.redirectUrl;
	}

	
}


