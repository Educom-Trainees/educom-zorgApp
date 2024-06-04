# API controllers
For each page of the Zapp there is a matching controller. 
This is to minimize the amount of different call methods required for the zapp to function.

### Parameters
The GET requests use RequestParams to determine the returned data.
This is formatted as ```?{param}={value}``` at the end of the url. 
For multiple parameters, you can link them with ```?{param}={value}&{param}={value}```

Examples:

```http://localhost:8080/appointments?date=2024-05-24```
Gives all appointments on 2024-05-24.

```http://localhost:8080/appointments?date=2024-05-24&count=6```
Gives the appoinments from 2024-05-24 until 2024-05-30.

### TODOs
- API for password changes (EmployeeDTO doesn't allow for it currently (2024-6-04))

