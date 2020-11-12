package pec.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.dto.AccountDto;
import pec.dto.EmployeeInfoDto;
import pec.mapper.LoginMapper;

/**
 * Login Dao
 * 25/8/2020
 * @author Su Su Lin
 */
@Repository
public class LoginDao {
	
	@Autowired
	private LoginMapper loginMapper;
	
	public EmployeeInfoDto getLoginInfo(String email) {
		
		return loginMapper.getLoginInfo(email);
	}
	
	

}
