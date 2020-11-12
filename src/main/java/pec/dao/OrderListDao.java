package pec.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.dto.DateRange;
import pec.dto.DetailOrderListDto;
import pec.dto.OrderListDto;
import pec.mapper.OrderListMapper;

/**
 * Order List Dao 31/8/2020
 * 
 * @author Yamin Thiri Aung
 */
@Repository
public class OrderListDao {

	@Autowired
	private OrderListMapper orderListMapper;
	
	public List<OrderListDto> getOrderList() {

		return orderListMapper.getOrderList();
	}

	public List<DetailOrderListDto> getDetailOrderList(int id) {

		return orderListMapper.getDetailOrderList(id);
	}

	public List<OrderListDto> getSearchList(DateRange dateRange) {

		return orderListMapper.getSearchList(dateRange);
	}

}
