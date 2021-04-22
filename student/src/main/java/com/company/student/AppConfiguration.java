package com.company.student;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@ComponentScan
public class AppConfiguration {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Gson gsonCustom(){
        GsonBuilder builder = new GsonBuilder();
        Gson gson =builder
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .create();
        return gson;
    }

}
