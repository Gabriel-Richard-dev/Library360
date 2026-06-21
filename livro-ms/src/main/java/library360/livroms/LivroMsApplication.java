package library360.livroms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LivroMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LivroMsApplication.class, args);
    }

}
