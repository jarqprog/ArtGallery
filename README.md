# ArtGallery
Spring MVC project

To run application:
* create database 'art_gallery' on MySQL server
* type in the command line: 'mvn clean install tomcat7:run'

Api functionalities (wip):

* GET http://host:port/artgallery/api/pictures - to see all pictures data
* POST http://host:port/artgallery/api/pictures - to add picture, ex. json:
    {
        "title": "some title"
    }

* DELETE http://host:port/artgallery/api/pictures/{ID} - to delete picture by id
