package pec.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.dto.CategoryDto;
import pec.dto.EmployeeInfoDto;
import pec.dto.InventoryInfoDto;
import pec.dto.MedicineInfoDto;
import pec.dto.PackagingInfoDto;
import pec.dto.ProductInfoDto;
import pec.mapper.ProductInfoMapper;
@Repository

public class ProductInfoDao {
	
	@Autowired
	private ProductInfoMapper productInfoMapper;
	
	public List<ProductInfoDto> getProductList(int office_info_id){
		return productInfoMapper.getProductList(office_info_id);
	}
	
	public List<MedicineInfoDto> getMedicineInfoList(){
		return productInfoMapper.getMedicineInfoList();
	}
	
	public List<CategoryDto> getCategoryInfoList(){
		return productInfoMapper.getCategoryInfoList();
	}
	
	public List<PackagingInfoDto> getPackagingList(){
		return productInfoMapper.getPackagingList();
	}
	
	public void addProduct(ProductInfoDto prdInfoDto) {
		productInfoMapper.addProduct(prdInfoDto);
	}
	
	public void addProductQuantity(InventoryInfoDto inventoryDto) {
		productInfoMapper.addProductQuantity(inventoryDto);
	}
	
	public ProductInfoDto getProduct(int product_info_id) {
		return productInfoMapper.getProduct(product_info_id);
	}
	
	public void updateProduct(ProductInfoDto productDto) {
		productInfoMapper.updateProduct(productDto);
	}
	
	public void updateProductWithImage(ProductInfoDto productDto) {
		productInfoMapper.updateProductWithImage(productDto);
	}
	
	public void deleteProduct(Integer product_info_id) {
		productInfoMapper.deleteProduct(product_info_id);
	}
	
	public int getDuplicate(String medicine_code) {
		return productInfoMapper.getDuplicate(medicine_code);
	}
}
