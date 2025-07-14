# 📚 Spring Boot + Elasticsearch Course Search App

This project is a simple Spring Boot application that:

- Indexes sample "course" data into Elasticsearch.
- Provides a `/api/search` endpoint with filters, sorting, and pagination.
- Uses the official **Elasticsearch Java Client (8.x)**.
- (Bonus Ready) fuzzy matching Implemented.

---

## 🚀 Getting Started

### 🛠 Prerequisites

- Java 17+
- Maven
- Docker + Docker Compose
- Elasticsearch 8.x (via Docker)

---

## 🐳 Elasticsearch Setup

### 1. Start Elasticsearch with Docker

In the project root, run:

```bash
docker compose up -d

#verify Elasticsearch is running
curl http://localhost:9200

#you can see sample data in sample-courses.json placed in src/main/resourses it looks like,

{
  "id": "1",
  "title": "Math Magic",
  "description": "Fun math course for young learners.",
  "category": "Math",
  "type": "COURSE",
  "gradeRange": "1st–3rd",
  "minAge": 6,
  "maxAge": 8,
  "price": 19.99,
  "nextSessionDate": "2025-07-20T10:00:00Z"
}

# now run spring boot
##▶️ Using terminal/command line:
###On Windows, use:

mvnw spring-boot:run

#OR if you have Maven installed globally:

mvn spring-boot:run

# OR run directly from ide


#once spring boot started you can start testing using browser or curl with given endpoints below

#for title and description
http://localhost:8080/api/search?q=math

#age range only
http://localhost:8080/api/search?minAge=6&maxAge=10

#price range only 
http://localhost:8080/api/search?minPrice=10&maxPrice=50

#age and price range both
http://localhost:8080/api/search?minAge=6&maxAge=10&minPrice=10&maxPrice=50

#category Filter
http://localhost:8080/api/search?category=Science

#type Filter
http://localhost:8080/api/search?type=COURSE

## WARNING in type filter there is an issue of small and capital letter search capital always in category also

## Sorting 
  #by price
  http://localhost:8080/api/search?sort=pricedesc
  
  #by date
  http://localhost:8080/api/search?sort=datedesc


# Full Search
  http://localhost:8080/api/search?q=Geometry&minAge=6&maxAge=15&minPrice=10&maxPrice=50&category=Math&type=COURSE&sort=pricedesc&page=0&size=10


# Fuzziness search
  http://localhost:8080/api/search?q=ath



## issues 
### there is an issue in startdate searching and title based sorting 
#### i will work on it and in autocomplete search


@author
Sanskar Dwivedi
#Thankyou
  


  



