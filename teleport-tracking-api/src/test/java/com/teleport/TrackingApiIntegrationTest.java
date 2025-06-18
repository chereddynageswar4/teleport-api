package com.teleport;

import com.teleport.dto.TrackingResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrackingApiIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void trackingNumberEndpointShouldReturnValidTrackingNumber() {
        String now = OffsetDateTime.now().toString();
        String url = String.format("http://localhost:%d/next-tracking-number?origin_country_id=MY&destination_country_id=ID&weight=1.234&created_at=%s&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox%%20Logistics&customer_slug=redbox-logistics", port, now);

        ResponseEntity<TrackingResponse> response = restTemplate.getForEntity(url, TrackingResponse.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getTracking_number()).matches("^[A-Z0-9]{16}$");
    }
}