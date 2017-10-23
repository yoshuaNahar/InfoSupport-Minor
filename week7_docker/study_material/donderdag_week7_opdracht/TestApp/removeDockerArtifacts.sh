docker stop $(docker ps -a -q)
docker rm --force $(docker ps -a -q)
docker rmi --force $(docker images -q)