# FlapKap Task
## First: 
- clone your project to your machine

## Second:
- open your project in intellij or your preferred ide

## Third:
- Create database on your machine and set the database values in the application.properties

## Fourth:
- Run the application

## Fifth:
Insert the two roles in your database before any calling to any api

INSERT INTO `flapkaptask`.`role` (`id`, `name`) VALUES ('1', 'ROLE_BUYER');

INSERT INTO `flapkaptask`.`role` (`id`, `name`) VALUES ('2', 'ROLE_SELLER');

### example on an endpoint to signup a user
![Sign up](https://github.com/ZeyadSultan/flapKap/blob/main/signUpExample.png)
