package com.teleport.controller;

import com.teleport.dto.TrackingResponse;
import com.teleport.service.TrackingNumberService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/next-tracking-number")
public class TrackingController {

    private final TrackingNumberService trackingNumberService;

    @GetMapping
    public TrackingResponse getNextTrackingNumber(
        @RequestParam @NotBlank String origin_country_id,
        @RequestParam @NotBlank String destination_country_id,
        @RequestParam double weight,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime created_at,
        @RequestParam UUID customer_id,
        @RequestParam String customer_name,
        @RequestParam String customer_slug
    ) {
        return trackingNumberService.generateTrackingNumber(origin_country_id, destination_country_id,
                weight, created_at, customer_id, customer_name, customer_slug);
    }
}