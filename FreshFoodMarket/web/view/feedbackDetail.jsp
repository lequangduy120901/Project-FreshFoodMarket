<%-- 
    Document   : feedbackDetail
    Created on : Jul 18, 2022, 10:10:39 PM
    Author     : HuyCQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA_Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, intial-scale=1.0">

        <title>Thông Tin Đánh Giá</title>
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">

        <link rel="stylesheet" type="text/css" href="css/feedbackDetailStyle.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
        <c:set value="${feedback}" var="feedback"/>
        <div class="content-container">
            <div class="content-infor">
                <div class="content-right">
                    <table class="infor-table">
                        <tr>
                            <td>Feedback ID</td>
                            <td>${feedback.feID}</td>
                        </tr>
                        <tr>
                            <td>Tên Khách Hàng</td>
                            <td>${feedback.customer.cusName}</td>
                        </tr>
                        <tr>
                            <td>Tên sản phẩm</td>
                            <td>${feedback.product.proName}</td>
                        </tr>
                        <tr>
                            <td>Mã sản phẩm</td>
                            <td>${feedback.product.proID}</td>
                        </tr>
                        <tr>
                            <td>Rate</td>
                            <td>${feedback.rated}</td>
                        </tr>
                        <tr>
                            <td>Ngày tạo</td>
                            <td><fmt:formatDate value="${feedback.dateCreate}" pattern="dd-MM-yyyy"/></td>
                        </tr>
                    </table>
                    <div class="content">
                        <label for="" style="font-size: 23px;">Nội dung</label>
                        <textarea name="des" id="des" class="des" disabled>${feedback.content}</textarea>
                    </div>
                </div>
            </div>
            <div class="button-ctn">
                <button onclick="back()">Quay lại</button>
                <script>
                    function back() {
                        window.location = "/FreshFoodMarket/feedbackManager";
                    }
                </script>
            </div>
        </div>

        <jsp:include page="footer.jsp"></jsp:include>
        <script type="text/javascript" src="js/productDetailSale.js"></script>
    </body>
</html>
