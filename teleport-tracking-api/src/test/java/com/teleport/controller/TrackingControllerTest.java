package com.teleport.controller;

import com.teleport.dto.TrackingResponse;
import com.teleport.service.TrackingNumberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrackingController.class)
public class TrackingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrackingNumberService trackingNumberService;

    @Test
    void shouldReturnTrackingNumberResponse() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        TrackingResponse mockResponse = new TrackingResponse("ABC123XYZ456LMNO", now);

        when(trackingNumberService.generateTrackingNumber(
                "MY", "ID", 1.234,
                now, UUID.fromString("de619854-b59b-425e-9db4-943979e1bd49"),
                "RedBox Logistics", "redbox-logistics"))
                .thenReturn(mockResponse);

        mockMvc.perform(get("/next-tracking-number")
                        .param("origin_country_id", "MY")
                        .param("destination_country_id", "ID")
                        .param("weight", "1.234")
                        .param("created_at", now.toString())
                        .param("customer_id", "de619854-b59b-425e-9db4-943979e1bd49")
                        .param("customer_name", "RedBox Logistics")
                        .param("customer_slug", "redbox-logistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tracking_number").value("ABC123XYZ456LMNO"));
    }
}