package com.propertywale.service.impl;

import com.propertywale.dto.PropertyDetailsDto;
import com.propertywale.dto.PropertySearchCriteria;
import com.propertywale.dto.PropertySummaryDto;
import com.propertywale.entity.Property;
import com.propertywale.entity.PropertyImage;
import com.propertywale.entity.PropertyStatus;
import com.propertywale.exception.ResourceNotFoundException;
import com.propertywale.repository.PropertyRepository;
import com.propertywale.repository.specification.PropertySpecification;
import com.propertywale.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    @Override
    public Page<PropertySummaryDto> search(PropertySearchCriteria criteria, Pageable pageable) {
        return propertyRepository.findAll(PropertySpecification.fromCriteria(criteria), pageable)
                .map(this::toSummary);
    }

    @Override
    public Page<PropertySummaryDto> findFeatured(Pageable pageable) {
        return propertyRepository.findByFeaturedTrueAndStatus(PropertyStatus.AVAILABLE, pageable)
                .map(this::toSummary);
    }

    @Override
    public Page<PropertySummaryDto> findLatest(Pageable pageable) {
        return propertyRepository.findByStatusOrderByCreatedDateDesc(PropertyStatus.AVAILABLE, pageable)
                .map(this::toSummary);
    }

    @Override
    public PropertyDetailsDto getDetailsByCode(String propertyCode) {
        Property property = propertyRepository.findByPropertyCode(propertyCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No property found with ID " + propertyCode));
        return toDetails(property);
    }

    private PropertySummaryDto toSummary(Property p) {
        return PropertySummaryDto.builder()
                .id(p.getId())
                .propertyCode(p.getPropertyCode())
                .name(p.getName())
                .categoryName(p.getCategory().getName())
                .price(p.getPrice())
                .areaSqft(p.getAreaSqft())
                .city(p.getCity())
                .locality(p.getLocality())
                .status(p.getStatus().name())
                .featured(p.isFeatured())
                .coverImageUrl(coverImageOf(p))
                .build();
    }

    private PropertyDetailsDto toDetails(Property p) {
        List<String> imageUrls = p.getImages().stream()
                .map(PropertyImage::getImageUrl)
                .toList();

        return PropertyDetailsDto.builder()
                .id(p.getId())
                .propertyCode(p.getPropertyCode())
                .name(p.getName())
                .categoryName(p.getCategory().getName())
                .price(p.getPrice())
                .areaSqft(p.getAreaSqft())
                .description(p.getDescription())
                .amenities(p.getAmenities())
                .city(p.getCity())
                .locality(p.getLocality())
                .address(p.getAddress())
                .latitude(p.getLatitude())
                .longitude(p.getLongitude())
                .status(p.getStatus().name())
                .featured(p.isFeatured())
                .createdDate(p.getCreatedDate())
                .imageUrls(imageUrls)
                .build();
    }

    private String coverImageOf(Property p) {
        return p.getImages().stream()
                .filter(PropertyImage::isCoverImage)
                .map(PropertyImage::getImageUrl)
                .findFirst()
                .or(() -> p.getImages().stream().map(PropertyImage::getImageUrl).findFirst())
                .orElse("/images/property-placeholder.svg");
    }
}
