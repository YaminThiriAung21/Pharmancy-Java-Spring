package pec.dao;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.dto.OfficeDto;
import pec.mapper.OfficeInfoMapper;

/**
 * Office Management Dao
 * 31/8/2020
 * @author Aung San Htay
 */  
@Repository
public class OfficeInfoDao {
	@Autowired
	private OfficeInfoMapper officeInfoMapper;
	
	public List<OfficeDto> getOfficeInfo() {
		return officeInfoMapper.getOfficeInfo();	
	}
	
	public void register(OfficeDto officeDto) {
		officeInfoMapper.register(officeDto);
	}

	public OfficeDto getUpdate(int id) {
		return officeInfoMapper.getUpdate(id);
	}

	public void update(OfficeDto officeDto) {
		officeInfoMapper.update(officeDto);
		
		
	}

	public void delete(int id) {
		officeInfoMapper.delete(id);
		
	}

	public List<OfficeDto> getDuplicate(int id) {
		return officeInfoMapper.getDuplicate(id);
	}

	public void OfficeUpdateWithNoImage(OfficeDto officeDto) {
		officeInfoMapper.OfficeUpdateWithNoImage(officeDto);
	}

	public List<OfficeDto> getDuplicatedCode(int id) {
		// TODO Auto-generated method stub
		return officeInfoMapper.getDuplicatedCode(id);
	}

}
