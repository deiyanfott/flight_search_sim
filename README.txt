This project uses lombok jar.

If lombok is not yet installed in the IDE,
download jar file at https://projectlombok.org/download.

After downloading jar file,
run java -jar /path/to/jar/lombok.jar and follow instructions on how to install.

Application can be run via IDE or command line.
For IDE, run as java application.

For command line, create a maven build run configuration with clean install.
Then run java -jar ${project.build.directory}/flight-search-simulator.jar in the CLI.

Notes:
1. I assume this is a flight search app, so I made most of the request params required.
2. No logging mechanism was introduced in the project.
3. Was not able to create much test cases
4. For the test case, you may overwrite the json files in the /src/test/resources classpath
5. You may modify the Rest API URLs in the application-<profile>.properties file
6. Request params are as follows:
   origin - String required only letters and spaces are allowed
   destination - String required only letters and spaces are allowed
   startDate - String required (format yyyy-MM-dd)
   endDate - String required (format yyyy-MM-dd)
   sortBy - String optional case sensitive (origin, destination, startDate, or endDate)
   sortMethod - String optional not case sensitive (A or D)
   pageNumber - Integer required
   limit - Integer optional should be > 1
7. Port used is default (8080)
8. Base API URL is http://localhost:8080/tokigames/dev/api/v1/flightSearch
9. Sample API call for this application below (contains all params):
   http://localhost:8080/tokigames/dev/api/v1/flightSearch?
   origin=Singapore&destination=Istanbul&startDate=2017-05-27
   &endDate=2019-08-30&pageNumber=1&limit=1&sortBy=startDate&sortMethod=A