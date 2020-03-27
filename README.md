
# Start agent

flume-ng agent \
--conf-file spool-to-hdfs.properties \
--name agent1 \
-Dflume.root.logger=WARN,console

# Check data in Hadoop 
hadoop fs -ls <dir>
hadoop fs -cat <dir>/*

# http post 
curl -X POST -H 'Content-Type: application/json; charset=UTF-8' -d '[{"headers" : {"eventheader1" : "event1", "eventheader2" : "event2" } , "body" : "This is the body2" }]' http://localhost:8989


# http post for event bucketing 
curl -X POST -H 'Content-Type: application/json; charset=UTF-8' -d '[{"headers" : {"topic" : "topic1" } , "body" : "This is the body in topic1" }]' http://localhost:8989

curl -X POST -H 'Content-Type: application/json; charset=UTF-8' -d '[{"headers" : {"topic" : "topic2" } , "body" : "This is the body in topic2" }]' http://localhost:8989


