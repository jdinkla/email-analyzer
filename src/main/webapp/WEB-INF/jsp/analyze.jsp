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
    <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
</head>

<body>

<!-- ------------------------------------- -->
<h1>Analyze emails with Elasticsearch</h1>

<c:if test="${exception}">
    <div class="padded_vert">
        <div class="alert alert-danger">
            <p>
                An exception occurred during the analysis:
            </p>
            <p>
                '${exceptionText}'.
            </p>
        </div>
    </div>
</c:if>


<div class="box">

    <p id="theDataLength">
        The query returned ? elements with data for ? dates.
    </p>

    <div id="theChart" style="margin: 1em 0 1em 0;">
    </div>

    <p>
        This chart was created with <a href="https://plot.ly/">Plotly</a>.
    </p>
</div>

<%@ include file="analyze_form.jsp" %>

<script>
    var trace1 = {
        x: [1, 2, 3, 4],
        y: [10, 15, 13, 17],
        type: 'scatter',
        name: 'Java'
    };
    var trace2 = {
        x: [1, 2, 3, 4],
        y: [16, 5, 11, 9],
        type: 'scatter',
        mode: 'lines+markers',
        name: 'C++'
    };

    var trace3 = {
        x: ["2016-01-01 00:00:00.000000", "2016-02-01 00:00:00.000000", "2016-03-01 00:00:00.000000", "2016-04-01 00:00:00.000000"],
        y: [16, 5, 11, 9],
        type: 'scatter',
        mode: 'lines+markers',
        name: 'C++'
    };
    // "yyyy-mm-dd HH:MM:SS.ssssss"

    var layout = {
        title:'Number of emails containing each keyword',
        //autosize: true,
        xaxis: {
            title: 'Time',
            showgrid: true,
            zeroline: true
        },
        yaxis: {
            title: 'Number of emails',
            showline: true
        }
    };

    //var data = [trace1, trace2];
    var data = ${data}
    var numberOfSeries = data.length
    var numberOfDates = data[0].x.length
    $("#theDataLength").replaceWith("The query returned " + numberOfSeries + " elements with data for " + numberOfDates + " dates.")
    Plotly.newPlot('theChart', data, layout);
</script>

<!-- ------------------------------------- -->
<div class="centered">
    <p>
        Copyright © 2016 Jörn Dinkla, <a href="http://www.dinkla.com">www.dinkla.com</a>
    </p>
</div>

</body>
</html>
