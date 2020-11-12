package pec.controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pec.dto.ExpiredItemDto;
import pec.service.ExpireErrorService;
import pec.service.ExpiredItemService;
import pec.datacode.AdminPageInfo;


/**
 * Expired Items Controller
 * 28/8/2020
 * @author Thiri Yadanar Aung
 */
@Controller
@RequestMapping("/expiredItemsHistory")
public class ExpiredItemController {
	
	@Autowired
	ExpireErrorService service;
	@Autowired
	ExpiredItemService expireService;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu(Model model) {
		List<ExpiredItemDto> expireHistoryList = expireService.getExpiredItem();
		System.out.print(expireHistoryList);
		model.addAttribute("expireHistoryList",expireHistoryList);
		return AdminPageInfo.History_Expired_Items.template;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String initialize(Model model) throws IOException {
		model= service.messageProperty(model);
		List<ExpiredItemDto> expireHistoryList = expireService.getExpiredItem();
		System.out.print(expireHistoryList);
		model.addAttribute("expireHistoryList",expireHistoryList);
		return AdminPageInfo.History_Expired_Items.template;
	}
}
