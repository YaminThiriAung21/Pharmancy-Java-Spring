package pec.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

/**
 * Account Service
 * 25/8/2020
 * @author Su Su Lin
 */
@Service
@Transactional
public class AccountService {
	
	public Model messageProperty(Model model) throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		model.addAttribute("E000001", prop.getProperty("E000001"));
		model.addAttribute("E000002", prop.getProperty("E000002"));
		return model; 
	}


}
