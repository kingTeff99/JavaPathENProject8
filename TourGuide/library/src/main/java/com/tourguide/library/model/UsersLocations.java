package com.tourguide.library.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author kingteff
 *
 */
@Data
public class UsersLocations {
	
	private UUID userId;
	
	private Location location;

}
