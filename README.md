# Kill-Switch-rollback
🚀 Feature Flag Service – Java &amp; Spring Boot  A backend Feature Flag (Feature Toggle) Service built with Java and Spring Boot to enable or disable features dynamically without redeployment. Supports environment-based flags, percentage rollouts, Redis caching, and audit logging, simulating real-world production systems.  

🚀 Feature Flag Service – Java & Spring Boot

A production-ready Feature Flag (Feature Toggle) Service built using Java & Spring Boot, enabling applications to turn features ON/OFF dynamically without redeploying code.
This project simulates how real-world backend systems manage safe deployments and gradual rollouts.

✨ What is a Feature Flag?

Feature flags allow teams to:

Release features gradually

Disable broken features instantly

Test features in production safely

Run experiments (A/B testing)

This service centralizes feature control via REST APIs.

🔥 Key Features

✅ Enable / Disable features dynamically

🌍 Environment-based flags (dev, stage, prod)

📊 Percentage-based rollout (e.g. 10%, 50%, 100%)

⚡ Redis caching for ultra-fast feature checks

🗄️ Database-backed persistence

🧾 Audit logging for feature changes

🔁 Cache fallback handling

❤️ Health check endpoint

🛠️ Tech Stack
Layer	Technology
Backend	Java, Spring Boot
API	REST
Database	MySQL
Cache	Redis
ORM	Spring Data JPA
Build Tool	Maven
Testing	Postman
📐 Project Architecture (High Level)
Client
  ↓
Controller (REST APIs)
  ↓
Service Layer (Business Logic)
  ↓
Cache Layer (Redis)
  ↓
Repository Layer (JPA)
  ↓
Database (MySQL)


Redis is checked first for fast reads, with DB fallback if cache is missing.

📂 API Endpoints
Health Check
GET /api/features/ping

Check Feature Status
GET /api/features/{featureKey}?env=prod

Create / Update Feature Flag
POST /api/features

Update Rollout Percentage
PUT /api/features/rollout

🧪 Sample Response
{
  "featureKey": "new_ui",
  "enabled": true,
  "rolloutPercentage": 50,
  "env": "prod"
}

🚀 Getting Started
Prerequisites

Java 17+

Maven

MySQL

Redis

Run Locally
git clone https://github.com/your-username/feature-flag-service.git
cd feature-flag-service
mvn spring-boot:run

🎯 Why This Project Matters

This project demonstrates:

Real-world backend problem solving

Clean layered architecture

Caching strategy with Redis

Scalable API design

Production-style feature rollout logic

It reflects patterns used by large-scale systems like Netflix, Amazon, and Flipkart.

📌 Future Enhancements

🔐 Role-based access control

📈 Metrics & monitoring

🧪 A/B testing support

🌐 Multi-tenant support

📊 Admin dashboard

👨‍💻 Author

Aryan Sharma
Backend Developer | Java | Spring Boot
