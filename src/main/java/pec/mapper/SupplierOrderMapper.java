package pec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pec.dto.SupplierDetailOrderInfoDto;
import pec.dto.SupplierDto;
import pec.dto.SupplierOrderInfoDto;

@Mapper
public interface SupplierOrderMapper {

	public List<SupplierOrderInfoDto> getSupplierOrderList(@Param("office_info_id") int office_info_id);
	
	public List<SupplierDto> getSupplierName();

	public List<SupplierDetailOrderInfoDto> getListForUpdate(Integer id);

	public SupplierOrderInfoDto getOrderList(Integer id);
	
	public void updateSupplierOrder(SupplierOrderInfoDto supOrderInfoDto);

	public void updateSupplierDetailOrder(SupplierDetailOrderInfoDto supplierDetailOrderInfoDto);
	
	public List<SupplierDetailOrderInfoDto> getSupplierDetailOrderList(Integer id);

	public void deleteOrder(Integer id);

	public void deleteDetailOrder(Integer id);

	public List<SupplierOrderInfoDto> getListByDate(SupplierOrderInfoDto supOInfoDto);
	
	public List<SupplierDetailOrderInfoDto> deleteFlag(Integer id);
	
	public void changeDeleteFlag(SupplierDetailOrderInfoDto supDOWDto1);
	
}
