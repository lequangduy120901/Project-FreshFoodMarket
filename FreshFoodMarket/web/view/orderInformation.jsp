<%-- 
    Document   : orderInformation
    Created on : Jul 9, 2022, 12:53:16 PM
    Author     : zedqu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Information</title>
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/productStyle.css">
        <style>
            table {
                counter-reset: section;
            }

            .stt:before {
                counter-increment: section;
                content: counter(section);
            }

            #update_button{
                border: solid 1px #000000;
                background-color: #009933;
                color: white;
                width: 200px;
                padding: 10px;
                margin: 50px;
                font-weight: bold;
                height: 50px;
            }
            #cancel_button{
                border: solid 1px #000000;
                background-color: #cc0000;
                color: white;
                width: 200px;
                padding: 10px;
                margin: 50px;
                font-weight: bold;
                height: 50px;
            }
        </style>
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include>
            </div>

            <div class="row" style="background-color: #fff; margin: 0 auto; max-width: 1180px;">
                <form id="frm_Cancel" method="POST">
                    <input type="hidden" name="orderID" id="orderID" value="${requestScope.order.orderID}">
                <input type="hidden" name="status" id="status" value="${requestScope.order.status}">
                <table style="text-align: center;">

                    <tr><th style="font-size: 20px; padding-bottom: 20px; padding-top: 20px;">Thông tin đơn hàng</th><tr>
                    <tr style="font-size: 15px; color: #009933; font-weight: bold">
                        <td style="width: 25%;">Mã đơn hàng</td>
                        <td style="width: 25%;">Ngày đặt hàng</td>
                        <td style="width: 25%;">Tổng tiền</td>
                        <td style="width: 25%;">Trạng thái đơn hàng</td>
                    </tr>
                    <tr>
                        <td style="font-size: 20px;">${requestScope.order.orderID}</td>
                        <td style="font-size: 20px;">${requestScope.order.dateCreate}</td>
                        <td style="font-size: 20px;"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${requestScope.order.total}" /> ₫</td>
                        <td style="font-size: 20px; ">
                            <c:choose>
                                <c:when test = "${requestScope.order.status == 0}">
                                    Đã đặt hàng. <br>
                                    (Ðang đợi xác nhận.)
                                </c:when>

                                <c:when test = "${requestScope.order.status == 1}">
                                    Đã xác nhận đơn hàng.<br>
                                    (Ðang đợi giao hàng.)
                                </c:when>

                                <c:when test = "${requestScope.order.status == 2}">
                                    Đang giao hàng.<br>
                                    (Ðang đợi nhận hàng.)
                                </c:when>

                                <c:when test = "${requestScope.order.status == 3}">
                                    Đã nhận hàng.<br>
                                </c:when> 

                                <c:otherwise>
                                    Đã hủy
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr><th style="font-size: 20px; padding-bottom: 20px; padding-top: 20px;">Thông tin khách hàng</th><tr>
                    <tr style="font-size: 20px;">
                        <td style="font-size: 15px; color: #009933; font-weight: bold;padding-bottom: 10px; padding-top: 10px;">Họ và tên</td>
                        <td style="text-align: left;">${requestScope.order.customer.cusName}</td>
                    </tr>
                    <tr style="font-size: 20px;">
                        <td style="font-size: 15px; color: #009933; font-weight: bold; padding-bottom: 10px; padding-top: 10px;">Giới tính</td>
                        <td style="text-align: left;"><c:choose>
                                <c:when test = "${requestScope.order.customer.gender == true}">
                                    Nam
                                </c:when>
                                <c:otherwise>
                                    Nữ
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr style="font-size: 20px;">
                        <td style="font-size: 15px; color: #009933; font-weight: bold;padding-bottom: 10px; padding-top: 10px;">Email</td>
                        <td style="text-align: left;">${requestScope.order.customer.email}</td>
                    </tr>
                    <tr style="font-size: 20px;">
                        <td style="font-size: 15px; color: #009933; font-weight: bold;padding-bottom: 10px; padding-top: 10px;">Số điện thoại</td>
                        <td style="text-align: left;">${requestScope.order.customer.cusPhone}</td>
                    </tr>
                    <tr style="font-size: 20px;">
                        <td style="font-size: 15px; color: #009933; font-weight: bold;padding-bottom: 10px; padding-top: 10px;">Địa chỉ nhận hàng</td>
                        <td style="text-align: left;" colspan="3">${requestScope.order.customer.cusAddress}</td>
                    </tr>
                    <tr><th style="font-size: 20px; padding-bottom: 20px; padding-top: 20px;">Danh sách sản phẩm</th><tr>
                </table>
                <table style="border: solid 1px black">
                    <tr style="font-size: 15px; color: #009933; font-weight: bold;text-align: center;">
                        <td style="width: 5%; text-align: center; border: 1px solid black">Stt</td>
                        <td style="width: 15%; border: 1px solid black">Hình thu nhỏ</td>
                        <td style="width: 20%; border: 1px solid black">Tên sản phẩm</td>
                        <td style="width: 10%; border: 1px solid black">Loại</td>
                        <td style="width: 10%; border: 1px solid black">Đơn giá</td>
                        <td style="width: 10%; border: 1px solid black">Số lượng</td>
                        <td style="width: 10%; border: 1px solid black">Tổng chi phí</td>

                    </tr>
                    <c:forEach items="${requestScope.orderDetails}" var="orderDetail">
                        <tr style="text-align: center; border: solid 1px black" >
                            <td class="stt" style="border: 1px solid black;"></td>
                            <td style="border: 1px solid black;"><img src="${orderDetail.product.image}" alt="ảnh của ${orderDetail.product.image}" style="width:80px;height:80px;"> </td>

                            <td style="border: 1px solid black;"><p><a href="productDetail?proid=${orderDetail.product.proID}" style="font-size: 15px; color: black;">${orderDetail.product.proName}</a></p></td>
                            <td style="border: 1px solid black;"><p style="font-size: 15px;">${orderDetail.product.type}</p></td>
                            <td style="font-size: 15px; border: 1px solid black;"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${orderDetail.product.price}" /> ₫</td>
                            <td style="border: 1px solid black;"><p style="font-size: 15px;">${orderDetail.quantity}</p></td>
                            <td style="font-size: 15px; border: 1px solid black;"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${orderDetail.total}" /> ₫</td>
                            <c:choose>
                                <c:when test = "${requestScope.order.status == 0}">

                                </c:when>

                                <c:when test = "${requestScope.order.status == 1}">

                                </c:when>

                                <c:when test = "${requestScope.order.status == 2}">

                                </c:when>

                                <c:when test = "${requestScope.order.status == 3}">
                                    <td style="width: 10%; border: 1px solid black;"><a href="cartcontroller?action=addtocart&pid=${orderDetail.product.proID}&quantity=1">Mua lại</a></td>
                                    <td style="width: 10%; border: 1px solid black;"><a href="productDetail?proid=${orderDetail.product.proID}">Đánh giá</a></td>
                                </c:when> 

                                <c:when test = "${requestScope.order.status == 4}">
                                    <td style="width: 10%; border: 1px solid black;"><a href="cartcontroller?action=addtocart&pid=${orderDetail.product.proID}&quantity=1">Mua lại</a></td>
                                </c:when> 

                                <c:otherwise>
                                    <td style="width: 10%; border: 1px solid black;"><a href="cartcontroller?action=addtocart&pid=${orderDetail.product.proID}&quantity=1">Mua lại</a></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>
                <table>
                    <tr>
                        <c:if test="${requestScope.order.status == 0}">
                            <td><input type="button" value="Cập nhật đơn hàng" name="update_button" id="update_button" onclick="update_function()">
                            </td>
                            <td><input type="button" value="Hủy đơn hàng" name="cancel_button" id="cancel_button" onclick="cancel_function()">
                            </td>                        
                        </c:if>
                        <c:if test="${requestScope.order.status != 0}">
                            <td style="text-align: center; padding: 20px;"><p id="button_message">(Chỉ đơn hàng chưa xác nhận mới có thể cập nhật hoặc hủy)</p></td>
                        </c:if>
                    </tr>
                </table>
            </form>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>   
            <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>  
            <script>

                                function update_function() {
                                    window.location = "cartcontroller?cusID=${requestScope.order.customer.cusID}";
                                }

                                function cancel_function() {
                                    let text;
                                    let choice = confirm("Bạn có chắc chắn muốn hủy đơn hàng")
                                    if (choice == true) {
                                        document.getElementById("status").value = -1;
                                        document.getElementById("frm_Cancel").submit();
                                    }
                                }
        </script>
    </body>
</html>
