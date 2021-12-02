package com.epam.esm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocaleManager {

    private static MessageSource messageSource;

    @Autowired
    public LocaleManager(MessageSource messageSource) {
        LocaleManager.messageSource = messageSource;
    }

    public static String getLocalizedMessage(String messagePath) {
        return messageSource.getMessage(messagePath, null, LocaleContextHolder.getLocale());
    }
}
