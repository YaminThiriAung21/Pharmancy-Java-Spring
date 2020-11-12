package pec.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.dto.ProductLocDto;
import pec.mapper.ProductLocRegMapper;

/**
 * Product Location Register Dao
 * 7/9/2020
 * @author Thet Mon Hnin+Yin Min Oo
 */
@Repository
public class ProductLocRegDao {
	
	@Autowired
	private ProductLocRegMapper productlocregMapper;
	
	public List<ProductLocDto> getProductLocRegList()
	{
		return productlocregMapper.getProductLocRegList();
	}
	
	public void registerProductLoc(ProductLocDto productlocDto)
	{
		productlocregMapper.registerProductLoc(productlocDto);
	}
	public void delete(int id)
	{
		productlocregMapper.delete(id);
	}
	
	public void updateProductLoc(ProductLocDto productlocDto) {
		productlocregMapper.updateProductLoc(productlocDto);
	}
	
	public ProductLocDto getUpdate(int id) {
		return productlocregMapper.getUpdate(id);
	}
	public List<ProductLocDto> getDuplicate(int id) {
		return productlocregMapper.getDuplicate(id);
	}

	public List<ProductLocDto> getDuplicateRegister() {
		// TODO Auto-generated method stub
		return productlocregMapper.getDuplicateRegister();
	}

	
	
		
}
