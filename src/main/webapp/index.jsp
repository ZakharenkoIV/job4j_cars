<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Car market</title>
</head>
<body>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script src="<c:url value="/js/script.js"/>"></script>

<jsp:include page="header.jsp"/>
<jsp:include page="/getAds"/>
<div class="mx-4 my-3 row">
    <c:if test="${sessionScope.authUser != null}">
        <c:if test="${requestScope.myAd == 'true'}">
            <div class="m-1">
                <a href="${pageContext.request.contextPath}/index.jsp">
                    <button type="button" class="btn btn-success" style="max-height: 38px">
                        <img src="${pageContext.request.contextPath}/image/home.svg" title="На главную"
                             alt="">
                    </button>
                </a>
            </div>
            <div class="m-1">
                <a href="${pageContext.request.contextPath}/Ad/add.jsp">
                    <button type="button" class="btn btn-success">Добавить объявление</button>
                </a>
            </div>
        </c:if>
        <c:if test="${requestScope.myAd != 'true'}">
            <div class="m-1">
                <a href="${pageContext.request.contextPath}/index.jsp?myAd=true">
                    <button type="button" class="btn btn-success">Мои объявления</button>
                </a>
            </div>
            <div class="m-1">
                <a href="${pageContext.request.contextPath}/Ad/add.jsp">
                    <button type="button" class="btn btn-success">Добавить объявление</button>
                </a>
            </div>
        </c:if>
    </c:if>
</div>
<h3 class="text-center my-5">Объявления по продаже машин</h3>
<div class="container h-100" id="adCards">
    <c:forEach var="ad" items="${requestScope.allAds}">
        <div class="border rounded row h-100 justify-content-center align-items-center my-2" id="ad${ad.id}">
            <div class="text-center col-sm-4">
                <div id="carousel" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carousel" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel" data-slide-to="1"></li>
                        <li data-target="#carousel" data-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner">
                        <c:if test="${ad.photos.size() == 0}">
                            <div class="carousel-item active">
                                <img class=" img-fluid rounded" style="max-height: 200px"
                                     src="${pageContext.request.contextPath}/imageServlet"
                                     alt="..."/>
                            </div>
                        </c:if>
                        <c:forEach var="photo" items="${ad.photos}">
                            <div class="carousel-item active">
                                <img class="p-3 img-fluid rounded" style="max-height: 200px"
                                     src="${pageContext.request.contextPath}/imageServlet?p1=${ad.id}&p2=${photo.path}"
                                     alt="..."/>
                            </div>
                        </c:forEach>
                    </div>
                    <a class="carousel-control-prev" href="#carousel" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Предыдущий</span>
                    </a>
                    <a class="carousel-control-next" href="#carousel" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Следующий</span>
                    </a>
                </div>
            </div>
            <div class="col-sm-4">
                Описание: <c:out value="${ad.description}"/><br/>
                Цена: <c:out value="${ad.price}"/> руб<br/>
                Марка: <c:out value="${ad.brandCar}"/><br/>
                Тип кузова: <c:out value="${ad.bodyType}"/><br/>
                Цвет: <c:out value="${ad.color}"/><br/>
                Пробег: <c:out value="${ad.mileage}"/> км<br/>
                В продаже: <c:out value="${ad.sale}"/><br/>
                Размещено: <c:out value="${ad.created}"/><br/>
            </div>
            <div class="text-center col-sm-4">
                Продавец: <c:out value="${ad.user.name}"/><br/>
                email: <c:out value="${ad.user.email}"/><br/>
                Телефон: <c:out value="${ad.phoneNumber}"/><br/>

                <c:if test="${sessionScope.authUser.id == ad.user.id}">
                    <div class="p-3 row justify-content-md-center">
                        <div class="m-1">
                            <a href="${pageContext.request.contextPath}/Ad/edit?p=${ad.id}">
                                <button type="button" class="btn btn-success" title="Изменить">
                                    <img src="${pageContext.request.contextPath}/image/edit.svg" alt="">
                                </button>
                            </a>
                        </div>
                        <div class="m-1">
                            <button type="button" class="btn btn-success" title="Удалить" onclick="deleteAd(${ad.id})">
                                <img src="${pageContext.request.contextPath}/image/x.svg" alt="">
                            </button>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>