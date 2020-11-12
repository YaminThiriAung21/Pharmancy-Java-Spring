package pec.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import pec.dao.LoginDao;
import pec.dto.AccountDto;
import pec.dto.EmployeeInfoDto;

/**
 * Login Service
 * 25/8/2020
 * @author Su Su Lin
 */
@Service
@Transactional
public class LoginService {
	
	@Autowired
	private LoginDao loginDao;
	
	public EmployeeInfoDto getLoginInfo(String email) {	
		return loginDao.getLoginInfo(email);
	}
	
	public Model msgProperty(Model model) throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		model.addAttribute("E000002", prop.getProperty("E000002"));
		model.addAttribute("E000004", prop.getProperty("E000004"));
		model.addAttribute("E000011", prop.getProperty("E000011"));
		model.addAttribute("E000114", prop.getProperty("E000114"));
		model.addAttribute("E000115", prop.getProperty("E000115"));
		
		return model;
		}
}
