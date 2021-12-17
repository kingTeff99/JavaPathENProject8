package com.tourguide.microservice.rewards.module;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * @author kingteff
 *
 */
@Configuration
public class TourGuideModule {
	
	/**
     * Gets gps util.
     *
     * @return the gps util
     */
    @Bean
    public GpsUtil getGpsUtil() {
        return new GpsUtil();
    }

    /**
     * Gets reward central.
     *
     * @return the reward central
     */
    @Bean
    public RewardCentral getRewardCentral() {
        return new RewardCentral();
    }

}
