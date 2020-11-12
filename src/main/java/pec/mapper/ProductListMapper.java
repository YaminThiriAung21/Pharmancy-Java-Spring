package pec.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pec.dto.ProductDetailDto;

@Mapper
public interface ProductListMapper {

	public List<ProductDetailDto> getProductList(@Param("office_id")int office_id);
	
	public List<ProductDetailDto> searchProductByName(@Param("office_id")int office_id,@Param("product_name")String product_name);
	
	public List<ProductDetailDto> getCategoryList(@Param("office_id")int office_id);
	
	public List<ProductDetailDto> getMedicineTypeList(@Param("office_id")int office_id);
	
	public List<ProductDetailDto> getMedicineNameList(@Param("office_id")int office_id);

	public List<ProductDetailDto> searchProductByMedicineType(@Param("office_id")int office_id,@Param("medicine_info_id")int medicine_info_id);
	
	public List<ProductDetailDto> searchProductByCategory(@Param("office_id")int office_id,@Param("category_info_id")int category_info_id);
	
}
