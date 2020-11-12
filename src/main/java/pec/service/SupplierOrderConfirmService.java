package pec.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
@Service
@Transactional
public class SupplierOrderConfirmService {
	
	public Model messageProperty(Model model) throws IOException {
		Properties prop = new Properties();
		prop.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("messages.properties"), StandardCharsets.UTF_8));
	
	return model; 
	}
}
