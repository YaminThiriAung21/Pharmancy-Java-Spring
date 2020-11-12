package pec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pec.dao.OrderListDao;
import pec.dto.DateRange;
import pec.dto.DetailOrderListDto;
import pec.dto.OrderListDto;

/**
 * Order List Service
 * 31/8/2020
 * @author Yamin Thiri Aung
 */
@Service
@Transactional
public class OrderListService {
	@Autowired
	private OrderListDao orderListDao;
	public List<OrderListDto> getOrderList()
	{
		return orderListDao.getOrderList();
	
	}
	public List<DetailOrderListDto> getDetailOrderList(int id)
	{
		return orderListDao.getDetailOrderList(id);
	
	}
	public List<OrderListDto> getSearchList(DateRange dateRange) 
	{
		return orderListDao.getSearchList(dateRange);
	}


}
