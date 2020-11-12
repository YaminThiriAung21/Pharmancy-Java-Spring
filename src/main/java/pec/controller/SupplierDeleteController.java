package pec.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pec.service.SupplierService;
import pec.datacode.AdminPageInfo;

@Controller
@RequestMapping("/admin/supplierDelete")

public class SupplierDeleteController {
	
	@Autowired
	SupplierService supplierService;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.SUPPLIER_INSERT.redirectUrl;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initialize(@RequestParam("row-check") List<Integer> id,RedirectAttributes attr) {
		for(int i=0;i<id.size();i++) {
			supplierService.delete(id.get(i));	
		}
		attr.addFlashAttribute("msg", "Supplier Delete Successful.");
		return AdminPageInfo.SUPPLIER_INSERT.redirectUrl;
	}
}
