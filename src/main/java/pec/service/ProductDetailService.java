package pec.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import pec.dao.ProductDetailDao;
import pec.dto.ProductDetailDto;


/**
 * ProductDetailController
 * 27/8/2020
 * @author Kyaw Min Khant, Zin Nwe Htet
 */

@Service
@Transactional
public class ProductDetailService {
	
	@Autowired
	private ProductDetailDao productdetaildao;
	
	public List<ProductDetailDto> getProductList(){		
		return productdetaildao.getProductList();
	}
	
	public ProductDetailDto getProductById(int product_info_id) {
		return productdetaildao.getProductById(product_info_id);
	};
	
	public Model messageProperty(Model model) throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		model.addAttribute("E000081", prop.getProperty("E000081"));
		return model; 
	}


}
