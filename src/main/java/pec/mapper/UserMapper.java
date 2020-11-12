package pec.mapper;

import org.apache.ibatis.annotations.Mapper;

import pec.dto.CustomerInfoDto;

@Mapper
public interface UserMapper {

	public CustomerInfoDto getLoginEmployeeInfo(String log_email_address);

	public CustomerInfoDto getLoginCustomerInfo(String log_email_address);
	
	public void signup(CustomerInfoDto userDto);

	public void loginUser(CustomerInfoDto userDto);

	public int getDuplicate(String email_address);

	public int getMaxLogin_user_id(CustomerInfoDto userDto);
	
}
