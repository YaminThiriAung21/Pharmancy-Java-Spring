package pec.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pec.dto.EmployeeInfoDto;
import pec.service.LoginService;

/**
 * User information acquisition service for login authentication.
 *
 * @author Min Aung Than Oo
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		session.invalidate();
		EmployeeInfoDto retDto = loginService.getLoginInfo(email);
		if (retDto == null) {
			throw new UsernameNotFoundException("User not found for name: " + email);
		}
		session.setAttribute("EmployeeInfoDto", retDto);
		return new LoginUserDetails(retDto);
	}

}
