package io.github.abbassizied.sms.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MessageConfig implements WebMvcConfigurer {

    @Bean(name = "messageSource")
    public MessageSource getMessageResource() {
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
        // Read i18n/messages_xxx.properties file.
        // For example: i18n/messages_en.properties
        messageResource.setBasename("classpath:i18n/messages");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }

    // To resolve locale based on the URL like: /en, /fr
    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver() {
        return new UrlLocaleResolver(); // You should updated this class based on your needs
    }

    // Interceptor for URL-based locale resolution
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        // Add a locale change interceptor to automatically set the language based on
        // the URL
        registry.addInterceptor(new UrlLocaleInterceptor()).addPathPatterns("/en/*", "/fr/*");
    }
}
