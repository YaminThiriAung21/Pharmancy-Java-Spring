package pec.mapper;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import pec.dto.OfficeDto;

/**
 * OfficeManagementMapper
 * 31/8/2020
 * @author Aung San Htay
 */
@Mapper
public interface OfficeInfoMapper {

	public List<OfficeDto> getOfficeInfo();

	public OfficeDto getUpdate(int id);

	public void update(OfficeDto officeDto);

	public void delete(int id);

	public void register(OfficeDto officeDto);

	public List<OfficeDto> getDuplicate(int id);

	public void OfficeUpdateWithNoImage(OfficeDto officeDto);

	public List<OfficeDto> getDuplicatedCode(int id);

}
