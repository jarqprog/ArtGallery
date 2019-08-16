# ArtGallery - REST API
# ArtGallery - simple web application

Spring Data / MVC / Security project. Java 8.

To run this application you will need MySQL server. Postman would be handy for POST, PUT and DELETE requests.

* create database 'art_gallery' on MySQL server (application handles tables creation)
* type in the command line: 'mvn clean install tomcat7:run'

Api functionalities at the moment (wip):

***
PICTURES:
* GET http://host:port/artgallery/api/pictures - to get all pictures, example:
    http://localhost:8080/artgallery/api/pictures
* GET http://host:port/artgallery/api/pictures/{ID} - to get picture having given id
    http://localhost:8080/artgallery/api/pictures/6
* POST http://host:port/artgallery/api/pictures - to add picture, ex. json:
    {
        "title": "some title"
    }
* PUT http://host:port/artgallery/api/pictures/{ID} - to update picture having given id, ex. json:
    {
        "title": "changed title"
    }
* DELETE http://host:port/artgallery/api/pictures/{ID} - to delete picture by id

***
CONTACTS:
* GET http://host:port/artgallery/api/contacts - to get all contacts
* GET http://host:port/artgallery/api/contacts/{ID} - to get contact with given id
* POST http://host:port/artgallery/api/contacts - to add contact, ex. json:
    {
      "firstName":"Nick","lastName":"Smith","nickname":"nicky"
    }
* PUT http://host:port/artgallery/api/contacts/{ID} - to update contact having given id
* DELETE http://host:port/artgallery/api/contacts/{ID} - to delete contact by id

***
COMMENTARIES:
* GET http://host:port/artgallery/api/pictures/commentaries - to get all commentaries
* GET http://host:port/artgallery/api/pictures/{{pictureID}}/commentaries - to get all commentaries by picture ID
* GET http://host:port/artgallery/api/pictures/{pictureID}/commentaries/{commentaryID} - to get commentary having given id
    and related to given picture (pictureID)
* GET http://host:port/artgallery/api/pictures/commentaries/{commentaryID} - to get commentary having given id
* POST http://host:port/artgallery/api/pictures/{pictureID}/commentaries/- to add commentary to picture having given pictureID, ex. json:
    {
      "content":"I like it!"
    }
* PUT http://host:port/artgallery/api/pictures/{pictureID}/commentaries/{commentaryID} - to update commentary having given commentaryID
    and related to given picture (pictureID), example:

    http://localhost:8080/artgallery/api/pictures/6/commentaries/7
    {
      "content":"I hate it!"
    }
* DELETE http://host:port/artgallery/api/pictures/{pictureID}/commentaries/{commentaryID} - to delete commentary having given commentaryID
    and related to given picture (pictureID)
* DELETE http://host:port/artgallery/api/pictures/commentaries/{commentaryID} - to delete commentary having given commentaryID

***
USERS:
* GET http://host:port/artgallery/api/users - to get all users
* GET http://host:port/artgallery/api/users/{ID} - to get user with given id
* POST http://host:port/artgallery/api/users - to add user, ex. json:
* PUT http://host:port/artgallery/api/users/{ID} - to update user having given id
* DELETE http://host:port/artgallery/api/users/{ID} - to delete user by id


todo (next iterations):
* add service and controllers for commentary domain - DONE
* remove interfaces with constants, add 'supplier' class which stores and shares given configuration (to encapsulate parameters, remove globals) - DONE
* add unit and integration tests
* exception handling, exception logging - DONE
* add optimistic locking - DONE
* add service and controllers for author domain
* add service and controllers for user domain - DONE
* add heart domain (likes) with services and controllers
* add friends (so user could invite someone to friends list)
* separate domain (entities, DTOs) from framework implementation details - DONE
* add users identification and authorisation (security), roles, privileges - DONE
* add profiles
* add user to picture and commentary methods (services, controllers) - DONE
* add hyper links
* add simple views (use Thymeleaf) - DONE
* -- at this moment REST API will be finished
