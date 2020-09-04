# OnlineShopRestApi
REST API for a shop project using Spring (Boot, MVC, JPA) MySQL database and Hibernate. In the future I'll implement Spring Security and Front-End. Please note that this project is still in early development stage.

###### Before starting the app please use this commands in you mysql database as root user:

CREATE DATABASE shop;

CREATE USER 'shop_user'@localhost IDENTIFIED BY '123';

GRANT ALL PRIVILEGES ON shop.* TO 'shop_user'@'localhost';

Now run all sql files connected in database folder for shop database.

###### If you get error while running this app please use also this command

SET GLOBAL time_zone = '+3:00'


###### To use this REST-API you will need a postman.

In PostManCommands folder you have ready to use import file with pre prepared commands.

## HTTP Endpoints:

**HTTP REQUEST TYPE || END-POINT || DECRIPTION**

POST   || http://localhost:8080/users  ||Create new user. **You will need to send json file, number(9 signs) and email fields have to be unique to create new user. It also create new cart for the user**

GET    || http://localhost:8080/users/{publicUserId} || Get user details on publicUserId.

PUT    || http://localhost:8080/users/{publicUserId} || Edit user on publicUserId.

GET    || http://localhost:8080/users/{publicUserId}/cart || Get the currently used cart that will content cart items.

POST   || http://localhost:8080/users/{publicUserId}/cart/order || Create order for user based on a current cart. **After that operation cart will be stored with order and new cart wil be assigned for user to use.**

POST   || http://localhost:8080/users/{publicUserId}/products/{publicProductId} || **It will create new cart item that is stored in lasted created cart for this user**

POST   || http://localhost:8080/products || Create a new product. **You will need to send json file, number and email fields have to be unique to create new user.**

GET    || http://localhost:8080/products/{publicProductId} || Get product details on publicProductId

PUT    || http://localhost:8080/products/{publicProductId} || Edit product. 
