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

    <title>Import emails into Elasticsearch</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" media="all" type="text/css" />

</head>

<body>

<!-- ------------------------------------- -->
<h1>Import emails into Elasticsearch</h1>

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

<!-- ------------------------------------- -->
<form:form action="import" method="post" modelAttribute="emailServerProperties">

    <div class="box">

        <p>
            Connection to the IMAP/POP3 email provider
        </p>

        <p>
            <strong>Warning:</strong> the password is transported unencrypted to the web server.
            Use only in a private and secure subnet.
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
