package com.teleport.service;

import com.teleport.dto.TrackingResponse;
import com.teleport.entity.TrackingNumber;
import com.teleport.repository.TrackingNumberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.OffsetDateTime;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrackingNumberService {

    private final TrackingNumberRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(TrackingNumberService.class);
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int MAX_LENGTH = 16;
    private final Random random = new Random();

    public TrackingResponse generateTrackingNumber(String origin, String destination,
                                                   double weight, OffsetDateTime createdAt,
                                                   UUID customerId, String customerName, String customerSlug) {
        String trackingNumber;
        TrackingNumber entity = null;

        while (entity == null) {
            trackingNumber = generateRandomTrackingNumber();
            try {
             entity = repository.save(TrackingNumber.builder()
                    .tracking_number(trackingNumber)
                    .created_at(createdAt)
                    .origin_country_id(origin)
                    .destination_country_id(destination)
                    .weight(weight)
                    .customer_id(customerId)
                    .customer_name(customerName)
                    .customer_slug(customerSlug)
                    .build());
            } catch (DataIntegrityViolationException e) {
                // Duplicate key violation, retry
                logger.warn("Duplicate tracking number generated, retrying...");
            }
        }


        logger.info("Generated tracking number [{}] for customer [{}]", trackingNumber, customerId);
        return TrackingResponse.builder()
                .tracking_number(trackingNumber)
                .created_at(entity.getCreated_at())
                .build();
    }

    private String generateRandomTrackingNumber() {
        StringBuilder sb = new StringBuilder(MAX_LENGTH);
        for (int i = 0; i < MAX_LENGTH; i++) {
            sb.append(CHAR_POOL.charAt(random.nextInt(CHAR_POOL.length())));
        }
        return sb.toString();
    }
}
