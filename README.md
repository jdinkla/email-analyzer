
# Email analyzer

TODO

* ES config an einer Stelle
* validation


Reads email from an Imap-Provider with [JavaMail](https://java.net/projects/javamail/pages/Home), loads
and analyzes them with [Elasticsearch](https://www.elastic.co/products/elasticsearch).

Written by [Jörn Dinkla](www.dinkla.com), see [www.dinkla.com](www.dinkla.com).

----
## Background


https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-nosql.html#boot-features-elasticsearch

----

## Installation

Run Elasticsearch in a container called `es-dev`

```
docker run -d -v $HOME/docker/elastic-dev:/usr/share/elasticsearch/data -p 9200:9200 -p 9300:9300 --name es-dev elasticsearch:1.7
```

or under Windows

```
docker run --name es-dev -d -v C:\virtual\docker\es-dev:/usr/share/elasticsearch/data -p 9200:9200 -p 9300:9300 elasticsearch:1.7
docker run --name es-dev -d -v C:/virtual/docker/es-dev:/usr/share/elasticsearch/data -p 9200:9200 -p 9300:9300 elasticsearch:1.7
```

Add [kibana](https://www.elastic.co/products/kibana) with

```
docker run --name kibana-dev --link es-dev:elasticsearch -p 5601:5601 kibana:4.1
```

### Access via web browser

Point your browser to [`http://localhost:9200/`](http://localhost:9200/) for Elasticsearch
and [`http://localhost:5601/`](http://localhost:5601/) for kibana.

### Stopping the container

```
docker stop kibana-dev
docker stop es-dev
```

### Restarting the container

```
docker start es-dev
docker start kibana-dev
```

----

# Useful links

If you want to learn more about the tools used:

* [Spring Boot documentation](x)
* [Spring Data Elasticsearch documentation](http://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/)
* [Elasticsearch Java API](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html)
* [Bootstrap](http://getbootstrap.com/getting-started/)

---
(c) 2016 Jörn Dinkla, [www.dinkla.com](http://www.dinkla.com)





