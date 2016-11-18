#!/bin/bash

trap ctrl_c INT

function ctrl_c() {
  echo "handling ctrl-c..." 
  PID=`ps -ef | grep -v grep | grep zook | awk '{print $2}'`
  echo $PID
  if [ ! -z "$PID" ];
    then 
      echo "going to kill..."
      kill -9 $PID
      echo "killing in progress..." 
    else 
      echo "no process is running"
  fi
  echo "end handling"
  exit 0;
}

cd ~/mkdev/bigdata/kafka_2.11-0.8.2.2
bin/zookeeper-server-start.sh config/zookeeper.properties &> /dev/null &
bin/kafka-server-start.sh config/server.properties &> /dev/null &

while true;     
  do sleep 1; 
done

echo "end"

