<%-- 
    Document   : cartCompletion
    Created on : Jun 15, 2022, 8:38:06 PM
    Author     : Dinh Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA_Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, intial-scale=1.0">
        
        <title>Fresh Food Market</title>
        
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">
        <link rel="stylesheet" type="text/css" href="css/cartCompletionStyle.css">
    </head>
    <body style="background-image: url('img/logo/anh2.jpg');">
        <div class="container">
        <div id="input">${requestScope.orderStatus}</div>
        <div class="popUp">
            <div class="headerText">
                <h1 id="notiesText">ĐẶT HÀNG THÀNH CÔNG!</h1>
            </div>
            <div class="mess" id="success" style="display: flex;">
                <p>Cảm ơn bạn đã đặt hàng tại Fresh Food Market. Chúng tôi đã gửi cho bạn một email về thông tin của đơn hàng. Vui lòng kiểm tra thông tin trong email của bạn.</p>
            </div>
            <div class="mess" id="failed">
                <p>Một vài sản phẩm mà bạn đặt mua đã vượt quá số lượng mà chúng tôi có thể cung cấp. Hãy kiểm tra số lượng đặt hàng của những sản phẩm trong danh sách phía dưới:</p>
                <table class="proTable">
                    <tr>
                        <th>Tên sản phẩm</th>
                        <th>Số lượng đặt hàng</th>
                        <th>Số lượng có sẵn</th>
                    </tr>
                    <c:forEach items="${listCD}" var="o">
                    <tr>
                        <td>${o.product.proName}</td>
                        <td>${o.quantity}</td>
                        <td>${o.product.quantity}</td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="mess" id="failed2" style="display: flex;">
                <p style="text-align: center;">Đã xảy ra lỗi trong quá trình xử lý đơn hàng.<br>Vui lòng đặt hàng lại.</p>
            </div>
            <div class="directed-btn">
                <button id="gobackbtn" onclick="goBack()">Trang Chủ</button>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="js/cartCompletion.js"></script>
    </body>
</html>
