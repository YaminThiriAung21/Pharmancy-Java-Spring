package pec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pec.dto.HomeDto;
import pec.dao.HomeDao;

@Service
@Transactional
public class HomeService {

	@Autowired
	private HomeDao dao;
	
	public List<HomeDto> getOfficeNameList(){
		return dao.getOfficeNameList();
	}
}
