# Email analyzer

Reads email from an Imap-Provider with [JavaMail](https://java.net/projects/javamail/pages/Home), loads
and analyzes them with [Elasticsearch](https://www.elastic.co/products/elasticsearch).

This app is the result of an experiment/learning with

* [Spring Boot](http://projects.spring.io/spring-boot/)
* [Spring Framework](http://projects.spring.io/spring-framework/)
* [Elasticsearch database](https://www.elastic.co/products/elasticsearch)
* [Spring Data Elasticsearch](http://projects.spring.io/spring-data-elasticsearch/)
* [Elasticsearch Java API](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html)
* [JavaMail API](https://java.net/projects/javamail/pages/Home)
* [Jackson JSON](https://github.com/FasterXML/jackson)
* [Spring MVC](http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc)
* [Spring Data](http://projects.spring.io/spring-data/)
* [Bootstrap](http://getbootstrap.com/)

Written by [Jörn Dinkla](www.dinkla.com), see [www.dinkla.com](www.dinkla.com).

----

### Installation

You need a running Elasticsearch 1.x instance for this app to work. You can simply run Elasticsearch in a docker
container (for example called `es-dev`)

```
$ docker run -d -v LOCAL_DIRECTORY:/usr/share/elasticsearch/data -p 9200:9200 -p 9300:9300 --name es-dev elasticsearch:1.7
```

[Spring Data Elasticsearch](http://projects.spring.io/spring-data-elasticsearch/) does not support Elasticsearch 2.X
at the time of writing. Therefore version 1.7 of Elasticsearch has to be used.

Then edit the file `application.properties` in the directory `src/main/resources` and set the host name or IP adress.

```
spring.data.elasticsearch.cluster-nodes=10.0.75.2:9300
```

Now you can start the application in the source directory with

```
$ gradle bootRun
```

### Running the application

Point your browser to [`http://localhost:8080/`](http://localhost:8080/) and follow the screens shown and explained
[in the documentation](http://www.dinkla.net/en/programming/spring-boot-elasticsearch.html)

### Cleanup

Stop the gradle application by interruping it with CTRL-C and stop the docker container with

```
$ docker stop es-dev
```

### Further reading

See the [documentation](http://www.dinkla.net/en/programming/spring-boot-elasticsearch.html).

### Optional: Analysis with kibana

[kibana](https://www.elastic.co/products/kibana) is a tool for ...

You can start a kibana container with docker (the elasticsearch container `es-dev` has to be linked to this container)

```
$ docker run -d --name kibana-dev --link es-dev:elasticsearch -p 5601:5601 kibana:4.1
```

Point your browser to [`http://localhost:5601/`](http://localhost:5601/) for kibana and create the index pattern
as shown in [in the documentation](http://www.dinkla.net/en/programming/spring-boot-elasticsearch.html).

After analysis you can stop the container with
 ```
$ docker stop kibana-dev
```

---
(c) 2016 Jörn Dinkla, [www.dinkla.com](http://www.dinkla.com)





