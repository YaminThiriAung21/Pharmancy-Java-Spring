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
 * 27/8/2020
 * @author Khin Me Me Latt
 */
@Service
@Transactional
public class ExpireErrorService {
	public Model messageProperty(Model model) throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
		model.addAttribute("E000056", prop.getProperty("E000056"));
		return model; 
	}
}
