package pec.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pec.datacode.AdminPageInfo;
import pec.dto.EmployeeInfoDto;
import pec.security.LoginUserDetails;
import pec.service.LoginService;

/**
 * Login Controller
 * 25/8/2020
 * @author Su Su Lin
 */
@Controller
public class LoginController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	LoginService loginservice;
	
	@RequestMapping(value="/login",  method = RequestMethod.GET)
	public String login(Model model,Authentication auth, HttpServletRequest req) throws IOException {
		//session.invalidate();
		//session.removeAttribute("EmployeeInfoDto");
		if(auth != null) {
			try {
				req.logout();
			} catch (ServletException e) {
				e.printStackTrace();
			}
		}
		model= loginservice.msgProperty(model);
		return AdminPageInfo.LOGIN.template;
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String loginSuccess(@AuthenticationPrincipal LoginUserDetails user,RedirectAttributes attr) {
		if(user.isSysAdmin()) {
			return AdminPageInfo.CATEGORY_INSERT.redirectUrl;
		} else if(user.isSysManagementAdmin()) {
			return AdminPageInfo.OFFICE_INFO.redirectUrl;
		} else{
			attr.addAttribute("displayMessage", "Employee email and password are invalid!");
			return AdminPageInfo.LOGIN.template;
		}		
		
	}
	
	@RequestMapping(value="/loginError", method = RequestMethod.GET)
	public String loginError(Model model) {
		model.addAttribute("displayMessage", "Employee email and password are invalid!");
		return AdminPageInfo.LOGIN.template;			
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String loginOut(@AuthenticationPrincipal LoginUserDetails user) {
		return AdminPageInfo.LOGIN.redirectUrl;							
	}

}
