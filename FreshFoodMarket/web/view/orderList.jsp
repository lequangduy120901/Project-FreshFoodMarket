<%-- 
    Document   : oderList
    Created on : Jul 18, 2022, 6:08:34 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/sliderListStyle.css">
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>
    <body>
    <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>

    <div class="slideContainer">
        <div class="list-top">
            <div class="list-top-top">
                <h3>Quản Lý Đơn hàng</h3>
            </div>
            <div class="list-top-bottom">
                <div class="ltb-left">
                    <form action="/FreshFoodMarket/manageOrder" method="POST">
                        <input type="text" placeholder="Nhập tên khách hàng" name="key" value="${requestScope.key}">
                        <button>Tìm</button>
                    </form>
                    <br>
                </div>
                <div class="ltb-right">
                    <form action="/FreshFoodMarket/manageOrder" method="POST">
                        <input type="hidden" name="key" value="${requestScope.key}">
                        Từ <input type="date" name="from" id="" value="${requestScope.from}" style="height: 30px;">
                        Đến <input type="date" name="to" id=""  value="${requestScope.to}" style="height: 30px;">
                        <select name="status" id="">
                            <option value="" disabled selected>Trạng thái</option>
                            <option value="0" ${(requestScope.status.equals("0"))?"selected":""}>Chưa xử lý</option>
                            <option value="1" ${(requestScope.status.equals("1"))?"selected":""}>Đang chuẩn bị hàng</option>
                            <option value="2" ${(requestScope.status.equals("2"))?"selected":""}>Đang giao hàng</option>
                            <option value="3" ${(requestScope.status.equals("3"))?"selected":""}>Thành công</option>
                            <option value="-1" ${(requestScope.status.equals("-1"))?"selected":""}>Thất bại</option>
                        </select>
                        <button>Lọc</button>
                    </form>
                    <br>
                    <button class="del" onclick="submit()">Xóa bộ lọc</button>
                </div>
                <div style="clear: both;"></div>
            </div>
        </div>
        <div class="list-bottom">
            <table class="slideTable">
                <tr>
                    <th style="width: 100px;">ID</th>
                    <th>Ngày đặt hàng</th>
                    <th>Tên khách hàng</th>
                    <th>Tổng giá tiền</th>
                    <th>Trạng thái</th>
                </tr>
            <c:forEach items="${listO}" var="o">
                <tr>
                    <td><a href="/FreshFoodMarket/orderDetail?orderID=${o.orderID}">${o.orderID}</a></td>
                    <td>${o.dateCreate}</td>
                    <td>${o.customer.cusName}</td>
                    <td>${o.total}</td>
                    <c:if test="${o.status == 0}">
                        <c:set var="status" value = "Chưa xử lý"></c:set>
                    </c:if>
                    <c:if test="${o.status == 1}">
                        <c:set var="status" value = "Đang chuẩn bị hàng"></c:set>
                    </c:if>
                    <c:if test="${o.status == 2}">
                        <c:set var="status" value = "Đang giao hàng"></c:set>
                    </c:if>
                    <c:if test="${o.status == 3}">
                        <c:set var="status" value = "Thành công"></c:set>
                    </c:if>
                    <c:if test="${o.status < 0}">
                        <c:set var="status" value = "Thất bại"></c:set>
                    </c:if>
                    <td>${status}</td>
                </tr>
            </c:forEach>
            </table>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
    
    <script src="js/orderList.js"></script>
    </body>
</html>
