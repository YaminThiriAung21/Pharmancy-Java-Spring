package pec.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.dto.ProductDetailDto;
import pec.mapper.ProductDetailMapper;


/**
 * ProductDetailController
 * 27/8/2020
 * @author Kyaw Min Khant, Zin Nwe Htet
 */

@Repository
public class ProductDetailDao {
	
	@Autowired
	private ProductDetailMapper productdetailmapper;
	
	public List<ProductDetailDto> getProductList(){
		
		return productdetailmapper.getProductList();
	}
	
	public ProductDetailDto getProductById(int product_info_id) {
		return productdetailmapper.getProductById(product_info_id);
	}
	

}
