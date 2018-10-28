# README #

### What is this repository for? ###

* Src code and issues regarding jactor-rises

This project is consisted as three microsevices and where one service is responsible for the
persistencde, one for the business logic, and one for the user interface.  This information is
written with [GitLab Flavoured Markdown](https://gitlab.com/help/user/markdown)

The only hard dependencies between jactor modules are:

```mermaid
graph TD;
    jactor.rises.commons-->jactor.rises.facade;
    jactor.rises.commons-->jactor.rises.persistence.orm;
    jactor.rises.commons-->jactor.rises.web;
    jactor.rises.model-->jactor.rises.facade;
```

### Set up ###

* spring-boot applications are created when building (`mvn install`)
    * `jactor-persistence-orm` which is a standalone rest application which handles the persistence on a database using an object relational model and `spring-data-jpa`
    * `jactor-facade` is a rest applications which is the business logic of jactor.
    * `jactor-web` which is a web application on apache tomcat
 * these applications can run side by side to get a full working web-application (using `mvn spring-boot:run` on each application)
* `jactor-persistence-orm` is using h2 (in-memory database), and is not finite
* after started `jactor-web`, point a browser to http://localhost:8080/jactor-web/

### Disclaimer ###

These applications are not 100% finished, but created for my own learning. That is to say:

* `Blog`s are only persisted in `jactor-persistence-orm` and only have an integration test of the rest api that verifies actual interactions.
* `GuestBook`s have interactions from `jactor-model` and are tested with an integration test, but lack interactions from `jactor-web`.
* `User` (with `Person` and `Address`) is fully integrated with communications from `jactor-web`, which use `jactor-model` and `jactor-persistence-orm` (through `jactor-facade`).
  * Note! Not all services for a `User` on `jactor-persistence-orm` is used.

 @@
 *0 Persistence in `jactor-persistence-orm` is not satisfactory dealing with identifications. There is therefore [an issue](https://github.com/jactor-rises/jactor-rises/issues/1) (help wanted) created for solving a potential "buggy" solution.

### Architecture Summary ###

There are three microservices in this architecture which all handles all of the technical
infrastructure needed for performing the business logic in jactor:

* `jactor-web` handles all user interaction
* `jactor-persistence-orm` handles persistence of the data
* `jactor-facade` is the business logic and handles all communication between the microservices

### Some technologies used on jactor-rises ###

* [spring framwork 5.x](https://spring.io/projects/spring-framework)
* [spring-boot 2.x](https://spring.io/projects/spring-boot)
* [hibernate 5.x](http://hibernate.org/orm/)
* [h2](http://h2database.com)
* [junit 5.x](https://junit.org/junit5/)
* [assertj](https://joel-costigliola.github.io/assertj/)
* [mockito](http://site.mockito.org)
* [thymeleaf](https://www.thymeleaf.org)
