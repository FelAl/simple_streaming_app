1) Запускаем zookeper
Запуск примера KafkaWordCount 



``` sh
alfe@rt:~/mkdev/bigdata/kafka_2.11-0.8.2.2$ bin/zookeeper-server-start.sh config/zookeeper.properties
```
2) Запускаем kafka server
```sh
alfe@rt:~/mkdev/bigdata/kafka_2.11-0.8.2.2$ bin/kafka-server-start.sh config/server.properties
```

3) Запускаем наш пример

Официальный пример
```sh
alfe@rt:~/mkdev/bigdata/compilation/spark-1.6.0$ bin/run-example org.apache.spark.examples.streaming.KafkaWordCount localhost:2181 test-consumer-group test 1
```

Моё приложение

```sh
./bin/spark-submit --class  SimpleApp --master local[4] /home/af/mkdev/bigdata/simple_streaming_app/target/scala-2.11/SimpleApp-assembly-1.0.jar  localhost:2181 test-consumer-group test 1
```



4) Начинаем писать сообщения в topic `test`

```sh
Calfe@rt:~/mkdev/bigdata/kafka_2.11-0.8.2.2$ bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
```

5) Эти сообщения будут видны в консоли,где мы запустили `bin/run-example...`





чтобы заработало
```scala
import org.apache.log4j.Logger
val Log = Logger.getLogger(TestApp.this.getClass().getSimpleName())
Log.info(“DEBUG info:” + zkQuorum)
```

для настройки логов перед spark-submit
export SPARK_SUBMIT_OPTS=-Dlog4j.configuration=file:/home/alfe/mkdev/bigdata/kafka_2.11-0.8.2.2/config/my_log4j.properties

в my_log4j.properties
```sh
alfe@rt:~/mkdev/bigdata/simple_streaming_app$ cat /home/alfe/mkdev/bigdata/kafka_2.11-0.8.2.2/config/my_log4j.properties
# Set everything to be logged to the console
log4j.rootCategory=INFO, console
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.target=System.err
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%d{yy/MM/dd HH:mm:ss} %p %c{1}: %m%n
```




for f in `ls -R`; do cat "$f" | perl -e "$/=undef;print<>=~/((?:\/\*(?:[^*]|(?:\*+[^*\/]))*\*+\/)|(?:\/\/.*))/g;"; done



find . -name "*.scala" -print0 | xargs -0 cat 


uniq -c already does what you want, just sort the input:

echo 'a s d s d a s d s a a d d s a s d d s a' | tr ' ' '\n' | sort | uniq -c
output:

  6 a
  7 d
  7 s

find . -name "*.scala" -print0 | xargs -0 cat | tr ' ' '\n' | sort | uniq -c

https://github.com/scala/scala/blob/317a1056cd8062331964d1bc65f1bfd945538551/src/library/scala/sys/SystemProperties.scala


https://github.com/jjmeyer0/spark-streaming-example/blob/master/src/main/scala/com/jj/streaming/service/ItemAction.scala
https://github.com/jjmeyer0/spark-streaming-example/tree/master/src/main/scala/com/jj/streaming


https://www.gitbook.com/book/jaceklaskowski/mastering-apache-spark/details



http://stackoverflow.com/questions/31183221/write-an-rdd-into-hdfs-in-a-spark-streaming-context

https://community.hortonworks.com/questions/54802/save-kafka-spark-streaming-messages-into-single-fi.html



+++++++
http://stackoverflow.com/questions/29383578/how-to-convert-rdd-object-to-dataframe-in-spark