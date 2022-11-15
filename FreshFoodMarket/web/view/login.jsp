<%-- 
    Document   : login
    Created on : Jun 1, 2022, 3:22:55 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA_Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>Đăng Nhập</title>
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">
        
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <link rel="stylesheet" text="text/css" href="css/main.css">
        <link rel="stylesheet" text="text/css" href="css/util.css">
    </head>

    <body>
        <div class="limiter">
            <div class="container-login100" style="background-image: url('img/logo/anh2.jpg');">
                <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54　form-login">
                    <form class="login100-form " action="/FreshFoodMarket/loginController?action=login" method="POST">
                        <span class="login100-form-title p-b-49" >
                            <div style="width: 100%; display: flex; justify-content: space-around;">
                                <img class="logo" src="img/logo/Logo2.png" alt="">
                            </div>
                            <br>
                            <div style="text-align: center;"><p class="text-danger" style="color: red;">${mess}</p></div>
                        </span>
                        <div class="wrap-input100 validate-input m-b-23" >
                            <span class="label-input100">Tên đăng nhập</span>
                            <input class="input100" type="text" name="username" placeholder="Tên đăng nhập">
                            <span class="focus-input100" ></span>
                        </div>
                        <div class="wrap-input100 validate-input" >
                            <span class="label-input100">Mật Khẩu</span>
                            <input class="input100" type="password" name="password" placeholder="Mật Khẩu">
                            <span class="focus-input100" ></span>
                        </div>

                        <div class="container-login100-form-btn" style="margin-top: 30px;">
                            <div class="wrap-login100-form-btn">
                                <div class="login100-form-bgbtn"></div>
                                <button class="login100-form-btn" type="submit">
                                    Đăng Nhập
                                </button>  

                            </div>
                        </div>   
                    </form>

                    <div class="container-login100-form-btn" style="margin: 20px auto;">
                        <div class="wrap-login100-form-btn">
                            <div class="login100-form-bgbtn"></div>
                            <a href="/FreshFoodMarket/homeController">
                                <button class="login100-form-btn">
                                    Trang Chủ
                                </button>  
                            </a>    
                        </div>
                    </div>
                    <div class="text-right p-t-8 p-b-15">
                        <a href="/FreshFoodMarket/resetpass">
                            Quên mật khẩu ?
                        </a>
                    </div>
                    <a href="/FreshFoodMarket/registerController?action=0" style="margin-left: 40%; margin-right: 40%; width: 20%;text-align: center; margin-bottom: 15px;">Tạo tài khoản</a>
                    <p style="height: 20px; width: 100%;"></p>
                </div>
            </div>
        </div>
        <div id="dropDownSelect1"></div>
    </body>
</html>
