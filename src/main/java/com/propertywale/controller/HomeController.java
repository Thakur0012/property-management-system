package com.propertywale.controller;

import com.propertywale.service.CategoryService;
import com.propertywale.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PropertyService propertyService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("featuredProperties", propertyService.findFeatured(PageRequest.of(0, 6)).getContent());
        model.addAttribute("latestProperties", propertyService.findLatest(PageRequest.of(0, 8)).getContent());
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
