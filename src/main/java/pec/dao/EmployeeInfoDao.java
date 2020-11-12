package pec.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pec.dto.EmployeeInfoDto;
import pec.mapper.EmployeeInfoMapper;

/**
 * Employee Info Dao
 * 27/8/2020
 * @author Thiha
 */
@Repository
public class EmployeeInfoDao {
	
	@Autowired
	private EmployeeInfoMapper EmployeeInfoMapper;
	
	public List<EmployeeInfoDto> getEmployeeInfo() {
		return EmployeeInfoMapper.getEmployeeInfo();
	}
	
	public EmployeeInfoDto getUpdate(int id) {
		return EmployeeInfoMapper.getUpdate(id);	
	}
	
	public void employeeInfoUpdate(EmployeeInfoDto employeeInfoDto) {
		EmployeeInfoMapper.employeeInfoUpdate(employeeInfoDto);
	}
	
	public void employeeLoginUpdate(EmployeeInfoDto employeeInfoDto) {
		EmployeeInfoMapper.employeeLoginUpdate(employeeInfoDto);
	}
	
	public void employeePasswordUpdate(EmployeeInfoDto employeeInfoDto) {
		EmployeeInfoMapper.employeePasswordUpdate(employeeInfoDto);
	}
	
	public void employeeInfoDelete(int id) {
		EmployeeInfoMapper.employeeInfoDelete(id);
	}
	
	public void employeeLoginDelete(int id) {
		EmployeeInfoMapper.employeeLoginDelete(id);
	}

	public List<EmployeeInfoDto> getDuplicate(int id) {
		return EmployeeInfoMapper.getDuplicate(id);
	}

	public List<EmployeeInfoDto> getDuplicateRegister() {
		return EmployeeInfoMapper.getDuplicateRegister();
	}

	public void employeeInfoRegister(EmployeeInfoDto employeeInfoDto) {
		EmployeeInfoMapper.employeeInfoRegister(employeeInfoDto);
	}

	public void employeeLoginRegister(EmployeeInfoDto employeeInfoDto) {
		EmployeeInfoMapper.employeeLoginRegister(employeeInfoDto);
	}

	public int getLoginUserInfoId(EmployeeInfoDto employeeInfoDto) {
		return EmployeeInfoMapper.getLoginUserInfoId(employeeInfoDto);
	}

	public List<String> getOfficeName() {
		return EmployeeInfoMapper.getOfficeName();
	}

	public int getOfficeId(String office_name) {
		return EmployeeInfoMapper.getOfficeId(office_name);
	}

	
	

}
