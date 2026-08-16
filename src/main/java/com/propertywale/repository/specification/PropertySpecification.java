package com.propertywale.repository.specification;

import com.propertywale.dto.PropertySearchCriteria;
import com.propertywale.entity.Property;
import com.propertywale.entity.PropertyStatus;
import org.springframework.data.jpa.domain.Specification;

/**
 * Builds a single combined Specification<Property> out of a
 * PropertySearchCriteria, so the repository/service layer never has to
 * write ad-hoc conditional query logic. Any criteria field left null
 * is simply skipped.
 */
public final class PropertySpecification {

    private PropertySpecification() {
    }

    public static Specification<Property> fromCriteria(PropertySearchCriteria criteria) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            // Only ever show non-sold properties in public search.
            predicates = cb.and(predicates, cb.notEqual(root.get("status"), PropertyStatus.SOLD));

            if (criteria == null) {
                return predicates;
            }

            if (criteria.getCity() != null && !criteria.getCity().isBlank()) {
                predicates = cb.and(predicates,
                        cb.equal(cb.lower(root.get("city")), criteria.getCity().toLowerCase()));
            }

            if (criteria.getLocality() != null && !criteria.getLocality().isBlank()) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("locality")), "%" + criteria.getLocality().toLowerCase() + "%"));
            }

            if (criteria.getCategoryId() != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("category").get("id"), criteria.getCategoryId()));
            }

            if (criteria.getMinBudget() != null) {
                predicates = cb.and(predicates,
                        cb.greaterThanOrEqualTo(root.get("price"), criteria.getMinBudget()));
            }

            if (criteria.getMaxBudget() != null) {
                predicates = cb.and(predicates,
                        cb.lessThanOrEqualTo(root.get("price"), criteria.getMaxBudget()));
            }

            if (criteria.getKeyword() != null && !criteria.getKeyword().isBlank()) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("name")), "%" + criteria.getKeyword().toLowerCase() + "%"));
            }

            return predicates;
        };
    }
}
