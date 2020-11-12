package pec.dto;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * Supplier Order Info Dto
 * 26/8/2020
 * @author Wutt Yee Tun
 */
@Data
public class SupplierOrderInfoDto {
	
	private Integer supplier_order_info_id;
	private Integer supplier_info_id;
	
	/*@DateTimeFormat(pattern="dd-MMM-yyyy")*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date order_receive_date;
	private String orderReceiveDate;
	private Integer total_price;
	private Integer office_info_id;
	private Integer delete_flag;
	private String insert_user;
	private Date insert_date;
	private String update_user;
	private Date update_date;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date order_date;
	private String orderDate;
	private String supplier_name;
	private String dateFrom;
	private String dateTo;
	@DateTimeFormat(pattern="dd-MMM-yyyy")
	private String date_from;
	private String date_to;
	private int rowIndex;
	
}