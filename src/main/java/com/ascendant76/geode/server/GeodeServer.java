package com.ascendant76.geode.server;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(
        scanBasePackages = {"com.ascendant76.geode.server", "com.ascendant76.geode.domain"})
public class GeodeServer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GeodeServer.class)
                .web(WebApplicationType.NONE)
                .build()
                .run(args);
    }
}
