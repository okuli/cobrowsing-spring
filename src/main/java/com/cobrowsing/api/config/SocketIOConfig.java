package com.cobrowsing.api.config;


import com.corundumstudio.socketio.SocketIOServer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocketIOConfig {

    @Autowired
    SocketIOConfigProp socketIOConfigProp;

    @Bean
    public SocketIOServer socketIOServer() {
        final com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(socketIOConfigProp.getHost());
        config.setPort(socketIOConfigProp.getPort());
        return new SocketIOServer(config);
    }

    @Bean
    public SmartInitializingSingleton startSocketIo() {
        return () -> socketIOServer().start();
    }

}
