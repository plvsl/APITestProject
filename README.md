# APITestProject

## Task

Write and submit a small, runnable suite of integration tests for the web service endpoints mentioned below:
 
GET : https://fakerestapi.azurewebsites.net/api/v1/Users
GET : https://fakerestapi.azurewebsites.net/api/v1/Users/{id}
POST : https://fakerestapi.azurewebsites.net/api/v1/Users

Request body schema

{
  "id": 0,
  "userName": "string",
  "password": "string"
}

PUT: https://fakerestapi.azurewebsites.net/api/v1/Users/{id}
Request body Schema

{
  "id": 0,
  "userName": "string",
  "password": "string"
}

DELETE: https://fakerestapi.azurewebsites.net/api/v1/Users/{id}

## Instructions

### Instructions to run tests 

- Open a terminal or command prompt
- Navigate to **Project Root**
- Execute `mvn clean test`

## Bugs

- POST request returns invalid Status Code (200 instead of 201) 
- POST request doesn't add new data to DataBase, only in Response Body 
- PUT request doesn't provide any changes in DataBase, only in Response Body
- DELETE request doesn't provide any changes in DataBase, only in Response Body
- DELETE request returns invalid Response Status Code (200) when delete entity with invalid or non-existing ID and 

