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
import pec.dto.OfficeDto;
import pec.service.OfficeInfoService;
import pec.datacode.AdminPageInfo;

/**
 * Office Management Controller
 * 25/8/2020
 * @author Aung San Htay
 */
@Controller
@RequestMapping("/admin/officeInfo")
public class OfficeInfoController {
	
	@Autowired
	pec.service.OfficeInfoService OfficeInfoService;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.OFFICE_INFO.redirectUrl;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String officeList(Model model, HttpServletRequest req) {
		
		Map<String, String[]> reqMap = req.getParameterMap();
		reqMap.forEach((key, value) -> {
			model.addAttribute(key, value[0]);
		});
		List<OfficeDto> officeList = OfficeInfoService.getOfficeInfo();
		model.addAttribute("officeList", officeList);
		return AdminPageInfo.OFFICE_INFO.template;
	}
}
