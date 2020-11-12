package pec.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Expired Item Service
 * 28/8/2020
 * @author Thiri Yadanar Aung
 */

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import groovyjarjarcommonscli.ParseException;
import pec.dao.ExpiredItemDao;
import pec.dto.ExpiredItemDto;
@Service
@Transactional
public class ExpiredItemService {
	
	@Autowired
	private ExpiredItemDao ExpiredItemDao;
	
	public List<ExpiredItemDto> getExpiredItem() {
		int office_info_id = 26;
		Date today = new Date();
		String oldDate;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		oldDate = sdf.format(today);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(oldDate));
		}catch(java.text.ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, 7);
		String newDate = sdf.format(c.getTime());
		return ExpiredItemDao.getExpiredItem(office_info_id, newDate);
		
	}
	

}
