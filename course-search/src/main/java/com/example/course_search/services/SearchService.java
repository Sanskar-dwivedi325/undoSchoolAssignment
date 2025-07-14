package com.example.course_search.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import com.example.course_search.model.CourseDocument;
import com.example.course_search.dto.SearchRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service

public class SearchService {

    private final ElasticsearchClient elasticsearchClient;

    public SearchService(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }



    public List<CourseDocument> searchCourses(SearchRequestDTO request) throws IOException {
        // Validate input
        if (request == null) {
            throw new IllegalArgumentException("SearchRequestDTO cannot be null");
        }

        // Ensure page and size are valid
        int page = Math.max(0, request.getPage());
        int size = Math.max(1, Math.min(request.getSize(), 100)); // Cap size at 100 to prevent large queries

        // Validate age range
        if (request.getMinAge() != null && request.getMaxAge() != null && request.getMinAge() > request.getMaxAge()) {
            throw new IllegalArgumentException("minAge cannot be greater than maxAge");
        }

        // Validate price range
        if (request.getMinPrice() != null && request.getMaxPrice() != null && request.getMinPrice() > request.getMaxPrice()) {
            throw new IllegalArgumentException("minPrice cannot be greater than maxPrice");
        }

        BoolQuery.Builder boolQuery = new BoolQuery.Builder();

        // Full-text search on title and description
        boolean hasFullTextQuery = request.getQ() != null && !request.getQ().trim().isEmpty();
        if (hasFullTextQuery) {
            MultiMatchQuery multiMatchQuery = MultiMatchQuery.of(mq -> mq
                    .fields("title", "description").fuzziness("AUTO")
                    .query(request.getQ().trim()).fuzziness("AUTO")

            );
            boolQuery.must(Query.of(q -> q.multiMatch(multiMatchQuery)));
        }


        if (request.getMinAge() != null || request.getMaxAge() != null) {
            BoolQuery.Builder ageBoolQuery = new BoolQuery.Builder();

            if (request.getMaxAge() != null) {
                ageBoolQuery.filter(Query.of(q -> q.range(r -> r
                        .field("maxAge")
                        .lte(JsonData.of(request.getMaxAge())))));
            }


            if (request.getMinAge() != null) {
                ageBoolQuery.filter(Query.of(q -> q.range(r -> r
                        .field("minAge")
                        .gte(JsonData.of(request.getMinAge())))));
            }
            boolQuery.filter(Query.of(q -> q.bool(ageBoolQuery.build())));
        }

        // Price range filter
        if (request.getMinPrice() != null || request.getMaxPrice() != null) {
            RangeQuery.Builder priceRange = new RangeQuery.Builder().field("price");
            if (request.getMinPrice() != null) {
                priceRange.gte(JsonData.of(request.getMinPrice()));
            }
            if (request.getMaxPrice() != null) {
                priceRange.lte(JsonData.of(request.getMaxPrice()));
            }
            boolQuery.filter(Query.of(q -> q.range(priceRange.build())));
        }

        // Exact match filters
        if (request.getCategory() != null && !request.getCategory().trim().isEmpty()) {
            boolQuery.filter(Query.of(q -> q.term(t -> t
                    .field("category")
                    .value(request.getCategory().trim()))));
        }

        if (request.getType() != null && !request.getType().trim().isEmpty()) {
            boolQuery.filter(Query.of(q -> q.term(t -> t
                    .field("type")
                    .value(request.getType().trim()))));
        }

        // Date range filter
        if (request.getStartDate() != null && !request.getStartDate().trim().isEmpty()) {
            try {
                // Parse startDate as ISO 8601
                Instant startDate = Instant.parse(request.getStartDate().trim());
                boolQuery.filter(Query.of(q -> q.range(r -> r
                        .field("nextSessionDate")
                        .gte(JsonData.of(startDate)))));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid startDate format, expected ISO 8601 (e.g., 2025-07-20T10:00:00Z)", e);
            }
        }

        // Sorting
        String sortField;
        SortOrder sortOrder;

        if (request.getSort() != null && !request.getSort().trim().isEmpty()) {
            switch (request.getSort().trim().toLowerCase()) {
                case "priceasc":
                    sortField = "price";
                    sortOrder = SortOrder.Asc;
                    break;
                case "pricedesc":
                    sortField = "price";
                    sortOrder = SortOrder.Desc;
                    break;
                case "titleasc":
                    sortField = "title";
                    sortOrder = SortOrder.Asc;
                    break;
                case "titledesc":
                    sortField = "title";
                    sortOrder = SortOrder.Desc;
                    break;
                case "dateasc":
                    sortField = "nextSessionDate";
                    sortOrder = SortOrder.Asc;
                    break;
                case "datedesc":
                    sortField = "nextSessionDate";
                    sortOrder = SortOrder.Desc;
                    break;
                default:

                    sortField = hasFullTextQuery ? "_score" : "nextSessionDate";
                    sortOrder = hasFullTextQuery ? SortOrder.Desc : SortOrder.Asc;
                    break;
            }
        } else {
            // Default sorting
            sortField = hasFullTextQuery ? "_score" : "nextSessionDate";
            sortOrder = hasFullTextQuery ? SortOrder.Desc : SortOrder.Asc;
        }

        // Build search request
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index("courses")
                .query(Query.of(q -> q.bool(boolQuery.build())))
                .sort(sort -> sort.field(f -> f.field(sortField).order(sortOrder)))
                .from(page * size)
                .size(size)
        );

        // Execute search
        SearchResponse<CourseDocument> response;
        System.out.println(searchRequest.toString());
        try {
            response = elasticsearchClient.search(searchRequest, CourseDocument.class);
        } catch (IOException e) {
            throw new IOException("Failed to execute Elasticsearch search: " + e.getMessage(), e);
        }

        // filtering out null sources
        List<CourseDocument> results = response.hits().hits().stream()
                .map(Hit::source)
                .filter(Objects::nonNull) // Skip null sources
                .collect(Collectors.toList());

        return results;
    }
}

