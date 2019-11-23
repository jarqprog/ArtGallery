
### REST api for person domain
    heroku link: https://galleryofart-person.herokuapp.com/

------------------------------
    HEROKU usefull commands:
    
        heroku plugins:install java
        
        heroku ps:scale web=1 --app galleryofart-person
        
        heroku run rails console --app galleryofart-person
        
        heroku logs --tail --app galleryofart-person
        
        mvn heroku:deploy-only