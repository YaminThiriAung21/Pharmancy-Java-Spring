package pec.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.mapper.InventoryMapper;

/**
 * Login Mapper
 * 27/8/2020
 * @author Thaw Myo Htet
 */

@Repository
public class InventoryDao {
	@Autowired
	private InventoryMapper inventoryMapper;
	
	public void addInventory(int amount, int product_info_id, int office_info_id) {
		inventoryMapper.addInventory(amount, product_info_id, office_info_id);
	}
	
	public void subtractInventory(int amount, int product_info_id, int office_info_id) {
		inventoryMapper.subtractInventory(amount, product_info_id, office_info_id);
	}
	
	public void expireInventory(int amount, int product_info_id, int office_info_id) {
		inventoryMapper.damageInventory(amount, product_info_id, office_info_id);
	}
	
	public void damageInventory(int amount, int product_info_id, int office_info_id) {
		inventoryMapper.expireInventory(amount, product_info_id, office_info_id);
	}
	
	public void totalExpireInventory(int amount, int product_info_id, int office_info_id) {
		inventoryMapper.totalExpireInventory(amount, product_info_id, office_info_id);
	}
	
	public void totalDamageInventory(int amount, int product_info_id, int office_info_id) {
		inventoryMapper.totalDamageInventory(amount, product_info_id, office_info_id);
	}
	
}
