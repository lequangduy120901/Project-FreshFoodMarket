

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page import="util.*"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<%
   Customer cus = (Customer) request.getAttribute("cus");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA_Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, intial-scale=1.0">
        
        <title>Thông Tin Khách Hàng</title>
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">
        
        <link rel="stylesheet" type="text/css" href="css/CustomerDetailSaleStyle.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
        
        <div class="content-container">
            <div class="top">
                <h3 class="bigTitle">Thông Tin Khách Hàng</h3>
                <!--<a type="button" class="del" id="del" name="del" href="CustomerDetailSaleController?action=deleteCus&cusID=${cus.cusID}" >Xóa</a>-->
            </div>
            <div class="update-time">
                <c:if test="${requestScope.mess != null}">
                    <p style="color: red;">${requestScope.mess}</p>
                </c:if>
<!--                <p>Lần cập nhật cuối: <fmt:formatDate value="${requestScope.product.updateDate}" pattern="dd-MM-yyyy"/></p>-->
            </div>
            <form id="proForm" action="/FreshFoodMarket/productDetailSale" method="POST">
                <div class="content-infor">
                    <div class="content-left">
                        <img id="img-pro" class="proImg" src="${cus.cusImage}" alt="">
                        <p id="imgCont">
                            <input id="img-input" class="file-input" type="file" accept="image/*" name="imgInput" onchange="">
                        </p>
                    </div>
                    <div class="content-right">
                        <table class="infor-table">
                            <tr>
                                <td>CusID</td>
                                <td><input id="id" name="CusID" type="text" value="${cus.cusID}" style="pointer-events: none;"></td>
                            </tr>
                           
                            <tr>
                                <td>Họ và Tên</td>
                                <td><input id="Cusame" name="CusName" type="text" value="${cus.cusName}" disabled></td>
                            </tr>
                            <tr>
                                <td>Giới Tính</td>
                                <td><input id="" name="gender" type="text" value="${(cus.gender == true)?"Nam":"Nữ"}" disabled></td>
                            </tr>
                            <tr>
                                <td>Địa Chỉ</td>
                                <td>
                                    <!--<input id="" name="cusAddress" type="text" value="" disabled>-->
                                    <p>${cus.cusAddress}</p>
                                </td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td><input id="" name="Email" type="text" value="${cus.email}" disabled></td>
                            </tr>
                            <tr>
                                <td>Ngày Đăng Ký</td>
                                <td><input id="" name="updateDate" type="text" value="${cus.updateDate}" disabled></td>
                            </tr>
                        </table>
                    </div>
                </div>
              
            </form>
            <div class="button-ctn">
                <!--<a ><button>Quay lại</button></a>-->
                <a href="CustomerListSaleController"><button type="button">Quay lại</button></a>
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
        <script type="text/javascript" src="js/productDetailSale.js"></script>
    </body>
</html>
