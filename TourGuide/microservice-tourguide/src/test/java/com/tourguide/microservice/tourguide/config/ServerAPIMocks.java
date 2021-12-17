package com.tourguide.microservice.tourguide.config;

import org.springframework.http.MediaType;

/**
 * 
 * @author kingteff
 *
 */
public class ServerAPIMocks {
	
	/**
     * Sets mock user api response.
     *
     * @param mockService the mock service
     * @throws IOException the io exception
     */
    public static void setupMockUserAPIResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(
                WireMock.get(
                        WireMock.urlEqualTo("/user/internalUser60"))
                        .willReturn(WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(
                                        copyToString(
                                                ServerAPIMocks.class.getClassLoader().getResourceAsStream("responses/mocks-UsersAPI-getUser.json"),
                                                defaultCharset()
                                        )
                                ))
        );

        mockService.stubFor(
                WireMock.post(
                        WireMock.urlEqualTo("/rewards/internalUser60"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        )

        );

        mockService.stubFor(
                WireMock.post(
                        WireMock.urlEqualTo("/tripDeals/internalUser60"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        )

        );

        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/rewards/internalUser60"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        ServerAPIMocks.class.getClassLoader().getResourceAsStream("responses/mocks-UsersAPI-getUserReward.json"),
                                        defaultCharset()))));

        //RewardsAPI
        mockService.stubFor(
                WireMock.post(
                                WireMock.urlEqualTo("/getRewardPoints/internalUser60"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(
                                                copyToString(
                                                        ServerAPIMocks.class.getClassLoader().getResourceAsStream("responses/mocks-RewardsAPI-getRewardsPoints.json"),
                                                        defaultCharset()
                                                )
                                        )
                        )
        );
        mockService.stubFor(
                WireMock.post(
                                WireMock.urlEqualTo("/getDistance"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(
                                                copyToString(
                                                        ServerAPIMocks.class.getClassLoader().getResourceAsStream("responses/mocks-RewardsAPI-getDistance.json"),
                                                        defaultCharset()
                                                )
                                        )
                        )
        );

        mockService.stubFor(
                WireMock.post(
                                WireMock.urlEqualTo("/calculateRewards/internalUser60"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(
                                                copyToString(
                                                        ServerAPIMocks.class.getClassLoader().getResourceAsStream("responses/mocks-RewardsAPI-calculateRewards.json"),
                                                        defaultCharset()
                                                )
                                        )
                        )
        );
    }

    /**
     * Sets mock user api reward responses.
     *
     * @param mockService the mock service
     * @param nbUsers     the nb users
     */
    public static void setupMockUserAPIRewardResponses(WireMockServer mockService, int nbUsers) {

        for (int i = 0; i < nbUsers; i++) {
            mockService.stubFor(
                    WireMock.post(
                                    WireMock.urlEqualTo("/rewards/internalUser" + i))
                            .willReturn(
                                    WireMock.aResponse()
                                            .withStatus(HttpStatus.OK.value())
                                            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                            )

            );
        }
    }


    /**
     * Sets mock user api calculate responses.
     *
     * @param mockService the mock service
     * @param nbUsers     the nb users
     */
    public static void setupMockUserAPICalculateResponses(WireMockServer mockService, int nbUsers) {

        for (int i = 0; i < nbUsers; i++) {
            mockService.stubFor(
                    WireMock.post(
                                    WireMock.urlEqualTo("/calculateRewards/internalUser" + i))
                            .willReturn(
                                    WireMock.aResponse()
                                            .withStatus(HttpStatus.OK.value())
                                            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                            )

            );
        }
    }

    /**
     * Sets mock user api create visited location responses.
     *
     * @param mockService the mock service
     * @param nbUsers     the nb users
     */
    public static void setupMockUserAPICreateVisitedLocationResponses(WireMockServer mockService, int nbUsers) {

        for (int i = 0; i < nbUsers; i++) {
            mockService.stubFor(
                    WireMock.post(
                                    WireMock.urlEqualTo("/addVisitedLocation/internalUser" + i))
                            .willReturn(
                                    WireMock.aResponse()
                                            .withStatus(HttpStatus.OK.value())
                                            .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                            )

            );
        }
    }

}
