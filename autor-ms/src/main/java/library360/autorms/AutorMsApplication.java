package library360.autorms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AutorMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutorMsApplication.class, args);
    }

}
