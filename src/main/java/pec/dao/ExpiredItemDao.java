package pec.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.dto.ExpiredItemDto;
import pec.mapper.ExpiredItemMapper;
/**
 * Expired Item Dao
 * 28/8/2020
 * @author Thiri Yadanar Aung
 */
@Repository
public class ExpiredItemDao {
	
	@Autowired
	private ExpiredItemMapper ExpiredItemMapper;
	
	public List<ExpiredItemDto> getExpiredItem(int office_info_id, String newDate) {
		return ExpiredItemMapper.getExpiredItem(office_info_id, newDate);
		
	   }
	}

