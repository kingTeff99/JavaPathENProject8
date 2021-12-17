package com.tourguide.microservice.tourguide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Tour guide controller.
 */
@RestController
public class TourGuideController {
	
	@Autowired
    private TourGuideService tourGuideService;

	@Autowired
    private UsersAPI usersAPI;

    /**
     * Index string.
     *
     * @return string "Greetings from TourGuide!"
     */
    @GetMapping("/")
    public String index() {
        return "Greetings from TourGuide!";
    }

    /**
     * Gets location.
     *
     * @param userName String
     * @return the location of this user
     */
    @GetMapping("/getLocation/{userName}")
    public VisitedLocation getLocation(@PathVariable String userName) {
    	return tourGuideService.getUserLocation(usersAPI.getUser(userName));
    }

    /**
     * Gets nearby attractions.
     *
     * @param userName the user name
     * @return the first 5 attraction of this user
     */
    @GetMapping("/getNearbyAttractions/{userName}")
    public List<AttractionResponse> getNearbyAttractions(@PathVariable String userName) {
        User user = usersAPI.getUser(userName);
    	VisitedLocation visitedLocation = tourGuideService.getUserLocation(user);
    	return tourGuideService.getNearByAttractions(visitedLocation, user);
    }

    /**
     * Gets trip deals.
     *
     * @param userName               the user name
     * @param userPreferencesRequest the user preferences request
     * @return a list of TripDeals based on user preferences
     */
    @GetMapping("/getTripDeals")
    public List<Provider> getTripDeals(@RequestParam String userName, @Valid UserPreferencesRequest userPreferencesRequest) {
        User user = usersAPI.getUser(userName);
        UserPreferences userPreferences = user.getUserPreferences();
        userPreferences.setCurrency(userPreferencesRequest.getCurrency());
        userPreferences.setAttractionProximity(userPreferencesRequest.getAttractionProximity());
        userPreferences.setHighPricePoint(userPreferencesRequest.getHighPricePoint());
        userPreferences.setLowerPricePoint(userPreferencesRequest.getLowerPricePoint());
        userPreferences.setTripDuration(userPreferencesRequest.getTripDuration());
        userPreferences.setTicketQuantity(userPreferencesRequest.getTicketQuantity());
        userPreferences.setNumberOfAdults(userPreferencesRequest.getNumberOfAdults());
        userPreferences.setNumberOfChildren(userPreferencesRequest.getNumberOfChildren());
        return tourGuideService.getTripDeals(user);
    }

    /**
     * Track user location.
     *
     * @param userName track the location of this user
     */
    @GetMapping("/trackUserLocation/{userName}")
    public void trackUserLocation(@PathVariable String userName){
        User user = usersAPI.getUser(userName);
        tourGuideService.trackUserLocation(user);
    }

}
