# Challenge 3 

Challenge 3, booking api with rest.

## How to Run 

This application is packaged as a war which has Tomcat 7 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -Dspring.profiles.active=test target/challenge3example-0.3.0.war
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"
```
* Check the stdout to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2014-10-04 18:24:58.870  INFO 10190 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8090/http
2014-10-04 18:24:58.872  INFO 10190 --- [           main] com.ahmed.example.Application        : Started Application in 6.764 seconds (JVM running for 7.06)
```

## About the Service

This is a simple seat booking service 
```com.ahmed.example.api.rest.UserSeatController``` on **port 8090**. (see below)
This is a simple seat booking service 
```com.ahmed.example.api.rest.AdminController``` on **port 8090**. (see below)


More interestingly, you can start calling some of the operational endpoints (see full list below) like ```/metrics``` and ```/health``` (these are available on **port 8091**)


 
Here is what this little application demonstrates: 


* All APIs are "self-documented" by Swagger using annotations 

Here are some endpoints you can call:

### Get information about system health, configurations, etc.

```
http://localhost:8091/env
http://localhost:8091/health
http://localhost:8091/info
http://localhost:8091/metrics
```

### First you need to create seats, admin functionality

```
GET http://localhost:8090/seats/admin/createseats?num=11
Accept: application/json
Content-Type: application/json

[
      {
      "id": 1,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 2,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 3,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 4,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 5,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 6,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 7,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 8,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 9,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 10,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 11,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   }
]

RESPONSE: HTTP 200\
```
##User Functionality

### Get all Seats
```
GET: http://localhost:8090/seats/user

[
      {
      "id": 1,
      "status": "PAIDRESERVED",
      "code": "irfan28440",
      "userName": "irfan",
      "reservedtime": 1501450329432
   },
      {
      "id": 2,
      "status": "TEMPBLOCK",
      "code": null,
      "userName": "irfan",
      "reservedtime": 1501450686439
   },
      {
      "id": 3,
      "status": "TEMPBLOCK",
      "code": null,
      "userName": "irfan",
      "reservedtime": 1501450691956
   },
      {
      "id": 4,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 5,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 6,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 7,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 8,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 9,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 10,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 11,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   }
]



```

### Get Free Seats
```
GET: http://localhost:8090/seats/user/free
[
      {
      "id": 4,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 5,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 6,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 7,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 8,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 9,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 10,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   },
      {
      "id": 11,
      "status": "AVAILABLE",
      "code": null,
      "userName": null,
      "reservedtime": null
   }
]


```


### Block a seat
```
PUT: http://localhost:8090/seats/user/3/reserve?user=irfan


{
   "id": 3,
   "status": "TEMPBLOCK",
   "code": null,
   "userName": "irfan",
   "reservedtime": 1501450691956
}
```
###Cancel block on seat
```
PUT: http://localhost:8090/seats/user/3/cancel?user=irfan



{
   "id": 3,
   "status": "AVAILABLE",
   "code": null,
   "userName": null,
   "reservedTime":null
}

```


### pay and reserve a seat
```
PUT: http://localhost:8090/seats/user/3/pay?userName=irfan


{
   "id": 3,
   "status": "PAIDRESERVED",
   "code": "irfan28440",
   "userName": "irfan",
   "reservedTime": 1501450329432
}
```

### retrieve seat info via conf code
```
GET: http://localhost:8090/seats/user/status/irfan28440


{
   "id": 3,
   "status": "PAIDRESERVED",
   "code": "irfan28440",
   "userName": "irfan",
   "reservedTime": 1501450329432
}

### Admin functionality

We've covered create Seats

###update seat with any values you want
```
PUT: http://localhost:8090/seats/admin/9
send request body with application/json
{
      "id": 9,
      "status": "TEMPBLOCK",
      "code": null
      "reservedTime": null
      "userName": null 
   }


```

###getall seats
```
list of seats should be returned

GET: http://localhost:8090/seats/admin/
list of seats should be returned


```
###freeall  seats
```
Freeing all seats

PUT: http://localhost:8090/seats/admin/
list of seats should be returned


```



