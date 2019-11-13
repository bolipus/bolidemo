package si.plapt.bodem.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import si.plapt.bodem.entities.auth.User;
import si.plapt.bodem.services.UserService;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userService.getByUsername(username);
		if (user.isPresent()) {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			user.get().getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
					.forEach(grantedAuthorities::add);

			return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
					user.get().getPassword(), grantedAuthorities);

		} else {
			throw new UsernameNotFoundException(username);
		}

	}

}
