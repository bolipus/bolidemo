package si.plapt.bodem.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
public class AppException extends Exception {

	private static final long serialVersionUID = -8621950740784932030L;
	
	
	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

}
