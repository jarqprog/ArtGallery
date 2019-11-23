
### REST api for person domain
    heroku link: https://galleryofart-arts.herokuapp.com/

------------------------------
    HEROKU usefull commands:
    
        heroku plugins:install java
        
        heroku ps:scale web=1 --app galleryofart-arts
        
        heroku run rails console --app galleryofart-arts
        
        heroku logs --tail --app galleryofart-arts
        
        mvn heroku:deploy-only