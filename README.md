# Offer API

Offer related API's exposed

## Getting Started
This is a maven project, so you can start running the project using
```
mvn clean install spring-boot:run
```

### swagger ui
```
http://localhost:8080/swagger-ui.html
```

## Create new Offer
```
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \
   "offer": "string" \
   "description": "buy 1 get 1 free", \
   "expiryDate": "2018-06-20", \
 }' 'http://localhost:8080/offers'
 ```

### Cancel an offer
```
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' 'http://localhost:8080/offers/cancelOffer/1'
```

### Get All the offers with out any RequestParam ( both valid and expired )
```
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/offers'
```

### Get All valid offers with  RequestParam expired = FALSE (which gives all the offers which are not expired)
```
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/offers?expired=FALSE'
```

### Get All expired offers with  RequestParam expired = TRUE (which gives all the offers which are expired)
```
curl -X GET --header 'Accept: application/json' 'http://localhost:8080/offers?expired=TRUE'
```
