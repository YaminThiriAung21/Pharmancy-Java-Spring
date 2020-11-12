package pec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pec.dto.ExpiredItemDto;

/**
 * Expired Item Mapper
 * 29/8/2020
 * @author Thiri Yadanar Aung
 */

@Mapper
public interface ExpiredItemMapper {
		public List<ExpiredItemDto> getExpiredItem(@Param("office_info_id") int office_info_id ,@Param("newDate")String newDate);


}
