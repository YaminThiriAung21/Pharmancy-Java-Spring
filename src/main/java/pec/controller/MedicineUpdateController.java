package pec.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pec.dto.MedicineInfoDto;
import pec.service.AddMedicineService;
import pec.datacode.AdminPageInfo;

@Controller
@RequestMapping("/admin/medicineUpdate")
public class MedicineUpdateController {
	@Autowired
	AddMedicineService   addMedicineService;
		
	@RequestMapping(value="/{medicine_info_id}", method = RequestMethod.GET)
	public String getMedicineInfoDto(Model model,@PathVariable("medicine_info_id") Integer id,HttpServletRequest req){
		
		
		
		/*Map<String, String[]> reqMap= req.getParameterMap();
		reqMap.forEach((key, value)-> {
			model.addAttribute(key, value[0]);});*/
		
		model.addAttribute("medicineInfoDto",new MedicineInfoDto());
		List<MedicineInfoDto> medicineList = addMedicineService.getMedicineInfoList();
		model.addAttribute("medicineList",medicineList);
		
		
		MedicineInfoDto medicineDto=addMedicineService.getMedicineInfoDto(id);
		model.addAttribute("medicineInfoDto",medicineDto);
		model.addAttribute("data_tb","save");
		return AdminPageInfo.MEDICINE_UPDATE.template;
	}
	
	
	@RequestMapping(params="update", method=RequestMethod.POST)
	public String update(Model model, @Valid MedicineInfoDto medicineInfoDto, BindingResult res, RedirectAttributes attr) {
	  
		/*if(res.hasErrors()) {
		model.addAttribute("msg","<p style='color:red'>Please fill the full data in form.</p>");
		return AdminPageInfo.MEDICINE_UPDATE.template;		
	}*/
       addMedicineService.updateMedicine(medicineInfoDto);
	attr.addFlashAttribute("msg", "Updated new medicine Successful.");
	return AdminPageInfo.MEDICINE_ADD.redirectUrl;
}

	
	
	
}
