package com.tiger.customer.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiger.common.utils.ObjectMapperUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return ObjectMapperUtil.objectMapper();
    }

}
