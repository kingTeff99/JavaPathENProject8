package com.tourguide.library.model;

import lombok.Data;

import gpsUtil.location.Attraction;

/**
 * Distance Of Attraction
 * @author kingteff
 *
 */
@Data
public class DistanceOfAttraction {
	
	private final double distance;
	
    private final Attraction attraction;
	

}
