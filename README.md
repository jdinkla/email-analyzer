# Email analyzer

This [Spring Boot](http://projects.spring.io/spring-boot/) web app reads emails from a POP3/IMAP-Provider, stores them in the
[Elasticsearch database](https://www.elastic.co/products/elasticsearch)
and analyzes them with the [Spring Data Elasticsearch](http://projects.spring.io/spring-data-elasticsearch/) framework.
The graph is drawn with [Plotly](https://plot.ly/).

There is further documentation at [Jörn Dinkla's homepage](http://www.dinkla.net/de/programming/spring-boot-elasticsearch-dev.html).

This code is meant as a prototype for learning, not a production ready software product.

Written by [Jörn Dinkla](www.dinkla.com), see [www.dinkla.com](www.dinkla.com).

## Overview

The following frameworks and libraries were used:

* [Spring Boot](http://projects.spring.io/spring-boot/) is the basis
* [Spring Framework](http://projects.spring.io/spring-framework/)
* [Spring MVC](http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc) for the web app
* [Spring Data](http://projects.spring.io/spring-data/) for handling data
* [Spring Data Elasticsearch](http://projects.spring.io/spring-data-elasticsearch/) for storing data in Elasticsearch
* [Elasticsearch database](https://www.elastic.co/products/elasticsearch) the database and search engine
* [Elasticsearch Java API](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html) for user defined queries in Elasticsearch
* [JavaMail API](https://java.net/projects/javamail/pages/Home) for reading emails from a provider
* [Jackson JSON](https://github.com/FasterXML/jackson) for converting data to and from JSON
* [Bootstrap](http://getbootstrap.com/) for the nice HTML5/CSS3 layout
* [Plotly](https://plot.ly/) for drawing the chart
* [gradle](http://gradle.org/) is used as a make tool
* [Spock](http://spockframework.org/) is used as the unit testing framework
* [Groovy](http://www.groovy-lang.org/) the Groovy programming language
* [Docker](https://www.docker.com/) Docker as a container

### Running the application

You can start all the services with [docker](https://www.docker.com/) and 
[docker-compose](https://docs.docker.com/compose/).

Use the following command:

```
$ docker-compose up
```

### Using the application

Point your browser to [`http://localhost:8080/`](http://localhost:8080/) and follow the screens shown and explained
[in the documentation](http://www.dinkla.net/de/programming/spring-boot-elasticsearch-doc.html)

### Optional: Analysing emails with kibana

[kibana](https://www.elastic.co/products/kibana) is a tool for analysing large data sets.

Point your browser to [`http://localhost:5601/`](http://localhost:5601/) for kibana and create the index pattern
as shown in [in the documentation](http://www.dinkla.net/de/programming/spring-boot-elasticsearch-doc.html).

### Cleanup

Stop the `docker-compose` with Control-C.

### Further reading

See the [documentation](http://www.dinkla.net/de/programming/spring-boot-elasticsearch-dev.html).

---
(c) 2016, 2018 Jörn Dinkla, [www.dinkla.com](http://www.dinkla.com)
