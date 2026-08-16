package com.propertywale.controller;

import com.propertywale.dto.PropertySearchCriteria;
import com.propertywale.service.CategoryService;
import com.propertywale.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * Public-facing property search & details pages.
 * GET /properties            -> search / listing page (filters via query params)
 * GET /properties/{code}     -> details page for a single property
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/properties")
public class PropertyController {

    private static final int PAGE_SIZE = 9;

    private final PropertyService propertyService;
    private final CategoryService categoryService;

    @GetMapping
    public String list(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String locality,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) BigDecimal minBudget,
            @RequestParam(required = false) BigDecimal maxBudget,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        PropertySearchCriteria criteria = PropertySearchCriteria.builder()
                .city(city)
                .locality(locality)
                .categoryId(categoryId)
                .minBudget(minBudget)
                .maxBudget(maxBudget)
                .keyword(keyword)
                .build();

        Page<?> results = propertyService.search(criteria, PageRequest.of(page, PAGE_SIZE));

        model.addAttribute("results", results);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("criteria", criteria);
        return "property-list";
    }

    @GetMapping("/{propertyCode}")
    public String details(@PathVariable String propertyCode, Model model) {
        model.addAttribute("property", propertyService.getDetailsByCode(propertyCode));
        return "property-details";
    }
}
