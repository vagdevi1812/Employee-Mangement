# employee-management
Two controller are placed
EmployeeController and DepartmentController

1. To access the url's firstly need to get the JWT token from the below url
 Post - http://localhost:8080/token/generate-token
 
       Body as below for user role

       {
        "username": "user2",
        "password": "testpassword1"
       }

        For admin role 

        {
          "username": "user1",
          "password": "testpassword"
        }


2. Once the token is generated we can access the controller endpoints by passing the authorized token in Headers

   Authorization  Bearer **{token}**
   
 
3. Have done the validations for the input request
 
4. Implemented internationalization for error messages. 

