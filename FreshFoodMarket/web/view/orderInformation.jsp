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

                    <tr><th style="font-size: 20px; padding-bottom: 20px; padding-top: 20px;">Th??ng tin ????n h??ng</th><tr>
                    <tr style="font-size: 15px; color: #009933; font-weight: bold">
                        <td style="width: 25%;">M?? ????n h??ng</td>
                        <td style="width: 25%;">Ng??y ?????t h??ng</td>
                        <td style="width: 25%;">T???ng ti???n</td>
                        <td style="width: 25%;">Tr???ng th??i ????n h??ng</td>
                    </tr>
                    <tr>
                        <td style="font-size: 20px;">${requestScope.order.orderID}</td>
                        <td style="font-size: 20px;">${requestScope.order.dateCreate}</td>
                        <td style="font-size: 20px;"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${requestScope.order.total}" /> ???</td>
                        <td style="font-size: 20px; ">
                            <c:choose>
                                <c:when test = "${requestScope.order.status == 0}">
                                    ???? ?????t h??ng. <br>
                                    (??ang ?????i x??c nh???n.)
                                </c:when>

                                <c:when test = "${requestScope.order.status == 1}">
                                    ???? x??c nh???n ????n h??ng.<br>
                                    (??ang ?????i giao h??ng.)
                                </c:when>

                                <c:when test = "${requestScope.order.status == 2}">
                                    ??ang giao h??ng.<br>
                                    (??ang ?????i nh???n h??ng.)
                                </c:when>

                                <c:when test = "${requestScope.order.status == 3}">
                                    ???? nh???n h??ng.<br>
                                </c:when> 

                                <c:otherwise>
                                    ???? h???y
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr><th style="font-size: 20px; padding-bottom: 20px; padding-top: 20px;">Th??ng tin kh??ch h??ng</th><tr>
                    <tr style="font-size: 20px;">
                        <td style="font-size: 15px; color: #009933; font-weight: bold;padding-bottom: 10px; padding-top: 10px;">H??? v?? t??n</td>
                        <td style="text-align: left;">${requestScope.order.customer.cusName}</td>
                    </tr>
                    <tr style="font-size: 20px;">
                        <td style="font-size: 15px; color: #009933; font-weight: bold; padding-bottom: 10px; padding-top: 10px;">Gi???i t??nh</td>
                        <td style="text-align: left;"><c:choose>
                                <c:when test = "${requestScope.order.customer.gender == true}">
                                    Nam
                                </c:when>
                                <c:otherwise>
                                    N???
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr style="font-size: 20px;">
                        <td style="font-size: 15px; color: #009933; font-weight: bold;padding-bottom: 10px; padding-top: 10px;">Email</td>
                        <td style="text-align: left;">${requestScope.order.customer.email}</td>
                    </tr>
                    <tr style="font-size: 20px;">
                        <td style="font-size: 15px; color: #009933; font-weight: bold;padding-bottom: 10px; padding-top: 10px;">S??? ??i???n tho???i</td>
                        <td style="text-align: left;">${requestScope.order.customer.cusPhone}</td>
                    </tr>
                    <tr style="font-size: 20px;">
                        <td style="font-size: 15px; color: #009933; font-weight: bold;padding-bottom: 10px; padding-top: 10px;">?????a ch??? nh???n h??ng</td>
                        <td style="text-align: left;" colspan="3">${requestScope.order.customer.cusAddress}</td>
                    </tr>
                    <tr><th style="font-size: 20px; padding-bottom: 20px; padding-top: 20px;">Danh s??ch s???n ph???m</th><tr>
                </table>
                <table style="border: solid 1px black">
                    <tr style="font-size: 15px; color: #009933; font-weight: bold;text-align: center;">
                        <td style="width: 5%; text-align: center; border: 1px solid black">Stt</td>
                        <td style="width: 15%; border: 1px solid black">H??nh thu nh???</td>
                        <td style="width: 20%; border: 1px solid black">T??n s???n ph???m</td>
                        <td style="width: 10%; border: 1px solid black">Lo???i</td>
                        <td style="width: 10%; border: 1px solid black">????n gi??</td>
                        <td style="width: 10%; border: 1px solid black">S??? l?????ng</td>
                        <td style="width: 10%; border: 1px solid black">T???ng chi ph??</td>

                    </tr>
                    <c:forEach items="${requestScope.orderDetails}" var="orderDetail">
                        <tr style="text-align: center; border: solid 1px black" >
                            <td class="stt" style="border: 1px solid black;"></td>
                            <td style="border: 1px solid black;"><img src="${orderDetail.product.image}" alt="???nh c???a ${orderDetail.product.image}" style="width:80px;height:80px;"> </td>

                            <td style="border: 1px solid black;"><p><a href="productDetail?proid=${orderDetail.product.proID}" style="font-size: 15px; color: black;">${orderDetail.product.proName}</a></p></td>
                            <td style="border: 1px solid black;"><p style="font-size: 15px;">${orderDetail.product.type}</p></td>
                            <td style="font-size: 15px; border: 1px solid black;"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${orderDetail.product.price}" /> ???</td>
                            <td style="border: 1px solid black;"><p style="font-size: 15px;">${orderDetail.quantity}</p></td>
                            <td style="font-size: 15px; border: 1px solid black;"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${orderDetail.total}" /> ???</td>
                            <c:choose>
                                <c:when test = "${requestScope.order.status == 0}">

                                </c:when>

                                <c:when test = "${requestScope.order.status == 1}">

                                </c:when>

                                <c:when test = "${requestScope.order.status == 2}">

                                </c:when>

                                <c:when test = "${requestScope.order.status == 3}">
                                    <td style="width: 10%; border: 1px solid black;"><a href="cartcontroller?action=addtocart&pid=${orderDetail.product.proID}&quantity=1">Mua l???i</a></td>
                                    <td style="width: 10%; border: 1px solid black;"><a href="productDetail?proid=${orderDetail.product.proID}">????nh gi??</a></td>
                                </c:when> 

                                <c:when test = "${requestScope.order.status == 4}">
                                    <td style="width: 10%; border: 1px solid black;"><a href="cartcontroller?action=addtocart&pid=${orderDetail.product.proID}&quantity=1">Mua l???i</a></td>
                                </c:when> 

                                <c:otherwise>
                                    <td style="width: 10%; border: 1px solid black;"><a href="cartcontroller?action=addtocart&pid=${orderDetail.product.proID}&quantity=1">Mua l???i</a></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>
                <table>
                    <tr>
                        <c:if test="${requestScope.order.status == 0}">
                            <td><input type="button" value="C???p nh???t ????n h??ng" name="update_button" id="update_button" onclick="update_function()">
                            </td>
                            <td><input type="button" value="H???y ????n h??ng" name="cancel_button" id="cancel_button" onclick="cancel_function()">
                            </td>                        
                        </c:if>
                        <c:if test="${requestScope.order.status != 0}">
                            <td style="text-align: center; padding: 20px;"><p id="button_message">(Ch??? ????n h??ng ch??a x??c nh???n m???i c?? th??? c???p nh???t ho???c h???y)</p></td>
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
                                    let choice = confirm("B???n c?? ch???c ch???n mu???n h???y ????n h??ng")
                                    if (choice == true) {
                                        document.getElementById("status").value = -1;
                                        document.getElementById("frm_Cancel").submit();
                                    }
                                }
        </script>
    </body>
</html>
