package darrotech.project.b2fly;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.Map;

@SpringBootApplication
public class B2flyApplication {

    public static void main(String[] args) {
        SpringApplication.run(B2flyApplication.class, args);
    }

    @Bean
    public CommandLineRunner printProperties(ConfigurableEnvironment environment) {
        return args -> {
            MutablePropertySources propertySources = environment.getPropertySources();
            propertySources.forEach(propertySource -> {
                if (propertySource.getName().equals("dotenv")) {
                    Map<String, Object> source = ((MapPropertySource) propertySource).getSource();
                    source.forEach((key, value) -> System.out.println(key + "=" + value));
                }
            });
        };
    }
}
