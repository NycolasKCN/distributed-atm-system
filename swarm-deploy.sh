#!/bin/sh
docker build -t distributed-atm-image . || { echo "Build falhou, encerrando."; return 1; }

export $(cat .env) > /dev/null 2>&1; 
docker stack deploy -c swarm-docker-compose.yml ${1:-STACK_NAME}
