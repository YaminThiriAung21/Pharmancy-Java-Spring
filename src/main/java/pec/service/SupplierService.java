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

import pec.dao.SupplierDao;
import pec.dto.EmployeeInfoDto;
import pec.dto.SupplierDto;

@Service
@Transactional

public class SupplierService {
	@Autowired
	HttpSession session;
	
	public Model messageProperty(Model model) throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		model.addAttribute("E000001", prop.getProperty("E000001"));
		model.addAttribute("E000002", prop.getProperty("E000002"));
		model.addAttribute("E000026", prop.getProperty("E000026"));
		model.addAttribute("E000043", prop.getProperty("E000043"));
		model.addAttribute("E000040", prop.getProperty("E000040"));
		model.addAttribute("E000041", prop.getProperty("E000041"));
		model.addAttribute("E000042", prop.getProperty("E000042"));
		model.addAttribute("E000129", prop.getProperty("E000129"));
		model.addAttribute("E000139", prop.getProperty("E000139"));
		model.addAttribute("E000141", prop.getProperty("E000141"));
		model.addAttribute("E000045", prop.getProperty("E000045"));
		model.addAttribute("E000140", prop.getProperty("E000140"));
		return model; 
	}

	@Autowired
	private SupplierDao dao;
	
	public List<SupplierDto> getSupplierList(){
		return dao.getSupplierList();
	}
	
	public void register(SupplierDto supplierDto) {

		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		supplierDto.setInsert_user(empDto.getEmployee_name());
		supplierDto.setUpdate_user(empDto.getEmployee_name());
		supplierDto.setDelete_flag(0);
		/*
		Date date = new Date(31-01-20);
		*/
		supplierDto.setInsert_date(new Date());
		supplierDto.setUpdate_date(new Date());

		dao.register(supplierDto);
		}

	public void update(SupplierDto supplierDto) {
	EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
	// supplierDto.setInsert_user("user");
	supplierDto.setUpdate_user(empDto.getEmployee_name());
	supplierDto.setDelete_flag(0);
	// supplierDto.setInsert_date(new Date());
	supplierDto.setUpdate_date(new Date());
	dao.update(supplierDto);
	}
	public SupplierDto getSupplierDto(Integer supplier_info_id) {
		return dao.getSupplierDto(supplier_info_id);
	}
	
	public void delete(Integer id) {
		dao.delete(id);
	}
	
	//For Registration
	public int DuplicateRegCode(SupplierDto supplierDto) {
		return dao.DuplicateRegCode(supplierDto);
	}
	public int DuplicateRegName(SupplierDto supplierDto) {
		return dao.DuplicateRegName(supplierDto);
	}
	public int DuplicateRegMail(SupplierDto supplierDto) {
		return dao.DuplicateRegMail(supplierDto);
	}
	public int DuplicateRegPhone(SupplierDto supplierDto) {
		return dao.DuplicateRegPhone(supplierDto);
	}
	
	//For UpdateDuplicate
	public int getDuplicateemail(SupplierDto supplierDto) {
		return dao.getDuplicateemail(supplierDto);
	}
	public int getDuplicatephno(SupplierDto supplierDto) {
		return dao.getDuplicatephno(supplierDto);
	}
	public int getDuplicatesuppliername(SupplierDto supplierDto) {
		return dao.getDuplicatesuppliername(supplierDto);
	}

	

	

	
	
	
}
