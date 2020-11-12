package pec.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pec.dto.AccountDto;
import pec.dto.EmployeeInfoDto;

/**
 * Login Mapper
 * 25/8/2020
 * @author Su Su Lin
 */
@Mapper
public interface LoginMapper {
	
	public EmployeeInfoDto getLoginInfo(@Param("email") String email);

}
