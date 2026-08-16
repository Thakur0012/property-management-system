package com.propertywale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Full detail view of a property. Owner name/contact are intentionally
 * NOT included here - per spec, owner details are hidden from anonymous
 * users and only revealed once a user submits an enquiry (future stage).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDetailsDto {

    private Long id;
    private String propertyCode;
    private String name;
    private String categoryName;
    private BigDecimal price;
    private Integer areaSqft;
    private String description;
    private String amenities;
    private String city;
    private String locality;
    private String address;
    private Double latitude;
    private Double longitude;
    private String status;
    private boolean featured;
    private LocalDateTime createdDate;
    private List<String> imageUrls;
}
