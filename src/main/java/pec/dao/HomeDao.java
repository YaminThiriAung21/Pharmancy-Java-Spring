package pec.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.dto.HomeDto;
import pec.mapper.HomeMapper;

@Repository
public class HomeDao {

	@Autowired
	private HomeMapper mapper;
	
	public List<HomeDto> getOfficeNameList(){
		return mapper.getOfficeNameList();
	}
}
