package pec.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pec.dao.ExpiredRemoveDao;
import pec.dto.DateRangeDto;
import pec.dto.ExpireDto;
import pec.dto.SupplierIdDto;
 
/**
 * Login Service
 * 27/8/2020
 * @author Khin Me Me Latt
 */
@Service
@Transactional
public class ExpireService {
	@Autowired
	private ExpiredRemoveDao expireDao;
	
	public List<ExpireDto> getExpiredItems() {	
		int office_info_id = 26;
		Date today = new Date();
		String oldDate;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		oldDate = sdf.format(today);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(oldDate));
		}catch(ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, 7);
		String newDate = sdf.format(c.getTime());
		return expireDao.getExpiredItems(office_info_id, newDate);
	}
	
	public void removeExpiredItems(SupplierIdDto supplierIdDto) {
		Integer[] SUPPLIER_DETAIL_ORDER_INFO_ID = supplierIdDto.getSUPPLIER_DETAIL_ORDER_INFO_ID();
		for(int i=0;i<SUPPLIER_DETAIL_ORDER_INFO_ID.length;i++) {
			expireDao.removeExpiredItems(SUPPLIER_DETAIL_ORDER_INFO_ID[i]);	
		}
	}
	
	public List<ExpireDto> dateSearch(DateRangeDto dateRange) {	
		int office_info_id = 26;
		String dateFrom, dateTo;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		dateFrom = sdf.format(dateRange.getDateFrom());
		dateTo = sdf.format(dateRange.getDateTo());
		Calendar cf = Calendar.getInstance();
		Calendar ct = Calendar.getInstance();
		try {
			cf.setTime(sdf.parse(dateFrom));
			ct.setTime(sdf.parse(dateTo));
		}catch(ParseException e) {
			e.printStackTrace();
		}
		cf.add(Calendar.DAY_OF_MONTH, 7);
		ct.add(Calendar.DAY_OF_MONTH, 7);
		dateFrom = sdf.format(cf.getTime());
		dateTo = sdf.format(ct.getTime());
		return expireDao.dateSearch(office_info_id, dateFrom, dateTo);
	}
	
	public String minExpireDate() {
		return expireDao.minExpireDate();
	}
}
