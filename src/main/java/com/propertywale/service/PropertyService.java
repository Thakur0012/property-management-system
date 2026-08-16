package com.propertywale.service;

import com.propertywale.dto.PropertyDetailsDto;
import com.propertywale.dto.PropertySearchCriteria;
import com.propertywale.dto.PropertySummaryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PropertyService {

    Page<PropertySummaryDto> search(PropertySearchCriteria criteria, Pageable pageable);

    Page<PropertySummaryDto> findFeatured(Pageable pageable);

    Page<PropertySummaryDto> findLatest(Pageable pageable);

    PropertyDetailsDto getDetailsByCode(String propertyCode);
}
