package pec.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pec.dto.SupplierDetailOrderInfoDto;
import pec.dto.SupplierDetailOrderWrapperDto;
import pec.dto.SupplierDto;
import pec.dto.SupplierOrderInfoDto;
import pec.service.SupplierOrderService;
import pec.datacode.AdminPageInfo;

/**
 * Supplier Order Update 
 * 27/8/2020
 * @author Wutt Yee Tun
 */

@Controller
@RequestMapping("/admin/supplierOrderUpdate")
public class SupplierOrderUpdateController {
	
	@Autowired
	HttpSession session;
	
	@Autowired 
	SupplierOrderService supplierOrderService;
	
	@ModelAttribute("supplierdropdown")
	public List<SupplierDto> getSupplierDropdown(){
		List<SupplierDto> supDto=supplierOrderService.getSupplierName();
		return supDto;
	}
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.SUPPLIER_ORDER_UPDATE.redirectUrl;
	}

	@RequestMapping(value="/{supOdInfoId}",method=RequestMethod.GET)
	public String initialize(Model model,@PathVariable("supOdInfoId") Integer sid) throws IOException {
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
		Date dd=new Date();
		String ans=date.format(dd);
		model.addAttribute("max",ans);
		model.addAttribute("min",ans);
		model= supplierOrderService.messageProperty(model);
		
		SupplierOrderInfoDto supODto=supplierOrderService.getOrderList(sid);
		List<SupplierDetailOrderInfoDto> supDOInfoDto=supplierOrderService.getListForUpdate(sid);
		
		model.addAttribute("supOrderInfoDto",supODto);
		
		SupplierDetailOrderWrapperDto supplierDetailWrapper = new SupplierDetailOrderWrapperDto();
		supplierDetailWrapper.setSupDetailOrderInfoDto(supDOInfoDto);
		model.addAttribute("supDetailOrderWrapperDto",supplierDetailWrapper);
		
		model= supplierOrderService.messageProperty(model);
		supplierDetailWrapper.setSupDetailOrderInfoDto1(supDOInfoDto);
		model.addAttribute("supDetailOrderWrapperDto1",supplierDetailWrapper);
		model.addAttribute("orderListSize",supDOInfoDto.size());
		session.removeAttribute("removeSupplierDetailList");
		return AdminPageInfo.SUPPLIER_ORDER_UPDATE.template;
	}
	
	@RequestMapping(params="update",method=RequestMethod.GET)
	public String actualUpdate(Model model,SupplierDetailOrderWrapperDto supDetailOrderInfoDto, SupplierOrderInfoDto supplierOrderInfoDto,BindingResult res)throws ServletException {
		
		/*  About SupplierDetailOrderWrapperDto supDetailOrderInfoDto for supplier_detail_order_info db table	 */
		
		System.out.println("supplierOrderInfoDto="+supplierOrderInfoDto);
		System.out.println("supDetailOrderInfoDto="+supDetailOrderInfoDto);
		List<SupplierDetailOrderInfoDto> supDOWDto=supDetailOrderInfoDto.getSupDetailOrderInfoDto();
		
		String mfDate="";
		String expireDate="";
		String month="";
		
		for(int i=0;i<supDOWDto.size();i++) {
			if(supDOWDto.get(i).getSupplier_detail_order_info_id()==null || supDOWDto.get(i).getProduct_info_id()==null) {
				break;
			}
			else {
				mfDate=supDOWDto.get(i).getManufactureDate();
				expireDate=supDOWDto.get(i).getExpireDate();
				System.out.println("mfDate="+mfDate);
				System.out.println("expireDate="+expireDate);
			
			try {
				String mfYear=mfDate.substring(0,4);
				String mfMonth=mfDate.substring(5,7);
				String mfDay=mfDate.substring(8);
				
				String exYear=expireDate.substring(0,4);
				String exMonth=expireDate.substring(5,7);
				String exDay=expireDate.substring(8);
				
				month=searchMonth(mfMonth);
				String mfFinalDate=mfDay+"-"+month+"-"+mfYear;
				Date mfDbDate=new SimpleDateFormat("dd-MMM-yyyy").parse(mfFinalDate);
				supDOWDto.get(i).setManufacture_date(mfDbDate);
				
				month=searchMonth(exMonth);
				String exFinalDate=exDay+"-"+month+"-"+exYear;
				Date exDbDate=new SimpleDateFormat("dd-MMM-yyyy").parse(exFinalDate);
				supDOWDto.get(i).setExpire_date(exDbDate);
				
				System.out.println("mfDbDate="+mfDbDate);
				System.out.println("exDbDate="+exDbDate);
				System.out.println("Expire_date in DB="+supDOWDto.get(i).getExpire_date());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}	
		supplierOrderService.updateSupplierDetailOrder(supDOWDto);
		/*List<SupplierDetailOrderInfoDto> supDOWDto1=supDetailOrderInfoDto.getSupDetailOrderInfoDto1();
		System.out.println("supDOWDto1="+supDOWDto1);
		for(int i=0;i<supDOWDto1.size();i++) {
			supplierOrderService.changeDeleteFlag(supDOWDto1.get(i));
		}*/
		
		/*If removed detail product exist, change delete flag 1  */
		List<SupplierDetailOrderInfoDto> removeList = (List<SupplierDetailOrderInfoDto>) session.getAttribute("removeSupplierDetailList");
		if(removeList  != null){
			for(int i=0;i<removeList.size();i++) {
				supplierOrderService.changeDeleteFlag(removeList.get(i));
			}
		}
		
			
		/*  About SupplierOrderInfoDto supplierOrderInfoDto for supplier_detail_order_info db table	 */
		String orderReceiveDate=supplierOrderInfoDto.getOrderReceiveDate();
		System.out.println("orderReceiveDate="+orderReceiveDate);
		
		String orYear=orderReceiveDate.substring(0,4);
		String orMonth=orderReceiveDate.substring(5,7);
		String orDay=orderReceiveDate.substring(8);
		month=searchMonth(orMonth);
		String orderReceiveDate_final=orDay+"-"+month+"-"+orYear;
		Date orRDDB;
		try {
			orRDDB = new SimpleDateFormat("dd-MMM-yyyy").parse(orderReceiveDate_final);
			supplierOrderInfoDto.setOrder_receive_date(orRDDB);
			System.out.println("orderReceiveDate in DB in try: "+orRDDB);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("orderReceiveDate in DB outside try : "+supplierOrderInfoDto.getOrder_receive_date());
		supplierOrderService.updateSupplierOrder(supplierOrderInfoDto);
		model.addAttribute("displayMessage","Update is successed completely.");
		List<SupplierOrderInfoDto> supODto= supplierOrderService.getSupplierOrderList();
		/*for (SupplierOrderInfoDto supplierOrderInfoDto : supODto) {
			System.out.println(supODto);
		}
		System.out.println("Supplier Name:"+supODto);*/
		model.addAttribute("supplierOrderInfoList",supODto);
		model.addAttribute("supplierOrderInfoDto",new SupplierOrderInfoDto());
		model.addAttribute("msg","Order update is successful");
		model.addAttribute("data_tb","save");
		return AdminPageInfo.SUPPLIER_ORDER_LIST.template;
		
	}
	
	@RequestMapping(params="remove",method=RequestMethod.GET)
	public String removeOrder(Model model,SupplierDetailOrderWrapperDto supDetailOrderInfoDto, SupplierOrderInfoDto supplierOrderInfoDto) throws IOException {
		SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
		Date dd=new Date();
		String ans=date.format(dd);
		model.addAttribute("max",ans);
		model.addAttribute("min",ans);
		model= supplierOrderService.messageProperty(model);
		int id = supplierOrderInfoDto.getRowIndex();
		//session.getAttribute("supplierOrderWrapperList");
		
		List<SupplierDetailOrderInfoDto> del = supDetailOrderInfoDto.getSupDetailOrderInfoDto();
		System.out.println(del);
		/*for(int i=0;i<del.size();i++) {
			if(i==id) {
				int id1=del.get(i).getSupplier_detail_order_info_id();
				
				List<SupplierDetailOrderInfoDto> sup=supplierOrderService.deleteFlag(id1);
				System.out.println("sup="+sup);
				supDetailOrderInfoDto.setSupDetailOrderInfoDto1(sup);
				//supDetailOrderInfoDto.setSupDetailOrderInfoDto1(del.get(i));
				model.addAttribute("supDetailOrderWrapperDto1",supDetailOrderInfoDto);
			}
		}*/
		List<SupplierDetailOrderInfoDto> removeList = (List<SupplierDetailOrderInfoDto>) session.getAttribute("removeSupplierDetailList");
		if(removeList != null){
			removeList.add(supDetailOrderInfoDto.getSupDetailOrderInfoDto().get(id));	
			session.setAttribute("removeSupplierDetailList", removeList);
		}else {
			List<SupplierDetailOrderInfoDto> newRemoveList = new ArrayList<SupplierDetailOrderInfoDto>();
			newRemoveList.add(supDetailOrderInfoDto.getSupDetailOrderInfoDto().get(id));	
			session.setAttribute("removeSupplierDetailList", newRemoveList);
		}
		
		del.remove(id);
		supDetailOrderInfoDto.setSupDetailOrderInfoDto(del);
		
		model.addAttribute("supOrderInfoDto",supplierOrderInfoDto);
		model.addAttribute("supDetailOrderWrapperDto",supDetailOrderInfoDto);
		model.addAttribute("orderListSize",del.size());
		/*model= supplierOrderService.messageProperty(model);
		
		SupplierOrderInfoDto supODto=supplierOrderService.getOrderList(sid);
		List<SupplierDetailOrderInfoDto> supDOInfoDto=supplierOrderService.getListForUpdate(sid);
		
		model.addAttribute("supOrderInfoDto",supODto);
		
		SupplierDetailOrderWrapperDto supplierDetailWrapper = new SupplierDetailOrderWrapperDto();
		supplierDetailWrapper.setSupDetailOrderInfoDto(supDOInfoDto);
		
		model.addAttribute("supDetailOrderWrapperDto",supplierDetailWrapper);
		model.addAttribute("orderListSize",supDOInfoDto.size());*/
		
		return AdminPageInfo.SUPPLIER_ORDER_UPDATE.template;
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
