# ArtGallery - REST API
# ArtGallery - simple web application

Spring Data / MVC / Security project. Java 9 with embedded Tomcat7

Postman would be handy for POST, PUT and DELETE requests.

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

Put ID number after resource to get single data, ex.
    
    /artgallery/api/{light or heavy}/pictures/1 <-- (with http GET method) to get Picture having ID=1
    
    /artgallery/api/{light or heavy}/pictures/1/commentaries/2 <-- (with http GET method) to get Commentary with ID=2
    related to Picture with ID=1 

URL without ID will return collection of objects

Main concepts (in short):
    //todo
    API - simple layered architecture (dao / services / controllers).
    Entities  

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





