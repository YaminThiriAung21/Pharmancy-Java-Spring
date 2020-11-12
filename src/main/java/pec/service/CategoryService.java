package pec.service;
/**
 * Category Service
 * 26/8/2020
 * @author Khin Thant Sin
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import pec.dao.CategoryDao;
import pec.dto.CategoryDto;
import pec.dto.EmployeeInfoDto;

@Service
@Transactional
public class CategoryService {
	@Autowired
	   private CategoryDao dao;

	  @Autowired
	  private HttpSession session;
		public List<CategoryDto> getCategoryList(){
		
			return dao.getCategoryList();
			
			}

   public void addCategory(CategoryDto categoryDto) {
 	  EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
 	  categoryDto.setInsert_date(new Date());
 	  categoryDto.setUpdate_date(new Date());
 	  categoryDto.setInsert_user(empDto.getEmployee_name());
 	  categoryDto.setUpdate_user(empDto.getEmployee_name());
 	  categoryDto.setDelete_flag(0);
			
			dao.addCategory(categoryDto);
		}

		public CategoryDto getCategoryDto(int category_info_id) {
			return dao.getCategoryDto(category_info_id);
		}
		public void updateCategory(CategoryDto categoryDto) {
			EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
			 categoryDto.setUpdate_user(empDto.getEmployee_name()); 
			 categoryDto.setUpdate_date(new Date());
			dao.updateCategory(categoryDto);
		}

		public void deleteCategory(Integer category_info_id) {
			dao.deleteCategory(category_info_id);
			
		}
	
	
	
	public Model messageProperty(Model model) throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		model.addAttribute("E000136", prop.getProperty("E000136"));
		model.addAttribute("E000137", prop.getProperty("E000137"));
		model.addAttribute("E000138", prop.getProperty("E000138"));
		return model; 
	}
	public int getDuplicate(String category_code) {
		return dao.getDuplicate(category_code);
	}

}
 