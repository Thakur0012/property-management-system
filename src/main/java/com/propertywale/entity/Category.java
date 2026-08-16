package com.propertywale.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Property category / type, e.g. Flat, Apartment, Plot, Bungalow,
 * Villa, Shop, Office, Commercial Space, Agricultural Land, Residential Land.
 */
@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 60)
    private String name;

    /** Font Awesome icon class used on category cards, e.g. "fa-building". */
    @Column(length = 40)
    private String icon;
}
