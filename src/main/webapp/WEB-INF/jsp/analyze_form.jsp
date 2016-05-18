<%--
  Created by IntelliJ IDEA.
  User: Dinkla
  Date: 18.05.2016
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2>Analyze emails</h2>

<form:form action="/analyze" method="post" class="form-horizontal" modelAttribute="analyzeParameters" >

    <div class="box">

        <p>
            Enter the topics separated by comma '<code>Java, Spring Boot, Scala</code>'
        </p>

        <table>
            <tr>
                <td>topics</td>
                <td><form:input type="text" path="topics" size="40" cssClass="form-control" /></td>
                <td class="validation-error"><form:errors path="topics" cssClass="validation-error" /></td>
            </tr>

        </table>

    </div>

    <button class="btn btn-success" type="submit">Analyze</button>

</form:form>

<div id="graph">
</div>
