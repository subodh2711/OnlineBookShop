# Online Book Shop


#Welcome to Online Book Shop
#For running the application you need to run the OnlineBookShopApplication main method
# Application will run in 8080 port
# In the HospitalController there are many api's has been provided
#For aading a book in online shop :
Curl Request :
curl --location --request POST 'http://localhost:8080/ectosense/post' \
--header 'Content-Type: application/json' \
--header 'Content-Type: text/plain' \
--data-raw '{
   "title":"end",
   "author":"test",
   "isbn":"jhsdh",
   "price":200
}'
#For getting the book based on title  :  http://localhost:8080/ectosense/getBooks/title/{title} --- like search supported
#For getting the book based on author :http://localhost:8080/ectosense/getBooks/author/{author} -- like search supported
#For getting the book based on isbn : http://localhost:8080/ectosense/getBooks/isbn/{isbn}
#For getting the title based on the isbn using serach media provided : http://localhost:8080/ectosense/getBooks/searchMedia/jhsdh
#if you want to buy a book : 
Curl Request:
curl --location --request DELETE 'http://localhost:8080/ectosense/buyBook/1' \
--header 'Content-Type: application/json'

To run the Application:
Steps
1.  mvn clean install
2. java -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005 demo-0.0.1-SNAPSHOT.jar




