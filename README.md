# polarcapeassignmet
# Graduate Assigment Project

This is a project made as an assignment as part of a candidate selection process by an employer.

#Requirements
-IntelliJ
-Postman
-Java version 8

#Bulit with Maven
#Spring dependencies used (with Spring Intilizer) WEB, H2 and SPRING DATA JPA

#Instructions console

After you have unzipped the assignment.rar file you can go ahead and right-click on the assignment folder and select “Open folder as IntelliJ IDEA Project”.

On the left-hand side in intelliJ open assignment>src>main>java and double click on the AssigmentApplication class. This will open the main class of the project. You can run the project by right clicking anywhere in the class and select “Run ‘AssigmentAppl…main’”. 

In the console you will see all the books already created sorted in chronological order and below it a message prompting you to enter the first letter of the last name of an Author. I encourage you to enter “B” or any other first letter from a last name of an author  that you can see from the list above. 

After that you will get a message prompting you to enter a first name of an author to get all the books on record that were released the decade the author was born. For this I encourage you enter “Rumena”.

And finally all authors that have written more than 3 books will be listed as well as the oldest and newest book on record. 

#Instructions Postman

Open postman, either as an chrome extension or as an app. 
Here you can get all books by sending a GET request on http://localhost:8080/books
DELETE a book by ISBN on http://localhost:8080/deleteBook/{id} you can verify this by sending a GET request. 
POST an E-book on http://localhost:8080/ebook (you should exclude the ISBN in the POST body)
POST a Print Copy book  on http://localhost:8080/printCopy (you should exclude the ISBN in the POST body)



