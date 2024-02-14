FROM bellsoft/liberica-openjdk-alpine:17-cds-x86_64

# install curl and jq
RUN apk add curl jq

# workspace
WORKDIR /home/selenium-docker

# we can add the required file to run the test
ADD target/docker-resources ./
ADD runner.sh      runner.sh 

# start the runner.sh
# once the changes are made in the automation script
# 1. create a package by hitting the command - mvn clean package -DskipTests
# 2. Then build  the image using the command - docker build -t=[username]/[imagename]
# 3. Then hit the command to execute the test - docker run -e BROWSER=chrome -e HUB_HOST=192.168.0.104 -e TEST_SUITE=flight-reservation.xml -e THREAD_COUNT=2 atharvahiwase7/dockerselenium
# 4. We can also create a container and directly execute the test cases. just save the yaml file and hit the command 
# docker-compose up. 

# Test results ar einside the container
# We can get the test results to the machine by doing volume mapping

# Hub takes time to connect. So script fails immediately.
# We created a "runner" script which checks the grid "readiness". Then run the tests!

# Run the command to initiate the tests
ENTRYPOINT sh runner.sh