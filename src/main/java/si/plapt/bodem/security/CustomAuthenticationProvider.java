package si.plapt.bodem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import si.plapt.bodem.services.UserService;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		/*UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		
		String userName = token.get
		*/
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return true;
	}

}
