package org.example.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        WebMvcConfigurer.super.configureViewResolvers(registry);
        registry.jsp("/WEB-INF/views/", ".jsp");
    }
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(authorConverter());
//    }
//
//    @Bean
//    public AuthorConverter authorConverter() {
//        return new AuthorConverter();
//    }
}
