# ArtGallery
Spring Data / MVC / Security project. Java 8.

To run this application you will need MySQL server. Postman would be handy for POST, PUT and DELETE requests.

* create database 'art_gallery' on MySQL server (application handles tables creation)
* type in the command line: 'mvn clean install tomcat7:run'

Api functionalities at the moment (wip):

***
PICTURES:
* GET http://host:port/artgallery/api/pictures - to see all pictures data
* GET http://host:port/artgallery/api/pictures/{ID} - to see picture with given id
* POST http://host:port/artgallery/api/pictures - to add picture, ex. json:
    {
        "title": "some title"
    }
* PUT http://host:port/artgallery/api/pictures/{ID} - to update picture with given id, ex. json:
    {
        "title": "changed title"
    }
* DELETE http://host:port/artgallery/api/pictures/{ID} - to delete picture by id


***
todo (next iterations):
* add service and controllers for commentary domain
* remove interfaces with constants, add 'supplier' class which stores and shares given configuration (to encapsulate parameters, remove globals)
* add unit and integration tests
* exception handling, exception logging
* users identification and authorisation (security)
* -- at this moment API will be finished
* add frontend
