package pec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import pec.dto.SupplierDto;

@Mapper
public interface SupplierMapper {
	
	public List<SupplierDto> getSupplierList();

	public void register(SupplierDto supplierDto);

	public void update(SupplierDto supplierDto);

	public SupplierDto getSupplierDto(Integer supplier_info_id);

	public void delete(Integer id);

	//public int DuplicateRegCode(String suppliercode);
	
	public int getDuplicateemail(SupplierDto supplierDto);

	public int getDuplicatephno(SupplierDto supplierDto);

	public int getDuplicatesuppliername(SupplierDto supplierDto);

	//RegistrationDuplicate
	public int DuplicateRegCode(SupplierDto supplierDto);

	public int DuplicateRegName(SupplierDto supplierDto);

	public int DuplicateRegMail(SupplierDto supplierDto);

	public int DuplicateRegPhone(SupplierDto supplierDto);

	

	
}
