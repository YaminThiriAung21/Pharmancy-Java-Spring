package pec.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Common Controller
 * 25/8/2020
 * @author Su Su Lin
 */
public class CommonController {

	public static Properties getValue(String propFileName) throws IOException {

		try (InputStream inputStream = CommonController.class.getClassLoader().getResourceAsStream(propFileName);) {

			Properties prop = new Properties();
			if (inputStream != null) {
				prop.load(new InputStreamReader(inputStream, "UTF8"));
			} else {
				throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
			}
			return prop;
		}
	}
	
}
