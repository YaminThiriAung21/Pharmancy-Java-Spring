package pec.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pec.dto.MedicineInfoDto;
import pec.service.AddMedicineService;
import pec.datacode.AdminPageInfo;

@Controller
@RequestMapping("/admin/medicineAdd")
public class MedicineAddController {
	@Autowired
    AddMedicineService addMedicineService;
	
	
	

	@RequestMapping(method = RequestMethod.GET, params ="top")
	public String initFromMenu() {
		return AdminPageInfo.MEDICINE_ADD.redirectUrl;
	}
		
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String initialize(Model model,HttpServletRequest req) throws IOException {
		
		
		/*Map<String, String[]> reqMap= req.getParameterMap();
		reqMap.forEach((key, value)-> {
			model.addAttribute(key, value[0]);});*/
		
		model.addAttribute("medicineInfoDto",new MedicineInfoDto());
		List<MedicineInfoDto> medicineList = addMedicineService.getMedicineInfoList();
		model.addAttribute("medicineList",medicineList);
		
		
		model= addMedicineService.messageProperty(model);
		model.addAttribute("medicineInfoDto", new MedicineInfoDto());
		model.addAttribute("data_tb","save");
		return AdminPageInfo.MEDICINE_ADD.template;
	}
	
	
	@RequestMapping(params="add", method=RequestMethod.POST)
	public String register(Model model, @Valid @ModelAttribute("medicineInfoDto") MedicineInfoDto medicineInfoDto, BindingResult res, RedirectAttributes attr) throws IOException {
		
		int count = addMedicineService.getDuplicate(medicineInfoDto.getMedicine_code());
		
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		
		if(count != 0) {
			//model = addMedicineService.messageProperty(model);
			model.addAttribute("errormsg", prop.getProperty("E000135"));
			List<MedicineInfoDto> medicineList = addMedicineService.getMedicineInfoList();
			model.addAttribute("medicineList",medicineList);
			//model.addAttribute("medicineInfoDto", new MedicineInfoDto());
			return AdminPageInfo.MEDICINE_ADD.template;
		}
		else {
		addMedicineService.addMedicine(medicineInfoDto);
		model.addAttribute("msg", "Added new medicine Successful.");
		}
		
		List<MedicineInfoDto> medicineList = addMedicineService.getMedicineInfoList();
		model.addAttribute("medicineList",medicineList);
		model.addAttribute("medicineInfoDto", new MedicineInfoDto());
		return AdminPageInfo.MEDICINE_ADD.template;
	}
	
	
	}
	
	
	
	
	
	
	
	
