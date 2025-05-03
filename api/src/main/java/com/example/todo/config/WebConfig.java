package com.example.todo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** Web configuration class. Handles web-related configurations such as CORS settings. */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Value("${CORS_ORIGINS}")
  private String corsOrigins;

  /**
   * Configures CORS settings. Allows access from frontend applications. Allowed origins are
   * configured through the CORS_ORIGINS environment variable.
   *
   * @param registry CORS registry
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedOrigins(corsOrigins.split(","))
        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
        .allowedHeaders("*")
        .allowCredentials(true);
  }
}
