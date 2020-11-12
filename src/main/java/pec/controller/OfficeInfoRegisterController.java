package pec.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pec.dto.AccountDto;
import pec.dto.EmployeeInfoDto;
import pec.dto.OfficeDto;
import pec.service.AccountService;
import pec.service.OfficeInfoService;
import pec.datacode.AdminPageInfo;


/**
 * Office Register Controller 25/8/2020
 * 
 * @author Aung San Htay
 */
@Controller
@RequestMapping("/admin/officeInfoRegister")
public class OfficeInfoRegisterController {

	@Autowired
	private OfficeInfoService officeInfoservice;

	@Autowired
	private HttpSession session;
	
	@RequestMapping(method = RequestMethod.GET, params = "top")
	public String initFromMenu() {
		return AdminPageInfo.OFFICE_INFO_REGISTER.redirectUrl;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initialize(Model model) throws IOException {
		model = officeInfoservice.messageProperty(model);
		model.addAttribute("officeDto", new OfficeDto());
		return AdminPageInfo.OFFICE_INFO_REGISTER.template;
	}

	@RequestMapping(params = "register", method = RequestMethod.POST)
	public String register(Model model, @Valid @ModelAttribute("officeDto") OfficeDto officeDto, BindingResult res,
			RedirectAttributes attr) throws IOException {
		
		List<OfficeDto> officeCodeList=officeInfoservice.getDuplicatedCode(officeDto.getOffice_info_id());
		Properties prop1 = new Properties();
		
		prop1.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"),
				StandardCharsets.UTF_8));
		for(int i = 0;i<officeCodeList.size();i++){
			if(officeDto.getOffice_code().equals(officeCodeList.get(i).getOffice_code())){
				model = officeInfoservice.messageProperty(model);
				model.addAttribute("errormsg", prop1.getProperty("E000024"));
				return AdminPageInfo.OFFICE_INFO_REGISTER.template;
			}
			
		}
		
		//Comment Closed Email Duplicate Check
		/*List<OfficeDto> officeList = officeInfoservice.getDuplicate(officeDto.getOffice_info_id());
		Properties prop = new Properties();
		
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"),
				StandardCharsets.UTF_8));
		for (int i = 0; i < officeList.size(); i++) {
			if (officeDto.getOffice_email_address().equals(officeList.get(i).getOffice_email_address())) {
				model = officeInfoservice.messageProperty(model);
				model.addAttribute("errormsg", prop.getProperty("E000025"));
				return AdminPageInfo.OFFICE_INFO_REGISTER.template;
			}
		}*/
		EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
		Date date=new Date();
		officeDto.setDelete_flag(0);
		officeDto.setInsert_user(empDto.getEmployee_name());
		officeDto.setInsert_date(date);
		officeDto.setUpdate_user(empDto.getEmployee_name());
		officeDto.setUpdate_date(date);
		
		officeDto.setOffice_image(officeDto.getOfficeimage().getBytes());
		officeDto.setOffice_image_name(officeDto.getOfficeimage().getOriginalFilename());

		String filePath="src/main/resources/static/images/office_image/";
		File imageFile = new File(filePath+officeDto.getOfficeimage().getOriginalFilename()); // we can put any name of file (just name of new file created).
		FileOutputStream outputStream;	
		outputStream = new FileOutputStream(imageFile);
		outputStream.write(officeDto.getOffice_image());
		outputStream.close();
		
		
		officeInfoservice.register(officeDto);
		attr.addAttribute("msg", "Office Registeration Successful.");
		return AdminPageInfo.OFFICE_INFO.redirectUrl;

	}
}
