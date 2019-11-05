package si.plapt.bodem.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {
	"http://localhost:4200",
	"https://bldemo.web.app"	
})
@RequestMapping("api/v1/codes")
public class CodesController {

}
