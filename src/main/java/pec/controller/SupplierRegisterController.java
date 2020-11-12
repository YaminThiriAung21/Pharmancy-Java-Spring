package pec.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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
import pec.dto.SupplierDto;
import pec.service.SupplierService;
import pec.datacode.AdminPageInfo;

/**
* Supplier Register
* 27/8/2020
* @author Lin Lae Win Wah
*/
@Controller
@RequestMapping("/admin/supplierRegister")

public class SupplierRegisterController {
	@Autowired
	SupplierService supplierService;
	
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.SUPPLIER_INSERT.redirectUrl;
	}
		
	@RequestMapping(method=RequestMethod.GET)
	public String initialize(Model model,HttpServletRequest req) throws IOException {
		
		/*Map<String, String[]> reqMap=req.getParameterMap();
		reqMap.forEach((key,value)->{
			model.addAttribute(key, value[0]);
		});*/
		List<SupplierDto> supplierList = supplierService.getSupplierList();
		model.addAttribute("supplierList", supplierList);
		
		model= supplierService.messageProperty(model);
		model.addAttribute("supplierDto", new SupplierDto());
		model.addAttribute("data_tb","save");
		return AdminPageInfo.SUPPLIER_INSERT.template;
	}
	
	@RequestMapping(params="register", method=RequestMethod.POST)
	public String register(Model model, @Valid SupplierDto supplierDto, BindingResult res, RedirectAttributes attr) throws IOException {
		if(res.hasErrors()) {
			model.addAttribute("msg","<p style='color:red'>Please Input Supplier Name.</p>");
			return AdminPageInfo.SUPPLIER_INSERT.template;		
		}
		
		/*int count = supplierService.DuplicateRegCode(supplierDto.getSupplier_code());
		int countemail = supplierService.getDuplicateemail(supplierDto);
		int countphno = supplierService.getDuplicatephno(supplierDto);
		int countname = supplierService.getDuplicatesuppliername(supplierDto.getSupplier_name());
		*/
		int count = supplierService.DuplicateRegCode(supplierDto);
		int namecount = supplierService.DuplicateRegName(supplierDto);
		int mailcount = supplierService.DuplicateRegMail(supplierDto);
		int phonecount = supplierService.DuplicateRegPhone(supplierDto);
		
		
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		
		/*if(countname !=0){
			model.addAttribute("msg", prop.getProperty("E000045"));
		}
		else */
		/*if(count != 0) {
			//model = addMedicineService.messageProperty(model);
			model.addAttribute("msg", prop.getProperty("E000139"));
			//return AdminPageInfo.SUPPLIER_INSERT.template;
		}else if(countemail !=0){
			model.addAttribute("msg", prop.getProperty("E000140"));
		}else if(countphno !=0){
			model.addAttribute("msg", prop.getProperty("E000141"));
		}
		else {
		
		supplierService.register(supplierDto);
		attr.addFlashAttribute("msg", "Supplier Register Successful.");
		}*/
		//return ("supplier/supplier_list");
		///return AdminPageInfo.SUPPLIER_INSERT.redirectUrl;
		if(namecount != 0){
			model.addAttribute("errormsg", prop.getProperty("E000045"));
			
			List<SupplierDto> supplierList = supplierService.getSupplierList();
			model.addAttribute("supplierList", supplierList);
			return AdminPageInfo.SUPPLIER_INSERT.template;	
		}
		else if(count != 0) {
			
			model.addAttribute("errormsg", prop.getProperty("E000139"));
			
			List<SupplierDto> supplierList = supplierService.getSupplierList();
			model.addAttribute("supplierList", supplierList);
			return AdminPageInfo.SUPPLIER_INSERT.template;	
		}
		else if(mailcount != 0) {
			model.addAttribute("errormsg", prop.getProperty("E000140"));
			
			List<SupplierDto> supplierList = supplierService.getSupplierList();
			model.addAttribute("supplierList", supplierList);
			return AdminPageInfo.SUPPLIER_INSERT.template;	
		}else if(phonecount != 0) {
			model.addAttribute("errormsg", prop.getProperty("E000141"));
			
			List<SupplierDto> supplierList = supplierService.getSupplierList();
			model.addAttribute("supplierList", supplierList);
			return AdminPageInfo.SUPPLIER_INSERT.template;	
		}
		else {
			supplierService.register(supplierDto);
			attr.addFlashAttribute("msg", "Supplier Register Successful.");
		}
		
		List<SupplierDto> supplierList = supplierService.getSupplierList();
		model.addAttribute("supplierList", supplierList);
		return AdminPageInfo.SUPPLIER_INSERT.redirectUrl;
		
	 }
	
	

	
}

