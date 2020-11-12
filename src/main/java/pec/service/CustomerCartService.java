package pec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pec.dto.InventoryInfoDto;
import pec.dao.CustomerCartDao;
import pec.dto.ProductInfoDto;

@Service
@Transactional
public class CustomerCartService {

	@Autowired
	private CustomerCartDao dao;
	
	public InventoryInfoDto getQuantity(Integer Id) {
		return dao.getQuantity(Id);
		
	}
}
