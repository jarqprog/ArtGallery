# ArtGallery - REST API
# ArtGallery - simple web application

Spring Data / MVC / Security project. Java 9 with embedded Tomcat7

To run application (using Maven) type in the command line: 'mvn clean install tomcat7:run -Dspring.profiles.active=dev'
Application will start with 'dev' Profile.

To run with 'prod' (production) Profile you will need MySQL server and create database 'art_gallery' on it
(application's ORM handles DB tables creation and populates some initial data).

Api functionalities at the moment (wip):

    /artgallery/api/light/.... <- to get simplified resources (without nested resources), ex.

        'http://localhost:8080/artgallery/api/light/pictures/commentaries' with GET method will return:
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

    /artgallery/api/heavy/.... <- to get full resources (with nested resources, to avoid multiple request), ex.
 
        http://localhost:8080/artgallery/api/heavy/pictures/1/commentaries/1 with GET method will return:

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
        
***
ENDPOINTS available at the moment:

    /artgallery/api/{light or heavy}/pictures
    /artgallery/api/{light or heavy}/pictures/commentaries
    /artgallery/api/{light or heavy}/users
    /artgallery/api/{light or heavy}/contacts

Handled Http methods at the moment: GET, POST, PUT, DELETE

Put ID number after resource to get single data, ex.
    
    /artgallery/api/{light or heavy}/pictures/1 <-- (with http GET method) to get Picture having ID=1
    
    /artgallery/api/{light or heavy}/pictures/1/commentaries/2 <-- (with http GET method) to get Commentary with ID=2
    related to Picture with ID=1 

URL without ID will return collection of objects

Main concepts (in short):
* typical multi-layered application (model - dao - <entities> - service - <DTOs> - controller - view)

* persistence - SQL Databases (MySQL - production, H2 - development, tests).
* model/domain objects - anemic/POJO-like (logic is handled by services)
    - entities - ORM's (Hibernate) representation of models
    - DTOs (Light/Heavy) - transfer representation of models, entities are not visible outside service layer
    - todo - HATEOAS-like representation of models

--- API (CRUD-like)
* DAO - using Spring Data (v.5) repositories
* Service layer - decided to put logic here, so it's the most heavy layer (planning to change architecture in the version 3.0)
* REST Controllers - using Spring MVC (v.5)
    
    Optimistic locking mechanism is used on entities;
    Using mostly unidirectional relations (in ORM) for better efficiency;
    Security with Tokens will be used (todo);
    At the moment only JSons are accepted (XML handling will be added);
    On POST or PUT (or PATCH - in the future) method nested objects (heavy DTOs) are ignored, so there is no risk
    to change something accidentally. For example:
        
        on endpoint PUT http://......../artgallery/api/heavy/pictures/1/commentaries/1
        API receive JSon with changed Commentary data and changed related Picture data (Picture is nested object):
        
                {
                    id: 1,
                    version: 0,
                    comment: "COMMENT IS CHANGED",
                    picture: {
                        id: 1,
                        version: 0,
                        title: "TITLE IS CHANGED IN PICTURE",
                        path: null,
                    }
                }
        
        Result - only Commentary data will be updated. To update Picture, JSon should be send on proper endpoint:
        PUT http://......../artgallery/api/heavy/pictures/1
        
                {
                    id: 1,
                    version: 0,
                    title: "TITLE IS CHANGED IN PICTURE",
                    path: null,
                }
    
      

--- Web application
    Using Spring MVC (v.5) and Thymeleaf. At the moment Web App is within the same monolith as API and there is coupling
    which I'm planning to remove (version 2.0) by dividing both applications.
    Web application is protected by Spring Security (v.5) basic authorisation mechanism (login-form, sessions)
    
--- Testing-API (Spring Test framework & JUnit 5 Jupiter)
* DAO + Service + related components - for me this is Unit (let's name it integration test),
  so I'm not using any Mocks here, putting most effort here
* REST Controllers - this layer is tested in isolation, so Mocks are involved here
* small components (if needed), for example EmailValidation, etc.

--- Testing-Web App (Spring Test framework & JUnit 5 Jupiter)
* Controllers - in isolation, using mocks
* small components - in isolation, using mocks

--- Loggin
    Using Logback (log files are generated in logs/ and logs-test/ directories)

--- Exception handling
    Exceptions are handled in one place - by dedicated controller annotated with Spring Web MVC's @ControllerAdvice
    After exception is thrown, Controller is creating 'ExceptionInfo' object with unique identifier (UUID), timestamp,
    message, exception data. That object is both - sent to the Client (it's serializable) and write to log. So Client can
    share received error's UUID and detailed info in log can be found.    
    



todo (next iterations):
* add service and controllers for commentary domain - DONE
* remove interfaces with constants, add 'supplier' class which stores and shares given configuration (to encapsulate parameters, remove globals) - DONE
* exception handling, exception logging - DONE
* add optimistic locking - DONE
* add service and controllers for author domain
* add service and controllers for user domain - DONE
* add heart domain (likes) with services and controllers
* add friends (so user could invite someone to friends list)
* separate domain (entities, DTOs) from framework implementation details - DONE
* add users identification and authorisation (security), roles, privileges - DONE for web application
* add profiles (dev / test / production) - DONE
* add unit tests for domain (assuming unit is service + dao layer), use in memory database (avoid testing mocks)
* add unit tests for controller (mock service layer, unit is controller behavior only)
* add user to picture and commentary methods (services, controllers) - DONE
* add simple views (use Thymeleaf) - DONE
* add hyper links (hateoas)
* move web application packages/resources to separated directory
* add api-keys security for REST application

-----
At this moment web application and REST application will be treated separately


REST app:
...

WEB app:
...





