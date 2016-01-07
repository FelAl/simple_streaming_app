Вопросы

1. Что такое real time обработка? 2 часа это реалтайм? А 10 секунд? А 10мс? Где граница?
Real time обработка подразумевает такую обработку, информация об успешности и состоянии которой непрерывно предоставляется конечному пользователю без его запроса. То есть он может куда-то зайти, посмотреть текущее состояние и наблюдать его изменение.(например, на веб странице с заказом статус: готовность 80%, а через 30 секунд готовность 90%, и всё это без перезагрузки страницы)
В зависимости от вида этой обработки, есть приемлимые временные интервалы. Например, если пользователь зашёл на сервис и хочет сделать перекодирвку какого-нибудь hd видео, это может занять несколько часов, но всё равно может быть сделано в real time.
Также, если пользователь ожидает какой-то текстовый шаблон заявления/справки, после того как ввёл необходимые данные, то запрос может обработаться в течение пары секунд и это тоже real time. Поэтому границы нет.
Ещё в real time обработке могут быть снижены требования к длительности хранения данных и к самому хранению данных. Например, после обработки hd видео в предыдущем примере, само видео может быть сжато и храниться всего пару дней или же может вообще не храниться ни в каком виде, при этом просто будет информация в логах, что происходила перекодировка видео, но исходного файла при этом не будет.
2. Как подсчитать максимальную и минимальную задержку между появление сообщения в кафка producer и появлением результат обработки этого сообщения в нашем приложении(терминал, база, распределенная память)?
3. Почему Kafka быстро компилируется, а Spark долго?
```sh
alfe@rt:~/Downloads$ ls -alth kafka* spark*
-rw-r----- 1 alfe alfe 1,6M янв.   6 14:20 kafka-0.9.0.0-src.tgz
-rw-r----- 1 alfe alfe  12M янв.   6 01:31 spark-1.6.0.tgz
```
Размер исходников Spark почти в 10 раз больше чем kafka.
Kafka занимается просто доставкой сообщений, в то время как spark предназначен для обработки данных, что является более общей задачей по сравнению с тем что делает kafka.
4. Как перед завершением работы приложения выполнить какой-то код?
5. Добавьте вывод дополнительной информации как в коде ниже. Что написало приложение?
``` scala
import org.apache.log4j.Logger
val Log = Logger.getLogger(TestApp.this.getClass().getSimpleName())
Log.info(“DEBUG info:” + zkQuorum)
```

```sh
...
16/01/07 13:34:29 INFO SimpleApp$: DEBUG info:localhost:2181
...
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





А это итоговое время полной компиляции с zinc

```sh
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO] 
[INFO] Spark Project Parent POM ........................... SUCCESS [ 25.316 s]
[INFO] Spark Project Test Tags ............................ SUCCESS [  6.353 s]
[INFO] Spark Project Launcher ............................. SUCCESS [ 17.092 s]
[INFO] Spark Project Networking ........................... SUCCESS [ 20.136 s]
[INFO] Spark Project Shuffle Streaming Service ............ SUCCESS [ 11.476 s]
[INFO] Spark Project Unsafe ............................... SUCCESS [ 11.647 s]
[INFO] Spark Project Core ................................. SUCCESS [03:06 min]
[INFO] Spark Project Bagel ................................ SUCCESS [  9.876 s]
[INFO] Spark Project GraphX ............................... SUCCESS [ 20.941 s]
[INFO] Spark Project Streaming ............................ SUCCESS [ 56.049 s]
[INFO] Spark Project Catalyst ............................. SUCCESS [01:34 min]
[INFO] Spark Project SQL .................................. SUCCESS [02:08 min]
[INFO] Spark Project ML Library ........................... SUCCESS [01:54 min]
[INFO] Spark Project Tools ................................ SUCCESS [ 15.521 s]
[INFO] Spark Project Hive ................................. SUCCESS [01:56 min]
[INFO] Spark Project Docker Integration Tests ............. SUCCESS [  4.785 s]
[INFO] Spark Project REPL ................................. SUCCESS [  7.665 s]
[INFO] Spark Project YARN Shuffle Service ................. SUCCESS [ 15.448 s]
[INFO] Spark Project YARN ................................. SUCCESS [ 17.823 s]
[INFO] Spark Project Assembly ............................. SUCCESS [02:16 min]
[INFO] Spark Project External Twitter ..................... SUCCESS [ 12.221 s]
[INFO] Spark Project External Flume Sink .................. SUCCESS [ 10.482 s]
[INFO] Spark Project External Flume ....................... SUCCESS [ 15.036 s]
[INFO] Spark Project External Flume Assembly .............. SUCCESS [  4.737 s]
[INFO] Spark Project External MQTT ........................ SUCCESS [ 49.188 s]
[INFO] Spark Project External MQTT Assembly ............... SUCCESS [  9.463 s]
[INFO] Spark Project External ZeroMQ ...................... SUCCESS [ 15.333 s]
[INFO] Spark Project External Kafka ....................... SUCCESS [ 18.556 s]
[INFO] Spark Project Examples ............................. SUCCESS [03:29 min]
[INFO] Spark Project External Kafka Assembly .............. SUCCESS [ 11.563 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 22:56 min
[INFO] Finished at: 2016-01-06T03:02:14+03:00
[INFO] Final Memory: 408M/1533M
```