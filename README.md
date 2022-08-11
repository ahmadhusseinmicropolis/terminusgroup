# Terminus Group Assessment

This is a application using Spring Boot, JPA, Hibernate, AngularJS and MySql database.

The application include the following features:
- Get users.
- Get user by Id.
- Create user.
- Update user.
- Delete user.

## The main project structure:

src
<br />|---- main
<br />|----|---- resource (webapp)
<br />|----|---- java (api)
<br />|---- test
<br />|----|---- jave (unit test)
<br />
<br />
## resource (webapp):
<br />This folder contains the angularJS web app, which contains the following:
<br />resource/statis/js
<br />|---- app.js
<br />|----|---- controlers/user.controller.js
<br />|----|---- services/user.service.js
<br /> in addition to the index.jsp file (src/main/webapp/WEB-INF/views/index.jsp)
<br />
<br />
## java (api):
<br />This folder contains the backend of the application.The classes are placed by layer i.e; all controllers placed in controllers package, services under services package, all entities under models, ...
<br />The folder contains the following:
<br />main/java
<br />|---- AssessmentApplication.java
<br />|----|---- controlers
<br />|----|----|---- HomeController.java
<br />|----|----|---- UserController.java
<br />|----|---- models
<br />|----|----|---- User.java
<br />|----|---- modelDtos
<br />|----|----|---- UserDto.java
<br />|----|---- repositories
<br />|----|----|---- UserRepository.java
<br />|----|---- services
<br />|----|----|---- IUserService.java
<br />|----|----|---- UserService.java
<br />
### Message Broker
<br /> We add message broker package, we use MqttClient to imlement the example.
<br />|----|---- messagebroker
<br />|----|----|---- MqttClient.java
<br />|----|----|---- MessageCallback.java
## jave (unit test)
<br /> This folder contains the unit test of the controllers.
<br />|---- UserControllerTest.java
<br />
<br />
