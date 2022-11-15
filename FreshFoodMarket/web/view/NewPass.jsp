<%-- 
    Document   : NewPass
    Created on : Jul 4, 2022, 11:13:59 PM
    Author     : bao nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cài lại password</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    </head>
    <body>
        <c:set value="${accID}" var="accID"></c:set>
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0">Đổi mật khẩu</h3>
                <h5 style="color: red">${mess}</h5>
            </div>
            <div class="card-body">
                <form class="form" role="form" autocomplete="off" action="/FreshFoodMarket/newpass?accID=${accID}" method="post">
                    <div class="form-group">
                        <label for="inputPasswordNew">Mật khẩu mới</label>
                        <input type="password" name="newPass" class="form-control" id="inputPasswordNew" required="">
                        <span class="form-text small text-muted">
                            Mật khẩu 8-20 có cả chữ và số
                        </span>
                    </div>
                    <div class="form-group">
                        <label for="inputPasswordNewVerify">Xác nhận mật khẩu</label>
                        <input type="password" name="rePass" class="form-control" id="inputPasswordNewVerify" required="">
                        <span class="form-text small text-muted">
                            Xác nhận lại mật khẩu
                        </span>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-success btn-lg float-right">Lưu</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
