#!/bin/sh
export $(cat .env) > /dev/null 2>&1; 
docker stack config -c swarm-docker-compose.yml
