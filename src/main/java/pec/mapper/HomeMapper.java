package pec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import pec.dto.HomeDto;

@Mapper
public interface HomeMapper {

		public List<HomeDto> getOfficeNameList();

}
