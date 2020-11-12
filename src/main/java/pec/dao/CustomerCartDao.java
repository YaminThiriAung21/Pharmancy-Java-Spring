package pec.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.dto.InventoryInfoDto;
import pec.dto.ProductInfoDto;
import pec.mapper.CartMapper;

@Repository
public class CustomerCartDao {

	@Autowired
	private CartMapper mapper;
	
	public InventoryInfoDto getQuantity(Integer Id){
		return mapper.getQuantity(Id);
	}
}
