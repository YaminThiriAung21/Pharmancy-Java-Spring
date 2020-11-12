package pec.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import pec.dao.EmployeeInfoDao;
import pec.dto.AccountDto;
import pec.dto.EmployeeInfoDto;

/**
 * Employee Service
 * 27/8/2020
 * @author Thiha
 */
@Service
@Transactional
public class EmployeeInfoService {
	
	@Autowired
	private EmployeeInfoDao EmployeeInfoDao;
	
	public List<EmployeeInfoDto> getEmployeeInfo() {
		return EmployeeInfoDao.getEmployeeInfo();
	}
	
	public EmployeeInfoDto getUpdate(int id) {
		return EmployeeInfoDao.getUpdate(id);
	}
	
	public void employeeInfoUpdate(EmployeeInfoDto employeeInfoDto) {
		EmployeeInfoDao.employeeInfoUpdate(employeeInfoDto);
	}
	
	public void employeeLoginUpdate(EmployeeInfoDto employeeInfoDto) {
		EmployeeInfoDao.employeeLoginUpdate(employeeInfoDto);
	}
	
	public void employeePasswordUpdate(EmployeeInfoDto employeeInfoDto) {
		String pass_encode = new BCryptPasswordEncoder().encode(employeeInfoDto.getPassword());
		employeeInfoDto.setPassword(pass_encode);
		EmployeeInfoDao.employeePasswordUpdate(employeeInfoDto);
	}
	
	public void employeeInfoDelete(int id) {
		EmployeeInfoDao.employeeInfoDelete(id);
	}
	
	public void employeeLoginDelete(int id) {
		EmployeeInfoDao.employeeLoginDelete(id);
	}
	
	public List<EmployeeInfoDto> getDuplicate(int id) {
		return EmployeeInfoDao.getDuplicate(id);
	}
	
	public Model messageProperty(Model model) throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		model.addAttribute("E000013", prop.getProperty("E000013"));
		model.addAttribute("E000003", prop.getProperty("E000003"));
		model.addAttribute("E000005", prop.getProperty("E000005"));
		model.addAttribute("E000016", prop.getProperty("E000016"));
		model.addAttribute("E000020", prop.getProperty("E000020"));
		model.addAttribute("E000104", prop.getProperty("E000104"));
		model.addAttribute("E000105", prop.getProperty("E000105"));
		model.addAttribute("E000107", prop.getProperty("E000107"));
		model.addAttribute("E000020", prop.getProperty("E000020"));
		model.addAttribute("E000109", prop.getProperty("E000109"));
		model.addAttribute("E000110", prop.getProperty("E000110"));
		model.addAttribute("E000002", prop.getProperty("E000002"));
		model.addAttribute("E000004", prop.getProperty("E000004"));
		model.addAttribute("E000012", prop.getProperty("E000012"));
		model.addAttribute("E000011", prop.getProperty("E000011"));
		model.addAttribute("E000006", prop.getProperty("E000006"));
		model.addAttribute("E000112", prop.getProperty("E000112"));
		model.addAttribute("E000007", prop.getProperty("E000007"));
		model.addAttribute("E000015", prop.getProperty("E000015"));
		model.addAttribute("E000106", prop.getProperty("E000106"));
		model.addAttribute("E000105", prop.getProperty("E000105"));
		model.addAttribute("E000010", prop.getProperty("E000010"));
		return model; 
	}

	public List<EmployeeInfoDto> getDuplicateRegister() {
		return EmployeeInfoDao.getDuplicateRegister();
	}

	public void employeeInfoRegister(EmployeeInfoDto employeeInfoDto) {
		EmployeeInfoDao.employeeInfoRegister(employeeInfoDto);
	}

	public void employeeLoginRegister(EmployeeInfoDto employeeInfoDto) {
		String pass_encode = new BCryptPasswordEncoder().encode(employeeInfoDto.getPassword());
		employeeInfoDto.setPassword(pass_encode);
		employeeInfoDto.setLogin_user_info_id(10);
		EmployeeInfoDao.employeeLoginRegister(employeeInfoDto);
	}

	public int getLoginUserInfoId(EmployeeInfoDto employeeInfoDto) {
		return EmployeeInfoDao.getLoginUserInfoId(employeeInfoDto);
	}

	public List<String> getOfficeName() {
		return EmployeeInfoDao.getOfficeName();
	}

	public int getOfficeId(String office_name) {
		return EmployeeInfoDao.getOfficeId(office_name);
	}

	
}
