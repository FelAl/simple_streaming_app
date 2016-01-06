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

```sh
alfe@rt:~/mkdev/bigdata/compilation/spark-1.6.0$ bin/run-example org.apache.spark.examples.streaming.KafkaWordCount localhost:2181 test-consumer-group test 1
```

4) Начинаем писать сообщения в topic `test`

```sh
Calfe@rt:~/mkdev/bigdata/kafka_2.11-0.8.2.2$ bin/kafka-console-producer.sh --broker-list localhost:90 --topic test
```

5) Эти сообщения будут видны в консоли,где мы запустили `bin/run-example...`