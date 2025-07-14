package com.example.course_search.dto;

import org.springframework.stereotype.Service;

@Service
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

    // No-args constructor
    public SearchRequestDTO() {
    }

    // All-args constructor
    public SearchRequestDTO(String q, Integer minAge, Integer maxAge, Double minPrice, Double maxPrice,
                            String category, String type, String startDate, String sort, int page, int size) {
        this.q = q;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.category = category;
        this.type = type;
        this.startDate = startDate;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }

    // Getters
    public String getQ() {
        return q;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getSort() {
        return sort;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    // Setters
    public void setQ(String q) {
        this.q = q;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }
}