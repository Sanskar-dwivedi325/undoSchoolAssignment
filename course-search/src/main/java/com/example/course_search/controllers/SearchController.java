package com.example.course_search.controllers;

import com.example.course_search.model.CourseDocument;
import com.example.course_search.dto.SearchRequestDTO;
import com.example.course_search.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/search")

public class SearchController {


    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @GetMapping
    public SearchResponse searchCourses(SearchRequestDTO request) throws IOException {
        List<CourseDocument> courses = searchService.searchCourses(request);



        return new SearchResponse(courses.size(), courses);
    }

    // Response class
    public record SearchResponse(long total, List<CourseDocument> courses) {}
}
