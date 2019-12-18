package com.texi.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Locale;

//@EnableWebMvc
@Configuration
@EnableAspectJAutoProxy
public class Config extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {

//    @Override
//    public Locale resolveLocale(HttpServletRequest request) {
//        List<Locale> LOCALES = Arrays.asList(
//                new Locale("en"),
//                new Locale("de"));
//
//        String headerLang = request.getHeader("Accept-Language");
//        System.out.println(headerLang);
//        return headerLang == null || headerLang.isEmpty()
//                ? Locale.getDefault()
//                : Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
//    }


    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        interceptor.setIgnoreInvalidLocale(true);
        return interceptor;
    }

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:messages");
        resource.addBasenames("classpath:user");
        resource.addBasenames("classpath:language");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }

    @Bean(name="validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean(name="localeResolver")
    public SessionLocaleResolver sessionLocaleResolver(){
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.US);
        return resolver;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

}
