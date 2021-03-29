<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
    <head>
        <%@ page contentType="text/html; charset=UTF-8" %>
        <title>ログイン</title>
        <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <form:form method="POST" action="${contextPath}/" modelAttribute="loginForm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group">
                    <span>${error}</span>
                </div>
                <div class="form-group">
                    <div class="form-label">
                        ログインID:
                    </div>
                    <div class="form-input">
                        <input name="username" type="text" class="form-control" />
                        <form:errors path="username"></form:errors>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label">
                        パスワード:
                    </div>
                    <div class="form-input">
                        <input name="password" type="password" class="form-control"/>
                        <form:errors path="password"></form:errors>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label">
                        &nbsp
                    </div>
                    <div class="form-input">
                        <button type="submit">ログイン</button>
                        <div><a href="${contextPath}/registration" class="btn btn-lg btn-primary btn-block">アカウント新規登録</a></div>
                    </div>
                </div>
            </form:form>
        </div>
    </body>
</html>
