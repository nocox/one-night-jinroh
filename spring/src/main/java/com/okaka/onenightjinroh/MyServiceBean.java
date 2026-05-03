package com.okaka.onenightjinroh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyServiceBean {

    @Value("${com.okaka.onenightjinroh.domain}")
    private String[] domain;

    public String[] getDomain() {
        return domain;
    }
}
