package org.tlbc.hymns.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.elasticsearch.client.ClientConfiguration;

class NewRestClientConfigTest {

    private NewRestClientConfig newRestClientConfigUnderTest;

    @BeforeEach
    void setUp() {
        newRestClientConfigUnderTest = new NewRestClientConfig();
    }

    @Test
    void testClientConfiguration() {
        // Setup
        // Run the test
        final ClientConfiguration result = newRestClientConfigUnderTest.clientConfiguration();

        // Verify the results
    }
}
