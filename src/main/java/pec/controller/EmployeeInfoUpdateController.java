package pec.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;
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
 * Account Insert Controller 27/8/2020
 * 
 * @author Thiha
 */
@Controller
@RequestMapping("/admin/employeeInfoUpdate")
public class EmployeeInfoUpdateController {

	@Autowired
	EmployeeInfoService EmployeeInfoService;
	
	@Autowired
	private HttpSession session;
	
	@ModelAttribute("accDropdown")
	public List<String> getStatusDropdown(){
		List<String> res = new ArrayList<String>();
		res.add("Active");
		res.add("Resigned");
		return res;	
	}	
	
	@ModelAttribute("OfficeName")
	public List<String> getOfficeName(){
		List<String> res = new ArrayList<String>();
		res = EmployeeInfoService.getOfficeName();
		return res;
	}
	
	@ModelAttribute("accRole")
	public List<String> getAccRole(){
		List<String> res = new ArrayList<String>();
		res.add("ADMIN");
		res.add("SYSTEM ADMIN");
		return res;	
	}	
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.EMPLOYEE_INFO_UPDATE.redirectUrl;
	}

	@RequestMapping(value = "/{emp_info_id}", method = RequestMethod.GET)
	public String initialize(Model model, @PathVariable("emp_info_id") int id) throws IOException {
		
		EmployeeInfoDto empacc = new EmployeeInfoDto();
		empacc = EmployeeInfoService.getUpdate(id);
		
		System.out.println("dfd"+empacc);
		
		model= EmployeeInfoService.messageProperty(model);
		model.addAttribute("empacc", empacc);
		model.addAttribute("data_tb", "save");
		return AdminPageInfo.EMPLOYEE_INFO_UPDATE.template;
	}
	
	@RequestMapping(params="updated", method = RequestMethod.POST)
	public String updated(Model model,@Valid @ModelAttribute("empacc")EmployeeInfoDto employeeInfoDto, BindingResult res, RedirectAttributes attr) throws IOException {
		
		List<EmployeeInfoDto>empList = EmployeeInfoService.getDuplicate(employeeInfoDto.getEmployee_info_id()); 
		
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		for(int i=0;i<empList.size();i++) {
			if(employeeInfoDto.getEmail_address().equals(empList.get(i).getEmail_address())) {
				model= EmployeeInfoService.messageProperty(model);
				model.addAttribute("errormsg", prop.getProperty("E000009"));
				return AdminPageInfo.EMPLOYEE_INFO_UPDATE.template;
			}
			else if(employeeInfoDto.getPhone_number().equals(empList.get(i).getPhone_number())) {
				model= EmployeeInfoService.messageProperty(model);
				model.addAttribute("errormsg", prop.getProperty("E000110"));
				return AdminPageInfo.EMPLOYEE_INFO_UPDATE.template;
			}
		}
		/*if(res.hasErrors()) {
			System.out.println("I'm here2 : "+employeeInfoDto.getEmployee_code()+" : "+employeeInfoDto.getEmployee_name());
			model.addAttribute("displayMessage", "<p style='color:red'>User input is invalid.</p>");
			return AdminPageInfo.EMPLOYEE_INFO_UPDATE.template;
		}*/
		
		EmployeeInfoDto empdto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		
		Date date=new Date();
		employeeInfoDto.setUpdate_user(empdto.getEmployee_name());
		employeeInfoDto.setUpdate_date(date);
		
		String passwordss = employeeInfoDto.getPassword();
		if(passwordss != null && !passwordss.isEmpty()) {
			EmployeeInfoService.employeePasswordUpdate(employeeInfoDto);
		}
		
		employeeInfoDto.setOffice_info_id(EmployeeInfoService.getOfficeId(employeeInfoDto.getOffice_name()));
		
		EmployeeInfoService.employeeInfoUpdate(employeeInfoDto);
		EmployeeInfoService.employeeLoginUpdate(employeeInfoDto);
		
		attr.addFlashAttribute("data_tb", "save");
		attr.addFlashAttribute("msg", "Employee Updated Successfully.");
		return AdminPageInfo.EMPLOYEE_INFO.redirectUrl;
	}
	
	
	
}
