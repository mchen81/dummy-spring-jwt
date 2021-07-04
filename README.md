# Description
This exmple project demostrates how spring boot and spring security handles user authentication with JWT.  

# Installation

## Docker
Once [Docker](https://www.docker.com/) is installed, then run-mysql.sh, this script will run a container of mysql8 and gets username/password set.   

## Config
The spring boot configuration is in src/main/resources/application.yml, you can set up the database connection here.   
You can also change JWT secret, issuer and duration in this file.   

# Endpoints
## Usage
Anyone can make a GET and POST to localhost:8080/hello.   
Anyone can make POST to /account/signup and /account/login.  
Only authenticated request can GET /secured

# Example 

## Register an account
<img width="624" alt="Screen Shot 2021-07-04 at 8 11 09 PM" src="https://user-images.githubusercontent.com/54089282/124384467-faf60680-dd03-11eb-9d12-7a25ad7498f5.png">

## Sign in
<img width="1243" alt="Screen Shot 2021-07-04 at 8 11 40 PM" src="https://user-images.githubusercontent.com/54089282/124384489-0c3f1300-dd04-11eb-905c-6d74bfa9d3b8.png">

## Make a request with JWT

<img width="1234" alt="Screen Shot 2021-07-04 at 8 12 24 PM" src="https://user-images.githubusercontent.com/54089282/124384510-27118780-dd04-11eb-8a4b-f3c060519590.png">
