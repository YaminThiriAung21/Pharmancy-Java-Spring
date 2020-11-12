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

import pec.dao.CustomerDao;
import pec.dto.CustomerInfoDto;
import pec.dto.EmployeeInfoDto;

/**
 * Customer Info Service
 * 27/8/2020
 * @author Htet Wai Yan Aung
 */

@Service
@Transactional
public class CustomerInfoService {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	  private HttpSession session;
	public List<CustomerInfoDto> getCustomerList(){
		return customerDao.getCustomerList();
	}
	
	public CustomerInfoDto getUpdate(int customer_info_id) {
		return customerDao.getUpdate(customer_info_id);
	}

	public void update(CustomerInfoDto customerDto) {
		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		customerDto.setUpdate_user(empDto.getEmployee_name()); 
		customerDto.setUpdate_date(new Date());
		customerDao.update(customerDto);
	}
	
	public Model messageProperty(Model model) throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		model.addAttribute("E000001", prop.getProperty("E000001"));
		model.addAttribute("E000003", prop.getProperty("E000003"));
		model.addAttribute("E000005", prop.getProperty("E000005"));
		model.addAttribute("E000010", prop.getProperty("E000010"));
		return model; 
	}

	public void delete(int id) {
		customerDao.delete(id);
	}

	public CustomerInfoDto getDelete(int id) {
		return customerDao.getDelete(id);
	}

	public void deleteId(CustomerInfoDto info) {
		customerDao.deleteId(info);
	}

}
