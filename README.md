# APIProjectForProduct


To compile and run this API service, you will need Apache Maven.

Install Apache Maven:
https://maven.apache.org/install.html

Once you clone this repository, you will need Apache Maven to build the POM file here.

Please ensure your `pom.xml` has the same `<java-version>xx</java-version` as the java version you are currently running. By default this will be set to 11. Tested with java 8, 11, and 18. 

You will then want to enter the project’s main directory and run a `mvn clean install` followed by `chmod +x mvnw` to make the maven running file executable. Then you will want to run `./mvnw clean spring-boot:run` to run the project(this will run on a server with Apache Tomcat). Once this project is running you will have an API service running at `localhost:8080` (if Tomcat is running on the default port).

The endpoints we have available are:
- GET /v1/products
- POST /v1/products

Your Content-Type must be application/json or you won’t be able to perform a POST.

When performing a POST you will want an input that looks like this

```
{
    "name": "Red Shirt", 
    "description": "Red hugo boss shirt", 
    "brand": "Hugo Boss",
    "tags": [ "red", "shirt", "slim fit" ], 
    "category": "apparel"
} 
```

If performed correctly, your response should resemble:

```
{
        "id": "a0a6ee2b-603d-4f88-9530-c9041350e138",
        "name": "Red Shirt",
        "description": "Red hugo boss shirt",
        "brand": "Hugo Boss",
        "tags": [
            "red",
            "shirt",
            "slim fit"
        ],
        "category": "apparel",
        "created_at": "2022-05-23T19:02:28Z"
}
```

The following query parameters may be added to your GET request
* category (Search of products by category, using an exact match on the text of the ‘category’ field)
* maxEntries (this is the maximum size a page should be, default is 10)
* Page (which page you would like to look at, default is page 1)

I have included a folder in the project called `PostmanRequests`. If you would like easy access to some basic tests I used, you may import these in to your own Postman environment. If you need to download Postman, you can do so here

https://www.postman.com/downloads/


Additionally, inside of the src folder, there is a unit test for the ProductController.


If you would like to examine your in memory H2 database while the project is running on localhost, you may do so by accessing the following link:

http://localhost:8080/h2-console

Driver Class: org.h2.Driver\
JDBC URL: jdbc:h2:mem:testdb\
User Name: centric\
Password:\
(The password field should remain empty)

