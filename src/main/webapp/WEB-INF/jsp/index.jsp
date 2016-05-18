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
    <title>Analyze emails with Elasticsearch</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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

<c:if test="${numImported > 0}">
    <div class="alert alert-success">
        <p>
            The import was successful and added ${numImported} emails to the database.
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
    <%@ include file="analyze_form.jsp" %>
</c:if>

<!-- ------------------------------------- -->
<div class="centered">
    <p>
        Copyright © 2016 Jörn Dinkla, <a href="http://www.dinkla.com">www.dinkla.com</a>
    </p>
</div>

</body>
</html>
