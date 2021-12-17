package com.tourguide.library.user;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserReward implements Serializable {
	
	public final VisitedLocation visitedLocation;
	
	public final Attraction attraction;

}
