package pec.security;

import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import pec.dto.EmployeeInfoDto;

/**
 * User detailed information class for login authentication
 *
 * @author Min Aung Than Oo
 */
@Getter
public class LoginUserDetails extends User {

	/** serialVersionUID */
	private static final long serialVersionUID = -3927819677897821853L;

	/** Role */
	private String role;

	/**
	 * User ID
	 */
	private Integer userId;

	/**
	 * @param retDto Account Information
	 */
	public LoginUserDetails(EmployeeInfoDto retDto) {
		super(retDto.getEmail_address(), retDto.getPassword(), AuthorityUtils.createAuthorityList("ROLE_" + retDto.getRole()));
		userId = retDto.getEmployee_info_id();
		role = retDto.getRole();
	}

	public boolean isSysAdmin() {
		return role.equals("ADMIN");
	}
	
	public boolean isSysManagementAdmin() {
		return role.equals("SYSTEM ADMIN");
	}

	public boolean isOperator() {
		return role.equals("USER");
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(LoginUserDetails.class.getSimpleName()).append('(').append("Role:").append(role).append(',')//
				.append("email:").append(getUsername()).append(',').append(')');
		return sb.toString();
	}
}
