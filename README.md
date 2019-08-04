# ArtGallery
Spring Data / MVC project.

To run application:
* create database 'art_gallery' on MySQL server (application handles tables creation)
* type in the command line: 'mvn clean install tomcat7:run'

Api functionalities (wip):

***
PICTURES:
* GET http://host:port/artgallery/api/pictures - to see all pictures data
* GET http://host:port/artgallery/api/pictures/{ID} - to see picture with given id
* POST http://host:port/artgallery/api/pictures - to add picture, ex. json:
    {
        "title": "some title"
    }
* DELETE http://host:port/artgallery/api/pictures/{ID} - to delete picture by id


***
todo (next iterations):
* add service and controllers for commentary domain
* add unit and integration tests
* exception handling, exception logging
* users identification and authorisation (security)
* -- at this moment API will be finished
* add frontend
