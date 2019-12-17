## To run for the first time ##

This source code should simply put data in TestData into a file and then output it to the consumer using kafka. 

If there is a service not available type error, try increasing the sleep time



run "./SOURCE.sh anew" which will setup all the things you need in new terminals (zookeeper, kafka, topic) 

without the anew argument only the connector and consumer will be generated.

The JSON output should be put in the terminal you executed the command from
