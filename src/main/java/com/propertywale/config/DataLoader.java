package com.propertywale.config;

import com.propertywale.entity.*;
import com.propertywale.repository.CategoryRepository;
import com.propertywale.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Seeds the in-memory H2 database with categories and sample listings so
 * the app is fully explorable right after `mvn spring-boot:run` without
 * any manual setup. Only runs on the "dev" profile (see
 * application-dev.properties) - production expects a real MySQL/Postgres
 * database with its own data.
 */
@Component
@RequiredArgsConstructor
@Profile({"dev", "default"})
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final PropertyRepository propertyRepository;

    private static final List<String> CATEGORY_NAMES = List.of(
            "Flat", "Apartment", "Plot", "Bungalow", "Villa",
            "Shop", "Office", "Commercial Space", "Agricultural Land", "Residential Land"
    );

    private static final Map<String, String> CATEGORY_ICONS = Map.ofEntries(
            Map.entry("Flat", "fa-building"),
            Map.entry("Apartment", "fa-city"),
            Map.entry("Plot", "fa-map"),
            Map.entry("Bungalow", "fa-house-chimney"),
            Map.entry("Villa", "fa-house"),
            Map.entry("Shop", "fa-store"),
            Map.entry("Office", "fa-briefcase"),
            Map.entry("Commercial Space", "fa-shop"),
            Map.entry("Agricultural Land", "fa-tractor"),
            Map.entry("Residential Land", "fa-map-location-dot")
    );

    @Override
    public void run(String... args) {
        if (categoryRepository.count() > 0) {
            return; // already seeded
        }

        CATEGORY_NAMES.forEach(name -> {
            Category c = new Category();
            c.setName(name);
            c.setIcon(CATEGORY_ICONS.getOrDefault(name, "fa-home"));
            categoryRepository.save(c);
        });

        Category flat = categoryRepository.findByNameIgnoreCase("Flat").orElseThrow();
        Category villa = categoryRepository.findByNameIgnoreCase("Villa").orElseThrow();
        Category plot = categoryRepository.findByNameIgnoreCase("Plot").orElseThrow();
        Category shop = categoryRepository.findByNameIgnoreCase("Shop").orElseThrow();
        Category office = categoryRepository.findByNameIgnoreCase("Office").orElseThrow();
        Category apartment = categoryRepository.findByNameIgnoreCase("Apartment").orElseThrow();

        seed("PW-1001", "Sunrise Residency 3BHK", flat, new BigDecimal("8500000"), 1450,
                "Pune", "Kharadi", "A bright, well-ventilated 3BHK close to IT parks and schools.",
                "Lift, Power Backup, Car Parking, Gym, Club House",
                18.5515, 73.9438, true, PropertyStatus.AVAILABLE,
                "https://picsum.photos/seed/pw1001a/800/533", "https://picsum.photos/seed/pw1001b/800/533");

        seed("PW-1002", "Green Meadows Villa", villa, new BigDecimal("32000000"), 3200,
                "Pune", "Baner", "Independent 4BHK villa with private garden and terrace.",
                "Private Garden, Servant Room, Modular Kitchen, 24x7 Security",
                18.5590, 73.7868, true, PropertyStatus.AVAILABLE,
                "https://picsum.photos/seed/pw1002a/800/533", "https://picsum.photos/seed/pw1002b/800/533");

        seed("PW-1003", "Riverside NA Plot", plot, new BigDecimal("6500000"), 2400,
                "Pune", "Wagholi", "Clear-title NA plot, ideal for building your dream home.",
                "Gated Layout, Wide Roads, Water Connection",
                18.5793, 73.9895, false, PropertyStatus.AVAILABLE,
                "https://picsum.photos/seed/pw1003a/800/533");

        seed("PW-1004", "MG Road Retail Shop", shop, new BigDecimal("12000000"), 650,
                "Pune", "Camp", "High-footfall ground-floor shop on MG Road commercial belt.",
                "Main Road Facing, Shutter, Washroom",
                18.5089, 73.8789, false, PropertyStatus.AVAILABLE,
                "https://picsum.photos/seed/pw1004a/800/533");

        seed("PW-1005", "Cyber Tower Office Space", office, new BigDecimal("18500000"), 1800,
                "Pune", "Hinjewadi", "Grade-A furnished office space, ready to move in.",
                "Furnished, Centralized AC, Cafeteria, Parking",
                18.5913, 73.7389, true, PropertyStatus.AVAILABLE,
                "https://picsum.photos/seed/pw1005a/800/533");

        seed("PW-1006", "Lakeview 2BHK Apartment", apartment, new BigDecimal("5200000"), 980,
                "Mumbai", "Powai", "Cozy 2BHK apartment with a lake-facing balcony.",
                "Lift, Gym, Kids Play Area, Power Backup",
                19.1197, 72.9050, false, PropertyStatus.AVAILABLE,
                "https://picsum.photos/seed/pw1006a/800/533");

        seed("PW-1007", "Palm Grove 3BHK Flat", flat, new BigDecimal("9750000"), 1600,
                "Bengaluru", "Whitefield", "Spacious flat in a gated community near IT corridor.",
                "Swimming Pool, Clubhouse, Jogging Track, Security",
                12.9698, 77.7500, true, PropertyStatus.AVAILABLE,
                "https://picsum.photos/seed/pw1007a/800/533");

        seed("PW-1008", "Heritage Bungalow", villa, new BigDecimal("45000000"), 4500,
                "Pune", "Koregaon Park", "Restored heritage bungalow with modern interiors.",
                "Heritage Architecture, Large Lawn, Parking for 4 Cars",
                18.5362, 73.8935, false, PropertyStatus.RESERVED,
                "https://picsum.photos/seed/pw1008a/800/533");

        seed("PW-1009", "Sold Sample Unit", flat, new BigDecimal("6100000"), 1100,
                "Pune", "Aundh", "Demonstration record marked as sold (excluded from search).",
                "Lift, Parking",
                18.5590, 73.8078, false, PropertyStatus.SOLD,
                "https://picsum.photos/seed/pw1009a/800/533");
    }

    private void seed(String code, String name, Category category, BigDecimal price, int areaSqft,
                       String city, String locality, String description, String amenities,
                       double lat, double lng, boolean featured, PropertyStatus status, String... imageUrls) {

        Property property = Property.builder()
                .propertyCode(code)
                .name(name)
                .category(category)
                .price(price)
                .areaSqft(areaSqft)
                .city(city)
                .locality(locality)
                .address(locality + ", " + city)
                .description(description)
                .amenities(amenities)
                .latitude(lat)
                .longitude(lng)
                .featured(featured)
                .status(status)
                .ownerName("PropertyWale Verified Owner")
                .ownerContact("+91-9800000000")
                .build();

        for (int i = 0; i < imageUrls.length; i++) {
            PropertyImage image = new PropertyImage();
            image.setImageUrl(imageUrls[i]);
            image.setCoverImage(i == 0);
            image.setProperty(property);
            property.getImages().add(image);
        }

        propertyRepository.save(property);
    }
}
