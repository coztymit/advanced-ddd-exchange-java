# advanced-ddd-exchange-java

Swagger UI: http://localhost:8080/swagger-ui/index.html
Database-H2 Console: http://localhost:8080/h2-console


## How to run the application

### Run the application with Maven

Example dealer number: ABC-01-2023-123

Create Negotiation 
1. Add currency pair to the system
   
curl -X 'POST' \
   'http://localhost:8080/currency-pair/add' \
   -H 'accept: */*' \
   -H 'Content-Type: application/json' \
   -d '{
   "baseCurrency": {
   "value": "EUR"
   },
   "targetCurrency": {
   "value": "PLN"
   }
   }'
