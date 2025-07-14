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

##  2.🐳 Elasticsearch Setup
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
  ```
  
  When Spring Boot starts, this data is **automatically indexed** into Elasticsearch under the `courses` index.
  
  ---
  
  ## ▶️ Running the Spring Boot App
  
  ### Terminal / Command Line
  
  ```bash
  # Windows
  mvnw spring-boot:run
  
  # Or if Maven is globally installed
  mvn spring-boot:run
  ```
  
  ### IDE
  
  You can also run `CourseSearchApplication.java` directly from IntelliJ, VS Code, or Eclipse.
  
  ---
  
  ## 🔍 API: Course Search
  
  ### 🔗 Base Endpoint
  
  ```
  GET http://localhost:8080/api/search
  ```
  
  ### Supported Query Parameters
  
  | Parameter     | Description                         |
  |---------------|-------------------------------------|
  | `q`           | Full-text search on title & description |
  | `minAge`/`maxAge` | Age range filter                 |
  | `minPrice`/`maxPrice` | Price range filter          |
  | `category`    | Filter by course category           |
  | `type`        | Filter by course type               |
  | `startDate`   | Filter by next session date (⚠️ not working yet) |
  | `sort`        | Sort by `pricedesc`, `priceasc`, or `datedesc` |
  | `page`        | Page number (default = 0)           |
  | `size`        | Page size (default = 10)            |
  
  ---
  
  ## 🧪 Example Requests
  
  #### 🔎 Full-text Search (Title & Description)
  ```bash
  http://localhost:8080/api/search?q=math
  ```
  
  #### 🎯 Age Range
  ```bash
  http://localhost:8080/api/search?minAge=6&maxAge=10
  ```
  
  #### 💰 Price Range
  ```bash
  http://localhost:8080/api/search?minPrice=10&maxPrice=50
  ```
  
  #### 🔄 Age + Price Range
  ```bash
  http://localhost:8080/api/search?minAge=6&maxAge=10&minPrice=10&maxPrice=50
  ```
  
  #### 📚 Category Filter
  ```bash
  http://localhost:8080/api/search?category=Science
  ```
  
  #### 🧩 Type Filter
  ```bash
  http://localhost:8080/api/search?type=COURSE
  ```
  
  > ⚠️ Note: Category and Type filters are **case-sensitive**. Use uppercase values (e.g., `COURSE`, `CLUB`).
  
  ---
  
  ### 🔃 Sorting
  
  - **By Price Descending**:
    ```bash
    http://localhost:8080/api/search?sort=pricedesc
    ```
  
  - **By Date Descending**:
    ```bash
    http://localhost:8080/api/search?sort=datedesc
    ```
  
  ---
  
  ### 🧠 Combined Filters Example (Full Search)
  
  ```bash
  http://localhost:8080/api/search?q=Geometry&minAge=6&maxAge=15&minPrice=10&maxPrice=50&category=Math&type=COURSE&sort=pricedesc&page=0&size=10
  ```
  
  ---
  
  ### 🌀 Fuzzy Search (Typos Handled Automatically)
  
  ```bash
  http://localhost:8080/api/search?q=ath
  ```
  
  > Example: `"ath"` matches `"Math"` or `"Path Planning"` due to fuzzy matching.
  
  ---
  
  ## 🐞 Known Issues
  
  - ❌ `startDate` filter currently not working
  - ❌ `sort=title` not yet implemented
  - ⚙️ Autocomplete search feature is not added yet
  
  ---
  
  ## 📦 Project Structure
  
  ```
  ├── config/             # Elasticsearch client config
  ├── controller/         # REST controllers
  ├── model/           # Elasticsearch document (CourseDocument)
  ├── dto/                # SearchRequestDTO
  ├── repository/         # ElasticsearchRepository (optional)
  ├── service/            # SearchService with filters & logic
  ├── resources/
  │   └── sample-courses.json
  
  ```
  ── docker-compose.yml
  ---
  
  ## 👤 Author
  
  **Sanskar Dwivedi**  
  📬 [Github](https://github.com/Sanskar-dwivedi325)
  🛠️ Passionate about backend & search systems
  
  ---
  
  > 📝 *Thank you for reviewing this project. More improvements coming soon!*
  
 






  


  



