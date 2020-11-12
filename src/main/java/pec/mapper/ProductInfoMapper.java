package pec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pec.dto.CategoryDto;
import pec.dto.InventoryInfoDto;
import pec.dto.MedicineInfoDto;
import pec.dto.PackagingInfoDto;
import pec.dto.ProductInfoDto;

@Mapper
public interface ProductInfoMapper {
	public List<ProductInfoDto> getProductList(@Param("office_info_id")int office_info_id);
	
	public List<MedicineInfoDto> getMedicineInfoList();
	
	public List<CategoryDto> getCategoryInfoList();
	
	public List<PackagingInfoDto> getPackagingList();

	public void addProduct(ProductInfoDto prdInfoDto);
	
	public void addProductQuantity(InventoryInfoDto inventoryDto);
	
	public ProductInfoDto getProduct(@Param("product_info_id")int product_info_id);

	public void updateProduct(ProductInfoDto prouductDto);
	
	public void updateProductWithImage(ProductInfoDto productDto);
	
	public void deleteProduct(int product_info_id);
	
	public int getDuplicate(String medicine_code);
}
