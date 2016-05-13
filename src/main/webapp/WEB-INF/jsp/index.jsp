<%--
  Created by IntelliJ IDEA.
  User: Dinkla
  Date: 13.05.2016
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- %@ taglib prefix="spring" uri="http://www.springframework.org/tags" %-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Analyze emails with Elasticsearch</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" media="all" type="text/css" />

</head>

<body>

<!-- ------------------------------------- -->
<h1>Analyze emails with Elasticsearch</h1>

<div class="box">

    <p>
        This prototype shows how to read emails from an IMAP/POP3 provider into Elasticsearch and how to analyze them with
        custom queries. The app is implemented with Spring Boot, Spring Data Elasticsearch, Spring MVC and JavaMail.
        JSON data is processed with the Jackson library.

        The source code is available at <a href="http://github.com/jdinkla/">github</a>.
    </p>

</div>

<!-- ------------------------------------- -->
<h2>Emails loaded into Elasticsearch</h2>

<div class="box">

    <p>
        The connection to Elasticsearch is:
    </p>

    <table>
        <tr>
            <td>node(s)</td>
            <td>${esNodes}</td>
        </tr>
        <tr>
            <td>index</td>
            <td>${esIndex}</td>
        </tr>
        <tr>
            <td>type</td>
            <td>${esType}</td>
        </tr>
    </table>

</div>


<c:if test="${numberOfEmails == 0}">
    <div class="alert alert-warning">
        <p>
            There are no emails in the database. You have to import them.
        </p>
    </div>
</c:if>

<c:if test="${numLoaded > 0}">
    <div class="alert alert-success">
        <p>
            The import was successful and added ${numLoaded} emails to the database.
        </p>
    </div>
</c:if>

<c:if test="${numberOfEmails > 0}">
    <div class="alert alert-info">
        <p>
            There are ${numberOfEmails} emails loaded.
        </p>
    </div>

    <form action="/delete" method="post">
        <button class="btn btn-danger" type="submit">Delete emails in database</button>
    </form>
</c:if>

<div class="padded_vert">
    <form action="/import" method="get">
        <button class="btn btn-primary" type="submit" >Import emails from mail server</button>
    </form>
</div>

<!-- ------------------------------------- -->
<c:if test="${numberOfEmails > 0}">
    <h2>Analyse emails</h2>

    <form action="/analyse" method="post">

        <div class="box">

            <p>
                Enter the topics seperated by white space:
            </p>

            <table>
                <tr>
                    <td>topics</td>
                    <td><input type="text" name="topics" size="40" /></td>
                    <td><span name="protocol.errors">Field is required.</span></td>
                </tr>

            </table>

        </div>

        <button class="btn btn-success" type="submit">Analyze</button>

    </form>

    <div id="graph">
    </div>

</c:if>


<!-- ------------------------------------- -->
<div class="centered">
    <p>
        Copyright © 2016 Jörn Dinkla, <a href="http://www.dinkla.com">www.dinkla.com</a>
    </p>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

</body>
</html>
