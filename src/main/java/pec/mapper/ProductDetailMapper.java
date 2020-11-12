package pec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pec.dto.ProductDetailDto;

/**
 * ProductDetailController
 * 27/8/2020
 * @author Kyaw Min Khant, Zin Nwe Htet
 */

@Mapper
public interface ProductDetailMapper {
	
	public List<ProductDetailDto> getProductList();
	
	public ProductDetailDto getProductById(@Param("product_info_id")int product_info_id);

}
