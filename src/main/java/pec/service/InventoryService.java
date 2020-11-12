package pec.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pec.dao.InventoryDao;
import pec.dto.EmployeeInfoDto;


/**
 * Login Mapper
 * 27/8/2020
 * @author Thaw Myo Htet
 */

@Service
@Transactional
public class InventoryService {

	@Autowired
	InventoryDao inventoryDao;
	
	@Autowired
    HttpSession session;
	
	 
	public void addInventory(int amount, int product_info_id) {	
		//System.out.print("amount"+amount+ "productid"+product_info_id+ "office"+office_info_id);
		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		
		inventoryDao.addInventory(amount, product_info_id, empDto.getOffice_info_id());
	}
	
	public void subtractInventory(int amount, int product_info_id, int office_info_id) {	
			inventoryDao.subtractInventory(amount, product_info_id, office_info_id);
	}
	
	public void expireInventory(int amount, int product_info_id) {	
		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		
		inventoryDao.expireInventory(amount, product_info_id, empDto.getOffice_info_id());
	}
	
	public void damageInventory(int amount, int product_info_id) {	
		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		
		inventoryDao.damageInventory(amount, product_info_id, empDto.getOffice_info_id());
	}
	
	public void totalExpireInventory(int amount, int product_info_id) {	
		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		
		inventoryDao.totalExpireInventory(amount, product_info_id, empDto.getOffice_info_id());
	}
	
	public void totalDamageInventory(int amount, int product_info_id) {	
		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		
		inventoryDao.totalDamageInventory(amount, product_info_id, empDto.getOffice_info_id());
	}
}
