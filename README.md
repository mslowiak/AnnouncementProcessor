# AnnouncementProcessor

## Problem description
This project is focused on flat rent announcements. Searching for an aparment is a hard task. The person who searches real estate offers faces many difficulties. These range from offers being announced on different portals, through insufficient information contained in the searchable data, to poorly grouped locations.


## Application description
The application tries to handle all of issues mentioned above. 
It is built on microservices as showed in image below.

![alt text](project_structure.png)

**Parser service** - Responsible for parsing announcements from different websites to the same format. Next task for this service is to push announcements in new format via JMS.

**Extractor service** - Responsible for extracting data from description like LOCATION and FULL COST OF RENT

**API service** - Responsible for export data with REST API

## Installation
There are two docker-compose files:
- docker-compose.yml - includes configuration for run application on localhost
- docker-compose-with-heroku-api.yml - includes configuration to run parser and extractor locally connected to the api deployed on heroku

To run this docker compose files you need to execute command:
docker-compose -f 'filename' up
