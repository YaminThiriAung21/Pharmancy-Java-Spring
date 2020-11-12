package pec.mapper;
/**
 * Category Mapper
 * 7/9/2020
 * @author Khin Thant Sin
 */
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;

import pec.dto.CategoryDto;
@Mapper
public interface CategoryMapper {

public List<CategoryDto> getCategoryList();
	
	public void addCategory(CategoryDto categoryDto);
	
	public CategoryDto getCategoryDto(@Param("category_info_id") int category_info_id);
	
	public void updateCategory(CategoryDto categoryDto);
	
	

	public void deleteCategory(Integer category_info_id);
	public int getDuplicate(String category_code);

	 
}
