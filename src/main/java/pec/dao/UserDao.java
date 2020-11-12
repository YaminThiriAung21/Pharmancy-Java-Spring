package pec.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pec.dto.CustomerInfoDto;
import pec.mapper.UserMapper;

@Repository
public class UserDao {

	@Autowired
	private UserMapper userMapper;
	
	public void signup(CustomerInfoDto userDto) {
		userMapper.signup(userDto);		
	}

	public void loginUser(CustomerInfoDto userDto) {
		userMapper.loginUser(userDto);
	}

	public int getDuplicate(String email_address) {
		return userMapper.getDuplicate(email_address);
	}

	public int getMaxLogin_user_id(CustomerInfoDto userDto) {
		return userMapper.getMaxLogin_user_id(userDto);
	}
	
	public CustomerInfoDto getLoginEmployeeInfo(String log_email_address) {
		return userMapper.getLoginEmployeeInfo(log_email_address);
	}

	public CustomerInfoDto getLoginCustomerInfo(String log_email_address) {
		return userMapper.getLoginCustomerInfo(log_email_address);
	}
	
	

}
