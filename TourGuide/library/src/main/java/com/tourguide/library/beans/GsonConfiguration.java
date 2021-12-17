package com.tourguide.library.beans;

import java.util.Date;

import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tourguide.library.adapter.MoneyTypeAdapterFactory;
import com.tourguide.library.adapter.UnixEpochDateTypeAdapter;

/**
 * 
 * @author kingteff
 *
 */
@Configuration
public class GsonConfiguration {
	
	/**
     * Type adapter registration gson builder customizer.
     *
     * @return the gson builder customizer
     */
    @Bean
    public GsonBuilderCustomizer typeAdapterRegistration() {
        return builder -> {
            builder.registerTypeAdapter(Date.class, new UnixEpochDateTypeAdapter());
            builder.registerTypeAdapterFactory(new MoneyTypeAdapterFactory());
        };
    }

}
