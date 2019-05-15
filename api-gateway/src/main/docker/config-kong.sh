#Creates the services.
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=category-service" --data "url=http://category-service:8080/category"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=chat-service" --data "url=http://chat-service:8080/chat"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=ad-service" --data "url=http://ad-service:8080/ad"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=user-service" --data "url=http://user-service:8080/user"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=search-service" --data "url=http://search-service:8080/search"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=image-service" --data "url=http://image-service:8080/image"
curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=stats-service" --data "url=http://stats-service:8080/stats"
#Creates the routes
curl -S -s -i -X POST  --url http://api-gateway:8001/services/category-service/routes --data "paths[]=/api/v1/category" 
curl -S -s -i -X POST  --url http://api-gateway:8001/services/chat-service/routes   --data "paths[]=/api/v1/chat" 
curl -S -s -i -X POST  --url http://api-gateway:8001/services/ad-service/routes    --data "paths[]=/api/v1/ad" 
curl -S -s -i -X POST  --url http://api-gateway:8001/services/stats-service/routes   --data "paths[]=/api/v1/stats"
curl -S -s -i -X POST  --url http://api-gateway:8001/services/image-service/routes   --data "paths[]=/api/v1/image"
curl -S -s -i -X POST  --url http://api-gateway:8001/services/search-service/routes   --data "paths[]=/api/v1/search"
curl -S -s -i -X POST  --url http://api-gateway:8001/services/user-service/routes   --data "paths[]=/api/v1/user"    
#Enable the Open ID Plugin
curl -S -s -i -X POST  --url http://api-gateway:8001/plugins --data "name=jwt" --data "enabled=true"
#curl -S -s -i -X POST  --url http://api-gateway:8001/plugins --data "name=oidc" --data "enabled=false"
curl -S -s -i -X POST  --url http://api-gateway:8001/consumers  --data "username=api-sso-proxied"   --data "custom_id=api-sso-proxied"
curl -S -s -i -X POST  --url http://api-gateway:8001/consumers/api-sso-proxied/jwt   -F "algorithm=RS256"  -F "rsa_public_key=@/tmp/keycloak_rsa_provider-key-pub.pem" -F "key=https://localhost/auth/realms/apigw"
curl -S -s -i -X POST  --url http://api-gateway:8001/consumers  --data "username=api-sso-not-proxied"   --data "custom_id=api-sso-not-proxied"
curl -S -s -i -X POST  --url http://api-gateway:8001/consumers/api-sso-not-proxied/jwt   -F "algorithm=RS256"  -F "rsa_public_key=@/tmp/keycloak_rsa_provider-key-pub.pem" -F "key=http://iam:8080/auth/realms/apigw"


