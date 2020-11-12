package pec.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.dto.ExpireDto;
import pec.dto.SupplierIdDto;
import pec.mapper.ExpiredMapper;

/** 
 * Expired Remove Dao
 * 27/8/2020
 * @author Khin Me Me Latt
 */
@Repository
public class ExpiredRemoveDao {

	@Autowired
	private ExpiredMapper expiredMapper;
	
	public List<ExpireDto> getExpiredItems(int office_info_id, String newDate) {
		return expiredMapper.getExpiredItems(office_info_id, newDate);
	}
	
	public void removeExpiredItems(int SUPPLIER_DETAIL_ORDER_INFO_ID) {
		expiredMapper.removeExpiredItems(SUPPLIER_DETAIL_ORDER_INFO_ID);
	}
	
	public List<ExpireDto> dateSearch(int office_info_id, String dateFrom,String dateTo){
		return expiredMapper.dateSearch(office_info_id, dateFrom, dateTo);
	}
	
	public String minExpireDate() {
		return expiredMapper.minExpireDate();
	}
}
