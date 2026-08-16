package com.propertywale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Trimmed-down view of a Property used in listing pages / cards
 * (home page featured & latest rails, search results grid).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertySummaryDto {

    private Long id;
    private String propertyCode;
    private String name;
    private String categoryName;
    private BigDecimal price;
    private Integer areaSqft;
    private String city;
    private String locality;
    private String status;
    private boolean featured;
    private String coverImageUrl;
}
