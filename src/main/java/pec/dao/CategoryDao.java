package pec.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pec.dto.CategoryDto;
import pec.mapper.CategoryMapper;

/**
 * Category Dao
 * 7/9/2020
 * @author Khin Thant Sin
 */
@Repository
public class CategoryDao {

	@Autowired
	private CategoryMapper mapper;
	
public List<CategoryDto> getCategoryList(){
		
		return mapper.getCategoryList();
	}
	
	public void addCategory(CategoryDto categoryDto) {
		mapper.addCategory(categoryDto);
	}
	
	public CategoryDto getCategoryDto(int category_info_id) {
		return mapper.getCategoryDto(category_info_id);
	}
	
	public void updateCategory(CategoryDto categoryDto) {
		mapper.updateCategory(categoryDto);
	}
	
	public void deleteCategory(Integer category_info_id) {
		mapper.deleteCategory(category_info_id);
	}
	public int getDuplicate(String category_code) {
		// TODO Auto-generated method stub
		return mapper.getDuplicate(category_code);
	}
	
	
}
