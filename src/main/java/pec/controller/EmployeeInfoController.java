package pec.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import pec.dto.AccountDto;
import pec.dto.EmployeeInfoDto;
import pec.service.AccountService;
import pec.service.EmployeeInfoService;
import pec.datacode.AdminPageInfo;

/**
 * Employee Info Controller
 * 27/8/2020
 * @author Thiha
 */
@Controller
@RequestMapping("/admin/employeeInfo")
public class EmployeeInfoController {
	
	@Autowired
	private EmployeeInfoService service;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
	
		
		return AdminPageInfo.EMPLOYEE_INFO.redirectUrl;
	}
		
	@RequestMapping(method=RequestMethod.GET)
	public String empList(Model model, HttpServletRequest req) {
		
		Map<String, String[]> reqMap = req.getParameterMap();
		reqMap.forEach((key,value)-> {
			model.addAttribute(key, value[0]);
		});
		
		List<EmployeeInfoDto> empList = service.getEmployeeInfo();
		//List<EmployeeInfoDto> empList = new ArrayList<EmployeeInfoDto>();
		model.addAttribute("empList",empList);
		return AdminPageInfo.EMPLOYEE_INFO.template;
	}

}
