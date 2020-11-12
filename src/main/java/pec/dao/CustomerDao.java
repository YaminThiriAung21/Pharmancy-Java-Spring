package pec.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.mapper.CustomerMapper;
import pec.dto.CustomerInfoDto;

/**
 * Customer Dao
 * 27/8/2020
 * @author Htet Wai Yan Aung
 */
@Repository
public class CustomerDao {
	
	@Autowired
	private CustomerMapper customerMapper;
	
	public List<CustomerInfoDto> getCustomerList(){
		return customerMapper.getCustomerList();
	}

	public CustomerInfoDto getUpdate(int customer_info_id) {
		return customerMapper.getUpdate(customer_info_id);	
	}
	
	public void update(CustomerInfoDto customerDto) {
		customerMapper.update(customerDto);
	}

	public void delete(int id) {
		customerMapper.delete(id);
	}

	public CustomerInfoDto getDelete(int id) {
		return customerMapper.getDelete(id);	
	}

	public void deleteId(CustomerInfoDto info) {
		customerMapper.deleteId(info);
	}
}
