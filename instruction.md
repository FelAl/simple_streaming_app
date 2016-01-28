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
./bin/spark-submit --class  SimpleApp --master local[4] /home/alfe/mkdev/bigdata/simple_streaming_app/target/simple_streaming_app-1.0-SNAPSHOT.jar  localhost:2181 test-consumer-group test 1
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

