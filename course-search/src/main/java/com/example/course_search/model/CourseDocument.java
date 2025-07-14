package com.example.course_search.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.Instant;


import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.DateFormat;



@Document(indexName = "courses")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDocument {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Keyword)
    private String type;

    @Field(type = FieldType.Keyword)
    private String gradeRange;

    @Field(type = FieldType.Integer)
    private Integer minAge;

    @Field(type = FieldType.Integer)
    private Integer maxAge;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Date, format = DateFormat.date_time)
    private Instant nextSessionDate;

    // No-args constructor
    public CourseDocument() {
    }

    // All-args constructor
    public CourseDocument(String id, String title, String description, String category, String type,
                          String gradeRange, Integer minAge, Integer maxAge, Double price, Instant nextSessionDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.type = type;
        this.gradeRange = gradeRange;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.price = price;
        this.nextSessionDate = nextSessionDate;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getGradeRange() {
        return gradeRange;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public Double getPrice() {
        return price;
    }

    public Instant getNextSessionDate() {
        return nextSessionDate;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGradeRange(String gradeRange) {
        this.gradeRange = gradeRange;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setNextSessionDate(Instant nextSessionDate) {
        this.nextSessionDate = nextSessionDate;
    }

    // Builder pattern
    public static class Builder {
        private String id;
        private String title;
        private String description;
        private String category;
        private String type;
        private String gradeRange;
        private Integer minAge;
        private Integer maxAge;
        private Double price;
        private Instant nextSessionDate;

        public Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder gradeRange(String gradeRange) {
            this.gradeRange = gradeRange;
            return this;
        }

        public Builder minAge(Integer minAge) {
            this.minAge = minAge;
            return this;
        }

        public Builder maxAge(Integer maxAge) {
            this.maxAge = maxAge;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder nextSessionDate(Instant nextSessionDate) {
            this.nextSessionDate = nextSessionDate;
            return this;
        }

        public CourseDocument build() {
            return new CourseDocument(id, title, description, category, type, gradeRange, minAge, maxAge, price, nextSessionDate);
        }
    }

    // Static method to create a new Builder instance
    public static Builder builder() {
        return new Builder();
    }

    // Optional: Override toString() for better debugging
    @Override
    public String toString() {
        return "CourseDocument{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", gradeRange='" + gradeRange + '\'' +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", price=" + price +
                ", nextSessionDate=" + nextSessionDate +
                '}';
    }

    // Optional: Override equals() and hashCode() for proper object comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseDocument that = (CourseDocument) o;

        if (!id.equals(that.id)) return false;
        if (!title.equals(that.title)) return false;
        if (!description.equals(that.description)) return false;
        if (!category.equals(that.category)) return false;
        if (!type.equals(that.type)) return false;
        if (!gradeRange.equals(that.gradeRange)) return false;
        if (!minAge.equals(that.minAge)) return false;
        if (!maxAge.equals(that.maxAge)) return false;
        if (!price.equals(that.price)) return false;
        return nextSessionDate.equals(that.nextSessionDate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + gradeRange.hashCode();
        result = 31 * result + minAge.hashCode();
        result = 31 * result + maxAge.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + nextSessionDate.hashCode();
        return result;
    }
}

