package pec.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;


import pec.dao.SupplierWrapperDao;
import pec.dto.EmployeeInfoDto;
import pec.dto.SupplierOrderInfoDto;

import pec.dto.SupplierWrapperDto;


@Service
@Transactional
public class SupplierOrderRegistrationService {
	@Autowired
	private SupplierWrapperDao supplierWrapperDao;
	 @Autowired
		HttpSession session;
    public List<SupplierWrapperDto> getProductList(){
    	EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		return supplierWrapperDao.getProductList(empDto.getOffice_info_id());
	}

    public SupplierWrapperDto addToOrder(int product_info_id) {
    	return supplierWrapperDao.addToOrder(product_info_id);
	}
   
    public List<SupplierWrapperDto> getSupplierName()
    {
    	return supplierWrapperDao.getSupplierName();
    }
    public void addDetail(SupplierWrapperDto supplierWrapperDto) {
    	EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
    	supplierWrapperDto.setInsert_date(new Date());
    	supplierWrapperDto.setUpdate_date(new Date());
    	supplierWrapperDto.setInsert_user(empDto.getEmployee_name());
    	supplierWrapperDto.setUpdate_user(empDto.getEmployee_name());
    	supplierWrapperDto.setDelete_flag(0);
    	supplierWrapperDao.addDetail(supplierWrapperDto);
	}
    public void addOrderInfo(SupplierOrderInfoDto supplierOrderInfoDto) {
    	EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
    	supplierOrderInfoDto.setInsert_date(new Date());
    	supplierOrderInfoDto.setUpdate_date(new Date());
    	supplierOrderInfoDto.setInsert_user(empDto.getEmployee_name());
    	supplierOrderInfoDto.setUpdate_user(empDto.getEmployee_name());
    	supplierOrderInfoDto.setDelete_flag(0);
    	supplierOrderInfoDto.setOffice_info_id(empDto.getOffice_info_id());
    	
    	supplierWrapperDao.addOrderInfo(supplierOrderInfoDto);
	}
	public Model messageProperty(Model model) throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
	
		model.addAttribute("E000031", prop.getProperty("E000031"));
		
		model.addAttribute("E000032", prop.getProperty("E000032"));
		
		model.addAttribute("E000034", prop.getProperty("E000034"));
		
		return model; 
	}

	
}
