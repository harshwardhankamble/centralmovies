# Steps
# Build JAR for central-movies-common-lib
mvn clean install

# Swagger URL - Api Gateway
# Movie Service
http://localhost:9595/api/v1/swagger-ui/index.html

# Theatre Management Service
http://localhost:9595/api/v2/swagger-ui/index.html

# Booking and Payment Service
http://localhost:9595/api/v3/swagger-ui/index.html


# And if your individually running service then user port number as 8081, 8082, 8083 simultaneously
# Before accessing application please first register with new user
# Generate token before accessing application
# And pass userid 
