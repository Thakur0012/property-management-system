package com.propertywale.repository;

import com.propertywale.entity.Property;
import com.propertywale.entity.PropertyStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long>,
        JpaSpecificationExecutor<Property> {

    Optional<Property> findByPropertyCode(String propertyCode);

    Page<Property> findByFeaturedTrueAndStatus(PropertyStatus status, Pageable pageable);

    Page<Property> findByStatusOrderByCreatedDateDesc(PropertyStatus status, Pageable pageable);
}
