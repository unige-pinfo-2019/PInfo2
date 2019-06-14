CONTAINER_ALREADY_STARTED="CONTAINER_ALREADY_STARTED_PLACEHOLDER"
if [ ! -e $CONTAINER_ALREADY_STARTED ]; then
    touch $CONTAINER_ALREADY_STARTED
    echo "-- First container startup --"

    # Services
    curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=category-service" --data-urlencode "url=http://category-service:8080/category"
    curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=ad-service" --data-urlencode "url=http://ad-service:8080/ad"
    curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=search-service" --data-urlencode "url=http://search-service:8080/search"
    curl -S -s -i -X POST --url http://api-gateway:8001/services --data "name=image-service" --data-urlencode "url=http://image-service:8080/image"
    
    # Routes
    # Category service
    curl -S -s -i -X POST --url http://api-gateway:8001/services/category-service/routes --data "name=category-route-show&paths[]=/api/v1/category&methods[]=GET"
    curl -S -s -i -X POST --url http://api-gateway:8001/services/category-service/routes --data "name=category-route-modify&paths[]=/api/v1/category&methods[]=POST&methods[]=DELETE&methods[]=PUT"  
    # Ad service 
    curl -S -s -i -X POST --url http://api-gateway:8001/services/ad-service/routes --data "name=ad-route-show&paths[]=/api/v1/ad&methods[]=GET"
    curl -S -s -i -X POST --url http://api-gateway:8001/services/ad-service/routes --data "name=ad-route-modify&paths[]=/api/v1/ad&methods[]=POST&methods[]=DELETE&methods[]=PUT"  
    # Image service 
    curl -S -s -i -X POST --url http://api-gateway:8001/services/image-service/routes --data "name=image-route-show&paths[]=/api/v1/image&methods[]=GET"
    curl -S -s -i -X POST --url http://api-gateway:8001/services/image-service/routes --data "name=image-route-modify&paths[]=/api/v1/image&methods[]=POST&methods[]=DELETE&methods[]=PUT"  
    # Search service
    curl -S -s -i -X POST --url http://api-gateway:8001/services/search-service/routes --data "name=search-route-show&paths[]=/api/v1/search&methods[]=GET" 
  
    # Enable the JWT Plugin on protected routes
    curl -S -s -i -X POST --url http://api-gateway:8001/routes/ad-route-modify/plugins --data "name=jwt"
    curl -S -s -i -X POST --url http://api-gateway:8001/routes/category-route-modify/plugins --data "name=jwt"
    curl -S -s -i -X POST --url http://api-gateway:8001/routes/image-route-modify/plugins --data "name=jwt"

    # Consumers
    curl -S -s -i -X POST --url http://api-gateway:8001/consumers  --data "username=api-sso-proxied"   --data "custom_id=api-sso-proxied"
    curl -S -s -i -X POST --url http://api-gateway:8001/consumers/api-sso-proxied/jwt   -F "algorithm=RS256"  -F "rsa_public_key=@/tmp/keycloak_rsa_provider-key-pub.pem" -F "key=https://pinfo2.unige.ch/auth/realms/apigw"
    curl -S -s -i -X POST --url http://api-gateway:8001/consumers  --data "username=api-sso-not-proxied"   --data "custom_id=api-sso-not-proxied"
    curl -S -s -i -X POST --url http://api-gateway:8001/consumers/api-sso-not-proxied/jwt   -F "algorithm=RS256"  -F "rsa_public_key=@/tmp/keycloak_rsa_provider-key-pub.pem" -F "key=http://iam:8080/auth/realms/apigw"

else
    echo "-- Not first container startup --"
fi


