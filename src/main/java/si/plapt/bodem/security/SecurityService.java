package si.plapt.bodem.security;

import java.util.Optional;

public interface SecurityService {
	Optional<String> getLoggedInUser();
	
}
