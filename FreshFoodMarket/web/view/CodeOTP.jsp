<%-- 
    Document   : ResetPass
    Created on : Jul 2, 2022, 10:00:31 PM
    Author     : bao nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quên mật khẩu</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4" style="background-image: url('img/logo/anh2.jpg');">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="text-center">
                                <h3><i class="fa fa-lock fa-4x"></i></h3>
                                <h2 class="text-center">Mã OTP</h2>
                                <p>Mã được gửi về Email.</p>
                                <h5 style="color: red">${mess}</h5>
                                <div class="panel-body">
                                    <c:set value="${otp}" var="otp"/>
                                    <form action="/FreshFoodMarket/otp?otp=${otp}&accID=${accID}" method="post">
                                        <c:set value="${accID}" var="accID"/>
<!--                                        <h4>${otp}</h4>
                                        <h4>${accID}</h4>-->
                                        <div class="form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class='fas fa-key'></i></span>
                                                <input  name="code" placeholder="code OTP" class="form-control"  type="text">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <input name="recover-submit" class="btn btn-lg btn-success  btn-block" value="Xác nhận" type="submit">
                                        </div>

                                        <input type="hidden" class="hide" name="token" id="token" value=""> 
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
