

### Build both the springboot jar and the docker image
Note that the springboot jar is build with a docker container so the user
does not need to have java or maven setup correctly to make the this demo work

./bin/build.bash

### Run the empmgr container
Exposes the empgr service on port 9101

./bin/run.bash

### Runs curl as a functional test for the empmgr service
./bin/curl_test.bash

### The database being used is an in memory version of the HyperSQL database
