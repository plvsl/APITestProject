# APITestProject

## Task

Write and submit a small, runnable suite of integration tests for the web service endpoints mentioned below:
 
GET : `https://fakerestapi.azurewebsites.net/api/v1/Users` <br/>
GET : `https://fakerestapi.azurewebsites.net/api/v1/Users/{id}` <br/>
POST : `https://fakerestapi.azurewebsites.net/api/v1/Users` <br/>

Request body schema

```
{
  "id": 0,
  "userName": "string",
  "password": "string"
}
```

PUT: `https://fakerestapi.azurewebsites.net/api/v1/Users/{id}` <br/>
Request body Schema

```
{
  "id": 0,
  "userName": "string",
  "password": "string"
}
```

DELETE: `https://fakerestapi.azurewebsites.net/api/v1/Users/{id}`

## Required Software and Tools

- Java Version: Oracle Java 8 
- Apache Maven Version: 3.9.X 
- Integrated Development Environment: Any version IntelliJ Idea or Eclipse


## Instructions

### Instructions to run tests 

- Open a terminal or command prompt
- Navigate to **Project Root**
- Execute `mvn clean test`

## Bugs

### 1. **POST** request returns invalid Status Code (200 instead of 201) <br/>

#### Steps to reproduce:<br/>

```
1. Send API POST request with valid body :
 
 {
  "id": 11,
  "userName": "User 11",
  "password": "Password11"
 }
 
2. Get the response 
3. Check response code status 

   ```

#### Expected Result:<br/>
```
Response Status Code : 201
```
#### Actual Result:<br/>
```
Response Status Code : 200
```


###2.  **POST** request doesn't change data state of the System

#### Steps to reproduce:<br/>
```
1. Send API POST request with valid body :
 
 {
  "id": 11,
  "userName": "User 11",
  "password": "Password11"
 }
 
2. Get the response body
3. Check that response body has valid data
4. Send API GET request
5. Get the response body of all elements
6. Check the number of elements in the system is changed (increased by one)
   ```

#### Expected Result:<br/>
```
Number of elements : 11
```
#### Actual Result:<br/>
```
Number of elements : 10
```

###3. **PUT** request doesn't change data state of the System

#### Steps to reproduce:<br/>
```
1. Send API PUT request with valid body :
 
 {
  "id": 1,
  "userName": "Polina",
  "password": "PolinaPasword"
 }
 
2. Get the response body
3. Check that response body has valid data
4. Send API GET request of the element with "id" : 1
5. Get the response body of the element with "id" : 1
6. Check if the response body has valid data
   ```

#### Expected Result:<br/>
```
The response body with "id" : 1

 {
  "id": 1,
  "userName": "Polina",
  "password": "PolinaPasword"
 }
```
#### Actual Result:<br/>
```
The response body with "id" : 1

 {
  "id": 1,
  "userName": "User 1",
  "password": "Password 1"
 }
```
###4. **DELETE** request returns invalid Response Status Code when delete entity with non-existing ID 

#### Steps to reproduce:<br/>
```
1. Send API DELETE request to delete element with non-existent "id" : 11
2. Get the response body
3. Check response status code  
```
#### Expected Result:<br/>
```
Response Status Code : 404
```
#### Actual Result:<br/>
```
Response Status Code : 200
```

###5. **DELETE** request returns invalid Response Status Code when delete entity with invalid(String) ID

#### Steps to reproduce:<br/>
```
1. Send API DELETE request to delete element with invalid String "id" : "String"
2. Get the response body
3. Check response status code  
```
#### Expected Result:<br/>
```
Response Status Code : 404
```
#### Actual Result:<br/>
```
Response Status Code : 200
```

###6. **DELETE** request doesn't change data state of the System

### Steps to reproduce:<br/>
```
1. Send API DELETE request to delete element with "id" : 1
3. Check that response code status equals 200
4. Send API GET request
5. Get the response body of all elements
6. Check the number of elements in the system is changed (decreased by one)
   ```
#### Expected Result:<br/>
```
Number of elements : 9
```
#### Actual Result:<br/>
```
Number of elements : 10
```
