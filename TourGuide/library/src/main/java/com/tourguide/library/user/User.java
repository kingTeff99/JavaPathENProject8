package com.tourguide.library.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import lombok.Data;

@Data
public class User {
	
	private final UUID userId;
	
    private final String userName;
    
    private String phoneNumber;
    
    private String emailAddress;
    
    private Date latestLocationTimestamp = new Date();
    
    private final List<VisitedLocation> visitedLocations = new CopyOnWriteArrayList<>();
    
    private List<UserReward> userRewards;
    
    private UserPreferences userPreferences = new UserPreferences();
    
    private List<Provider> tripDeals = new ArrayList<>();

}
