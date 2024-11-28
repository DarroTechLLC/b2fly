//package darrotech.project.b2fly;
//
//import io.github.cdimascio.dotenv.Dotenv;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.ConfigurableEnvironment;
//import org.springframework.core.env.MapPropertySource;
//import org.springframework.core.env.MutablePropertySources;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class DotenvConfig {
//
//    @Bean
//    public MapPropertySource loadDotenv(ConfigurableEnvironment environment) {
//        Dotenv dotenv = Dotenv.load();
//        Map<String, Object> envMap = new HashMap<>();
//
//        dotenv.entries().forEach(entry -> envMap.put(entry.getKey(), entry.getValue()));
//
//        MapPropertySource propertySource = new MapPropertySource("dotenv", envMap);
//        MutablePropertySources propertySources = environment.getPropertySources();
//        propertySources.addFirst(propertySource);
//
//        return propertySource;
//    }
//}
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
        // Convert System environment variables to a Map<String, Object>
        Map<String, Object> systemEnvMap = new HashMap<>();
        System.getenv().forEach((key, value) -> systemEnvMap.put(key, value));

        // Load environment variables from the Render environment
        MapPropertySource systemEnvPropertySource = new MapPropertySource("systemEnvironment", systemEnvMap);

        // Conditionally load from .env file if not found in the system environment
        Dotenv dotenv = Dotenv.configure().ignoreIfMalformed().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> systemEnvMap.putIfAbsent(entry.getKey(), entry.getValue()));

        MapPropertySource propertySource = new MapPropertySource("combinedEnvironment", systemEnvMap);
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.addFirst(propertySource);

        return propertySource;
    }
}

