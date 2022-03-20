# Tecnical test REST CRUD API with Spring Boot, H2, JPA and Hibernate

## Steps to Setup

**1. Clone the application**

```bash
https://github.com/VORONIN-Roman-1/Technical-test.git
```

**2. Build and run the app using maven**

```bash
mvn package
java -jar target/Technical-test-0.0.1-SNAPSHOT.jar

```

Alternatively, you can run the app without packaging

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

**3. Build documentation (JavaDoc)**

```bash
mvn javadoc:javadoc
```

## Explore Rest APIs

The app defines the following CRUD APIs.

```
    GET /api/users/{userName}
    
    POST /api/users    
```

Create a user:

```
POST /api/users
Accept: application/json
Content-Type: application/json

{
    "userName": "Marjorie",
    "birthDate": "2004-03-14",
    "country": "France",
    "phoneNumber": "+33 0657454545",
    "gender": "FEMALE"
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/api/users/Marjorie
```

Retrieve a user:

```
GET /api/users/Emmanuel

Response: HTTP 200
```
