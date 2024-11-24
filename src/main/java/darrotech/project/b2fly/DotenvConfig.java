package darrotech.project.b2fly;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DotenvConfig {

    @Bean
    public MapPropertySource loadDotenv(ConfigurableEnvironment environment) {
        Dotenv dotenv = Dotenv.load();
        Map<String, Object> envMap = new HashMap<>();

        dotenv.entries().forEach(entry -> envMap.put(entry.getKey(), entry.getValue()));

        MapPropertySource propertySource = new MapPropertySource("dotenv", envMap);
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.addFirst(propertySource);

        return propertySource;
    }
}
