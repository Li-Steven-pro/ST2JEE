# M1 - JEE Project : Interns Supervision Solution

-Requirement : 
-**WAMP**
-**Apache NetBeans IDE**
-**Server : Glassfish 5.1.0 with jdk1.8.0_271**
-**MySQL 5.7.28**

-DL wamp depuis https://www.wampserver.com/
-Installation wamp

-connexion au serveur
-importer la database en executant les requètes de DatabaseJEE.sql
	-si vous recharger DatabaseJEE.sql, 
		sortez des commentaires les DROP USER en début de fichier.
-création d’un user

-ouvrir Apache NetBeans IDE
-aller dans “services”, ouvrir glassfish server

-dl le dossier mysql-connector-java
-puis le mettre dans GlassFish_Server -> glassfish -> lib
-sur l’IDE clic droit sur “Services->Drivers-> MySQL(connector/J driver)”
-cliquer sur “Customize”
-cliquer sur “Add”
-cliquer sur le .jar dans le dossier mysql-connector-java

-insérer  “  SET GLOBAL time_zone = '+1:00'; “ dans le sql de phpmyadmin

-se connecter à la database sur l’IDE :
services -> databases -> MySQL server atlocalhost:3306 ->  connect
puis
services -> databases -> MySQL server atlocalhost:3306 -> st2eedb -> connect

-les dépendences supplémentaires qui seront installées par maven sont : 
-mysql-connector-java 

-faire clic droit sur le projet puis “Build with dependencies”


-Build le projet
