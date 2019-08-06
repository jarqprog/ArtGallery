# ArtGallery - REST API
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

todo (next iterations):
* add service and controllers for commentary domain - DONE
* remove interfaces with constants, add 'supplier' class which stores and shares given configuration (to encapsulate parameters, remove globals) - DONE
* add unit and integration tests
* exception handling, exception logging - WIP
* add service and controllers for author domain
* add service and controllers for user domain
* add users identification and authorisation (security), roles, privileges
* add profiles
* add user to picture and commentary methods (services, controllers)
* -- at this moment API will be finished
* add simple frontend

todo (future)
* add better frontend
* add auctions and online shop