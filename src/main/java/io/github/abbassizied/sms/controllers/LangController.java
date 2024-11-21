package io.github.abbassizied.sms.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
@RequestMapping("/lang")
public class LangController {

    private final LocaleResolver localeResolver;

    public LangController(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    @GetMapping
    public String switchLanguage(HttpServletRequest request, String lang) {
        // Set the locale using the LocaleResolver
        Locale locale = new Locale(lang);
        localeResolver.setLocale(request, null, locale);

        // Redirect to the last visited page
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/");
    }
}
