package pec.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Login Mapper
 * 27/8/2020
 * @author Thaw Myo Htet
 */
@Mapper
public interface InventoryMapper {

	public void addInventory(@Param("amount") int amount, @Param("product_info_id") int product_info_id, @Param("office_info_id") int office_info_id);

	public void subtractInventory(@Param("amount") int amount, @Param("product_info_id") int product_info_id, @Param("office_info_id") int office_info_id);
	
	public void expireInventory(@Param("amount") int amount, @Param("product_info_id") int product_info_id, @Param("office_info_id") int office_info_id);
	
	public void damageInventory(@Param("amount") int amount, @Param("product_info_id") int product_info_id, @Param("office_info_id") int office_info_id);
	
	public void totalExpireInventory(@Param("amount") int amount, @Param("product_info_id") int product_info_id, @Param("office_info_id") int office_info_id);

	public void totalDamageInventory(@Param("amount") int amount, @Param("product_info_id") int product_info_id, @Param("office_info_id") int office_info_id);

}

