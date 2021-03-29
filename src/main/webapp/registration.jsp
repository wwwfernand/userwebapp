<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
    <head>
        <%@ page contentType="text/html; charset=UTF-8" %>
        <title>アカウント新規登録</title>
        <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    </head>

    <body>
        <div class="container">
            <form:form method="POST" modelAttribute="userForm">
                <div class="form-group">
                    <div class="form-label">
                        ログインID:
                    </div>
                    <spring:bind path="username">
                        <div class="form-input">
                            <form:input type="text" path="username" class="form-control" ></form:input>
                            <form:errors path="username"></form:errors>
                        </div>
                    </spring:bind>
                </div>
                <div class="form-group">
                    <div class="form-label">
                        パスワード:
                    </div>
                    <spring:bind path="password">
                        <div class="form-input">
                            <form:input type="password" path="password" class="form-control"></form:input>
                            <form:errors path="password"></form:errors>
                        </div>
                    </spring:bind>
                </div>
                <div class="form-group">
                    <div class="form-label">
                        &nbsp
                    </div>
                    <div class="form-input">
                        <button type="submit">登録</button>
                    </div>
                </div>
            </form:form>
        </div>
    </body>
</html>
