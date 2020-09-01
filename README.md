# OnlineShopRestApi
REST API for a shop project using Spring Boot, MVC, JPA and Hibernate. In the future I'll implement Spring Security and Front-End.


In Postman import file you have a ready to use 

What type of json fileds are required to send http request to endpoints you can see in postman via exporting file to 
###### HTTP Endpoints:

**HTTP REQUEST TYPE || END-POINT || DECRIPTION**

POST   || http://localhost:8080/users  ||Create new user. **You will need to send json file, number and email fields have to be unique to create new user. It also create new cart for the user**
GET    || http://localhost:8080/users/{publicUserId} || Get user details on publicUserId.
DELETE || http://localhost:8080/users/{publicUserId} || Delete user on publicUserId. **Currently not working**
PUT    || http://localhost:8080/users/{publicUserId} || Edit user on publicUserId.
GET    || http://localhost:8080/users/{publicUserId}/cart || Get currently used cart that will conatint cart items.
POST   || http://localhost:8080/users/{publicUserId}/cart/order || Create order for user based on current cart. **After that oepration cart will be stored with order and new cart wil be asigned for user to use.**
GET    || http://localhost:8080/users/{publicUserId}/order || Return list of orders that this users created.

POST   || http://localhost:8080/users/{publicUserId}/products/{publicProductId} || **It will create new cart item that is stored in lasted created cart for this user**

POST   || http://localhost:8080/products || Create new product. **You will need to send json file, number and email fields have to be unique to create new user.**
GET    || http://localhost:8080/products/{publicProductId} || Get product details on publicProductId
DELETE || http://localhost:8080/products/{publicProductId} || Delete product. **Currently not working**
PUT    || http://localhost:8080/products/{publicProductId} || Edit product. 
