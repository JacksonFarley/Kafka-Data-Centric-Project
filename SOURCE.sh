#!/bin/bash


if [ "$1" == 'anew' ]
then
# assuming one is in the kafka directory

gnome-terminal -- bin/zookeeper-server-start.sh config/zookeeper.properties

sleep 5

gnome-terminal -- bin/kafka-server-start.sh config/server.properties

sleep 5

# make single broker topic

bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic source-test

# make connector from the example

sleep 5

fi


# the .csv file that kafka reads is in connect-console-source.properties
# topic found there as well

gnome-terminal -- bin/connect-standalone.sh config/connect-standalone.properties config/connect-file-source.properties

# should output the csv with a JSON object for each line
sleep 2

bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic source-test 

