package pec.datacode;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Admin Page Information List
 * 25/8/2020
 * @author Su Su Lin
 */
@Getter
@ToString
public enum AdminPageInfo {

	/** Login **/
	LOGIN("Login", "/login", "home/login"),

	/** Transition to TOP screen **/
	LOGIN_TOP("Login", "/", "home/login"),

	/** Login Error **/
	LOGIN_ERROR("Login Error", "/loginError", "home/login"),

	/** Logout **/
	LOGOUT("Logout", "/logOut", "home/login"),

	/** Force to Logout **/
	FORCE_LOGOUT("Login", "/forceLogOut", "home/login"),

	/** Error Screen **/
	ERROR("Error", "/err", "home/error"),

	/** Error Screen **/
	SUCCESS("Successful", "/success", "home/success"),

	// Account Information
	/** Account Insert **/
	ACCOUNT_INSERT("Account Registration", "/admin/accountRegister", "account/account"),

	/** ACCOUNT UPDATE **/
	ACCOUNT_UPDATE("Account Update", "/admin/accountUpdate/%s", "account/account"),

	/** ACCOUNT UPDATE CONFIRM **/
	ACCOUNT_UPDATE_CONFIRM("Account Update", "/user/accountUpdate", "account/account"),

	/**Office Management
	 * 31/8/2020
	 * @author Aung San Htay**/
	OFFICE_INFO("Office Info", "/admin/officeInfo", "office/office_info"),
	
	OFFICE_INFO_UPDATE("Office Info Update", "/admin/officeInfoUpdate", "office/office_info_update"),

	OFFICE_INFO_DELETE("Office Info Delete", "/admin/officeInfoDelete", "office/office_info"),
	
	OFFICE_INFO_REGISTER("Office Info Register", "/admin/officeInfoRegister", "office/office_info_register"),	
	
	//EMPLOYEE INFORMATION
	/** EMPLOYEE INFO List **/
	EMPLOYEE_INFO("Employee Info","/admin/employeeInfo","employee/employee_info"),
	
	/** EMPLOYEE INFO Register **/
	EMPLOYEE_INFO_REGISTER("Employee Info Register","/admin/employeeInfoRegister","employee/employee_info_register"),

	/** EMPLOYEE INFO Update **/
	EMPLOYEE_INFO_UPDATE("Employee Info Update","/admin/employeeInfoUpdate","employee/employee_info_update"),
	
	/** EMPLOYEE INFO Update **/
	EMPLOYEE_INFO_UPDATE_CONFIRM("Employee Info Update","/admin/employeeInfoUpdate","employee/employee_info"),
	
	/** EMPLOYEE INFO Delete **/
	EMPLOYEE_INFO_DELETE("Employee Info Delete","/admin/employeeInfoDelete","employee/employee_info"),

	/** CUSTOMER LISTS **/
	CUSTOMER_LIST("Customer List", "/admin/customerList", "customer/customer_list"),

	/** CUSTOMER UPDATE **/
	CUSTOMER_UPDATE("Customer Update", "/admin/customerUpdate", "customer/customer_update"),
	
	/** CUSTOMER DELETE **/
	CUSTOMER_DELETE("Customer Delete", "/admin/customerDelete", "customer/customer_list"),
	
	
	//Category Information
	/**Category Insert**/
	CATEGORY_INSERT("Category Add", "/admin/categoryAdd", "product/category_add"),
	
	/**Category Update**/
	CATEGORY_UPDATE("Category Update","/admin/categoryUpdate","product/category_update"),
	
	/** CATEGORY UPDATE CONFIRM **/
	CATEGORY_UPDATE_CONFIRM("Category Update", "/admin/categoryUpdate", "product/category_update"),
	
	/** CATEGORY DELETE **/
	CATEGORY_DELETE("Category Delete", "/admin/categoryDelete", "product/category_add"),	
	
    //Medicine Information
	/** Add medicine information**/
	MEDICINE_ADD("Add Medicine Information", "/admin/medicineAdd", "product/medicineadd"),
	
	/** Update medicine information **/
	MEDICINE_UPDATE("Update Medicine Information", "/admin/medicineUpdate", "product/medicineupdate"),
	
	/** Delete medicine information **/
	MEDICINE_DELETE("Delete Medicine Information","/admin/deleteMedicine", "product/medicineadd"),
	
	/** SUPPLIER REGISTRATION **/
	SUPPLIER_INSERT("Supplier Registration", "/admin/supplierRegister", "supplier/supplier_register"),
	
	SUPPLIER_UPDATE("Supplier Update","/admin/supplierUpdate","supplier/supplier_update"),
	
	SUPPLIER_DELETE("Supplier Delete","/admin/supplierDelete","supplier/supplier_register"),
	
	// Product Information
	/** Product List **/
	PRODUCT_LIST("Product List", "/admin/productInfo", "product/product_info"),
	
	/** Product Insert **/
	PRODUCT_INSERT("Product Add", "/admin/productInfo", "product/product_info"),
	
	/** PRODUCT UPDATE **/
	PRODUCT_UPDATE("Product Update", "/admin/productUpdate", "product/product_update"),
	
	/** PRODUCT UPDATE CONFIRM **/
	PRODUCT_UPDATE_CONFIRM("Product Update", "/admin/productUpdate", "product/product_update"),
	
	/** PRODUCT DELETE **/
	PRODUCT_DELETE("Product Delete", "/admin/productDelete", "product/product_info"),

	/** PRODUCT LOCATION REGISTER **/
	PRODUCT_LOCATION_INSERT("Product Location Registration","/admin/prodcutlocRegister","product/productloc"),
	
	/** PRODUCT LOCATION REGISTERED LIST **/
	PRODUCT_LOCATION_INSERT_LIST("Product Location Registration List","/admin/prodcutlocRegisterList","product/productlocreglist"),
	
	/** PRODUCT LOCATION LIST DELETE**/
	PRODUCT_LOCATION_LIST_DELETE("Product Location List Delete","/admin/productloclistDelete","product/productlocreglist"),
	
	/** PRODUCT LOCATION UPDATE **/
	PRODUCT_LOCATION_UPDATE("Product Location Update", "/admin/productLocUpdate", "product/ProductLocUpdate"),
	
	/** SUPPLIER ORDER REGISTRATION **/
	SUPPLIER_ORDER_REGISTRATION("Supplier Order Registration", "/admin/supplier_order_registration", "supplier/supplier_order_registration"),
	
	/** ADD TO SUPPLIER ORDER  **/
	ADD_TO_SUPPLIER_ORDER("Add to Supplier Order", "/admin/supplier_order_registration/addToOrder", "supplier/supplier_order_registration"),
	
	/** REMOVE TO SUPPLIER ORDER  **/
	REMOVE_TO_SUPPLIER_ORDER("Add to Supplier Order", "/admin/supplier_order_registration/removeOrder", "supplier/supplier_order_registration"),
	
	/** SUPPLIER ORDER REGISTRATION **/
	SUPPLIER_ORDER_CONFIRM("Supplier Order Confirm", "/admin/supplier_order_confirm", "supplier/supplier_order_confirm"),
	
	/** SUPPLIER ORDER UPDATE **/
	SUPPLIER_ORDER_UPDATE("Supplier Order Update","/admin/supplierOrderUpdate","supplier/supplier_order_update"),
	
	/** SUPPLIER ORDER LIST **/
	SUPPLIER_ORDER_LIST("Supplier Order List","/admin/supplierOrderList","supplier/supplier_order_list"),
	
	/** SUPPLIER ORDER DELETE **/
	SUPPLIER_ORDER_DELETE("Supplier Order List","/admin/supplierOrderDelete","supplier/supplier_order_list"),
	
	/** CUSTOMER ORDER HISTORY **/
	CUSTOMER_ORDER_HISTORY("Customer Order History", "/admin/customerOrderHistory", "history/orderlist"),
	
	/** DETAIL ORDER HISTORY **/
	DETAIL_ORDER_HISTORY("Detail Order History", "/admin/customerOrderHistory/%s", "history/orderlist"),
	
	/** REMOVE EXPIRE **/
	EXPIRE_REMOVE("Expire Remove", "/expireRemove", "history/expireRemove"),
	
	/** REMOVE EXPIRE ITEMS**/
	EXPIRE_REMOVE_ITEMS("Expire Remove items", "/expireRemove/remove", "history/expireRemove"),
	
	/** EXPIRE EDIT**/
	EXPIRE_EDIT("Expire Edit", "/expireRemove/edit", "history/expireRemove"),
	
	/** EXPIRE EDIT**/
	EXPIRE_DATE_SEARCH("Expire Date Search", "/expireRemove/dateSearch", "history/expireRemove"),
	
	/** Stock Count Home Page **/
	STOCK_COUNT_HOME("Stock Count Home Page", "/stockCountHome", "home/admin_index"),
	
	/** History ExpiredItems **/
	History_Expired_Items("Expired Items History", "/expiredItemsHistory", "history/expired_items_history"),

	
	;

	/** Screen Title */
	public final String title;

	/** URL */
	public final String url;

	/** Forward URL */
	public final String forwardUrl;

	/** Redirect URL */
	public final String redirectUrl;

	/** HTML File Path */
	public final String template;

	/**
	 * PageInfo Initialize
	 *
	 * @param title Page Title
	 * @param url URL
	 * @param template src/main/resources/templates/ HTML File Path
	 */
	private AdminPageInfo(final String title, final String url, final String template) {
		this.title = title;
		this.url = url;
		assertThat("Beginning of the URL path start with /" + this, url.charAt(0), is('/'));
		this.forwardUrl = UrlBasedViewResolver.FORWARD_URL_PREFIX + url;
		this.redirectUrl = UrlBasedViewResolver.REDIRECT_URL_PREFIX + url;
		this.template = template;
		if (StringUtils.isNotEmpty(template)) {
			assertThat("Please delete / from the beginning of the template path" + this, template.charAt(0), is(not('/')));
		}
	}

	/**
	 * Bind parameters to URL
	 *
	 * @see java.util.Formatter
	 * @param args Parameter
	 * @return URL
	 */
	public String url(Object... args) {
		return String.format(url, args);
	}

	/**
	 * Bind a parameter to a Forward URL
	 *
	 * @see java.util.Formatter
	 * @param args Parameter
	 * @return URL
	 */
	public String forwardUrl(Object... args) {
		return String.format(forwardUrl, args);
	}

	/**
	 * Bind parameters to Redirect URL
	 *
	 * @see java.util.Formatter
	 * @param args Parameter
	 * @return URL
	 */
	public String redirectUrl(Object... args) {
		return String.format(redirectUrl, args);
	}
}
