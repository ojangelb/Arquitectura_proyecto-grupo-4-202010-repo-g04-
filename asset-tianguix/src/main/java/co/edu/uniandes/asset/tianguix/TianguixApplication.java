package co.edu.uniandes.asset.tianguix;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
@ComponentScan(basePackages="co.edu.uniandes.asset")
public class TianguixApplication {

	public static void main(String[] args) {
		run(TianguixApplication.class, args);
	}

}
