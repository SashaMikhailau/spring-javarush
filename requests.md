PUT http://localhost:8080/topjava/rest/meals/100006
Accept: application/json
Cache-Control: no-cache
Content-Type: application/json

{"dateTime":"2019-05-31T13:00:00","description":"New","calories":1500}

###

POST http://localhost:8080/topjava/rest/meals
Accept: application/json
Cache-Control: no-cache
Content-Type: application/json

{"id":"null","dateTime":"2019-05-31T13:00:00","description":"New","calories":1500,"user":null}

###

POST http://localhost:8080/topjava/rest/meals/filter?start_date=2015-05-30&end_date=2015-05-31&start_time=09:00:00&end_time=21:00:30
Accept: application/json
Cache-Control: no-cache

###

POST http://localhost:8080/topjava/rest/meals/filter?start_date=2015-05-31&end_date=2015-05-31&start_time=&end_time=
Accept: application/json
Cache-Control: no-cache

###

POST http://localhost:8080/topjava/rest/meals/filter?start_date=&end_date=&start_time=11:00:00&end_time=19:00:30
Accept: application/json
Cache-Control: no-cache

###

GET http://localhost:8080/topjava/rest/meals
Accept: application/json
Cache-Control: no-cache

###

GET http://localhost:8080/topjava/rest/meals/100005
Accept: application/json
Cache-Control: no-cache

###



