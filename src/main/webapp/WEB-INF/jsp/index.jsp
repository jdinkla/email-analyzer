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

    <p>Say something about Elasticsearch.</p>

</div>

<!-- ------------------------------------- -->
<h2>Emails in Elasticsearch</h2>

<div class="box">

    <p>
        Connection to Elasticsearch
    </p>

    <table>
        <tr>
            <td>nodes</td>
            <td>${esNodes}</td>
            <td>spring.data.elasticsearch.cluster-nodes</td>
        </tr>
        <tr>
            <td>index</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>type</td>
            <td></td>
            <td></td>
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

<!-- ------------------------------------- -->
<form:form action="import" method="post" modelAttribute="emailServerProperties">

    <div class="box">

        <p>
            Connection data for email storage: POP3 / IMAP / JavaMail
        </p>

        <p>
            <strong>Warning:</strong> the password is transported unencrypted to web server. Only use in a private
            and secure subnet.
        </p>

        <table>
            <tr>
                <td>protocol</td>
                <td><form:input type="text" path="protocol" /></td>
                <td><span name="protocol.errors">Field is required.</span></td>
            </tr>
            <tr>
                <td>host</td>
                <td><form:input type="text" path="host" /></td>
                <td><span name="host.errors">Field is required.</span></td>
            </tr>
            <tr>
                <td>user</td>
                <td><form:input type="text" path="user" /></td>
                <td><span name="user.errors">Field is required.</span></td>
            </tr>
            <tr>
                <td>password</td>
                <td><form:input type="text" path="password" /></td>
                <td><span name="password.errors">Field is required.</span></td>
            </tr>
            <tr>
                <td>folder</td>
                <td><form:input type="text" path="folder" /></td>
                <td><span name="folder.errors">Field is required.</span></td>
            </tr>
        </table>

    </div>

    <button class="btn btn-primary" type="submit">Import emails from mail server</button>

</form:form>

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
