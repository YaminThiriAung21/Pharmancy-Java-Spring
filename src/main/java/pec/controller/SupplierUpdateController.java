package pec.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pec.dto.SupplierDto;
import pec.service.SupplierService;
import pec.datacode.AdminPageInfo;

/**
* Supplier Update
* 27/8/2020
* @author Lin Lae Win Wah
*/
@Controller
@RequestMapping("/admin/supplierUpdate")
public class SupplierUpdateController {
	@Autowired
	SupplierService supplierService;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.SUPPLIER_UPDATE.redirectUrl;
	}

	@RequestMapping(value="/{supplier_info_id}", method = RequestMethod.GET)
	public String getSupplierDto(Model model,@PathVariable("supplier_info_id") Integer id,RedirectAttributes attr,HttpServletRequest req) throws IOException {
		//service.getAccountDto(id);
		//model.addAttribute("accountDto", new AccountDto());
		///SupplierDto supplierList = supplierService.getSupplierDto(id);
		///model.addAttribute("supplierDto",supplierList);
		//model.addAttribute("id",id);
		/*Map<String, String[]> reqMap= req.getParameterMap();
		reqMap.forEach((key, value)-> {
			model.addAttribute(key, value[0]);});
		
		model.addAttribute("supplierDto",new SupplierDto());*/
		
		List<SupplierDto> supplierList = supplierService.getSupplierList();
		model.addAttribute("supplierList",supplierList);
		
		model= supplierService.messageProperty(model);
		SupplierDto supplierDto=supplierService.getSupplierDto(id);
		model.addAttribute("supplierDto",supplierDto);
		model.addAttribute("data_tb","save");
		return AdminPageInfo.SUPPLIER_UPDATE.template;
	}
	
	@RequestMapping(params= "update", method = RequestMethod.POST)
	public String update(Model model,@Valid SupplierDto supplierDto,BindingResult res, RedirectAttributes attr) throws IOException {
		//attr.addAttribute("displayMessage", "Update Account");
		//model.addAttribute("accountDto", new AccountDto());
		/*if(res.hasErrors()) {
			model.addAttribute("displayMessage", "User input is invalid");
			return AdminPageInfo.SUPPLIER_UPDATE.template;
		}*/
		//int id=1;
		
		//int count = supplierService.getDuplicate(supplierDto.getSupplier_code());
		int countemail = supplierService.getDuplicateemail(supplierDto);
		int countphno = supplierService.getDuplicatephno(supplierDto);
		int countname = supplierService.getDuplicatesuppliername(supplierDto);
		
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		
		if(countname !=0){
			model.addAttribute("errormsg", prop.getProperty("E000045"));
			
			model= supplierService.messageProperty(model);
			SupplierDto supplierDto1=supplierService.getSupplierDto(supplierDto.getSupplier_info_id());
			model.addAttribute("supplierDto",supplierDto1);
			
			List<SupplierDto> supplierList = supplierService.getSupplierList();
			model.addAttribute("supplierList", supplierList);
			return AdminPageInfo.SUPPLIER_UPDATE.template;
		}else if(countemail != 0){
			model.addAttribute("errormsg", prop.getProperty("E000140"));
			model= supplierService.messageProperty(model);
			SupplierDto supplierDto1=supplierService.getSupplierDto(supplierDto.getSupplier_info_id());
			model.addAttribute("supplierDto",supplierDto1);
			
			List<SupplierDto> supplierList = supplierService.getSupplierList();
			model.addAttribute("supplierList", supplierList);
			return AdminPageInfo.SUPPLIER_UPDATE.template;
			
			
		}else if(countphno !=0){
			model.addAttribute("errormsg", prop.getProperty("E000141"));
			model= supplierService.messageProperty(model);
			SupplierDto supplierDto1=supplierService.getSupplierDto(supplierDto.getSupplier_info_id());
			model.addAttribute("supplierDto",supplierDto1);
			
			List<SupplierDto> supplierList = supplierService.getSupplierList();
			model.addAttribute("supplierList", supplierList);
			return AdminPageInfo.SUPPLIER_UPDATE.template;
		}
		else {
			supplierService.update(supplierDto);
			attr.addFlashAttribute("msg", "Supplier has Updated Successfully");
		}
		
		List<SupplierDto> supplierList = supplierService.getSupplierList();
		model.addAttribute("supplierList", supplierList);
		return AdminPageInfo.SUPPLIER_INSERT.redirectUrl;
		//return "account/accountupdate";
	}
	
	
}