package pec.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import pec.dao.MedicineInfoDao;
import pec.dao.ProductInfoDao;
import pec.dto.CategoryDto;
import pec.dto.EmployeeInfoDto;
import pec.dto.InventoryInfoDto;
import pec.dto.MedicineInfoDto;
import pec.dto.PackagingInfoDto;
import pec.dto.ProductInfoDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ProductInfoService {
	@Autowired
	private ProductInfoDao productInfoDao;
	
	@Autowired
	private HttpSession session;
	
	public List<ProductInfoDto> getProductList(){
		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		
		return productInfoDao.getProductList(empDto.getOffice_info_id());
	}
	
	public List<MedicineInfoDto> getMedicineInfoList() {
		return productInfoDao.getMedicineInfoList();
	}
	
	public List<CategoryDto> getCategoryInfoList() {
		return productInfoDao.getCategoryInfoList();
	}
	
	public List<PackagingInfoDto> getPackagingList() {
		return productInfoDao.getPackagingList();
	}

	public Model messageProperty(Model model) throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		model.addAttribute("E000027", prop.getProperty("E000027"));
		model.addAttribute("E000028", prop.getProperty("E000028"));
		model.addAttribute("E000046", prop.getProperty("E000046"));
		model.addAttribute("E000047", prop.getProperty("E000047"));
		model.addAttribute("E000048", prop.getProperty("E000048"));
		model.addAttribute("E000049", prop.getProperty("E000049"));
		model.addAttribute("E000050", prop.getProperty("E000050"));
		model.addAttribute("E000126", prop.getProperty("E000126"));
		model.addAttribute("E000127", prop.getProperty("E000127"));
		model.addAttribute("E000128", prop.getProperty("E000128"));
		model.addAttribute("E000130", prop.getProperty("E000130"));
		model.addAttribute("E000132", prop.getProperty("E000132"));
		return model; 
	}

	
	public void addProduct(ProductInfoDto prdInfoDto) {
		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		prdInfoDto.setOffice_info_id(empDto.getOffice_info_id()); 
		prdInfoDto.setInsert_date(new Date());
		prdInfoDto.setUpdate_date(new Date());
		prdInfoDto.setInsert_user(empDto.getEmployee_name());
		prdInfoDto.setUpdate_user(empDto.getEmployee_name());
		prdInfoDto.setDelete_flag(0);
		productInfoDao.addProduct(prdInfoDto);
	}
	
	public void addProductQuantity(InventoryInfoDto inventoryDto) {
		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		inventoryDto.setTotal_damage_quantity(0);
		inventoryDto.setTotal_expired_quantity(0);
		inventoryDto.setTotal_quantity(0);
		inventoryDto.setOffice_info_id(empDto.getOffice_info_id()); 
		inventoryDto.setInsert_date(new Date());
		inventoryDto.setUpdate_date(new Date());
		inventoryDto.setInsert_user(empDto.getEmployee_name());
		inventoryDto.setUpdate_user(empDto.getEmployee_name());
		inventoryDto.setDelete_flag(0);
		productInfoDao.addProductQuantity(inventoryDto);
	}
	
	public ProductInfoDto getProduct(int product_info_id) {
		return productInfoDao.getProduct(product_info_id);
	}
	
	public void updateProduct(ProductInfoDto productDto) {
		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		productDto.setUpdate_date(new Date()); 
		productDto.setUpdate_user(empDto.getEmployee_name());
		productInfoDao.updateProduct(productDto);
	}
	
	public void updateProductWithImage(ProductInfoDto productDto) {
		productInfoDao.updateProductWithImage(productDto);
	}
	
	public void deleteProduct(Integer product_info_id) {
		productInfoDao.deleteProduct(product_info_id);
	}
	
	public int getDuplicate(String product_code) {
		return productInfoDao.getDuplicate(product_code);
	}
}
