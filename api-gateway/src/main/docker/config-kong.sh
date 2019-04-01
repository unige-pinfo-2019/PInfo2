#Creates the services.
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=category-service" --data "url=http://category-service:8080/category"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=chat-service" --data "url=http://chat-service:8080/chat"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=ad-service" --data "url=http://ad-service:8080/ad"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=stats-service" --data "url=http://stats-service:8080/stats"
#Creates the routes
curl -S -s -i -X POST  --url http://api-gateway:8001/services/category-service/routes --data "paths[]=/api/category" 
curl -S -s -i -X POST  --url http://api-gateway:8001/services/chat-service/routes   --data "paths[]=/api/chat" 
curl -S -s -i -X POST  --url http://api-gateway:8001/services/ad-service/routes    --data "paths[]=/api/ad" 
curl -S -s -i -X POST  --url http://api-gateway:8001/services/stats-service/routes   --data "paths[]=/api/stats" 
#Enable the Open ID Plugin
curl -S -s -i -X POST --url http://api-gateway:8001/plugins --data "name=oidc" --data "config.client_id=api-gateway" --data "config.client_secret=798751a9-d274-4335-abf6-80611cd19ba1" --data "config.discovery=https%3A%2F%2Flocalhost%2Fauth%2Frealms%2Fapigw%2F.well-known%2Fopenid-configuration"
