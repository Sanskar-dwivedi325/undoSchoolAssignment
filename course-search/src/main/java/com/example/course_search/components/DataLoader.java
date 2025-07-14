package com.example.course_search.components;


import com.example.course_search.model.CourseDocument;
import com.example.course_search.repository.CourseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component

public class DataLoader implements CommandLineRunner {

    private final CourseRepository courseRepository;

    public DataLoader(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        InputStream input = new ClassPathResource("sample-courses.json").getInputStream();

        List<CourseDocument> courses = mapper.readValue(input, new TypeReference<>() {});
        courseRepository.saveAll(courses);

        System.out.println("Sample data indexed into Elasticsearch");
    }
}

