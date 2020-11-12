package pec.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import pec.service.AccountService;
import pec.datacode.AdminPageInfo;

/**
 * Account Insert Controller
 * 25/8/2020
 * @author Su Su Lin
 */
@Controller
@RequestMapping("/admin/accountRegister")
public class AccountRegisterController {
	
	@Autowired
	AccountService accountService;
	
	@ModelAttribute("accdropdown")
	public List<String> getAccountDropdown(){
		List<String> res = new ArrayList<String>();
		res.add("ADMIN");
		res.add("USER");
		return res;		
	}
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.ACCOUNT_INSERT.redirectUrl;
	}
		
	@RequestMapping(method=RequestMethod.GET)
	public String initialize(Model model) throws IOException {
		model= accountService.messageProperty(model);
		model.addAttribute("accountDto", new AccountDto());
		return AdminPageInfo.ACCOUNT_INSERT.template;
	}
	
	@RequestMapping(params="register", method=RequestMethod.POST)
	public String register(Model model, @Valid AccountDto accountDto, BindingResult res, RedirectAttributes attr) {
		if(res.hasErrors()) {
			model.addAttribute("msg","<p style='color:red'>Please Input User Name.</p>");
			return AdminPageInfo.ACCOUNT_INSERT.template;		
		}
		attr.addFlashAttribute("msg", "User Register Successful.");
		return AdminPageInfo.ACCOUNT_INSERT.redirectUrl;
	}

}
