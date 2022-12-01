# MagazinUniversal

This application works as an online shop. We have different entities, such as clients, products, orders. It is implemented with the help of a relational database. This application contains all the actions and righs that an administrator would have.

## Requirements

Running program requires an IDE for Java development.
I personally use IntelliJ IDEA Community 2021.2.3

```bash
java --version
java 11.0.12 2021-07-20 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.12+8-LTS-237)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.12+8-LTS-237, mixed mode)
```

MySQL server is needed for the implementation of the database.
The version of the server I use is 8.0.27 (MySQL Community Server - GPL)
I personally use MySQL Workbench for the implementation of tables and relation.  
Also, we need a .jar fille that will manage our connectoin between the Java application and the data base.
I recommend using: mysql-connector-java-8:0:28.jar 
as external library

## Usage

The application opens with the welcoming screen of our online universal store. 
![image](https://user-images.githubusercontent.com/69772634/205094128-052a6501-5b5b-4eed-9e04-7f4a651828d2.png)

The user can change the setting of the switch button to bavigate further into the application. Let's say we decide on "Detalii Clenti" (Details of the clients) further. Afterwards, some activities can be done, such as:  
  Show all the clients  
  Search after ID  
  Modify after ID  
  Delete after ID  
  Create client
  ![image](https://user-images.githubusercontent.com/69772634/205094514-f28d0c74-cf2b-4ed1-b121-7d85036ab237.png)
The image above shows all the clients that are stored in our relational database  

Similar actions can be done on other entities such as products and orders.  
Another functionality that my application has is that the user can print a receipt of one of the orders already existent in the database. The output file is a .ttxt file that ocntians details about the given order.

## Further development
The first thing I would develop further would be the user interface to make it friendlier and easier to use. Another thing that can be implemented would be some different use-cases. Instead of showing all the activities that an administrator can do, the application could show different rights for different types of users (client, employee)
