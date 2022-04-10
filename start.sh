#!/bin/bash

docker build -t ase-project/backend:latest -t ase-project/backend:v.1.0 .
cd ase-project-fe
docker build -t ase-project/frontend:latest -t ase-project/frontend:v.1.0 .
docker-compose up -d
echo "🎉 Successfully built the application"
echo "💄 Frontend: http://localhost:3000"
echo "🛠 Backend: http://localhost:9000"

