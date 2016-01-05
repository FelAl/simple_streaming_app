Вопросы

1. Что такое real time обработка? 2 часа это реалтайм? А 10 секунд? А 10мс? Где граница?
2. Как подсчитать максимальную и минимальную задержку между появление сообщения в кафка producer и появлением результат обработки этого сообщения в нашем приложении(терминал, база, распределенная память)?
3.Почему Kafka быстро компилируется, а Spark долго?
4. Как перед завершением работы приложения выполнить какой-то код?
5. Добавьте вывод дополнительной информации как в коде ниже. Что написало приложение?
``` scala
import org.apache.log4j.Logger
val Log = Logger.getLogger(TestApp.this.getClass().getSimpleName())
Log.info(“DEBUG info:” + zkQuorum)
```

1. Корректно обрабатывать SIGTERM который пошлется тобой же, дорогой коллега, нажатием клавиш ctrl+c


2. Скомпилировать Spark при помощи zinc (`./build/zinc-0.3.5.3/bin/zinc -start`), засечь время и сравнить со временем компиляции без zinc.




Как долго компилировался spark?

`mvn -Pyarn -Phadoop-2.4 -Dscala-2.11 -DskipTests clean package`

```sh
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO] 
[INFO] Spark Project Parent POM ........................... SUCCESS [  6.618 s]
[INFO] Spark Project Launcher ............................. SUCCESS [ 17.626 s]
[INFO] Spark Project Networking ........................... SUCCESS [ 18.188 s]
[INFO] Spark Project Shuffle Streaming Service ............ SUCCESS [ 10.205 s]
[INFO] Spark Project Unsafe ............................... SUCCESS [ 31.433 s]
[INFO] Spark Project Core ................................. SUCCESS [05:47 min]
[INFO] Spark Project Bagel ................................ SUCCESS [01:51 min]
[INFO] Spark Project GraphX ............................... SUCCESS [08:55 min]
[INFO] Spark Project Streaming ............................ SUCCESS [19:47 min]
[INFO] Spark Project Catalyst ............................. SUCCESS [34:54 min]
[INFO] Spark Project SQL .................................. SUCCESS [42:29 min]
[INFO] Spark Project ML Library ........................... SUCCESS [  01:08 h]
[INFO] Spark Project Tools ................................ SUCCESS [01:31 min]
[INFO] Spark Project Hive ................................. SUCCESS [31:38 min]
[INFO] Spark Project REPL ................................. SUCCESS [02:07 min]
[INFO] Spark Project YARN ................................. SUCCESS [06:42 min]
[INFO] Spark Project Assembly ............................. SUCCESS [07:03 min]
[INFO] Spark Project External Twitter ..................... SUCCESS [01:26 min]
[INFO] Spark Project External Flume Sink .................. SUCCESS [01:31 min]
[INFO] Spark Project External Flume ....................... SUCCESS [03:21 min]
[INFO] Spark Project External Flume Assembly .............. SUCCESS [ 26.836 s]
[INFO] Spark Project External MQTT ........................ FAILURE [ 16.490 s]
[INFO] Spark Project External MQTT Assembly ............... SKIPPED
[INFO] Spark Project External ZeroMQ ...................... SKIPPED
[INFO] Spark Project External Kafka ....................... SKIPPED
[INFO] Spark Project Examples ............................. SKIPPED
[INFO] Spark Project External Kafka Assembly .............. SKIPPED
[INFO] Spark Project YARN Shuffle Service ................. SKIPPED
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
```

4 часа ушло


после zink start
``` sh
[INFO] Reactor Summary:
[INFO] 
[INFO] Spark Project Parent POM ........................... SUCCESS [ 16.514 s]
[INFO] Spark Project Launcher ............................. SUCCESS [ 33.591 s]
[INFO] Spark Project Networking ........................... SUCCESS [ 40.967 s]
[INFO] Spark Project Shuffle Streaming Service ............ SUCCESS [ 13.633 s]
[INFO] Spark Project Unsafe ............................... SUCCESS [ 35.521 s]
[INFO] Spark Project Core ................................. SUCCESS [04:48 min]
[INFO] Spark Project Bagel ................................ SUCCESS [ 11.149 s]
[INFO] Spark Project GraphX ............................... SUCCESS [ 25.140 s]
[INFO] Spark Project Streaming ............................ SUCCESS [ 54.893 s]
[INFO] Spark Project Catalyst ............................. SUCCESS [01:13 min]
[INFO] Spark Project SQL .................................. SUCCESS [01:33 min]
[INFO] Spark Project ML Library ........................... SUCCESS [01:45 min]
[INFO] Spark Project Tools ................................ SUCCESS [  4.596 s]
[INFO] Spark Project Hive ................................. SUCCESS [02:06 min]
[INFO] Spark Project REPL ................................. SUCCESS [  8.673 s]
[INFO] Spark Project YARN ................................. SUCCESS [ 15.660 s]
[INFO] Spark Project Assembly ............................. SUCCESS [02:19 min]
[INFO] Spark Project External Twitter ..................... SUCCESS [ 15.445 s]
[INFO] Spark Project External Flume Sink .................. SUCCESS [ 11.188 s]
[INFO] Spark Project External Flume ....................... SUCCESS [ 17.299 s]
[INFO] Spark Project External Flume Assembly .............. SUCCESS [  5.609 s]
[INFO] Spark Project External MQTT ........................ FAILURE [  0.652 s]
[INFO] Spark Project External MQTT Assembly ............... SKIPPED
[INFO] Spark Project External ZeroMQ ...................... SKIPPED
[INFO] Spark Project External Kafka ....................... SKIPPED
[INFO] Spark Project Examples ............................. SKIPPED
[INFO] Spark Project External Kafka Assembly .............. SKIPPED
[INFO] Spark Project YARN Shuffle Service ................. SKIPPED
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 19:02 min
[INFO] Finished at: 2016-01-05T18:15:28+03:00
[INFO] Final Memory: 69M/761M
[INFO] ------------------------------------------------------------------------
```


20 минут чтобы дойти до того же шага.



