package si.plapt.bodem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class BoliDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoliDemoApplication.class, args);
	}

}
