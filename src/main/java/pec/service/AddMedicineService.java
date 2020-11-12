package pec.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import pec.dao.MedicineInfoDao;
import pec.dto.EmployeeInfoDto;
import pec.dto.MedicineInfoDto;

@Service
@Transactional
public class AddMedicineService {
	   @Autowired
	   private MedicineInfoDao dao;
	   
	   @Autowired
		  private HttpSession session;

		public List<MedicineInfoDto> getMedicineInfoList(){
		
			return dao.getMedicineInfoList();
			
			}
		
		
			

       public void addMedicine(MedicineInfoDto medicineInfoDto) {
	
    	   EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
    	 	  
    	   medicineInfoDto.setInsert_date(new Date());
    	   medicineInfoDto.setUpdate_date(new Date());
    	   medicineInfoDto.setInsert_user(empDto.getEmployee_name());
    	   medicineInfoDto.setUpdate_user(empDto.getEmployee_name());
			dao.addMedicine(medicineInfoDto);
		}

		public MedicineInfoDto getMedicineInfoDto(int medicine_info_id) {
			return dao.getMedicineInfoDto(medicine_info_id);
		}
		 
		public void updateMedicine(MedicineInfoDto medicineInfoDto) {
			EmployeeInfoDto empDto = (EmployeeInfoDto) session.getAttribute("EmployeeInfoDto");
			medicineInfoDto.setUpdate_user(empDto.getEmployee_name()); 
			medicineInfoDto.setUpdate_date(new Date());
			dao.updateMedicine(medicineInfoDto);
		}

		public void deleteMedicine(Integer id) {
			dao.deleteMedicine(id);
			
		}
		
		/*public List<MedicineInfoDto> getDuplicate(int id) {
			return dao.getDuplicate(id);
		}*/


			
	
	public Model messageProperty(Model model) throws IOException{
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		model.addAttribute("E000133", prop.getProperty("E000133"));
		model.addAttribute("E000134", prop.getProperty("E000134"));
		model.addAttribute("E000135", prop.getProperty("E000135"));
		return model; 
		
	}

	public int getDuplicate(String medicine_code) {
		return dao.getDuplicate(medicine_code);
	}

}
