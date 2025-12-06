/**
 *	
 *	@author		: CHOUABBIA Amine
 *
 *	@Name		: WebConfig
 *	@CreatedOn	: 06-26-2025
 *	@UpdatedOn	: 12-06-2025 (Updated for Spring Boot 4.0 - No Deprecated APIs)
 *
 *	@Type		: Class
 *	@Layer		: Configuration
 *	@Package	: Configuration
 *
 **/

package dz.mdn.iaas.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Web Configuration Class - Spring Boot 4.0 Compatible
 * 
 * Configures REST API settings, CORS, and Jackson serialization.
 * 
 * UPDATED for Spring Boot 4.0:
 * - Removed @EnableWebMvc (preserves Spring Boot auto-configuration)
 * - Uses Jackson2ObjectMapperBuilderCustomizer (recommended pattern)
 * - No deprecated methods used
 * - Property-based CORS configuration
 * - JavaTimeModule auto-registered by Spring Boot
 * 
 * Features:
 * - CORS configuration from application.properties
 * - JSON serialization with ISO-8601 dates
 * - Static resource handling
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${cors.allowed.origins:http://localhost:3000,http://localhost:4200}")
    private String allowedOrigins;

    @Value("${cors.allowed.methods:GET,POST,PUT,DELETE,PATCH,OPTIONS}")
    private String allowedMethods;

    @Value("${cors.allow.credentials:true}")
    private boolean allowCredentials;

    @Value("${cors.max.age:3600}")
    private long maxAge;

    /**
     * Configure CORS settings for API endpoints
     * Reads configuration from application.properties for environment flexibility
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(allowedOrigins.split(","))
                .allowedMethods(allowedMethods.split(","))
                .allowedHeaders("*")
                .exposedHeaders("Authorization", "Content-Disposition", "X-Total-Count")
                .allowCredentials(allowCredentials)
                .maxAge(maxAge);
    }

    /**
     * Configure static resource handlers
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve static resources
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        // Serve API documentation
        registry.addResourceHandler("/docs/**")
                .addResourceLocations("classpath:/static/docs/");

        // Serve uploaded files (if applicable)
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:f:/files/");
    }

    /**
     * Customize Jackson ObjectMapper using the recommended Spring Boot 4.0 approach
     * 
     * This uses Jackson2ObjectMapperBuilderCustomizer which is the official
     * Spring Boot way to customize the auto-configured ObjectMapper.
     * 
     * Benefits:
     * - Works with Spring Boot auto-configuration
     * - No deprecated methods
     * - JavaTimeModule is auto-registered by Spring Boot
     * - Configuration can be ordered with @Order if needed
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            // Serialize dates as ISO-8601 strings, not timestamps
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            // Pretty print JSON in responses (optional - can disable in production)
            builder.featuresToEnable(SerializationFeature.INDENT_OUTPUT);

            // Don't fail on unknown properties during deserialization
            builder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            // Exclude null values from JSON output
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        };
    }

    /**
     * ALTERNATIVE: If you need direct ObjectMapper customization
     * 
     * Uncomment this method if Jackson2ObjectMapperBuilderCustomizer doesn't
     * provide enough control. This gives you the builder instance directly.
     * 
     * Note: This approach is still supported in Spring Boot 4.0
     */
    /*
    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

        // Disable timestamp serialization for dates
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Enable pretty printing
        builder.featuresToEnable(SerializationFeature.INDENT_OUTPUT);

        // Don't fail on unknown properties
        builder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // Exclude null values
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);

        return builder;
    }
    */
}