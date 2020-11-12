package pec.datacode;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * User Page Information List
 * 25/8/2020
 * @author Su Su Lin
 */
@Getter
@ToString
public enum UserPageInfo {

	/** Home **/
	USER_Home("User Home", "/user/pharmacyHome", "home/index"),
	
	/** Product List **/
	Product_list("Product List", "/user/productList/", "product/productlist"),
	
	Product_Filter("Product filter","/user/productList","product/productlist"),
	
	/** ProductDetail **/
	Product_Detail("Product Detail", "/user/ProductDetail", "product/productdetail"),
	
	Cart("Cart", "/user/AddtoCart", "product/cart"),
	
	/** CustomerCart **/
	CustomerOrder("Customer order", "/user/CustomerCart", "customer/CustomerCart"),
	
	/* OrderCheckout */
	Order_Checkout("Order Checkout", "/user/orderCheckout", "customer/ordercheckout"),

	/* PlaceOrder */
	Place_Order("Place Order", "/user/placeOrder", "customer/thankyou"),
		
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
	private UserPageInfo(final String title, final String url, final String template) {
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
