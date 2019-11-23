
### Art Gallery web application
    heroku link: https://galleryofart-web.herokuapp.com/

------------------------------
    HEROKU usefull commands:
    
        heroku plugins:install java
        
        heroku ps:scale web=1 --app galleryofart-web
        
        heroku run rails console --app galleryofart-web
        
        heroku logs --tail --app galleryofart-web
        
        mvn heroku:deploy-only