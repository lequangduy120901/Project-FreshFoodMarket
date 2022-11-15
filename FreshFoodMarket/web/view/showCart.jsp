<%-- 
    Document   : showCart
    Created on : May 30, 2022, 1:11:03 PM
    Author     : THAI BAO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="util.Product"%>
<%@page import="java.util.List"%>
<%@page import="DAO.ProductDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Giỏ hàng</title>
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>

        <div style="min-height: calc(100vh - 140px - 129.79px);">
            <div class="table-responsive">
                <table class="table table-striped">
                    <tr style="text-align: center">
                        <th scope="col">Ảnh</th>
                        <th scope="col">Tên sản phẩm</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Đơn giá</th>
                        <th scope="col">Tổng</th>
                        <th scope="col">Xóa</th>
                    </tr>
                <c:forEach items="${listC}" var="c">
                    <tr style="text-align: center">
                        <td><img src="${c.product.image}" style="width: 90px; height: 90px; object-fit: cover;"></td>
                        <td>${c.product.proName}</td>
                    <form action="cartcontroller?action=updateCart" method="POST">
                        <td>
                            <input type="submit" value="-" name="submit"/>
                            <input type="hidden" value="${c.product.proID}" name="pid"/>
                            <input type="text" name="quantity"  value="${c.quantity}" readonly="readonly" style="width: 40px; text-align: center"/>
                            <input type="submit" value="+" name="submit"/>
                        </td>
                    </form>
                        <td>${c.product.price}</td>
                        <td>${c.product.price * c.quantity}</td>
                        <td><a href="cartcontroller?action=delete&pid=${c.product.proID}">Xóa</a></td>
                    </tr>
                </c:forEach>    
                </table>
            </div>

            <div class="col mb-2">
                <div class="row">
                    <div class="col-sm-12  col-md-6">
                        <a style="color: black" href="categories?idC=0">
                            <button class="btn btn-lg btn-block btn-light" style="">Tiếp tục mua sắm</button>
                        </a>
                    </div>  
                    <div class="col-sm-12 col-md-6 text-right">
                        <c:if test="${sessionScope.user != null}">
                            <a style="color: black" href="/FreshFoodMarket/cartContact">
                                <button  class="btn btn-lg btn-block btn-success text-uppercase" style="background-color: #2a8738;">Đặt hàng</button>
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
                            
        <div style="clear: both;"></div>
        <jsp:include page="footer.jsp"></jsp:include> 

        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>

