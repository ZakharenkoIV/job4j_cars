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

    <title>Authorization</title>
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
<h3 class="text-center my-3">Регистрация</h3>
<div class="container my-3">
    <form method="POST" action="${pageContext.request.contextPath}/reg">
        <div class="form-group">
            <label for="regName">Имя</label>
            <input type="text" class="form-control" id="regName" name="regName" aria-describedby="emailHelp"
                   placeholder="Введите имя">
        </div>
        <div class="form-group">
            <label for="regEmail">Email</label>
            <input type="email" class="form-control" id="regEmail" name="regEmail" aria-describedby="emailHelp"
                   placeholder="Введите email">
        </div>
        <div class="form-group">
            <label for="regPassword">Пароль</label>
            <input type="password" class="form-control" id="regPassword" name="regPassword" placeholder="Введите пароль">
        </div>
        <div class="form-group">
            <label for="regPassword2">Повторите пароль</label>
            <input type="password" class="form-control" id="regPassword2" name="regPassword2" placeholder="Повторите пароль">
        </div>
        <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
    </form>
</div>
</body>
</html>
