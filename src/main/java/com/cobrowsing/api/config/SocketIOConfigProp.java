package com.cobrowsing.api.config;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Validated
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "socket-io")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SocketIOConfigProp {

    @NotNull
    Integer port;

    @NotEmpty
    String host;

}
