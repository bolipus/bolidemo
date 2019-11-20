package si.plapt.bodem.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import si.plapt.bodem.entities.auth.User;
import si.plapt.bodem.services.UserService;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;
	
	/*public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("admin"));
		System.out.println(new BCryptPasswordEncoder().encode("manager"));
		System.out.println(new BCryptPasswordEncoder().encode("demo"));
		System.out.println(new BCryptPasswordEncoder().encode("demo2"));
	}*/
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userService.getByUsername(username);
		
		UserBuilder userBuilder = null;
		
		if (user.isPresent()) {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			user.get().getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
					.forEach(grantedAuthorities::add);
			
			userBuilder = org.springframework.security.core.userdetails.User.withUsername(user.get().getUsername());
			userBuilder.password(new BCryptPasswordEncoder().encode(user.get().getPassword()));
			userBuilder.password(user.get().getPassword());
			userBuilder.authorities(grantedAuthorities);

			return userBuilder.build();

		} else {
			throw new UsernameNotFoundException(username);
		}

	}

}
