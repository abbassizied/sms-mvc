package io.github.abbassizied.sms.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.*;

import java.util.Locale;

@Configuration
public class I18nConfig implements WebMvcConfigurer {

	@Bean(name = "messageSource")
	public MessageSource getMessageResource() {
		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
		// Read i18n/messages_xxx.properties file.
		// For example: i18n/messages_en.properties
        messageResource.setBasename("classpath:i18n/messages");
        messageResource.setDefaultEncoding("UTF-8");
        messageResource.setCacheSeconds(3600); // Cache for an hour
		return messageResource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.ENGLISH);
		slr.setLocaleAttributeName("session.current.locale");
		slr.setTimeZoneAttributeName("session.current.timezone");
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		// Interceptor to change the language based on a request parameter
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang"); // Use ?lang=fr, ?lang=en, etc.
		return localeChangeInterceptor;
	}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Adding the LocaleChangeInterceptor to the registry
        registry.addInterceptor(localeChangeInterceptor());
    }
}