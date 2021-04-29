#!/bin/bash
aws ecr get-login-password --region eu-west-1 | docker login --username AWS --password-stdin 519687253662.dkr.ecr.eu-west-1.amazonaws.com
docker build -t contentux-platform .
docker tag contentux-platform:latest 519687253662.dkr.ecr.eu-west-1.amazonaws.com/contentux-platform:latest
docker push 519687253662.dkr.ecr.eu-west-1.amazonaws.com/contentux-platform:latest