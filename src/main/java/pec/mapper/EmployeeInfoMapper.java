package pec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pec.dto.EmployeeInfoDto;

/**
 * Employee Info Mapper 27/8/2020
 * 
 * @author Thiha
 */
@Mapper
public interface EmployeeInfoMapper {

	public List<EmployeeInfoDto> getEmployeeInfo();

	public EmployeeInfoDto getUpdate(int id);

	public void employeeInfoUpdate(EmployeeInfoDto employeeInfoDto);

	public void employeeLoginUpdate(EmployeeInfoDto employeeInfoDto);

	public void employeePasswordUpdate(EmployeeInfoDto employeeInfoDto);

	public void employeeInfoDelete(int id);

	public void employeeLoginDelete(int id);

	public List<EmployeeInfoDto> getDuplicate(int id);

	public List<EmployeeInfoDto> getDuplicateRegister();

	public void employeeInfoRegister(EmployeeInfoDto employeeInfoDto);

	public void employeeLoginRegister(EmployeeInfoDto employeeInfoDto);

	public int getLoginUserInfoId(EmployeeInfoDto employeeInfoDto);

	public List<String> getOfficeName();

	public int getOfficeId(String office_name);
}
