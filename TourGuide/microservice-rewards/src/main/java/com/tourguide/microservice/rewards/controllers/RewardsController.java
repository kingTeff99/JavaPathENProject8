package com.tourguide.microservice.rewards.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tourguide.microservice.rewards.service.RewardsService;

/**
 * 
 * @author kingteff
 *
 */
@RestController
public class RewardsController {
	
	private final RewardsService rewardsService;
	
    private final UsersAPI usersAPI;
    
    /**
     * Instantiates a new Rewards controller.
     *
     * @param rewardsService the rewards service
     * @param usersAPI       the users api
     */
    public RewardsController(RewardsService rewardsService, UsersAPI usersAPI){
        this.rewardsService = rewardsService;
        this.usersAPI = usersAPI;
    }

    /**
     * Calculate rewards list.
     *
     * @param userName the user name
     * @return the list
     */
    @PostMapping("/calculateRewards/{userName}")
    public List<UserReward> calculateRewards(@PathVariable String userName) {
        return rewardsService.calculateRewards(userName);
    }

    /**
     * Gets reward points.
     *
     * @param attraction the attraction
     * @param userName   the user name
     * @return the reward points
     */
    @PostMapping("/getRewardPoints/{userName}")
    public int getRewardPoints(@RequestBody Attraction attraction, @PathVariable String userName) {
        return rewardsService.getRewardPoints(attraction, usersAPI.getUser(userName));
    }

    /**
     * Get distance double.
     *
     * @param distancesHolder the distances holder
     * @return the double
     */
    @PostMapping("/getDistance")
    public double getDistance(@RequestBody DistancesHolder distancesHolder){
        return rewardsService.getDistance(distancesHolder.getLocation1(), distancesHolder.getLocation2());
    }

}
