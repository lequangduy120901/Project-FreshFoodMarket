<%-- 
    Document   : myOrders
    Created on : Jun 18, 2022, 9:58:29 AM
    Author     : zedqu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Orders</title>
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">

        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/productStyle.css">

        <style>
            .nav-pills>li.active>a, .nav-pills>li.active>a:hover, .nav-pills>li.active>a:focus {
                color: #fff;
                background-color: #428bca;
                font-weight:bold;
                font-size:16px;
            }

            .margintop20 {
                margin-top:20px;
            }

            .nav-pills>li>a {
                border-radius: 0px;
            }

            a {
                color: #000;
                text-decoration: none;
            }

            a:hover {
                color: #000;
                text-decoration: none;
            }

            .nav-stacked>li+li {
                margin-top: 0px;
                margin-left: 0;

            }

            .active2 {
                border-right:4px solid #428bca;
                color: #009933;
            }
            h3{
                padding-top: 30px;
                padding-bottom: 20px;
            }
            table{
                border: solid 1px green;
                margin-bottom: 10px;
                width: 100%;
                height: 100%;

            }

            table td{
                white-space: nowrap;
            }

            td{
                border-right: solid 1px black;
                border-bottom: solid 1px black;
                border-top: solid 1px black;
            }

            th{
                border-right: solid 1px black;
                border-bottom: solid 1px black;
                border-top: solid 1px black;
                text-align: center;
            }

            #link_to_oi{
                color: black;
            }

            .more_button{
                font-size: 15px;
                border: none;
                background-color: green;
                color: white;
                width: 100%;
            }
        </style>

        <script>
            var ids = [];
            <c:forEach items="${requestScope.orders}" var="order">
                ids.push(${fn:replace(order.orderID, "OID", "")});
            </c:forEach>


            function hideControlsbyClassName(name)
            {
                var controls = document.getElementsByClassName(name);
                for (var i = 0; i < controls.length; i++)
                {
                    controls[i].style.display = 'none';
                }
            }

            function showControlsbyClassName(name)
            {
                var controls = document.getElementsByClassName(name);
                for (var i = 0; i < controls.length; i++)
                {
                    controls[i].style.display = 'inline';
                }
            }

            function hideMoreModeControls()
            {
                for (var i = 0; i < ids.length; i++)
                {
                    hideControlsbyClassName('moremode' + ids[i]);
                }
            }
            function hideLessModeControls()
            {
                for (var i = 0; i < ids.length; i++)
                {
                    hideControlsbyClassName('lessmode' + ids[i]);
                }
            }
            function showMoreModeControls(id)
            {
                for (var i = 0; i < ids.length; i++)
                {
                    showControlsbyClassName('moremode' + ids[i]);
                }
            }
            function showLessModeControls()
            {
                for (var i = 0; i < ids.length; i++)
                {
                    showControlsbyClassName('lessmode' + ids[i]);
                }
            }

            function showMore_onclick(id) {
                var textButton = document.getElementById('button' + id);
                if (textButton.value === 'Xem Th??m') {
                    hideControlsbyClassName('lessmode' + id);
                    showControlsbyClassName('moremode' + id);
                    textButton.value = '???n b???t';
                } else {
                    hideControlsbyClassName('moremode' + id);
                    showControlsbyClassName('lessmode' + id);
                    textButton.value = 'Xem Th??m';
                }
            }
        </script>
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
            <div class="row" style="background-color: #fff; margin: 0 auto; max-width: 1180px; min-height: calc(100vh - 140px - 129.79px);">
                <div class="col-lg-2 col-md-2 col-sm-2 column margintop20" id="left_menu" style="margin-top: 50px;">

                    <ul class="nav nav-pills nav-stacked">
                        <li class="active text-center">
                        </li> 
                        <li><a href="/FreshFoodMarket/userProfile" ><span class="glyphicon glyphicon-chevron-right"></span>T??i kho???n</a>
                        </li> 
                        <li><a href="/FreshFoodMarket/myOrders" class="active2"><span class="glyphicon glyphicon-chevron-right"></span>Qu???n l?? ????n h??ng</a>
                        </li> 
                        <li><a href="ChangePass"><span class="glyphicon glyphicon-chevron-right"></span>?????i m???t kh???u</a>
                        </li> 
                        <!--<li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span>L???ch s??? giao d???ch</a>-->
                        </li>
                        <li><a href="/FreshFoodMarket/loginController?action=logout"><span class="glyphicon glyphicon-chevron-right"></span>????ng xu???t</a>
                        </li>
                    </ul>

                </div>
                <div class="col-lg-9 col-md9 col-sm-9 column margintop20" id="table_right">
                    <h3 style="color: #009933">Danh s??ch ????n h??ng</h3>
                    <h4 style="color: black;">Kh??ch h??ng: ${requestScope.cus.cusName}</h4>

                <table style="text-align: center;">
                    <tr style="font-size: 15px; color: #009933; font-weight: bold">
                        <td style="width: 6%;">M?? ????n h??ng</td>
                        <td style="width: 6%;">Ng??y ?????t h??ng</td>
                        <td style="width: 76%;">S???n ph???m</td>
                        <td style="width: 6%;">T???ng ti???n</td>
                        <td style="width: 6%;">Tr???ng th??i ????n h??ng</td>
                    </tr>
                    <c:forEach items="${requestScope.orders}" var="order">
                        <tr>
                            <td>
                                <a href="orderInformation?orderID=${order.orderID}" id="link_to_oi"  style="font-size: 15px;">${order.orderID}<br>
                                    <p style="font-size: 10px;">
                                        (Click ????? xem chi ti???t ????n h??ng)
                                    </p>
                                </a>
                            </td>
                            <td style="font-size: 15px;">${order.dateCreate}</td>
                            <td>
                                <span class="lessmode${fn:replace(order.orderID, "OID", "")}">
                                    <c:forEach items="${requestScope.firsts}" var="firsts">
                                        <c:if test="${firsts.order.orderID == order.orderID}">
                                            <div class="row">
                                                <div class="col-lg-5 col-md-5 col-sm-5">
                                                    <img src="${firsts.product.image}" alt="???nh c???a ${firsts.product.image}" style="width:80px;height:80px;"> 
                                                </div>
                                                <div class="col-lg-7 col-md-7 col-sm-7" style="text-align: left;">
                                                    <p><a href="productDetail?proid=${firsts.product.proID}" style="font-size: 15px; color: black;">${firsts.product.proName}</a></p>
                                                    <p style="font-size: 15px;">Lo???i: ${firsts.product.type}</p>
                                                    <p style="font-size: 15px;">S??? l?????ng: ${firsts.quantity}</p>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </span>
                                <span class="moremode${fn:replace(order.orderID, "OID", "")}">        
                                    <c:forEach items="${requestScope.orderDetails}" var="orderDetail">
                                        <c:if test="${orderDetail.order.orderID == order.orderID}">
                                            <div class="row">
                                                <div class="col-lg-5 col-md-5 col-sm-5">
                                                    <img src="${orderDetail.product.image}" alt="???nh c???a ${orderDetail.product.image}" style="width:80px;height:80px;"> 
                                                </div>
                                                <div class="col-lg-7 col-md-7 col-sm-7" style="text-align: left">
                                                    <p><a href="productDetail?proid=${orderDetail.product.proID}" style="font-size: 15px; color: black;">${orderDetail.product.proName}</a></p>
                                                    <p style="font-size: 15px;">Lo???i: ${orderDetail.product.type}</p>
                                                    <p style="font-size: 15px;">S??? l?????ng: ${orderDetail.quantity}</p>

                                                </div>
                                            </div> 
                                        </c:if>
                                    </c:forEach>
                                </span>
                                <input type="button" value="Xem Th??m" class="more_button" id="button${fn:replace(order.orderID, "OID", "")}" onclick="showMore_onclick(${fn:replace(order.orderID, "OID", "")});"/>
                            </td>

                            <td style="font-size: 15px;"><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${order.total}" /> ???</td>
                            <td style="font-size: 15px; ">
                                <c:choose>
                                    <c:when test = "${order.status == 0}">
                                        ???? ?????t h??ng. <br>
                                        (??ang ?????i x??c nh???n.)
                                    </c:when>

                                    <c:when test = "${order.status == 1}">
                                        ???? x??c nh???n ????n h??ng.<br>
                                        (??ang ?????i giao h??ng.)
                                    </c:when>

                                    <c:when test = "${order.status == 2}">
                                        ??ang giao h??ng.<br>
                                        (??ang ?????i nh???n h??ng.)
                                    </c:when>

                                    <c:when test = "${order.status == 3}">
                                        ???? nh???n h??ng.<br>
                                    </c:when> 

                                    <c:when test = "${order.status == 5}">
                                        ???? ????nh gi??.
                                    </c:when>

                                    <c:otherwise>
                                        ???? h???y
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                Trang : 
                <c:set var="page" value="${page}"/>
                <div class="pagination" style="margin-bottom: 50px;">
                    <c:forEach begin="${1}" end="${num}" var="i">
                        <a class="${i==page?"active":""}"href="myOrders?&page=${i}">${i}</a>
                    </c:forEach>
                </div>
            </div>
        </div>
        <script>hideMoreModeControls();</script>
        <jsp:include page="footer.jsp"></jsp:include>   
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>  
    </body>
</html>
