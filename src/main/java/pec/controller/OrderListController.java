package pec.controller;
/**
 * Customer Order History
 * 25/8/2020
 * @author Yamin Thiri Aung 
 */
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pec.dto.DateRange;
import pec.dto.DetailOrderListDto;
import pec.dto.OrderListDto;
import pec.service.OrderListService;
import pec.datacode.AdminPageInfo;

@Controller
@RequestMapping("/admin/customerOrderHistory")
public class OrderListController {
	@Autowired
	
	private OrderListService orderListService;

	@RequestMapping(method = RequestMethod.GET)
	public String OrderList(Model model, HttpServletRequest req, RedirectAttributes attr) {
		Map<String, String[]> reqMap = req.getParameterMap();
		reqMap.forEach((key, value) -> {
			model.addAttribute(key, value[0]);
		});

		List<OrderListDto> orderList = orderListService.getOrderList();
		model.addAttribute("orderList", orderList);	
		model.addAttribute("displayMessage","<strong>Customer Order History For Last One Month</strong>");
		return AdminPageInfo.CUSTOMER_ORDER_HISTORY.template;

	}

	@RequestMapping(value = "/{accId}", method = RequestMethod.GET)
	public String DetailOrderList(Model model, @PathVariable("accId") Integer id,HttpSession session) {
		List<DetailOrderListDto> detailOrderList = orderListService.getDetailOrderList(id);

		@SuppressWarnings("unchecked")
		List<OrderListDto> searchOrderList = (List<OrderListDto>) session.getAttribute("searchOrderList");
		if(searchOrderList  == null){
			List<OrderListDto> orderList = orderListService.getOrderList();
			model.addAttribute("orderList", orderList);
		}
		else {
			model.addAttribute("orderList", searchOrderList);
		}
		
		model.addAttribute("detailOrderList", detailOrderList);
		model.addAttribute("displayMessage","<strong> Customer Order History</strong>");
		model.addAttribute("showModal", "show");
		return AdminPageInfo.DETAIL_ORDER_HISTORY.template;
	}
	
	@RequestMapping(params="search", method=RequestMethod.POST)
	public String save(DateRange dateRange, Model model,RedirectAttributes attr,HttpSession session) throws ParseException {
     	String dateFromString = dateRange.getDateFrom()+"";
     	model.addAttribute("startDate", dateRange.getDateFrom());
     	model.addAttribute("endDate", dateRange.getDateTo());
     	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
     	DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MMM-yy");
     	LocalDate dateFrom1 = LocalDate.parse(dateFromString, formatter1); 
     	String dateFrom = formatter2.format(dateFrom1);
     	
		String dateToEnd = dateRange.getDateTo()+" 11:59:59.000000000 PM";
		DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss.SSSSSSSSS a");
		DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("dd-MMM-yy hh:mm:ss.SSSSSSSSS a");
		LocalDateTime dateTo1 = LocalDateTime.parse(dateToEnd, formatter3);
		String dateTo = formatter4.format(dateTo1);
		dateRange.setDateFrom(dateFrom);
		dateRange.setDateTo(dateTo);
	    model.addAttribute("dateRange", dateRange);
		List<OrderListDto> orderList = orderListService.getSearchList(dateRange);
		model.addAttribute("displayMessage","<strong>Customer Order History</strong>");
		model.addAttribute("orderList", orderList);
		session.setAttribute("searchOrderList",orderList);
		return AdminPageInfo.CUSTOMER_ORDER_HISTORY.template;
	}
}

