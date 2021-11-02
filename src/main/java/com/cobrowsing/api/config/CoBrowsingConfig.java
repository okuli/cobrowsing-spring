package com.cobrowsing.api.config;

import com.cobrowsing.api.dto.CustomerMessageData;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties({SocketIOConfigProp.class})
public class CoBrowsingConfig {

    @Bean
    public List<CustomerMessageData> inMemoryCustomerData() {
        return new ArrayList<>();
    }

}
