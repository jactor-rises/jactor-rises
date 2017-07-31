# README #

Src code for hjemme-application (client, module, persistence, and front end)

### What is this repository for? ###

* Src code and issues regarding hjemme
* hjemme has none releases yet as this is used mostly for my own learning
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* you need a web server to deploy hjemme-web (hjemme.war), this is developed using apache tomcat
* check out code and run mvn clean install on hjemme and hjemme-web
* the code uses an embedded hsql database and no other dependencies are needed
* after deployment, the application is visible on localhost:8080/hjemme/home.do
* to deploy on tomcat, copy the buildt hjemme.war to CATALINE_(HOME/BASE)/webapps

### Some artifacts used in hjemme ###

* spring framwork
* hibernate
* hsqldb
* assertj
* mockito
* aspect j
* sitemesh