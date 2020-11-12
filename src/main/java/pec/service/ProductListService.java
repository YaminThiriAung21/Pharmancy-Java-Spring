package pec.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pec.dao.ProductListDao;
import pec.dto.ProductDetailDto;

@Service
@Transactional
public class ProductListService {

	@Autowired
	private ProductListDao dao;
	
	
	public List<ProductDetailDto> getProductList(int office_id){
		return dao.getProductList(office_id);

	}
	
	public List<ProductDetailDto> searchProductByName(int office_id,String product_name) {
		return dao.searchProductByName(office_id,product_name);
	}
	
	public List<ProductDetailDto> getCategoryList(int office_id) {
		return dao.getCategoryList(office_id);  
	}
	public List<ProductDetailDto> getMedicineTypeList(int office_id) {
		return dao.getMedicineTypeList(office_id);
	}
	public List<ProductDetailDto> getMedicineNameList(int office_id) {
		return dao.getMedicineNameList(office_id);
	}
	
	public List<ProductDetailDto> searchProductByMedicineType(int office_id,int medicine_info_id) {
		return dao.searchProductByMedicineType(office_id,medicine_info_id); 
	}
	
	public List<ProductDetailDto> searchProductByCategory(int office_id,int category_info_id) {
		return dao.searchProductByCategory(office_id,category_info_id); 
	}
}
