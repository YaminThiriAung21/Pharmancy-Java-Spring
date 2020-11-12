package pec.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import pec.dao.SupplierOrderDao;
import pec.dto.EmployeeInfoDto;
import pec.dto.SupplierDetailOrderInfoDto;
import pec.dto.SupplierDto;
import pec.dto.SupplierOrderInfoDto;

@Service
@Transactional
public class SupplierOrderService {

	@Autowired
	SupplierOrderDao supOLDao;
	
	@Autowired
	private HttpSession session;
	
	public Model messageProperty(Model model) throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		
		model.addAttribute("E000031", prop.getProperty("E000031"));
		model.addAttribute("E000032", prop.getProperty("E000032"));
		model.addAttribute("E000034", prop.getProperty("E000034"));
		model.addAttribute("E000037", prop.getProperty("E000037"));
		
		return model; 
	}
	
	public List<SupplierOrderInfoDto> getSupplierOrderList(){
		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		
		return supOLDao.getSupplierOrderList(empDto.getOffice_info_id());
	}
	
	public List<SupplierDto> getSupplierName(){
		return supOLDao.getSupplierName();
	}

	public List<SupplierDetailOrderInfoDto> getListForUpdate(Integer id) {
		return supOLDao.getListForUpdate(id);
	}

	public SupplierOrderInfoDto getOrderList(Integer id) {
		return supOLDao.getOrderList(id);
	}
	
	public void updateSupplierOrder(SupplierOrderInfoDto supOrderInfoDto) {
		
		LocalDate updateDate=LocalDate.now();
		int upDate_year=updateDate.getYear();
		Month upDate_monthInt=updateDate.getMonth();
		int upDate_day=updateDate.getDayOfMonth();
		/*System.out.println("upDate_year"+upDate_year);
		System.out.println("upDate_monthInt"+upDate_monthInt);
		System.out.println("upDate_day"+upDate_day);*/
		
		String upYear=String.valueOf(upDate_year);
		String upDay=String.valueOf(upDate_day);
		String upMonth=upDate_monthInt.toString();
		upMonth=upMonth.substring(0,3);
		String finalUpdateDate=upDay+"-"+upMonth+"-"+upYear;
		System.out.println("finalUpdateDate="+finalUpdateDate);
		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		
		try {
			Date updateDateDB=new SimpleDateFormat("dd-MMM-yyyy").parse(finalUpdateDate);
			//Date insertdateDB=new SimpleDateFormat("dd-MMM-yyyy").parse(finalUpdateDate);
			
			supOrderInfoDto.setDelete_flag(0);
			//supOrderInfoDto.setInsert_date(insertdateDB);
			supOrderInfoDto.setUpdate_date(updateDateDB);
			//supOrderInfoDto.setInsert_user("Admin1");
			supOrderInfoDto.setUpdate_user(empDto.getEmployee_name());
			//supOrderInfoDto.setUpdate_user("Admin5");
			//supOrderInfoDto.setOffice_info_id(1);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("supDOWDto="+supOrderInfoDto);
		supOLDao.updateSupplierOrder(supOrderInfoDto);
		
	}

	/*  About SupplierDetailOrderWrapperDto supDetailOrderInfoDto for supplier_detail_order_info db table	 */
	public void updateSupplierDetailOrder(List<SupplierDetailOrderInfoDto> supDOWDto) {
		
		LocalDate updateDate=LocalDate.now();
		int upDate_year=updateDate.getYear();
		Month upDate_monthInt=updateDate.getMonth();
		int upDate_day=updateDate.getDayOfMonth();
		
		String upYear=String.valueOf(upDate_year);
		String upDay=String.valueOf(upDate_day);
		String upMonth=upDate_monthInt.toString();
		upMonth=upMonth.substring(0,3);
		String finalUpdateDate=upDay+"-"+upMonth+"-"+upYear;
		System.out.println("finalUpdateDate="+finalUpdateDate);
		
		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		try {
			Date updateDateDB=new SimpleDateFormat("dd-MMM-yyyy").parse(finalUpdateDate);
			//Date insertdateDB=new SimpleDateFormat("dd-MMM-yyyy").parse(finalUpdateDate);
			
			for(int i=0;i<supDOWDto.size();i++) {
				supDOWDto.get(i).setDelete_flag(0);
				supDOWDto.get(i).setExpire_flag(0);
				//supDOWDto.get(i).setInsert_date(insertdateDB);
				supDOWDto.get(i).setUpdate_date(updateDateDB);
				//supDOWDto.get(i).setInsert_user("Admin4");
				supDOWDto.get(i).setUpdate_user(empDto.getEmployee_name());
				//supDOWDto.get(i).setUpdate_user("Admin7");
				int total=supDOWDto.get(i).getProduct_price()*supDOWDto.get(i).getProduct_quantity();
				supDOWDto.get(i).setProduct_total_price(total);
				supOLDao.updateSupplierDetailOrder(supDOWDto.get(i));
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<SupplierDetailOrderInfoDto> getSupplierDetailOrderList(Integer id) {
		return supOLDao.getSupplierDetailOrderList(id);
	}

	public void deleteOrder(Integer id) {
		supOLDao.deleteOrder(id);
	}

	public void deleteDetailOrder(Integer id) {
		supOLDao.deleteDetailOrder(id);		
	}

	public List<SupplierOrderInfoDto> getListByDate(SupplierOrderInfoDto supOInfoDto) {
		return supOLDao.getListByDate(supOInfoDto);
	}
	
	public List<SupplierDetailOrderInfoDto> deleteFlag(Integer id) {
		return supOLDao.deleteFlag(id);
	}
	public void changeDeleteFlag(SupplierDetailOrderInfoDto supDOWDto1){
		supOLDao.changeDeleteFlag(supDOWDto1);
	}
}
