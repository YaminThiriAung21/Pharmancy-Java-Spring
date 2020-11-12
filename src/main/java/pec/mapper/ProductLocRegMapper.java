package pec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import pec.dto.ProductLocDto;

/**
 * Product Location Register Mapper
 * 25/8/2020
 * @author Thet Mon Hnin
 */
@Mapper
public interface ProductLocRegMapper {

	
	
	public void updateProductLoc(ProductLocDto productlocDto);
		
	public ProductLocDto getUpdate(int id);
	public List<ProductLocDto> getProductLocRegList();
	public void registerProductLoc(ProductLocDto productlocDto);
	public void delete(int id);
	public List<ProductLocDto> getDuplicate(int id);

	public List<ProductLocDto> getDuplicateRegister();
}

