# M1 - JEE Project : InternsSuper visi onSolution
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

-mettre dans le pom.xml dans les dependencies :

<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.22</version>
</dependency>

-faire clic droit sur le projet puis “Build with dependencies”


-Build le projet
