package com.okaka.onenightjinroh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Profile({"default", "stg"})
public class MyEnv {
    @Autowired
    private Environment env;

    public boolean isStg(){
        return Arrays.asList(env.getActiveProfiles()).contains("stg");
    }
}
