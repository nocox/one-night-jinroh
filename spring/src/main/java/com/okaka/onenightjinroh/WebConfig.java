package com.okaka.onenightjinroh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@Profile("default")
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    MyServiceBean myServiceBean;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("[My Settings] CORS allowed origin : " + myServiceBean.getDomain());
        registry.addMapping("/**")
                .allowedOrigins(myServiceBean.getDomain())
                .allowedMethods("GET", "POST")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
