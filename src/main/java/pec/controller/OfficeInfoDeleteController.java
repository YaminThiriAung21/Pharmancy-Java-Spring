package pec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pec.dto.CustomerInfoDto;
import pec.service.OfficeInfoService;
import pec.datacode.AdminPageInfo;

/**
 * Office Info Delete
 * 1/9/2020
 * @author Aung San Htay
 */
@Controller
@RequestMapping("/admin/officeInfoDelete")
public class OfficeInfoDeleteController {
	
	@Autowired
	private OfficeInfoService officeInfoservice;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.OFFICE_INFO.redirectUrl;
	}
		
	@RequestMapping(method = RequestMethod.GET)
	public String delete(Model model, @RequestParam("row-check") List<Integer> id) {
		
		for(int i=0; i<id.size();i++) {
			officeInfoservice.delete(id.get(i));
		}
		return AdminPageInfo.OFFICE_INFO.redirectUrl;
	}
	
}

