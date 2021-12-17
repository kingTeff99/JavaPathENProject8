package com.tourguide.microservice.tourguide.module;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Tour guide module.
 */
@Configuration
public class TourGuideModule {
	
	/**
     * Gets gps util.
     *
     * @return GpsUtil gps util
     */
    @Bean
    public GpsUtil getGpsUtil() {
        return new GpsUtil();
    }

    /**
     * Gets reward central.
     *
     * @return RewardCentral reward central
     */
    @Bean
    public RewardCentral getRewardCentral() {
        return new RewardCentral();
    }

}
