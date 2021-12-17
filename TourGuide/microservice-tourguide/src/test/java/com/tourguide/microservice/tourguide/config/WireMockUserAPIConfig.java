package com.tourguide.microservice.tourguide.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 
 * @author kingteff
 *
 */
@TestConfiguration
public class WireMockUserAPIConfig {
	
	@Autowired
    private WireMockServer wireMockServer;

    /**
     * Wire mock server wire mock server.
     *
     * @return the wire mock server
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer wireMockServer() {
        return new WireMockServer(9561);
    }

}
