[![Build Status](https://travis-ci.org/unige-pinfo-2019/PInfo2.svg?branch=master)](https://travis-ci.org/unige-pinfo-2019/PInfo2)

# Agility team

"Entrepreneurial Philanthropy is not just a philosophy or a dream. It is a promise that philanthropy is at its best when it is founded on entrepreneurial zest and agility."

## Members

 - Marvin Fourastié (Project leader)
 - Corentin Bugnot  (System administrator)
 - Heloy Estevanin
 - Romain Bigot
 - Adrien Razurel
 - Raphaël Lutz
 
```
mvn clean install
mvn install -Ppackage-docker-image
cd docker-compose/
docker-compose -f docker-compose-api-gw.yml up
```