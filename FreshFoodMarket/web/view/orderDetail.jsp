<%-- 
    Document   : orderDetail
    Created on : Jul 20, 2022, 1:37:37 PM
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
        <link rel="stylesheet" href="css/orderDetailStyle.css">
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>

            <div class="pay">
                <h2>Chi tiết đơn hàng </h2>
                <div class="information">

                    <div class="table-container">
                        <table class="table">
                            <tr>
                                <td>ID</td>
                                <td><input name="cusName" type="text" disabled value="${requestScope.order.orderID}"></td>
                        </tr>
                        <tr>
                            <td>Tên khách hàng</td>
                            <td><input name="email" type="text" disabled value="${requestScope.order.customer.cusName}"></td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td><input name="cusPhone" type="text" disabled value="${requestScope.order.customer.email}"></td>
                        </tr>
                        <tr>
                            <td>Địa chỉ</td>
                            <td><input name="cusAddress" type="text" disabled value="${requestScope.order.customer.cusAddress}"></td>
                        </tr>
                        <tr>
                            <td>Số điện thoại</td>
                            <td><input name="cusAddress" type="text" disabled value="${requestScope.order.customer.cusPhone}"></td>
                        </tr>
                        <tr>
                            <td>Ngày đặt hàng</td>
                            <td><input name="cusAddress" type="text" disabled value="${requestScope.order.dateCreate}"></td>
                        </tr>
                        <tr>
                            <td>Trạng thái</td>
                            <td>
                                <form id="myForm" action="/FreshFoodMarket/orderDetail" method="POST">
                                    <input name="orderID" type="hidden" value="${requestScope.order.orderID}">   
                                    <select name="status" id="">
                                        <option value="0" ${(requestScope.order.status == 0)?"selected":""}>Chưa xử lý</option>
                                        <option value="1" ${(requestScope.order.status == 1)?"selected":""}>Đang chuẩn bị hàng</option>
                                        <option value="2" ${(requestScope.order.status == 2)?"selected":""}>Đang giao hàng</option>
                                        <option value="3" ${(requestScope.order.status == 3)?"selected":""}>Thành công</option>
                                        <option value="-1" ${(requestScope.order.status == -1)?"selected":""}>Thất bại</option>
                                    </select>
                                </form>
                            </td>
                        </tr>

                    </table>
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
                    <h3>Tổng: <fmt:formatNumber value="${total}"/> đ</h3>
                    <div class="button">
                        <button onclick="submit2()">Quay lại</button>
                        <button onclick="submitForm()">Lưu</button>
                    </div>

                </div>
            </div>
        </div>
        <div style="height: 20px;"></div>

        <jsp:include page="footer.jsp"></jsp:include>
        <script src="js/cartContact.js"></script>    
    </body>
</html>
