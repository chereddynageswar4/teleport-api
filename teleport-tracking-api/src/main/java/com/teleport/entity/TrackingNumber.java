package com.teleport.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "tracking_numbers", uniqueConstraints = @UniqueConstraint(columnNames = {"tracking_number"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackingNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 16)
    private String tracking_number;

    @Column(nullable = false)
    private OffsetDateTime created_at;

    @Column(nullable = false)
    private String origin_country_id;

    @Column(nullable = false)
    private String destination_country_id;

    private double weight;

    @Column(nullable = false)
    private UUID customer_id;

    private String customer_name;

    private String customer_slug;
}