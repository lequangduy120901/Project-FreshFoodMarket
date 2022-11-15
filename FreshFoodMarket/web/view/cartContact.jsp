<%-- 
    Document   : cartContact
    Created on : Jun 26, 2022, 11:50:52 PM
    Author     : Admin
--%>
<%@page import="util.Customer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="css/cartContactStyle.css">
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>
    <body>
        <%
        Customer cuss = (Customer) session.getAttribute("cus");
        
        %>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
        <div class="pay">
            <h1>Đặt hàng</h1>
            <div class="payment">
                <p>Hình thức thanh toán</p>
                <p>Thanh toán khi nhận hàng</p>
            </div>
            <div class="delivery">
                <p>Hình thức nhận hàng</p>
                <p>Giao hàng tận nơi</p>
            </div>
            <div class="information">
<!--                <h4>Delivery information</h4>
                <div class="input">
                    <input type="checkbox" id="change" name = "change">Change delivery information
                </div>-->
                <div class="table-container">
                    <form action="/FreshFoodMarket/cartCompletion" id="myForm">
                        <table class="table">
                            <tr>
                                <td>Tên</td>
                                <td><input name="cusName" type="text" value="<%=cuss.getCusName()%>"></td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td><input name="email" type="text" value="<%=cuss.getEmail()%>"></td>
                            </tr>
                            <tr>
                                <td>Số điện thoại</td>
                                <td><input name="cusPhone" type="text" value="<%=cuss.getCusPhone()%>"></td>
                            </tr>
                            <tr>
                                <td>Địa chỉ</td>
                                <td><input name="cusAddress" type="text" value="<%=cuss.getCusAddress()%>"></td>
                            </tr>
                        </table>
                    </form>
                            <table class="table-1">
                                
                    <tr class="tr-1" boder="1">
                        <td>Hình ảnh</td>
                        <td>Tên sản phẩm</td>
                        <td>Giá</td>
                        <td>Số lượng</td>

                    </tr>
                    <c:set var="total" value="${0}"/>
                   <c:forEach items="${list}" var="o">
                    <tr>
                        <td><img class="img" src="${o.product.image}" alt=""></td>
                        <td>${o.product.proName}</td>
                        <td><fmt:formatNumber value="${o.product.price}"/> đ</td>
                        <td>${o.quantity}</td>
                    </tr>
                    <c:set var="total" value="${total + o.product.price * o.quantity}"/>
                   </c:forEach>
                </table>
                   <h3>Total: <fmt:formatNumber value="${total}"/> đ</h3>
                    <div class="button">
                        <button onclick="submit()" type="submit">Giỏ hàng</button>
                        <button type="submit" onclick="submitForm()">Đặt hàng</button>
                    </div>
                </div>
            </div>
        </div>
                    <div style="height: 20px;"></div>
        <jsp:include page="footer.jsp"></jsp:include>                    
        <script src="js/cartContact.js"></script>
    </body>
</html>
