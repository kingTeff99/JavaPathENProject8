package com.tourguide.library.model;

import lombok.Data;

/**
 * The User preferences request
 * @author kingteff
 *
 */
@Data
public class UserPreferencesRequest {
	
	private int attractionProximity = Integer.MAX_VALUE;
	
    private String currency = "USD";
    
    private int lowerPricePoint;
    
    private int highPricePoint;
    
    private int tripDuration = 1;
    
    private int ticketQuantity = 1;
    
    private int numberOfAdults = 1;
    
    private int numberOfChildren = 0;

}
