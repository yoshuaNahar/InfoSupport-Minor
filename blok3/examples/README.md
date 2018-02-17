# SpringCloudParent
SpringCloudParent can be used to configure general settings for all examples. It can be used to upgrade the Spring Boot version for instance.

# Running the examples
-	Go to the directory of the example and run
	-	mvn spring-boot:run
	-	mvn clean package && java -jar target/*.jar
	-	or from the IDE

# General information
-	The port numbers are configured in the bootstrap.properties and application.properties files in the 	resources packages

# Basic Eureka example
-	These applications are involved
	-	EurekaServer: handles the registration and discovery of services
	-	EurekaService: returns some information about the service
	-	EurekaClient: returns the information retrieved from the services
-	Run scenario:
	-	Start the server, service and client. Go to the URL of the client, notice the information the 		service returned
	
# Eureka load balancing examples
-	Use either Ribbon or Feign for loadbalancing
-	These applications are involved
	-	EurekaServer: same as before
	-	EurekaService: start the application twice with different ports to verify the loadbalancing 		functionality
	-	EurekaClientRibbon:
	-	EurekaClientFeign: 
-	Run scenario
	-	Start the server. Start the service twice with different ports. Start the two clients. Go to the 		url's of the two clients and hit the refresh button. Notice that round robin loadbalancing is 		used to retrieve the information from the two services.	
	-	Start an extra instance of the service on another port. Go to the url of the client, notice that 		the new instance of the service isn't included yet as we use client side load balancing.
	
# Eureka with Hystrix
-	Use Hystrix as circuit breaker
-	These applications are involved
	-	EurekaServer: same as before
	-	EurekaTerribleService: has two REST endpoints
			One REST endpoint always has a 2 second delay
			The other REST endpoint has a 2 second delay for half of the requests 
	-	EurekaClientHystrix: returns the service response or a fallback response if the service hasn't 		responded within 1 second
-	Run scenario
	-	Start the server, service and client.
	-	Call the '/' endpoint a few times. Notice that the fallback message is always displayed
	-	Call the '/50percent' endpoint a few times. Notice that half of the time the services response 		is displayed and the other half contains the fallback message.
