
A spring boot mvc application
 - user and address, password reset entities with CRUD operation
 - AWS SES integration to send registration email during email creation with JWT token
 - AWS SES integration to send reset password email with JWT Token
 - AWS credentials are in D:/users/sunil/.aws/credentials
 -
 - Test cases with mokito and junit 5
 - Test REST endpoints with REST Assured
 -
 - Generate documentation with Swagger - http://localhost:8080/mobile-app-ws/swagger-ui.html
 -
 - CORS setup in WebConfig.java, WsSecurity.java  
 -
 - Secure endpoints 
 -- SEE WSSecurity.config()
 --OR on Controllers
 ---@PreAuthorize("hasRole('ROLE_ADMIN)')")
 ---@PreAuthorize("hasAuthority('hasRole(DELETE_AUTHORITY)')") | @EnableGlobalMethodSecurity(prePostEnabled = true) enable it
 ---@Secured("ROLE_ADMIN")   | @EnableGlobalMethodSecurity(securedEnabled = true) enable it
 -
 -
 - H2 console - http://localhost:8080/mobile-app-ws/h2-console
select * from users;
select * from users_roles;
select * from roles;
select * from roles_authorities;
select * from authorities;
 
 Refer <b>https://github.com/sethiyasunil/spring-boot-verification-service</b>
 It offers html page to reset password and email verification



 Refer <b>https://github.com/sethiyasunil/sping-boot-rest-assured-test</b>
 It offers integration test using REST Assured 
 
