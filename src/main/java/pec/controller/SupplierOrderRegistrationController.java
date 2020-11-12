package pec.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import pec.dto.SupplierOrderWrapperDto;
import pec.dto.SupplierWrapperDto;
import pec.service.SupplierOrderRegistrationService;
import pec.datacode.AdminPageInfo;


/**
 * Supplier Order Registration
 * 27/8/2020
 * @author Rupar Htin Lin
 */
@Controller
@RequestMapping("/admin/supplier_order_registration")
public class SupplierOrderRegistrationController {
	
	@Autowired 
	SupplierOrderRegistrationService supplierOrderRegistrationService;
	@Autowired
	HttpSession session;
	
	@ModelAttribute("supplierdropdown")
	public List<SupplierWrapperDto> getSupplierName()
	{
		List<SupplierWrapperDto> supDto=supplierOrderRegistrationService.getSupplierName();
		return supDto;
	}	
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.SUPPLIER_ORDER_REGISTRATION.redirectUrl;
	}

	/*@RequestMapping(method=RequestMethod.GET)
	public String initialize(Model model) throws IOException {
		model= supplierOrderRegistrationService.messageProperty(model);
		model.addAttribute("accountDto", new AccountDto());
		return AdminPageInfo.SUPPLIER_ORDER_REGISTRATION.template;
	}*/
	/*@RequestMapping(method = RequestMethod.GET)
	public String initialize(@RequestParam("row-check") List<Integer> prod_info_id ) {
		for(int i=0;i<prod_info_id.size();i++) {
			System.out.println("product id : "+prod_info_id.get(i));
		}
		
		return AdminPageInfo.SUPPLIER_ORDER_REGISTRATION.template;
	}*/
	
	@RequestMapping(method = RequestMethod.GET)
	public String productList(Model model,  HttpServletRequest req) throws IOException{
		SupplierOrderWrapperDto supplierOrderWrapperDto = new SupplierOrderWrapperDto();
		
		List<SupplierWrapperDto> productList = supplierOrderRegistrationService.getProductList();
/*		List<SupplierWrapperDto> supplierOrderList = new ArrayList<SupplierWrapperDto>();
		SupplierWrapperDto supplierWrapperDto = new SupplierWrapperDto();
		supplierWrapperDto.setProduct_code("PC001");
		supplierOrderList.add(supplierWrapperDto);
		supplierOrderWrapperDto.setSupplierOrderList(supplierOrderList);*/
		supplierOrderWrapperDto.setProductList(productList);
		//model.addAttribute("productList",productList);
		session.setAttribute("supplierOrderWrapperList", supplierOrderWrapperDto);
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
		Date dd=new Date();
		String ans=date.format(dd);
		model.addAttribute("max",ans);
		model.addAttribute("min",ans);
		model=supplierOrderRegistrationService.messageProperty(model);
		model.addAttribute("supplierOrderWrapperDto",supplierOrderWrapperDto);
		model.addAttribute("data_tb", "save");
		return AdminPageInfo.SUPPLIER_ORDER_REGISTRATION.template;
	}
	
	@RequestMapping(params="addTo",method=RequestMethod.GET)
	public String addToOrder(Model model,@ModelAttribute SupplierOrderWrapperDto supplierOrderWrapperDto, @RequestParam("row-check") List<Integer> prod_info_id) throws IOException {
		
		//supplierOrderWrapperDto = (SupplierOrderWrapperDto) session.getAttribute("supplierOrderWrapperList");
		for(int i=0;i<prod_info_id.size();i++) {
			System.out.println("product id : "+prod_info_id.get(i));
			SupplierWrapperDto productDto = supplierOrderRegistrationService.addToOrder(prod_info_id.get(i));
			productDto.setProduct_quantity(1);
			productDto.setProduct_total_price(productDto.getProduct_quantity()*productDto.getBuy_price());
			supplierOrderWrapperDto.getSupplierOrderList().add(productDto);
		}
		
		List<SupplierWrapperDto> productList = supplierOrderRegistrationService.getProductList();
		supplierOrderWrapperDto.setProductList(productList);
		session.setAttribute("supplierOrderWrapperList", supplierOrderWrapperDto);
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
		Date dd=new Date();
		String ans=date.format(dd);
		model.addAttribute("max",ans);
		model.addAttribute("min",ans);
		model=supplierOrderRegistrationService.messageProperty(model);
		model.addAttribute("supplierOrderWrapperDto",supplierOrderWrapperDto);
		model.addAttribute("orderListSize", supplierOrderWrapperDto.getSupplierOrderList().size());
		model.addAttribute("data_tb", "save");
		return AdminPageInfo.ADD_TO_SUPPLIER_ORDER.template;
	}
	@RequestMapping(params="order",method=RequestMethod.GET)
	public String order(Model model,@ModelAttribute SupplierOrderWrapperDto supplierOrderWrapperDto) {
		session.setAttribute("supplierOrderWrapperList", supplierOrderWrapperDto);
		System.out.println("dddddddd"+supplierOrderWrapperDto);
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
		Date dd=new Date();
		String ans=date.format(dd);
		model.addAttribute("max",ans);
		model.addAttribute("min",ans);
		model.addAttribute("supplierOrderWrapperDto",supplierOrderWrapperDto);
		
		return AdminPageInfo.SUPPLIER_ORDER_CONFIRM.template;
	}
	@RequestMapping(params="remove", method=RequestMethod.GET)
	public String removeOrder(Model model,@ModelAttribute SupplierOrderWrapperDto supplierOrderWrapperDto) throws IOException  {
		int id = supplierOrderWrapperDto.getSupplierOrderInfoDto().getRowIndex();
		//session.getAttribute("supplierOrderWrapperList");
	
		List<SupplierWrapperDto> del = supplierOrderWrapperDto.getSupplierOrderList();
		del.remove(id);
		supplierOrderWrapperDto.setSupplierOrderList(del);
		List<SupplierWrapperDto> productList = supplierOrderRegistrationService.getProductList();
		supplierOrderWrapperDto.setProductList(productList);
		session.setAttribute("supplierOrderWrapperList", supplierOrderWrapperDto);
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
		Date dd=new Date();
		String ans=date.format(dd);
		model.addAttribute("max",ans);
		model.addAttribute("min",ans);
		model.addAttribute("orderList", del);
		model.addAttribute("orderListSize", del.size());
		model.addAttribute("data_tb", "save");
		model=supplierOrderRegistrationService.messageProperty(model);
	
		return AdminPageInfo.REMOVE_TO_SUPPLIER_ORDER.template;
	}
		
	
}
