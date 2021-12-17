package com.tourguide.microservice.rewards.config;

public class UserAPIMocks {
	

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
                                                UserAPIMocks.class.getClassLoader().getResourceAsStream("responses/mocks-UsersAPI-getUser.json"),
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
                        WireMock.get(WireMock.urlEqualTo("/rewards/internalUser60")
                        ).willReturn(WireMock.aResponse().withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(
                                        copyToString(UserAPIMocks.class.getClassLoader()
                                                .getResourceAsStream("responses/mocks-UsersAPI-getUserReward.json"), defaultCharset()
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

}
