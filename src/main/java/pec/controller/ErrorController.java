package pec.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Error Controller
 *
 * @author Su Su Lin
 */
@Controller
public class ErrorController {

	@RequestMapping(value = { "/err" }, method = RequestMethod.GET)
	public String initialize(Model model, HttpServletRequest req) {

		Map<String, String[]> reqMap = req.getParameterMap();
		  reqMap.forEach((key,value)-> {
		   model.addAttribute(key, value[0]);
		  });
		return "error";
	}
}
