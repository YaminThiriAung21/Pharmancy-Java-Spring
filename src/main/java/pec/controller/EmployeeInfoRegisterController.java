package pec.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
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
@RequestMapping("/admin/employeeInfoRegister")
public class EmployeeInfoRegisterController {

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
		return AdminPageInfo.EMPLOYEE_INFO_REGISTER.redirectUrl;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initialize(Model model) throws IOException {
		
		EmployeeInfoDto empacc = new EmployeeInfoDto();
	
		model= EmployeeInfoService.messageProperty(model);
		model.addAttribute("empacc", empacc);
		return AdminPageInfo.EMPLOYEE_INFO_REGISTER.template;
	}
	
	@RequestMapping(params="register", method = RequestMethod.POST)
	public String updated(Model model,@Valid @ModelAttribute("empacc")EmployeeInfoDto employeeInfoDto, BindingResult res, RedirectAttributes attr) throws IOException {
		
		List<EmployeeInfoDto>empList = EmployeeInfoService.getDuplicateRegister(); 
		
		System.out.println("emplist : "+empList);
		
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		
		for(int i=0;i<empList.size();i++) {
			//System.out.println("loop : "+i);
			if(employeeInfoDto.getEmployee_code().equals(empList.get(i).getEmployee_code())) {
				System.out.println("hi code");
				model= EmployeeInfoService.messageProperty(model);
				model.addAttribute("errormsg", prop.getProperty("E000108"));
				return AdminPageInfo.EMPLOYEE_INFO_REGISTER.template;
			}
			else if(employeeInfoDto.getEmail_address().equals(empList.get(i).getEmail_address())) {
				System.out.println("Hi email");
				model= EmployeeInfoService.messageProperty(model);
				model.addAttribute("errormsg", prop.getProperty("E000009"));
				return AdminPageInfo.EMPLOYEE_INFO_REGISTER.template;
			}
			else if(employeeInfoDto.getPhone_number().equals(empList.get(i).getPhone_number())) {
				System.out.println("hi phone ");
				model= EmployeeInfoService.messageProperty(model);
				model.addAttribute("errormsg", prop.getProperty("E000110"));
				return AdminPageInfo.EMPLOYEE_INFO_REGISTER.template;
			}
		}
		//System.out.println("id : "+ employeeInfoDto.getEmployee_info_id());
		/*if(res.hasErrors()) {
			System.out.println("I'm here2 : "+employeeInfoDto.getEmployee_code()+" : "+employeeInfoDto.getEmployee_name());
			model.addAttribute("displayMessage", "<p style='color:red'>User input is invalid.</p>");
			return AdminPageInfo.EMPLOYEE_INFO_UPDATE.template;
		}*/
		
		
		
		EmployeeInfoDto empdto = new EmployeeInfoDto();
		empdto=(EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		
		System.out.println(session.getAttribute("EmployeeInfoDto"));
		
		employeeInfoDto.setDelete_flag(0);
		employeeInfoDto.setInsert_user(empdto.getEmployee_name());
		
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		employeeInfoDto.setInsert_date(date);
		employeeInfoDto.setUpdate_user(empdto.getEmployee_name());
		employeeInfoDto.setUpdate_date(date);
		
		employeeInfoDto.setOffice_info_id(EmployeeInfoService.getOfficeId(employeeInfoDto.getOffice_name()));
		
		EmployeeInfoService.employeeLoginRegister(employeeInfoDto);
		
		int loginid = EmployeeInfoService.getLoginUserInfoId(employeeInfoDto);
		
		employeeInfoDto.setLogin_user_info_id(loginid);
		EmployeeInfoService.employeeInfoRegister(employeeInfoDto);
		
		
		attr.addFlashAttribute("msg", "Employee Registered Successfully.");
		return AdminPageInfo.EMPLOYEE_INFO.redirectUrl;
	}
	
	
	
}
