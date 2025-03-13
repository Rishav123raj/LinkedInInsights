```markdown
# LinkedIn Insights Microservice

🚀 A Spring Boot microservice to scrape and store LinkedIn Page insights, posts, and comments with AI-powered summaries!

## 📌 Features

- ✅ Scrapes LinkedIn page details (name, followers, industry, website, profile picture).
- ✅ Fetches recent posts and comments from a LinkedIn page.
- ✅ Stores data in MySQL (or MongoDB) with JPA repositories.
- ✅ Filters pages by follower count, name, and industry.
- ✅ AI-powered summary generation using OpenAI API.
- ✅ Implements Redis caching for fast responses.
- ✅ Uploads profile pictures to AWS S3 for storage.
- ✅ Exposes REST APIs with pagination and filtering.
- ✅ Dockerized for seamless deployment.

## 🚀 Getting Started

### 1️⃣ Prerequisites

Ensure you have the following installed:

- Java 17 (or higher)
- Maven
- MySQL (or MongoDB)
- Redis (for caching)
- Docker (optional, for containerization)

### 2️⃣ Clone the Repository

```sh
git clone https://github.com/your-username/linkedin-insights.git
cd linkedin-insights
```

### 3️⃣ Configure Database and API Keys

Edit `src/main/resources/application.properties` and update:

```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/linkedin_insights
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

# Redis Configuration
spring.cache.type=redis
spring.redis.host=localhost
spring.redis.port=6379

# OpenAI API Key
openai.api.key=your_openai_api_key

# AWS S3 Configuration
aws.s3.bucket-name=your-bucket-name
```

Replace `your_password`, `your_openai_api_key`, and `your-bucket-name` with your actual credentials.

### 4️⃣ Build and Run the Application

```sh
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### 5️⃣ Test APIs using Postman

Import `postman_collection.json` into Postman and use the following endpoints.

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | `/api/pages/{pageId}` | Fetch LinkedIn Page details (scrape if not in DB) |
| GET    | `/api/pages/search?minFollowers=50000` | Search pages by follower count, name, industry |
| GET    | `/api/pages/{pageId}/summary` | AI-generated summary of a LinkedIn page |
| GET    | `/api/posts/{pageId}` | Fetch recent posts from a LinkedIn page |
| GET    | `/api/comments/{postId}` | Fetch comments on a post |

## 📦 Running with Docker

To containerize the application and run it using Docker, use:

```sh
# Build the Docker image
docker build -t linkedin-insights .

# Run the Docker container
docker run -p 8080:8080 linkedin-insights
```

## 🌍 Deployment Guide

### Deploying to AWS EC2

1. Launch an EC2 instance (Ubuntu 20.04 or higher).
2. Install Java & Docker on EC2:

```sh
sudo apt update
sudo apt install openjdk-17-jdk
sudo apt install docker.io
```

3. Transfer Docker Image to EC2:

```sh
scp linkedin-insights.jar ubuntu@your-ec2-ip:/home/ubuntu/
```

4. Run Application on EC2:

```sh
java -jar linkedin-insights.jar
```

## 🎯 Technologies Used

- Spring Boot 3.x
- Spring Data JPA (MySQL)
- Spring Web (REST APIs)
- Redis (Caching)
- AWS S3 (Image Storage)
- OpenAI API (AI Summaries)
- Jsoup (Web Scraping)
- Docker (Containerization)

## 📌 To-Do List (Future Enhancements)

- ✅ Add OAuth authentication for secured API access.
- ✅ Deploy to Kubernetes with Helm charts.
- ✅ Implement GraphQL API for better querying.
- ✅ Add Unit & Integration Tests for API endpoints.

## 📝 License

This project is licensed under the MIT License - feel free to use, modify, and distribute it.

## 🤝 Contributing

We welcome contributions! 🎉 If you'd like to contribute:

1. Fork the repo.
2. Create a new branch (`git checkout -b feature-xyz`).
3. Commit your changes (`git commit -m "Added feature XYZ"`).
4. Push to the branch (`git push origin feature-xyz`).
5. Create a Pull Request.

## 📞 Contact & Support

For issues, suggestions, or questions, contact:

- Email: your-email@example.com
```
