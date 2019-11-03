# ArtGallery - REST API v1.2
# ArtGallery - simple web application

Spring Data / MVC / Security project. Java 11 with embedded Tomcat9

To build application (using Maven) type in the command line:

        mvn package -Dspring.profiles.active={one of the spring profile: test/dev/prod}

To run application (using webapp-runner):

        java -Dspring.profiles.active={test/dev/prod} -jar target/dependency/webapp-runner.jar target/*.war

for example, to build and run with development profile:

        mvn package -Dspring.profiles.active=dev
        java -Dspring.profiles.active=dev -jar target/dependency/webapp-runner.jar target/*.war
        

To run with 'prod' (production) Profile you will need MySQL server and create database 'art_gallery' on it
(application's ORM handles DB tables creation and populates some initial data).

Api functionalities at the moment (wip):

     {host}/api/{version}/resource_name/{resourceID}.... <- resource name should be plural, ex.
     
    'http://localhost:8080/api/v1/pictures/1' with GET method will return data of Picture with ID=1

    Some resources which doesn't exists independently require to use 'parent resource', ex. 

    'http://localhost:8080/api/v1/pictures/1/commentaries' with GET method will return all Commentaries
    related to Picture with ID=1:
    [
        {
            id: 1,
            version: 0,
            comment: "This is my first painting",
            userId: 3,
            pictureId: 1
        },
        {
            id: 2,
            version: 0,
            comment: "Do you like it?",
            userId: 3,
            pictureId: 1
        }
    ]
    
    Resources are available in different format:
        - 'thin' - default DTO type, simplified, nested objects as ID numbers (please see example above);
        - 'fat' - where nested objects are fully exposed - theye're haevy, so use it if necessary - to avoid
            triggering multiple requests;
        - 'hateoas' - hateoas like - reources exposed as hyperlinks (not implemented yet);
    
    Output format depends on provided request parameter '?mode=...' (fat/thin/hateoas) used with GET method (it doesn't
    apply to other http methods), ex:
     
    'http://localhost:8080/api/v1/pictures/1/commentaries/1?mode=fat' with GET method will return Commentary
    with ID=1 related to given Picture (ID=1) in the 'fat' format:
    {
        id: 1,
        version: 0,
        comment: "This is my first painting",
        user: {
            id: 3,
            version: 0,
                contact: {
                id: 3,
                version: 0,
                firstName: "Betty",
                lastName: "Sue",
                nickname: "betty80",
                email: "bettys@gmail.com"
                },
            login: "betty80"
        },
        picture: {
            id: 1,
            version: 0,
            title: "Spring",
            path: null,
            author: {
                id: 1,
                version: 0,
                artisticNickname: "betty-artist",
                    contact: {
                        id: 3,
                        version: 0,
                        firstName: "Betty",
                        lastName: "Sue",
                        nickname: "betty80",
                        email: "bettys@gmail.com"
                    }
            },
            user: {
                id: 3,
                version: 0,
                contact: {
                    id: 3,
                    version: 0,
                    firstName: "Betty",
                    lastName: "Sue",
                    nickname: "betty80",
                    email: "bettys@gmail.com"
                },
                login: "betty80"
            }
        }
    }

    ENDPOINTS available at the moment:
    
        /api/v1/pictures
        /api/v1/pictures/commentaries
        /api/v1/users
        /api/v1/contacts
        /api/v1/authors
    
    Handled Http methods at the moment: GET, POST, PUT, DELETE
    
    At the moment API consumes data in JSon format, but handling xml will be added soon.
    
    While triggering POST or PUT method you can provide data in different format in request body as long as data matches
    the specific DTO (interface) types you can find in package com.jarqprog.artGallery.api.domains

    On POST or PUT method nested objects are ignored, so there is no risk to change something accidentally. For example:
        
    on endpoint PUT 'http://......../api/v1/pictures/1/commentaries/1'
    API receives JSon with changed Commentary data and changed related Picture data (Picture is nested object):
    
            {
                id: 1,
                version: 0,
                comment: "COMMENT IS CHANGED",
                picture: {
                    id: 1,
                    version: 0,
                    title: "TITLE IS CHANGED IN PICTURE",
                    path: "some/path",
                },
                pictureId: 1
            }
    
    Result - only Commentary data will be updated. To update Picture, JSon should be send on proper endpoint:
    PUT http://......../api/v1/pictures/1
    
            {
                id: 1,
                version: 0,
                title: "TITLE IS CHANGED IN PICTURE",
                path: "some/path",
                userId: 3,
                authorId: 3
            }
                    
                      
Main concepts (in short):

--- API
* multi-layered CRUD-like application:
    - persistence (SQL - H2/MySQL)
    - dao - using Spring Data (v.5) repositories
    - service
    - controllers, REST Controllers - using Spring MVC (v.5)

* domain models:
    - using immutable Value Objects inside API (package com.jarqprog.artGallery.domain)
    - entities - (for ORM-hibernate purposes) - can be created only from VOs by using factory method,
        they are not immutable, but they haven't any setters,
        using then in DAO and service layer (package com.jarqprog.artGallery.api.domains) 
    - DTO - anemic POJO objects - for serialization/transfer in controller layer - api produces and consumes
        those kind of objects (com.jarqprog.artGallery.api.domains)
        
* step towards distributed architecture and DDD - there are two decoupled domains:
    - personal (related to Users, Contacts, Security,  etc.)
    - artistic (Pictures, Commentaries, Authors, etc.)
    Decoupling is in every layer (dao, service, controller). Also there're no references in models between those domains,
    so Commentary knows its Picture, but doesn't know anything about User (except its login/nick). Picture knows its
    Author, but can see only User's login. User has knowledge related to its Contact, but doesn't know anything about
    Pictures. So it's possible at this moment to divide this api, use different DBs, frameworks, etc.
    Also road has been opened to create DDD aggregates and to move logic there.
    
* security - not implemented yet - will use api-keys. 
    
--- Testing (Spring Test framework & JUnit 5 Jupiter)
* DAO + Service + related components - for me this is Unit (let's name it integration test),
  so I'm not using any Mocks here, putting most effort testing here
* REST Controllers - this layer is tested in isolation, so Mocks are involved here
* small components - (if needed), for example EmailValidation, etc.
    Trying to test behavior, not concrete implementation.

--- Logging
    Using Logback (log files are generated in logs/ and logs-test/ directories)

--- Exception handling
    Exceptions are handled in one place - by dedicated controller annotated with Spring Web MVC's @ControllerAdvice
    After exception is thrown, Controller is creating 'ExceptionInfo' object with unique identifier (UUID), timestamp,
    message, exception data. That object is both - sent to the Client (it's serializable) and write to log. So Client can
    share received error's UUID and detailed info in log can be found.    

 
--- web application
    will be moved to different repository soon (before version 2.0). It is simple application based on Spring MVC,
    Thymeleaf and Spring Security (session based). This is very beginning - at this moment User can register,
    login and see its data. (Putting most efforts to REST API now) 

todo (next iterations):
* exception handling, exception logging - DONE
* add optimistic locking - DONE
* add users identification and authorisation (security), roles, privileges - DONE for web application
* add unit tests for domain (assuming unit is service + dao layer), use in memory database (avoid testing mocks)
* add unit tests for controller (mock service layer, unit is controller behavior only)
* add hyper links (hateoas)
* move web application packages/resources to separated directory
* add api-keys security for REST application

-----
At this moment web application and REST application will be treated separately


REST app:
* add heart domain (likes) with services and controllers
* add friends (so userData could invite someone to friends list)

WEB app:
...





