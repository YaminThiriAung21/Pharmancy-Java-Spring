package pec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pec.dto.InventoryInfoDto;
import pec.dto.ProductInfoDto;

@Mapper
public interface CartMapper {

	public InventoryInfoDto getQuantity(Integer Id);
	
}
