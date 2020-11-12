package pec.dao;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.dto.SupplierOrderInfoDto;
import pec.dto.SupplierOrderWrapperDto;
import pec.dto.SupplierWrapperDto;
import pec.mapper.SupplierWrapperMapper;




@Repository
public class SupplierWrapperDao {
	@Autowired
	private SupplierWrapperMapper supplierWrapperMapper;
	public List<SupplierWrapperDto> getProductList(int office_info_id){
		return supplierWrapperMapper.getProductList(office_info_id);
	}
	public SupplierWrapperDto addToOrder(int product_info_id) {
		return supplierWrapperMapper.addToOrder(product_info_id);
	}
	public List<SupplierWrapperDto> getSupplierName(){
		return supplierWrapperMapper.getSupplierName();
	}
	public void addDetail(SupplierWrapperDto supplierWrapperDto) {
		supplierWrapperMapper.addDetail(supplierWrapperDto);
	}
	public void addOrderInfo(SupplierOrderInfoDto supplierOrderInfoDto) {
		supplierWrapperMapper.addOrderInfo(supplierOrderInfoDto);
	}
}

