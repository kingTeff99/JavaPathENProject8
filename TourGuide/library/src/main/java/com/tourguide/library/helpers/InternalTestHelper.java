package com.tourguide.library.helpers;

/**
 * The type Internal test helper.
 */
public class InternalTestHelper {
	
		// Set this default up to 100,000 for testing
		private static int internalUserNumber = 100;

	    /**
	     * Sets internal user number.
	     *
	     * @param internalUserNumber the internal user number
	     */
	    public static void setInternalUserNumber(int internalUserNumber) {
			InternalTestHelper.internalUserNumber = internalUserNumber;
		}

	    /**
	     * Gets internal user number.
	     *
	     * @return the internal user number
	     */
	    public static int getInternalUserNumber() {
			return internalUserNumber;
		}

}
