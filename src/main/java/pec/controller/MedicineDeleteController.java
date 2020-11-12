package pec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import pec.service.AddMedicineService;
import pec.datacode.AdminPageInfo;

@Controller
@RequestMapping("/admin/deleteMedicine")
public class MedicineDeleteController {
	@Autowired
    AddMedicineService  addMedicineService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String initialize(@RequestParam("row-check") List<Integer> id,RedirectAttributes attr) {
		for(int i=0;i<id.size();i++) {
			
			addMedicineService.deleteMedicine(id.get(i));
			attr.addFlashAttribute("msg", "Deleted  medicine  Successful.");
		}
		return AdminPageInfo.MEDICINE_ADD.redirectUrl;
	}	
	
}
