### How to run
1. gradle clean build
2. docker-build.sh

### What tests should be implemented in the programming of such a project? What tests would you include in your project if given more time?

Unit tests covering controller and service layers, and integration tests covering big chunks of functionality (e.g. starting new search, expecting running Amass container, etc)

### How can you measure the performance of your project? Can you optimize your code to run faster?

I would use any java profiler (e.g. JProfiler, JMeter) or if infrastructure allows Grafana or New Relic could be used.
To run code faster multi-threading could be introduced.

### What bottlenecks can be caused by the OSINT tools given to this project?
Both of OSINT tools are used from Docker container means that extra time to handle containers life-cycle. Also implementation of such tools seems to be black box with possible bottlenecks in communication with external services.
