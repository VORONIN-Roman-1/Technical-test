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

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.
```
    GET /api/users?userName={userName}
    
    POST /api/users    
```
Create a user:
```
PUT /api/users
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
Location header: http://localhost:8080/api/users?userName=Marjorie
```

Retrieve a user:

```
http://localhost:8080/api/users?userName=Emmanuel

Response: HTTP 200
```