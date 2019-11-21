
### HEROKU deployment:

heroku plugins:install java
heroku deploy:jar target/dependency/webapp-runner.jar --app galleryofart-person

heroku ps:scale web=0 --app galleryofart-person

heroku run rails console --app galleryofart-person

heroku logs --tail --app galleryofart-person