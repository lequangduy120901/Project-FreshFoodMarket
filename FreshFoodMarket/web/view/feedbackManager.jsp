<%-- 
    Document   : feedbackManager
    Created on : Jul 18, 2022, 10:10:21 PM
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
        
        <title>Quản Lý Đánh giá</title>
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">
        
        <link rel="stylesheet" type="text/css" href="css/productListSaleStyle.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>

        <div class="proContainer">
            <div class="list-top">
                <div class="list-top-top">
                    <h3>Quản Lý Đánh Giá</h3>
                </div>
                <div class="list-top-bottom">
                    <div class="ltb-left">
                        <form action="feedbackManager" method="POST">
                            <input name="key" type="text" value="" placeholder="Tìm kiếm theo proID">
                            <button>Tìm</button>
                        </form>
                        <br>
                    </div>
                    <div class="ltb-right">
                        <form action="filterFeedback" method="POST">
                            <select name="rated" id="">
                                <option value="" selected disabled>Rate</option>
                                <option value="p1" >↑</option>
                                <option value="p2" >↓</option>
                            </select>
                            <button>Lọc</button>
                        </form>
                            <br>
                            <button style="float: right;" onclick="reset()">Xóa bộ lọc</button>
                    </div>
                    <div style="clear: both;"></div>
                </div>
            </div>
            <div class="list-bottom">
                    <table class="proTable">
                        <tr>
                            <th style="width: 110px;">Feedback ID</th>
                            <th style="width: 110px;">Customer ID</th>
                            <th style="width: 120px;">Product ID</th>
                            <th style="width: 90px;">Rated</th>
                            <th>Content</th>
                            <th style="width: 150px;">Ngày tạo</th>
                            <th style="width: 100px;">Xem</th>
                        </tr>
                    <c:forEach items="${listFB}" var="o">
                        <tr>
                            <td>${o.feID}</td>
                            <td>${o.customer.cusID}</td>
                            <td>${o.product.proID}</td>
                            <td>${o.rated}</td>
                            <td>${o.content}</td>
                            <td><fmt:formatDate value="" pattern="dd-MM-yyyy"/>${o.dateCreate}</td>
                            <td>
                                <button onclick="submit(${o.feID})">Xem</button>
                                <script>
                                    function submit(feID) {
                                        window.location = "feedbackDetail?feID=" + feID;
                                    }
                                    function reset() {
                                        window.location = "/FreshFoodMarket/feedbackManager";
                                    }
                                </script>
                            </td>
                        </tr>
                    </c:forEach>
                    </table>
                
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
