package pec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pec.dto.DateRange;
import pec.dto.DetailOrderListDto;
import pec.dto.OrderListDto;

/**
 * Order List Mapper
 * 31/8/2020
 * @author Yamin Thiri Aung
 */
@Mapper
public interface OrderListMapper {
	
	public List<OrderListDto> getOrderList();
	public List<DetailOrderListDto> getDetailOrderList(int id);
	public List<OrderListDto> getSearchList(DateRange dateRange);

}

