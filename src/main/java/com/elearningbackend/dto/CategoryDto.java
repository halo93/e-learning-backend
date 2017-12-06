package com.elearningbackend.dto;

import com.elearningbackend.entity.Subcategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CategoryDto {
    @JsonProperty("category_code")
    private String categoryCode;
    @JsonProperty("category_introduction")
    private String categoryIntro;
    @JsonProperty("subcategories_name")
    private List<String> subcategoriesName;
    @JsonProperty("subcategories_count")
    private int subcategoriesCount;
    @JsonProperty("creation_date")
    private Timestamp creationDate;
    @JsonProperty("update_date")
    private Timestamp lastUpdateDate;

    @JsonIgnore
    private Set<Subcategory> subcategories;

    public CategoryDto(String categoryCode, String categoryIntro, Set<Subcategory> subcategories) {
        this.categoryCode = categoryCode;
        this.categoryIntro = categoryIntro;
        this.subcategories = subcategories;
    }

    public CategoryDto(String categoryCode, String categoryIntro) {
        this.categoryCode = categoryCode;
        this.categoryIntro = categoryIntro;
    }

    public List<String> getSubcategoriesName() {
        subcategoriesName = subcategories.stream().map(Subcategory::getDisplayName).collect(Collectors.toList());
        return subcategoriesName;
    }

    public int getSubcategoriesCount() {
        subcategoriesCount = subcategories.size();
        return subcategoriesCount;
    }
}
