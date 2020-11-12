package pec.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SupplierOrderWrapperDto {
	List<SupplierWrapperDto> productList = new ArrayList<SupplierWrapperDto>();
	List<SupplierWrapperDto> supplierOrderList = new ArrayList<SupplierWrapperDto>();
	SupplierOrderInfoDto supplierOrderInfoDto = new SupplierOrderInfoDto();
	SupplierWrapperDto supplierWrapperDto=new SupplierWrapperDto();

}
