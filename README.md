# educom-zorgApp
Dit is een private repository voor Educom trainees om een groepsopdracht te doen.
/
/
Hierbij wordt een zorg applicatie gebouwd gebaseerd op [deze opdracht](https://e-learning.educom.nu/cases/ZAPP/intro)

## Installatie Backend

1. Install java JDK (version 18 and up)
- Download the latest version JDK from https://www.oracle.com/java/technologies/downloads/
- Check if everything is working by typing ```java --version``` in cmd
- Open the [Environment variabeles](https://e-learning.educom.nu/elaborations/advanced-programming/environments#environments)
  (searching "environment" in the home screen should work for Windows)
- Find "JAVA_HOME" in system variables (not user variables) and click Edit _-or-_ create a new variabele and call it "JAVA_HOME"
- Set the value to the folder of the installed java, for example ```C:\Program Files\Java\jdk-22```

2. Download Maven - https://maven.apache.org/download.cgi

3. Unzip Maven into an appropriate folder (e.g. ```C:\Program Files```)

4. Add Maven path to Environment variables
- Edit [Environment variabeles](https://e-learning.educom.nu/elaborations/advanced-programming/environments#environments) 
  (searching "environment" in the home screen should work for Windows)
- Find "Path" in system variables (not user variables) and click Edit
- Click New to add variable to Path (e.g. ```C:\Program Files\apache-maven-3.9.6\bin```)
- check if everything is working by typing ```mvn --version``` in *new* cmd window

5. Add the Database user
- In phpmyadmin create a new user called "zapp-admin" and give it a strong password and the following rights: 
  * `Data.*`, 
  * `Structuur.*` 
  * `SHOW_DATABASE` 
  * `LOCK_TABLES` 
- Create a new [Environment variabele](https://e-learning.educom.nu/elaborations/advanced-programming/environments#environments), give it as name `MYSQL_USER` and as value "zapp-admin"
- Create another environment variable with name `MYSQL_PASSWORD` and as value the password given to the database user in the previous step.

- In phpmyadmin create another new user called "zapp-user" and give it a strong password and the following rights: 
  * `Data.*` 
- Create a new Environment variabele, give it as name `ZAPP_USER` and as value "zapp-user"
- Create another environment variable with name `ZAPP_PASSWORD` and as value the password given to the database user in the previous step.
6. In a **new** cmd (or in VS cmd or Power Shell) go to the backend folder (command ```cd backend```) and run command ```mvn install``` \
This should let Maven install all requirements and put everything into a .JAR file

7. To create the database and give it a test fill run ```java -jar .\target\zapp-1.jar TestDbMaker```

8. Test the installation bij running the Jar file to start Springboot \
e.g. ```java -jar .\target\zapp-1.jar```
- Go to http://localhost:8080 to see "Hello Wereld"
- Go to http://localhost:8080/customers to see the customers

## Installatie Frontend

1. Install `nodejs` (version 20.12 or up) from this site https://nodejs.org/en/download
2. Install `VITE` with the command ```npm install -g vite```
3. In cmd (or in VS cmd or Power Shell) go to the frontend folder command ```cd frontend``` 
4. Run command ```npm update```
5. Test de installation bij running ```npm run dev``` en kijk of de applicatie start op http://localhost:5174/ of enter ```o``` in de cmd

## Runnen van de Backend

1. In cmd (or in VS cmd or Power Shell) go to the backend folder: command ```cd backend``` 
2. Run command ```java -jar .\target\zapp-1.jar```

## Runnen van de Frontend

1. In cmd (or in VS cmd or Power Shell) go to the frontend folder: command ```cd frontend``` 
2. Run command ```npm run dev```
3. go to http://localhost:5174/ of enter ```o``` in de cmd

