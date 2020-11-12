package pec.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pec.dto.SupplierDetailOrderInfoDto;
import pec.dto.SupplierOrderInfoDto;
import pec.service.SupplierOrderService;
import pec.datacode.AdminPageInfo;

/**
 * Supplier Order List 
 * 29/8/2020
 * @author Wutt Yee Tun
 */

@Controller
@RequestMapping("/admin/supplierOrderList")
public class SupplierOrderListController {
	
	@Autowired
	SupplierOrderService supOLService;

	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.SUPPLIER_ORDER_LIST.redirectUrl;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String initialize(Model model) throws IOException {
		List<SupplierOrderInfoDto> supODto= supOLService.getSupplierOrderList();
		/*for (SupplierOrderInfoDto supplierOrderInfoDto : supODto) {
			System.out.println(supODto);
		}
		System.out.println("Supplier Name:"+supODto);*/
		model= supOLService.messageProperty(model);
		model.addAttribute("supplierOrderInfoList",supODto);
		model.addAttribute("supplierOrderInfoDto",new SupplierOrderInfoDto());
		model.addAttribute("data_tb","save");
		
		//model.addAttribute("showModal", "show");
		return AdminPageInfo.SUPPLIER_ORDER_LIST.template;
	}
	
	@RequestMapping(value = "/{accId}", method = RequestMethod.GET)
	public String DetailOrderList(Model model, @PathVariable("accId") Integer id) throws IOException {
		model= supOLService.messageProperty(model);
		List<SupplierDetailOrderInfoDto> supplierDetailOrderList = supOLService.getListForUpdate(id);
		List<SupplierOrderInfoDto> supODto= supOLService.getSupplierOrderList();
		
		model.addAttribute("supplierOrderInfoDto",new SupplierOrderInfoDto());
		model.addAttribute("supplierOrderInfoList",supODto);
		model.addAttribute("supplierDetailOrderList",supplierDetailOrderList);
		model.addAttribute("showModal","show");
		model.addAttribute("data_tb","save");
		return AdminPageInfo.SUPPLIER_ORDER_LIST.template;
	}
	
	@RequestMapping(params="search", method = RequestMethod.GET)
	public String DetailOrderList(Model model,SupplierOrderInfoDto supOInfoDto) throws ParseException {
		//System.out.println("DateFrom="+supOInfoDto.getDateFrom());
		//System.out.println("DataTo="+supOInfoDto.getDateTo());
		
		String month="";
		
		String dateFrom=supOInfoDto.getDateFrom();
		String dateTo=supOInfoDto.getDateTo();
		
		String dateFrom_Year=dateFrom.substring(0,4);
		String dateFrom_Month=dateFrom.substring(5,7);
		String dateFrom_Day=dateFrom.substring(8);
		month=searchMonth(dateFrom_Month);
		String dateFrom_final=dateFrom_Day+"-"+month+"-"+dateFrom_Year+" 12.00.00.000 AM";
		//Date dateFromDB= new SimpleDateFormat("dd-MMM-yy HH.mm.ss.S a").parse(dateFrom_final);
		
		String dateTo_Year=dateTo.substring(0,4);
		String dateTo_Month=dateTo.substring(5,7);
		String dateTo_Day=dateTo.substring(8);
		month=searchMonth(dateTo_Month);
		String dateTo_final=dateTo_Day+"-"+month+"-"+dateTo_Year+" 11.59.59.999 PM";
		//Integer i=Integer.parseInt(dateTo_Day);
		/*i=i+1;
		dateTo_Day=i.toString();*/
		
		//Date dateToDB= new SimpleDateFormat("dd-MMM-yy HH.mm.ss.S a").parse(dateTo_final);
		
		//System.out.println("dateFromDB="+dateFromDB);
		//System.out.println("datTo_final="+dateTo_final);
		//System.out.println("dateToDB="+dateToDB);
		
		supOInfoDto.setDate_from(dateFrom_final);
		supOInfoDto.setDate_to(dateTo_final);
		
		List<SupplierOrderInfoDto> supODto= supOLService.getListByDate(supOInfoDto);
		System.out.println("LIST="+supODto);
		model.addAttribute("supplierOrderInfoList",supODto);
		model.addAttribute("data_tb","save");
		return AdminPageInfo.SUPPLIER_ORDER_LIST.template;
	}
	
	public String searchMonth(String mth) {
		String month="";
		switch(mth) {
		case "01": month="JAN"; break;
		case "02": month="FEB"; break;
		case "03": month="MAR"; break;
		case "04": month="APR"; break;
		case "05": month="MAY"; break;
		case "06": month="JUN"; break;
		case "07": month="JUL"; break;
		case "08": month="AUG"; break;
		case "09": month="SEP"; break;
		case "10": month="OCT"; break;
		case "11": month="NOV"; break;
		case "12": month="DEC"; break;
		}
		return month;
	}

}
