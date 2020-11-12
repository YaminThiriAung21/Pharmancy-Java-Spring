package pec.controller;

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
import pec.dto.EmployeeInfoDto;
import pec.service.EmployeeInfoService;
import pec.datacode.AdminPageInfo;

/**
 * Account Insert Controller 28/8/2020
 * 
 * @author Thiha
 */
@Controller
@RequestMapping("/admin/employeeInfoDelete")
public class EmployeeInfoDeleteController {

	@Autowired
	EmployeeInfoService EmployeeInfoService;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.EMPLOYEE_INFO.redirectUrl;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initialize(@RequestParam("row-check") List<Integer> id, RedirectAttributes attr) {
		for(int i=0;i<id.size();i++) {
			EmployeeInfoService.employeeInfoDelete(id.get(i));	
			EmployeeInfoService.employeeLoginDelete(id.get(i));
		}
		attr.addFlashAttribute("data_tb", "save");
		return AdminPageInfo.EMPLOYEE_INFO.redirectUrl;
	}
	
}
