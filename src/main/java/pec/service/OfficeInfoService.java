package pec.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import pec.dao.OfficeInfoDao;
import pec.dto.EmployeeInfoDto;
import pec.dto.OfficeDto;

/**
 * Office Info Service
 * 25/8/2020
 * @author Aung San Htay
 */
@Service
@Transactional
public class OfficeInfoService {
	
	@Autowired
	private OfficeInfoDao officeInfoDao;
	@Autowired
	private HttpSession session;
	
	public void register(OfficeDto officeDto) {
		officeInfoDao.register(officeDto);		
		}

	public List<OfficeDto> getOfficeInfo() {
		return officeInfoDao.getOfficeInfo();
	}

	public OfficeDto getUpdate(int id) {
		return officeInfoDao.getUpdate(id);
	}

	public void update(OfficeDto officeDto) {
		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		officeDto.setUpdate_user(empDto.getEmployee_name());
		officeDto.setUpdate_date(new Date());
		
		officeInfoDao.update(officeDto);
	}

	public void delete(int id) {
		officeInfoDao.delete(id);
		
	}
	

	public Model messageProperty(Model model) throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		model.addAttribute("E000018", prop.getProperty("E000018"));
		model.addAttribute("E000019", prop.getProperty("E000019"));
		model.addAttribute("E000020", prop.getProperty("E000020"));
		model.addAttribute("E000021", prop.getProperty("E000021"));
		model.addAttribute("E000022", prop.getProperty("E000022"));
		model.addAttribute("E000023", prop.getProperty("E000023"));
		model.addAttribute("E000024", prop.getProperty("E000024"));
		model.addAttribute("E000025", prop.getProperty("E000025"));
		model.addAttribute("E000101", prop.getProperty("E000101"));
		model.addAttribute("E000102", prop.getProperty("E000102"));
		model.addAttribute("E000103", prop.getProperty("E000103"));

		return model;
	}

	public List<OfficeDto> getDuplicate(int id) {
		return officeInfoDao.getDuplicate(id);
	}

	public void OfficeUpdateWithNoImage(OfficeDto officeDto) {
		officeInfoDao.OfficeUpdateWithNoImage(officeDto);
		
	}

	public List<OfficeDto> getDuplicatedCode(int id) {
		// TODO Auto-generated method stub
		return officeInfoDao.getDuplicatedCode(id);
	}

		


}
