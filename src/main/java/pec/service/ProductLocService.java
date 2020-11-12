package pec.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Properties;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import pec.dao.ProductLocRegDao;
import pec.dto.ProductLocDto;

/**
 * Product Location Service
 * 25/8/2020
 * @author Thet Mon Hnin
 */
@Service
@Transactional
public class ProductLocService {
	

	public Model messageProperty(Model model) throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		model.addAttribute("E000082", prop.getProperty("E000082"));
		model.addAttribute("E000083", prop.getProperty("E000083"));
		return model; 
	}

	@Autowired
	private ProductLocRegDao productlocregDao;
	
	public List<ProductLocDto> getProductLocRegList()
	{
		return productlocregDao.getProductLocRegList();
	}

	public void registerProductLoc(ProductLocDto productlocDto) {
		String code=productlocDto.getLocation_code();
		String place=productlocDto.getProduct_location_place();
		productlocDto.setLocation_code(code);
		productlocDto.setProduct_location_place(place);
		
		
		productlocDto.setProduct_location_info_id(21);
		productlocDto.setInsert_user("Thet Mon Hnin");
		
		productlocDto.setInsert_date(new Date());
		productlocDto.setUpdate_user("Thet Mon Hnin");
		productlocDto.setUpdate_date(new Date());
	
		productlocDto.setDelete_flag("0");

		productlocregDao.registerProductLoc(productlocDto);
		
		
	}
	public  void delete(int id) {
		
		productlocregDao.delete(id);
		
	}
	public void updateProductLoc(ProductLocDto productlocDto) {
		productlocregDao.updateProductLoc(productlocDto);
	}
	
	public ProductLocDto getUpdate(int id) {
		return productlocregDao.getUpdate(id);
	}
	
	public List<ProductLocDto> getDuplicate(int id) {
		return productlocregDao.getDuplicate(id);
	}

	public List<ProductLocDto> getDuplicateRegister() {
		// TODO Auto-generated method stub
		return productlocregDao.getDuplicateRegister();
	}
}




