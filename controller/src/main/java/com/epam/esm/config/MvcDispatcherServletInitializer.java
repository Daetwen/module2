package com.epam.esm.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletRegistration;
import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan("com.epam.esm")
public class MvcDispatcherServletInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer
        implements WebMvcConfigurer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{DataBaseConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof AbstractJackson2HttpMessageConverter) {
                AbstractJackson2HttpMessageConverter jacksonconverter = (AbstractJackson2HttpMessageConverter) converter;
                jacksonconverter.getObjectMapper()
                        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            }
        }
    }
}
