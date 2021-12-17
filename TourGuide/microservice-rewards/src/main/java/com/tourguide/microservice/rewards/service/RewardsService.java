package com.tourguide.microservice.rewards.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author kingteff
 *
 */
@Service
public class RewardsService {
	
	private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

    /**
     * @newFixedThreadPool fix the number of Thread
     */
    private ExecutorService executorService = Executors.newFixedThreadPool(64);

    // proximity in miles
    private final int defaultProximityBuffer = 10;
    private int proximityBuffer = defaultProximityBuffer;
    private final RewardCentral rewardsCentral;
    private final List<gpsUtil.location.Attraction> attractions = new CopyOnWriteArrayList<gpsUtil.location.Attraction>();

    @Autowired
    private UsersAPI usersAPI;

    /**
     * Instantiates a new Rewards service.
     *
     * @param gpsUtil       the gps util
     * @param rewardCentral the reward central
     */
    public RewardsService(GpsUtil gpsUtil, RewardCentral rewardCentral) {
        this.rewardsCentral = rewardCentral;
        attractions.addAll(gpsUtil.getAttractions());
    }

    /**
     * Sets proximity buffer.
     *
     * @param proximityBuffer the proximity buffer
     */
    public void setProximityBuffer(int proximityBuffer) {
        this.proximityBuffer = proximityBuffer;
    }

    /**
     * Calculate rewards list.
     *
     * @param userName the user name
     * @return the list
     */
    public List<UserReward> calculateRewards(String userName) {
        User user = usersAPI.getUser(userName);
        return calculateRewards(user);
    }

    /**
     * Reset thread pool.
     */
    public void resetThreadPool() {
        executorService = Executors.newFixedThreadPool(64);
    }

    /**
     * Calculate rewards list.
     *
     * @param user the user
     * @return the rewards for this user calculateRewards use executorService
     */
    public List<UserReward> calculateRewards(User user) {
        List<VisitedLocation> userLocations = user.getVisitedLocations();
        String userName = user.getUserName();
        List<UserReward> newUserRewards = new ArrayList<>();

        for (VisitedLocation visitedLocation : userLocations) {
            for (Attraction attraction : attractions) {
                if (user.getUserRewards().stream().noneMatch(reward -> reward.attraction.attractionName.equals(attraction.attractionName))) {
                    if (nearAttraction(visitedLocation, attraction)) {
                        UserReward userReward = new UserReward(visitedLocation, attraction, 0);
                        user.addUserReward(userReward);
                        newUserRewards.add(userReward);
                    }
                }
            }
        }

        for (UserReward userReward : newUserRewards) {
            CompletableFuture
                    .supplyAsync(() -> getRewardPoints(userReward.attraction, user), executorService)
                    .thenAccept((points) -> {
                        userReward.setRewardPoints(points);
                        usersAPI.createUserReward(userName, userReward);
                    });
        }

        return user.getUserRewards();
    }

    /**
     * Gets executor service.
     *
     * @return the executor service
     */
    public ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * Is within attraction proximity boolean.
     *
     * @param attraction the attraction
     * @param location   the location
     * @return the boolean
     */
    public boolean isWithinAttractionProximity(Attraction attraction, Location location) {
        int attractionProximityRange = 200;
        return !(getDistance(attraction, location) > attractionProximityRange);
    }

    private boolean nearAttraction(VisitedLocation visitedLocation, Attraction attraction) {
        return !(getDistance(attraction, visitedLocation.location) > proximityBuffer);
    }

    /**
     * Gets reward points.
     *
     * @param attraction the attraction
     * @param user       the user
     * @return the reward points
     */
    public int getRewardPoints(Attraction attraction, User user) {
        return rewardsCentral.getAttractionRewardPoints(attraction.attractionId, user.getUserId());
    }

    /**
     * Gets distance.
     *
     * @param loc1 the loc 1
     * @param loc2 the loc 2
     * @return the distance
     */
    public double getDistance(Location loc1, Location loc2) {
        double latitude1 = Math.toRadians(loc1.latitude);
        double longitude1 = Math.toRadians(loc1.longitude);
        double latitude2 = Math.toRadians(loc2.latitude);
        double longitude2 = Math.toRadians(loc2.longitude);

        double angle = Math.acos(Math.sin(latitude1) * Math.sin(latitude2)
                + Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(longitude1 - longitude2));

        double nauticalMiles = 60 * Math.toDegrees(angle);
        return STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
    }

}
