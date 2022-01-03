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

    <title>Edit Ad</title>
</head>
<body>

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

<div class="mx-4 my-3 row">
    <div class="m-1">
        <a href="${pageContext.request.contextPath}/index.jsp">
            <button type="button" class="btn btn-success" style="max-height: 38px">
                <img src="${pageContext.request.contextPath}/image/home.svg" title="На главную"
                     alt="">
            </button>
        </a>
    </div>
</div>
<h3 class="text-center my-3">Изменить объявление</h3>
<div class="container my-3">
    <form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/Ad/edit?p=${requestScope.ad.id}"
          accept-charset="utf-8">
        <div class="form-group">
            <label for="inputDescription">Описание</label>
            <input type="text" class="form-control" id="inputDescription" placeholder="Описание"
                   name="inputDescription" value="${requestScope.ad.description}">
        </div>
        <div class="form-group">
            <label for="inputPrice">Цена</label>
            <input type="text" class="form-control" id="inputPrice" placeholder="Цена" name="inputPrice"
                   value="${requestScope.ad.price}">
        </div>
        <div class="form-group">
            <label for="inputPhoneNumber">Номер телефона</label>
            <input type="text" class="form-control" id="inputPhoneNumber" placeholder="Цена" name="inputPhoneNumber"
                   value="${requestScope.ad.phoneNumber}">
        </div>
        <div class="form-group">
            <label for="inputMileage">Пробег</label>
            <input type="text" class="form-control" id="inputMileage" placeholder="Пробег" name="inputMileage"
                   value="${requestScope.ad.mileage}">
        </div>
        <div class="form-group">
            <label for="inputColor">Цвет</label>
            <input type="text" class="form-control" id="inputColor" placeholder="Цвет" name="inputColor"
                   value="${requestScope.ad.color}">
        </div>
        <div class="form-group">
            <label for="inputBrandCar">Марка</label>
            <input type="text" class="form-control" id="inputBrandCar" placeholder="Марка" name="inputBrandCar"
                   value="${requestScope.ad.brandCar}">
        </div>
        <div class="form-group">
            <label for="inputBodyType">Тип кузова</label>
            <input type="text" class="form-control" id="inputBodyType" placeholder="Тип кузова" name="inputBodyType"
                   value="${requestScope.ad.bodyType}">
        </div>
        <div class="form-group">
            <label for="inputPhoto">Фото:</label>
            <input type="file" class="form-control-file" id="inputPhoto" name="inputPhoto">
        </div>
        <button type="submit" class="btn btn-primary">Изменить</button>
    </form>
</div>
</body>
</html>