<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<header class="card-header row">
    <c:if test="${sessionScope.authUser != null}">
        <div class="mx-3">
            ${sessionScope.authUser.name}
            |
            <a href="${pageContext.request.contextPath}/exit">Выход</a>
        </div>
    </c:if>
    <c:if test="${sessionScope.authUser == null}">
        <div class="mx-3">
            <a href="${pageContext.request.contextPath}/login.jsp">Вход</a>
            или
            <a href="${pageContext.request.contextPath}/reg.jsp">Регистрация</a>
        </div>
    </c:if>
</header>