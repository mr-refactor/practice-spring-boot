# First Spring Boot API

## Overview
* Models data for a Student (com.example.demo.student)
* Crud Functionality
* Separates concerns with multiple layers (Controller -> Service -> Entity -> Data Access)

## Installation
* Clone the repository and navigate to the directory
* Run `./mvnw spring-boot:run` to build the project and start the server
### Use
* List all students `GET "localhost:8080/api/v1/student"`
* Create a new student `POST "localhost:8080/api/v1/student"`
  <br /> Accepted JSON body fields:
  * name
  * email
  * dob
* Update student info `PATCH "localhost:8080/api/v1/student/{studentId}?{queryParameters}"` 
  <br />Accepted query parameters:
  * name
  * email
* Delete a student `DELETE "localhost:8080/api/v1/student/{studentId}"`
    