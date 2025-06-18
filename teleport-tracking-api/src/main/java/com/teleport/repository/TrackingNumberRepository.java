package com.teleport.repository;

import com.teleport.entity.TrackingNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrackingNumberRepository extends JpaRepository<TrackingNumber, Long> {
    Optional<TrackingNumber> findByTrackingNumber(String trackingNumber);
    boolean existsByTrackingNumber(String trackingNumber);
}