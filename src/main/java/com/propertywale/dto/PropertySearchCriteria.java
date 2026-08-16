package com.propertywale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Carries every optional filter the home page / property list page can submit:
 * city, locality/area, category, and a budget (price) range.
 * Any field left null is simply ignored by the search.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertySearchCriteria {

    private String city;
    private String locality;
    private Long categoryId;
    private BigDecimal minBudget;
    private BigDecimal maxBudget;
    private String keyword; // matches against property name
}
