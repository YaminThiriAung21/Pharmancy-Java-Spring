package pec.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pec.dto.SupplierOrderInfoDto;
import pec.service.SupplierOrderService;
import pec.datacode.AdminPageInfo;

/**
 * Supplier Order Delete 
 * 15/9/2020
 * @author Wutt Yee Tun
 */

@Controller
@RequestMapping("/admin/supplierOrderDelete")
public class SupplierOrderDeleteController {

	@Autowired
	SupplierOrderService supOLService;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.SUPPLIER_ORDER_DELETE.redirectUrl;
	}
	
	
	
	
	@RequestMapping(params="delete",method = RequestMethod.GET)
	public String deleteRowCheck(Model model, @RequestParam("row-check") List<Integer> id) {
		
		for(int i=0; i<id.size();i++) {
			System.out.println("id="+id.get(i));
			supOLService.deleteOrder(id.get(i));
			supOLService.deleteDetailOrder(id.get(i));
		}
		//model.addAttribute("displayMessage","<p style='color:green'>Order deletion is successful.</p>");
		model.addAttribute("msg","Order deletion is successful."); 
		//return AdminPageInfo.SUPPLIER_ORDER_LIST.redirectUrl;
		List<SupplierOrderInfoDto> supODto= supOLService.getSupplierOrderList();
		/*for (SupplierOrderInfoDto supplierOrderInfoDto : supODto) {
			System.out.println(supODto);
		}
		System.out.println("Supplier Name:"+supODto);*/
		model.addAttribute("supplierOrderInfoList",supODto);
		model.addAttribute("supplierOrderInfoDto",new SupplierOrderInfoDto());
		model.addAttribute("data_tb","save");
		return AdminPageInfo.SUPPLIER_ORDER_LIST.template;
	}
	
}

