package pec.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import pec.dao.UserDao;
import pec.dto.CustomerInfoDto;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public Model msgProperty(Model model) throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		model.addAttribute("E000001", prop.getProperty("E000001"));
		model.addAttribute("E000002", prop.getProperty("E000002"));
		model.addAttribute("E000003", prop.getProperty("E000003"));
		model.addAttribute("E000004", prop.getProperty("E000004"));
		model.addAttribute("E000005", prop.getProperty("E000005"));
		model.addAttribute("E000006", prop.getProperty("E000006"));
		model.addAttribute("E000007", prop.getProperty("E000007"));
		model.addAttribute("E000008", prop.getProperty("E000008"));
		model.addAttribute("E000010", prop.getProperty("E000010"));
		model.addAttribute("E000011", prop.getProperty("E000011"));
		model.addAttribute("E000017", prop.getProperty("E000017"));
		return model; 
	}

	public CustomerInfoDto getLoginEmployeeInfo(String log_email_address) {
		return userDao.getLoginEmployeeInfo(log_email_address);
	}

	public CustomerInfoDto getLoginCustomerInfo(String log_email_address) {
		return userDao.getLoginCustomerInfo(log_email_address);
	}

	public void signup(CustomerInfoDto userDto) {
		
//		int loginId = userDao.getMaxLogin_user_id()+1;

		userDao.signup(userDto);
		
//		userDao.loginUser(userDto);
	}

	public int getDuplicate(String email_address) {
		return userDao.getDuplicate(email_address);
	}

	public int getMaxLogin_user_id(CustomerInfoDto userDto) {
		return userDao.getMaxLogin_user_id(userDto);
	}

	public void loginUser(CustomerInfoDto userDto) {
		String pass_encode = new BCryptPasswordEncoder().encode(userDto.getPassword());
		userDto.setPassword(pass_encode);
		userDao.loginUser(userDto);
	}
	
}
