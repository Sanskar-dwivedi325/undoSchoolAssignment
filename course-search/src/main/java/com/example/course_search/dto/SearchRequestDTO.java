package com.example.course_search.dto;

import lombok.Data;

@Data
public class SearchRequestDTO {
    private String q;
    private Integer minAge;
    private Integer maxAge;
    private Double minPrice;
    private Double maxPrice;
    private String category;
    private String type;
    private String startDate;
    private String sort;
    private int page = 0;
    private int size = 10;
}