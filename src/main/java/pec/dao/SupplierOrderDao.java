package pec.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.dto.SupplierDetailOrderInfoDto;
import pec.dto.SupplierDto;
import pec.dto.SupplierOrderInfoDto;
import pec.mapper.SupplierOrderMapper;

@Repository
public class SupplierOrderDao {

	@Autowired
	SupplierOrderMapper supOLMap;
	
	public List<SupplierOrderInfoDto> getSupplierOrderList(int office_info_id){
		return supOLMap.getSupplierOrderList(office_info_id);
	}
	
	public List<SupplierDto> getSupplierName(){
		return supOLMap.getSupplierName();
	}

	public List<SupplierDetailOrderInfoDto> getListForUpdate(Integer id) {
		return supOLMap.getListForUpdate(id);
	}

	public SupplierOrderInfoDto getOrderList(Integer id) {
		return supOLMap.getOrderList(id);
	}
	
	public void updateSupplierOrder(SupplierOrderInfoDto supOrderInfoDto) {
		System.out.println("SupplierOrderInfoDto="+supOrderInfoDto);
		supOLMap.updateSupplierOrder(supOrderInfoDto);
	}

	public void updateSupplierDetailOrder(SupplierDetailOrderInfoDto supplierDetailOrderInfoDto) {
		System.out.println("SupplierDetailOrderInfoDto="+supplierDetailOrderInfoDto);
		System.out.println(supplierDetailOrderInfoDto.getExpire_date());
		supOLMap.updateSupplierDetailOrder(supplierDetailOrderInfoDto);
	}
	
	public List<SupplierDetailOrderInfoDto> getSupplierDetailOrderList(Integer id) {
		return supOLMap.getSupplierDetailOrderList(id);
	}

	public void deleteOrder(Integer id) {
		System.out.println("id="+id);
		supOLMap.deleteOrder(id);
	}

	public void deleteDetailOrder(Integer id) {
		supOLMap.deleteDetailOrder(id);
	}

	public List<SupplierOrderInfoDto> getListByDate(SupplierOrderInfoDto supOInfoDto) {
		return supOLMap.getListByDate(supOInfoDto);
	}
	
	public List<SupplierDetailOrderInfoDto> deleteFlag(Integer id) {
		return supOLMap.deleteFlag(id);
	}
	
	public void changeDeleteFlag(SupplierDetailOrderInfoDto supDOWDto1){
		supOLMap.changeDeleteFlag(supDOWDto1);
	}
}
