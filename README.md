# OnlineShopRestApi
REST API writen with Spring Boot (MVC, JPA, Security) and MySQL database. 
JSON as communication between client and application. 
JWT is used as a way of Authorization users.

## Database Setup

###### Before starting the app please use this commands in you mysql database as root user:

CREATE DATABASE shop;

CREATE USER 'shop_user'@localhost IDENTIFIED BY '123';

GRANT ALL PRIVILEGES ON shop.* TO 'shop_user'@'localhost';

If you want to fill database with test data run database dump from database folder. If you wont do it app still will be working but most Postman request will have to be edited to use actuall public id that will be created.

###### If you get error while running this app please use also this command

SET GLOBAL time_zone = '+3:00'

## How to use the app

###### To use this REST-API you will need a postman.

In PostManCommands folder you have ready to use import files with pre prepared commands.

# HTTP Endpoints:

**HTTP REQUEST TYPE || END-POINT || DECRIPTION**

## Public Endpoints

POST   || http://localhost:8080/users  || Create new user.

GET    || http://localhost:8080/users/login || It returns user publicUserID and Authorization in Headers. They are needed if you want to acces User or Admin Endpoints. 
                                               For Every user that is currently in database passsword is: 123
                                             
GET    || http://localhost:8080/products/{publicProductId} || Get product details on publicProductId.

GET    || http://localhost:8080/products?page=0&limit=10 || Get products details, it's pagable request.

## Admin Endpoints
#### Every request here require to use Authorization Header with value genereted while loging in.
** Admin account: email:"test@admin.com", password:"123"

POST   || http://localhost:8080/products || Create a new product.

PUT    || http://localhost:8080/products/{publicProductId} || Edit product.

GET    || http://localhost:8080/users?page=0&limit=3 || Get users, it's pagable request.

## User Endpoints

#### Every request here require to use Authorization Header with value genereted while loging in.

**Only user with email: "test1@gmail.com", password:"123" publicId:"f2d4330c-afd7-41cb-9286-7346450f59d4" have created orders.
 
GET    || http://localhost:8080/users/{publicUserId} || Get user details on publicUserId.

PUT    || http://localhost:8080/users/{publicUserId} || Edit user on publicUserId.

POST   || http://localhost:8080/users/{publicUserId}/products/{publicProductId} || Add new product to the cart.

GET    || http://localhost:8080/users/{publicUserId}/cart || Get cart with items that it contain.

POST   || http://localhost:8080/users/{publicUserId}/cart/order || Create order for user based on a current cart.

GET    || http://localhost:8080/users/{publicUserId}/orders?page=0&limit=5 || Get all orders created by user, it's pagable request.

GET    || http://localhost:8080/users/{publicUserId}/orders/{publicOrderId} || Get order on publicOrderId if it's created by user that is calling this request.
