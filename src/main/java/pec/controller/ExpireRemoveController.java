package pec.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pec.dto.DateRangeDto;
import pec.dto.ExpireDto;
import pec.dto.SupplierIdDto;
import pec.service.ExpireErrorService;
import pec.service.ExpireService;
import pec.datacode.AdminPageInfo;

/** 
 * Account Expire Remove Controller
 * 27/8/2020
 * @author Khin Me Me Latt
 */
@Controller
@RequestMapping("/expireRemove")
public class ExpireRemoveController {
	
	@Autowired
	ExpireErrorService service;
	@Autowired
	ExpireService expireService;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu(Model model) {
		return AdminPageInfo.EXPIRE_REMOVE.redirectUrl;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String initialize(Model model) throws IOException {
		List<ExpireDto> expireList = expireService.getExpiredItems();
		DateRangeDto dateRange = new DateRangeDto();
        dateRange.setDateFrom(new Date());
        dateRange.setDateTo(new Date());
        model.addAttribute("dateRange", dateRange);
		model.addAttribute("expireList",expireList);
		model.addAttribute("SUPPLIER_DETAIL_ORDER_INFO_ID", new SupplierIdDto());
		model= service.messageProperty(model);
		return AdminPageInfo.EXPIRE_REMOVE.template;
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(Model model, ExpireDto expireDto) throws IOException {
		System.out.println(expireDto);
		return AdminPageInfo.EXPIRE_REMOVE_ITEMS.template;
	}
	
	@RequestMapping(value="/dateSearch", method=RequestMethod.POST)
	public String dateSearch(Model model, DateRangeDto dateRange) throws IOException {
		List<ExpireDto> expireList = expireService.dateSearch(dateRange);
		model.addAttribute("expireList",expireList);
		model.addAttribute("SUPPLIER_DETAIL_ORDER_INFO_ID", new SupplierIdDto());
		return AdminPageInfo.EXPIRE_REMOVE.template;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String edit(@RequestParam(value="action", required=true) String action, SupplierIdDto supplierIdDto) {

	    if (action.equals("expire")) {
	    	return AdminPageInfo.EXPIRE_REMOVE.redirectUrl;  
	    }

	    if (action.equals("remove")) {
	    	expireService.removeExpiredItems(supplierIdDto);
	    	return AdminPageInfo.EXPIRE_REMOVE.redirectUrl;
	    }
		return null;
	}
}
