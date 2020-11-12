package pec.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pec.dto.ProductDetailDto;
import pec.mapper.ProductListMapper;

@Repository
public class ProductListDao {

	@Autowired
	private ProductListMapper mapper;
	
	public List<ProductDetailDto> getProductList(int office_id){
		return mapper.getProductList(office_id);
		
	}
	
	public List<ProductDetailDto> searchProductByName(int office_id,String product_name) {
		return mapper.searchProductByName(office_id,product_name);
	}
	
	public List<ProductDetailDto> getCategoryList(int office_id){
		return mapper.getCategoryList(office_id);
		
	}
	public List<ProductDetailDto> getMedicineTypeList(int office_id){
		return mapper.getMedicineTypeList(office_id);
		
	}
	public List<ProductDetailDto> getMedicineNameList(int office_id){
		return mapper.getMedicineNameList(office_id);
		
	}
	
	public List<ProductDetailDto> searchProductByMedicineType(int office_id,int medicine_info_id) {
		return mapper.searchProductByMedicineType(office_id,medicine_info_id);
	}

	public List<ProductDetailDto> searchProductByCategory(int office_id,int category_info_id) {
		return mapper.searchProductByCategory(office_id,category_info_id);
	}
	
}
