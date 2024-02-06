# FlapKap Task
### First: 
- clone your project to your machine

### Second:
- open your project in intellij or your preferred ide

### Third:
- Create database on your machine and set the database values in the application.properties

### Fourth:
- Run the application

### Fifth:
- Insert the two roles in your database before any calling to any api

INSERT INTO `flapkaptask`.`role` (`id`, `name`) VALUES ('1', 'ROLE_BUYER');

INSERT INTO `flapkaptask`.`role` (`id`, `name`) VALUES ('2', 'ROLE_SELLER');

### Example on an endpoint to signup a user
![Sign up](https://github.com/ZeyadSultan/flapKap/blob/main/signUpExample.png)

### Example on an endpoint to create a product and the user is a seller
![create product example](https://github.com/ZeyadSultan/flapKap/blob/main/createProductExample.png)

### Example on an endpoint to deposit coins and the user is a buyer
![deposit coins](https://github.com/ZeyadSultan/flapKap/blob/main/depositExample.png)

### Example on an endpoint to buy product and the user is a buyer
![buy product](https://github.com/ZeyadSultan/flapKap/blob/main/buyExample.png)
