[![Build Status](https://travis-ci.org/unige-pinfo-2019/PInfo2.svg?branch=master)](https://travis-ci.org/unige-pinfo-2019/PInfo2) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=unige-pinfo-2019_PInfo2&metric=coverage)](https://sonarcloud.io/dashboard?id=unige-pinfo-2019_PInfo2) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=unige-pinfo-2019_PInfo2&metric=alert_status)](https://sonarcloud.io/dashboard?id=unige-pinfo-2019_PInfo2)

# Agility team

"Entrepreneurial Philanthropy is not just a philosophy or a dream. It is a promise that philanthropy is at its best when it is founded on entrepreneurial zest and agility."

## Members

 - Marvin Fourastié (Project leader)
 - Corentin Bugnot  (System administrator)
 - Heloy Estevanin
 - Romain Bigot
 - Adrien Razurel
 - Raphaël Lutz
 
 ## Instructions
 To compile all the microservices and their Docker images:
 
```
mvn clean install
mvn install -Ppackage-docker-image
```

If a modification has been done on the frontend side, one should build all web-ui static files and start/restart the API gateway:

```
cd web-ui
ng build
cd ..
cd docker-compose
docker-compose -f docker-compose-api-gw.yml up
```

List of services ports:

User service: `13080`
Category service: `12080`
Search service: `11080`
Ad service: `15080`
Image service: `14080`
