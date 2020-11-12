package pec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import pec.dto.SupplierDto;
import pec.dto.SupplierOrderInfoDto;
import pec.dto.SupplierOrderWrapperDto;
import pec.dto.SupplierWrapperDto;



/**
 * Login Mapper
 * 25/8/2020
 * @author Rupar Htin Lin
 */

@Mapper
public interface SupplierWrapperMapper {
	public  List<SupplierWrapperDto> getProductList(@Param("office_info_id") int office_info_id);
	public SupplierWrapperDto addToOrder(@Param("product_info_id") int product_info_id);
	public List<SupplierWrapperDto> getSupplierDropdown();
	public List<SupplierWrapperDto> getSupplierName();
	/*public void orderConfirm(SupplierWrapperDto supplierWrapperDto);*/
	public void addDetail(SupplierWrapperDto supplierWrapperDto);
	public void addOrderInfo(SupplierOrderInfoDto supplierOrderInfoDto);
}
