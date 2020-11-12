package pec.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pec.dto.MedicineInfoDto;
import pec.mapper.MedicineInfoMapper;

@Repository
public class MedicineInfoDao {
	@Autowired
	private MedicineInfoMapper mapper;
	
	public List<MedicineInfoDto> getMedicineInfoList(){		
		return mapper.getMedicineInfoList();
	}
	
	public void addMedicine(MedicineInfoDto medicineInfoDto) {
		mapper.addMedicine (medicineInfoDto);
	}
	
	public MedicineInfoDto getMedicineInfoDto(int medicine_info_id) {
		return mapper.getMedicineInfoDto(medicine_info_id);
	}
	
	public void updateMedicine(MedicineInfoDto medicineInfoDto) {
		mapper.updateMedicine(medicineInfoDto);
	}
	
	public void deleteMedicine(Integer id) {
		mapper.deleteMedicine(id);
	}

	public int getDuplicate(String medicine_code) {
		return mapper.getDuplicate(medicine_code);
	}
	

}
