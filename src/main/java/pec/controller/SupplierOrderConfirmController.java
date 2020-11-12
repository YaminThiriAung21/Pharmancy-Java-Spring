package pec.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pec.dto.SupplierOrderWrapperDto;
import pec.service.InventoryService;
import pec.service.SupplierOrderRegistrationService;
import pec.datacode.AdminPageInfo;

/**
 * Login Controller
 * 25/8/2020
 * @author Rupar Htin Lin
 */
@Controller
@RequestMapping("/admin/supplier_order_confirm")
public class SupplierOrderConfirmController {
	
	@Autowired 
	SupplierOrderRegistrationService supplierOrderRegistrationService;
	
    @Autowired
	HttpSession session;
    
    @Autowired
	InventoryService inventoryService;
    
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.SUPPLIER_ORDER_CONFIRM.redirectUrl;
	}

	@RequestMapping(params="orderConfirm",method=RequestMethod.GET)
	public String orderConfirm(Model model,RedirectAttributes attr) {
		SupplierOrderWrapperDto supplierOrderWrapperDto = (SupplierOrderWrapperDto) session.getAttribute("supplierOrderWrapperList");
		
		supplierOrderRegistrationService.addOrderInfo(supplierOrderWrapperDto.getSupplierOrderInfoDto());
		for (int i=0; i< supplierOrderWrapperDto.getSupplierOrderList().size(); i++) {
			supplierOrderWrapperDto.getSupplierOrderList().get(i).setProduct_price(supplierOrderWrapperDto.getSupplierOrderList().get(i).getBuy_price());
			supplierOrderWrapperDto.getSupplierOrderList().get(i).setSupplier_order_info_id(supplierOrderWrapperDto.getSupplierOrderInfoDto().getSupplier_order_info_id());
			
			supplierOrderRegistrationService.addDetail(supplierOrderWrapperDto.getSupplierOrderList().get(i));
			inventoryService.addInventory(supplierOrderWrapperDto.getSupplierOrderList().get(i).getProduct_quantity(), supplierOrderWrapperDto.getSupplierOrderList().get(i).getProduct_info_id());
		}
		System.out.println("dddddddd"+supplierOrderWrapperDto);
		attr.addFlashAttribute("msg", "Supplier Order Registration is Successful.");
		//model.addAttribute("supplierOrderWrapperDto",supplierOrderWrapperDto);
		//model.addAttribute("supplierWrapperDto",supplierWrapperDto);
		return AdminPageInfo.SUPPLIER_ORDER_LIST.redirectUrl;
	}
	
}
