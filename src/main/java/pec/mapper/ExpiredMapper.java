package pec.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pec.dto.ExpireDto;
import pec.dto.SupplierIdDto;
 
/**
 * Expired Mapper
 * 27/8/2020
 * @author Khin Me Me Latt
 */
@Mapper
public interface ExpiredMapper {

	public List<ExpireDto> getExpiredItems(@Param("office_info_id") int office_info_id, @Param("newDate") String newDate);
	
	public void removeExpiredItems(@Param("SUPPLIER_DETAIL_ORDER_INFO_ID") int SUPPLIER_DETAIL_ORDER_INFO_ID);
	
	public List<ExpireDto> dateSearch(@Param("office_info_id") int office_info_id, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo);
	
	public String minExpireDate();
}
