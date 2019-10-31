package si.plapt.bodem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {
	"http://localhost:4200",
	"https://bldemo.web.app"	
})

public class MainController {

	@ResponseBody
	@GetMapping(path = "/")
	public ResponseEntity<String> getIndex() {
		return ResponseEntity.ok("<H1>Hello spring boot BoliDemo!</H1>");
	}
}
