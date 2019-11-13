package si.plapt.bodem.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SecurityServiceImpl implements SecurityService {
	
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	public Optional<String> getLoggedInUser() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		if (userDetails instanceof UserDetails) {
			return Optional.of(((UserDetails) userDetails).getUsername());
		}
		
		return Optional.empty();
	}

}
