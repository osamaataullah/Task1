# User Detail Service
This project implements a RESTful web service with basic HTTP authentication that accepts a username in a query parameter. The service checks if the provided username exists in a property file. If the user exists, it retrieves the workstation value associated with that user and posts it to another web service.

### Features
* User Detail Endpoint: Exposes a RESTful endpoint /userDetail that accepts a username as a query parameter.
* Authentication: Uses basic HTTP authentication to secure the endpoint.
* Property File Lookup: Checks if the provided username exists in a property file and retrieves the associated workstation value.
* Posting to Another Service: Posts the user's details (username and workstation) to another web service (http://localhost:port/appName2/addUserInfo).
* Response Handling: Returns appropriate HTTP responses (200 OK for success, 404 Not Found for user not found, and 500 Internal Server Error for any unexpected errors).

### Implementation
**Controller (UserInfoController)** <br/>
Dependencies: The controller class depends o n PropertyFileReader to read user properties and AnotherServiceClient to post user details to another service.
Endpoint: Provides a GET endpoint /userDetail to accept a username as a query parameter.
Processing Logic: Checks if the user exists in the property file. If found, retrieves the workstation and posts the user details to another service. Returns appropriate HTTP responses based on the outcome. <br/>
**Configuration (PropertyFileReader)** <br/>
Property File Path: Reads the path to the user property file from the application properties.
Workstation Lookup: Provides a method to look up the workstation associated with a user in the property file. <br/>
**Client (AnotherServiceClient)** <br/>
Dependencies: Uses RestTemplate to make HTTP POST requests.
Posting to Another Service: Provides a method to post user details to another service using JSON payload.
### Usage
* Configure the application.properties file with the appropriate values:
properties
```
user.property.file.path=/path/to/user/property/file.properties
another.service.url=http://localhost:port/appName2/addUserInfo
```
* Run the application.
* Access the /userDetail endpoint with a username as a query parameter, e.g., http://localhost:port/appName/userDetail?user=admin.
* Monitor the logs for successful or failed attempts.
### Dependencies
* Spring Boot: Provides the framework for building and running the application.
* Spring Web: Used for creating RESTful web services.
* Spring Security: Used for implementing basic HTTP authentication.
* Spring Boot Starter Test: Provides testing utilities for Spring Boot applications.
## Notes
* Ensure that the user property file contains entries in the format username=workstation.
* This implementation assumes a simple scenario with properties stored in a file. Adjustments may be required for more complex scenarios or when using a database for user data storage.
