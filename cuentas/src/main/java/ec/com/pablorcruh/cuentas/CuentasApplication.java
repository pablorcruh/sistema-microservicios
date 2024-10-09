package ec.com.pablorcruh.cuentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableWebMvc
@EnableSpringDataWebSupport
public class CuentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuentasApplication.class, args);
	}

}
