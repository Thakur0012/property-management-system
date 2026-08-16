package com.propertywale.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Core property listing entity.
 *
 * Owner contact details are intentionally kept out of any DTO that is
 * rendered to a logged-out/anonymous user - see PropertyDetailsDto.
 */
@Entity
@Table(name = "properties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Human-friendly public identifier shown to users, e.g. "PW-1007". */
    @Column(nullable = false, unique = true, length = 20)
    private String propertyCode;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal price;

    /** Built-up / carpet area in square feet. */
    @Positive
    @Column(nullable = false)
    private Integer areaSqft;

    @Column(length = 4000)
    private String description;

    @Column(length = 2000)
    private String amenities;

    @NotBlank
    @Column(nullable = false, length = 80)
    private String city;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String locality;

    @Column(length = 300)
    private String address;

    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PropertyStatus status;

    private boolean featured;

    // Owner contact - never exposed directly to anonymous users via DTO.
    @Column(length = 100)
    private String ownerName;

    @Column(length = 20)
    private String ownerContact;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PropertyImage> images = new ArrayList<>();

    @PrePersist
    void onCreate() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
        if (status == null) {
            status = PropertyStatus.AVAILABLE;
        }
    }
}
