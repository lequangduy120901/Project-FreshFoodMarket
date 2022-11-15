<%-- 
    Document   : footer
    Created on : May 26, 2022, 3:20:53 AM
    Author     : Dinh Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
     <!--To import header into an other jsp file, copy the line below to your file--> 
     <%--  <jsp:include page="footer.jsp"></jsp:include>  --%> 


    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fresh Food Market</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <style>        
        .croplogo {
            width: 190px;
            height: 95px;
            object-fit: cover;
        }
        
        footer {
            background-color: #333;
            padding-top: 15px;
            padding-bottom: 15px;
            font-family: Roboto, sans-serif;
            width: 100vw;
        }
        
        .container {
            max-width: 1180px;
            background-color: #333;
        }
        
        .footer-text {
            font-weight: bold;
            color: rgb(255, 255, 255);
            margin: 0;
        }
    </style>
</head>

<body>
    <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <div class="footer__about">
                        <div class="footer__logo">
                            <a href="/FreshFoodMarket/homeController"><img class="croplogo" style="display: block; margin: 0 auto;" src="img/logo/Logo.png"></a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5 col-md-5 col-sm-7">
                    <h4 style="font-weight: bold; color:#fff">Fresh Food Co Ltd</h4>
                    <p class="footer-text">Địa chỉ 1: No 1, Hacker way, Menlo Park, CA</p>
                    <p class="footer-text">Địa chỉ 2: No 1 Rathbone Square, Fitzrovia, London</p>
                    <p class="footer-text">Địa chỉ 3: 4 Grand Canal Square, Grand Canal Harbour</p>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-5">
                    <h4 style="font-weight: bold; color:#fff">Thông tin liên lạc</h4>
                    <p class="footer-text">Điện thoại: 0123456789</p>
                    <p class="footer-text">Email: freshfoodmarket@gmail.com</p>
                    <p class="footer-text">Facebook: freshfoodmarket.facebook.com</p>
                </div>
            </div>
        </div>
    </footer>
</body>

</html>
