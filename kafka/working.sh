----------------1
cd /usr/hdp/current/kafka-broker/bin
./kafka-topics.sh --create --zookeeper sandbox-hdp.hortonworks.com:2181 --replication-factor 1 --partitions 1 --topic kosmin
./kafka-topics.sh --list --zookeeper sandbox-hdp.hortonworks.com:2181
./kafka-topics.sh --describe --topic cities --zookeeper sandbox-hdp.hortonworks.com:2181
./kafka-console-producer.sh --broker-list sandbox-hdp.hortonworks.com:6667 --topic kosmin
./kafka-console-consumer.sh --bootstrap-server sandbox-hdp.hortonworks.com:6667  --topic kosmin --from-beginning

----------------2
kafka connector
cd /usr/hdp/current/kafka-broker/conf
connect-standalone.properties 
connect-file-sink.properties / how to store - consumer
connect-file-source.properties / producer

#connect-standalone.properties 
bootstrap.servers=sandbox-hdp.hortonworks.com:6667

#connect-file-sink.properties
file=/home/maria_dev/kafka/logout.txt
topics=kosmin-log

#connect-file-source.properties
file=/home/maria_dev/kafka/access_log.txt
topic=kosmin-log

cd /usr/hdp/current/kafka-broker/bin
./kafka-console-consumer.sh --bootstrap-server sandbox-hdp.hortonworks.com:6667  --topic kosmin-log
./connect-standalone.sh ~/kafka/connect-standalone.properties ~/kafka/connect-file-source.properties ~/kafka/connect-file-sink.properties

###server start broker
./zookeeper-server-start.sh ../config/zookeeper.properties
./kafka-server-start.sh ../config/server.properties

----------------------
#zookeeper
/usr/hdp/current/zookeeper-client/bin
./zkCli.sh -server sandbox-hdp.hortonworks.com:2181
$  ls /brokers/ids # Gives the list of active brokers
$  ls /brokers/topics #Gives the list of topics
$  get /brokers/ids/0 #Gives more detailed information of the broker id '0'
