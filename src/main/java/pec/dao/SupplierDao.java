package pec.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.dto.SupplierDto;
import pec.mapper.SupplierMapper;

@Repository
public class SupplierDao {
	
	@Autowired
	private SupplierMapper mapper;
	
	public List<SupplierDto> getSupplierList(){
		return mapper.getSupplierList();
	}
	public void register(SupplierDto supplierDto) {
		mapper.register(supplierDto);
	}
	
	public void update(SupplierDto supplierDto) {
		mapper.update(supplierDto);
	}
	public SupplierDto getSupplierDto(Integer supplier_info_id) {
		return mapper.getSupplierDto(supplier_info_id);
	}
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		mapper.delete(id);
	}
	/*public int DuplicateRegCode(String suppliercode) {
		return mapper.DuplicateRegCode(suppliercode);
	}*/
	public int getDuplicateemail(SupplierDto supplierDto) {
		return mapper.getDuplicateemail(supplierDto);
	}
	
	public int getDuplicatephno(SupplierDto supplierDto) {
		return mapper.getDuplicatephno(supplierDto);
	}
	public int getDuplicatesuppliername(SupplierDto supplierDto) {
		return mapper.getDuplicatesuppliername(supplierDto);
	}
	
	//RegistrationDuplicate
	public int DuplicateRegCode(SupplierDto supplierDto) {
		return mapper.DuplicateRegCode(supplierDto);
	}
	public int DuplicateRegName(SupplierDto supplierDto) {
		return mapper.DuplicateRegName(supplierDto);
	}
	public int DuplicateRegMail(SupplierDto supplierDto) {
		return mapper.DuplicateRegMail(supplierDto);
	}
	public int DuplicateRegPhone(SupplierDto supplierDto) {
		return mapper.DuplicateRegPhone(supplierDto);
	}
	
	
	
	

	
}
