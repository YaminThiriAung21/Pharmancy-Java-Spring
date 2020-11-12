package pec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pec.dto.CustomerInfoDto;

/**
 * Customer Mapper
 * 27/8/2020
 * @author Htet Wai Yan Aung
 */

@Mapper
public interface CustomerMapper {
	
	public List<CustomerInfoDto> getCustomerList();
	
	public CustomerInfoDto getUpdate(int customer_info_id);

	public void update(CustomerInfoDto customerDto);

	public CustomerInfoDto getDelete(int id);
	
	public void delete(int id);

	public void deleteId(CustomerInfoDto info);
	
}
