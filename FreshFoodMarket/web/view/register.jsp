<%-- 
    Document   : register
    Created on : Jun 1, 2022, 3:23:10 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Tạo tài khoản
        </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="icon" type="image/png" href="images/icons/favicon.ico" />

        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">

        <link rel="stylesheet" type="text/css" href="css/main.css">

        <link rel="stylesheet" type="text/css" href="css/util.css">


    </head>

    <body>
        <div class="limiter">
            <div class="container-login100" style="background-image: url('img/logo/anh2.jpg');">
                <div class="wrap-login100 p-l-55 p-r-55 p-t-20 p-b-20">
                    <form class="login100-form validate-form" action="/FreshFoodMarket/registerController?action=register" method="POST">
                        <span class="login100-form-title p-b-20">
                            <img class="logo" src="img/logo/Logo2.png" alt="">
                            <div style="text-align: center;"><p class="text-danger" style="color: red;">${mess}</p></div>
                        </span>
                        <h4>Nhập thông tin tài khoản</h4>
                        <div class="wrap-input100 validate-input " data-validate="Full Name ">
                            <input class="input100" type="text" name="cusName" placeholder="Họ và tên">
                        </div>
                        <div style="margin: 20px 0 0 0; width:100%;font-family: calibri;">
                            <input style="margin-right: 10px;" name="gender" type="radio" checked="checked" value="1"/>Nam
                            <input style="margin: 0 10px 0 40px;" name="gender" type="radio" value="0"/>Nữ
                        </div> 
                        <div class="wrap-input100 validate-input" data-validate="Phone">
                            <input class="input100" type="text" name="cusPhone" placeholder="Số điện thoại">
                        </div>
                        <div class="wrap-input100 validate-input" data-validate="Email">
                            <input class="input100" type="text" name="email" placeholder="Email">
                        </div>
                        <div class="wrap-input100 validate-input" data-validate="Address">
                            <input class="input100" type="text" name="cusAddress" placeholder="Địa chỉ">
                        </div>

                        <div class="wrap-input100 validate-input " data-validate="Username">
                            <input class="input100" type="text" name="username" placeholder="Tên đăng nhập">
                        </div>
                        <div class="wrap-input100 validate-input " data-validate="Password">
                            <input id="password1" class="input100" type="password" name="password" placeholder="Mật khẩu">
                        </div>
                        <div class="wrap-input100 validate-input " data-validate="Corfirm Password ">
                            <input  id="password2" class="input100" type="password" name="Confirm password" placeholder="Nhập lại mật khẩu">
                        </div>
                        <div class="container-login100-form-btn" style="margin: 60px 0 20px 0 ;">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button class="login100-form-btn" type="submit" name ="submit">
                                    Tạo tài khoản
                                </button>
                            </div>
                        </div>   
                    </form>
                    <div class="text-right " style="margin-left: 40%; margin-right: 40%; width: 20%;">
                        <a href="/FreshFoodMarket/loginController?action=0">Đăng Nhập</a>
                    </div> 
                </div>
            </div>
        </div>
        <script type="text/javascript">
            window.onload = function () {
                document.getElementById("password1").onchange = validatePassword;
                document.getElementById("password2").onchange = validatePassword;
            }
            function validatePassword() {
                var pass2 = document.getElementById("password2").value;
                var pass1 = document.getElementById("password1").value;
                if (pass1 != pass2)
                    document.getElementById("password2").setCustomValidity("Mật khẩu không trùng khớp!");
                else
                    document.getElementById("password2").setCustomValidity('');
                //empty string means no validation error
            }
        </script>
    </body>

</html>
