package com.texi.app.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Translator {
    private static ReloadableResourceBundleMessageSource messageSource;

    @Autowired
    Translator(ReloadableResourceBundleMessageSource messageSource) {
        Translator.messageSource = messageSource;
    }

    public String getMessage(String msgCode) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, null, locale);
    }

    public String getMessage(String message, Locale locale) {
        return messageSource.getMessage(message, null, (locale != null ? locale : Locale.getDefault()));
    }
}
