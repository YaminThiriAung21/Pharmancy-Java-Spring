package pec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import pec.dto.MedicineInfoDto;



@Mapper
public interface MedicineInfoMapper {
	
	public List<MedicineInfoDto> getMedicineInfoList();
	
	public void addMedicine(MedicineInfoDto medicineInfoDto);
	
	public MedicineInfoDto getMedicineInfoDto(int medicine_info_id);
	
	public void updateMedicine(MedicineInfoDto medicineInfoDto);
	
	

	public void deleteMedicine(Integer id);

	public int getDuplicate(String medicine_code);

}
