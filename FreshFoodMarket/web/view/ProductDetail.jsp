<%-- 
    Document   : ProductDetail
    Created on : May 29, 2022, 3:49:32 PM
    Author     : zedqu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${requestScope.choosedP.proName}</title>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

        <link rel="shortcut icon" href="img/logo/Icon-logo.png">

        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/productStyle.css">
        <link rel="stylesheet" href="css/feedback.css"/>
        <style>

            .buy_now_button{
                border:1px solid green;
                background-color: green;
                width: 150px;
                height: 50px;
                margin-top: 50px;
                margin-bottom: 50px;
                color: white;
            }
            .add_to_cart_button{

                border:1px solid green;
                width: 150px;
                height: 50px;
                background-color: white;
                margin-top: 50px;
                margin-bottom: 50px;
                color: green;
            }
            .describe{
                margin: 50px;
                padding: 20px;
            }
            .feedback_container{
                margin: 50px;
                padding: 20px;
            }
            .product_detail_right{
                margin-top: 50px;
                padding-top: 30px;
                margin-bottom: 50px;
                padding-bottom: 30px;
                padding-left: 20px;
                font-size: 15px;
                border-left: 2px solid green;
            }



            .product_detail_right td{

                font-size: 25px;
                padding-left: 60px;
                padding-top: 10px;
                color: green;
            }

            .product_detail_right #product_detail_right_proid{
                font-size: 15px;
                color: #666666;
            }
            .product_detail_right #product_detail_right_remaining{
                font-size: 15px;
                color: #666666;
            }

            .image_left{
                margin-top: 50px;
                margin-bottom: 50px;
                padding-left: 20px;
                padding-bottom: 20px;
            }
            .feedback_button{
                border:1px solid green;
                background-color: green;
                width: 150px;
                height: 50px;
                margin-top: 50px;
                margin-bottom: 50px;
                color: white;
            }
            .related_product_item{
                border: 1px solid green;
                margin-bottom: 50px;
                margin-top: 50px;
                text-align: center;
            }
            .relatedButton{
                background-color: white;
                border: none;
            }
            .rating {
                display: flex;
                flex-direction: row-reverse;
                justify-content: left;
            }

            .rating > input{
                display:none;
            }

            .rating > label {
                position: relative;
                width: 1.1em;
                font-size: 30px;
                color: #FFD700;
                cursor: pointer;
            }

            .rating > label::before{
                content: "\2605";
                position: absolute;
                opacity: 0;
            }

            .rating > label:hover:before,
            .rating > label:hover ~ label:before {
                opacity: 1 !important;
            }

            .rating > input:checked ~ label:before{
                opacity:1;
            }

            .rating:hover > input:checked ~ label:before{
                opacity: 0.4;
            }
            #feedback_alert{
                display: none;
                color: red;
            }
            #product_detail_right_rated{
                position: relative;
                width: 1.1em;
                font-size: 20px;
                color: #ddd;
                cursor: pointer;
            }

        </style>
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>

            <div class="container_body">
                <div class="row">
                    <div class = "col-md-1"></div>
                    <div class="col-md-10">
                        <div class = "content_upper">
                            <div class="row">
                                <div class = "pagging_left">
                                    <div class = "col-sm-1">
                                    </div>
                                </div>
                                <div class="col-md-5">
                                    <div class = "image_left">
                                        <img src="${requestScope.choosedP.image}" alt="ảnh của ${requestScope.choosedP.proName}" style="width:70%;height:70%">
                                </div>
                            </div>
                            <div class="col-lg-5 col-md-5">
                                <div class="product_detail_right">
                                    <form>
                                        <table>
                                            <colgroup>
                                                <col width="400" span="2">

                                            </colgroup>
                                            <tr><th>Tên sản phẩm</th><td>${requestScope.choosedP.proName}</td></tr>
                                            <tr><th>Đánh giá</th>
                                                <td id = "product_detail_right_rated">
                                                    <label id="star_1">☆</label>
                                                    <label id="star_2">☆</label>
                                                    <label id="star_3">☆</label>
                                                    <label id="star_4">☆</label>
                                                    <label id="star_5">☆</label>
                                                    <p id = "product_detail_right_remaining">(Tổng ${numberCom} lượt đánh giá)</p>
                                                </td></tr>
                                            <tr><th>Mã sản phẩm</th><td id="product_detail_right_proid">${requestScope.choosedP.proID}</td></tr>
                                            <tr><th>Loại sản phẩm</th><td><a href="categories?idC=${requestScope.choosedP.category.cateID}">${requestScope.choosedP.category.cateName}</a></td></tr>
                                            <tr><th>Đơn giá</th><td><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${requestScope.choosedP.price}" /> ₫</td></tr>
                                            <tr><th>Loại</th><td>${requestScope.choosedP.type} </td></tr>
                                            <tr><th>Số lượng</th><td><input oninput="quantityChangedFunction()" type="number" id="quantity" name="quantity" min="1" max="${requestScope.choosedP.quantity}" value="1"></td></tr>
                                            <tr><th></th><td id = "product_detail_right_remaining">(Còn ${requestScope.choosedP.quantity} sản phẩm trong kho)</td></tr>
                                            <tr><th>Giá tiền tạm tính:</th><td id ="temp_total_money">${requestScope.choosedP.price} ₫</td></tr>
                                        </table>
                                    </form>
                                </div>

                                <div class="row">
                                    <div class="col-sm-1"></div>
                                    <div class="col-sm-5">
                                        <button class="buy_now_button" onclick="buyNowFunction()">Mua Ngay</button>
                                    </div>
                                    <div class="col-sm-5">
                                        <button class="add_to_cart_button" onclick="addToCartFunction()">Thêm vào giỏ hàng</button>
                                    </div>
                                    <div class="col-sm-1"></div>
                                </div>
                            </div>
                            <script>
                                function ratedStarShowFunction() {
                                    for (var i = 1, max = ${requestScope.choosedP.rated}; i <= max; i++) {
                                        document.getElementById("star_" + i).style.color = '#FFD700';
                                    }
                                }
                                ratedStarShowFunction();

                                function quantityChangedFunction() {
                                    var x = document.getElementById("quantity").value;
                                    var res = x *${requestScope.choosedP.price};
                                    document.getElementById("temp_total_money").innerHTML = res + " ₫";
                                }

                                function buyNowFunction() {
                                    var x = document.getElementById("quantity").value;
                                    if (${requestScope.currentCus == null}) {
                                        alert("Vui lòng đăng nhập để mua hàng");
                                    } else {
                                        //link to Bill controller + send proID, Current User ID, quantity
                                        window.location = "cartcontroller?action=addtocart&pid=${requestScope.choosedP.proID}&quantity=" + x;
                                    }
                                }
                                function addToCartFunction() {
                                    var x = document.getElementById("quantity").value;
                                    if (${requestScope.currentCus == null}) {
                                        alert("Vui lòng đăng nhập để sử dụng giỏ hàng");
                                    } else {
                                        //Add to cart
                                        window.location = "cartcontroller?action=addtocart&pid=${requestScope.choosedP.proID}&quantity=" + x;
                                    }
                                }
                            </script>
                            <div class = "col-sm-1">

                            </div>
                        </div>
                    </div>

                    <div class = "content_lower">
                        <div class="row">
                            <div class="describe">
                                <h4>Mô tả sản phẩm</h4>
                                ${requestScope.choosedP.description}
                            </div>
                        </div>
                    </div>
                    <!---View Feedback-->
                    <div class="container">
                        <div class="row">
                            <div class="panel panel-default widget" style="margin-bottom: 0;">
                                <div class="panel-heading">
                                    <span class="glyphicon glyphicon-comment"></span>
                                    <h3 class="panel-title">Đánh giá</h3>
                                    <span class="label label-info">${numberCom}</span>
                                </div>

                                <div class="panel-body"  style="overflow-y: scroll; max-height: 220px">
                                    <c:forEach items="${listFeedback}" var="feeback">
                                        <div class="row">
                                            <div class="col-xs-2 col-md-1">
                                                <img src="${feeback.customer.cusImage}" class="img-circle img-responsive" alt="" />
                                            </div>
                                            <div class="col-xs-10 col-md-11">
                                                <div>
                                                    <div>
                                                        <label id="starF_1_${feeback.feID}">☆</label>
                                                        <label id="starF_2_${feeback.feID}">☆</label>
                                                        <label id="starF_3_${feeback.feID}">☆</label>
                                                        <label id="starF_4_${feeback.feID}">☆</label>
                                                        <label id="starF_5_${feeback.feID}">☆</label>
                                                    </div>
                                                    
                                                    <script>
                                                        function ratedStarShowFunction() {
                                                            for (var i = 1, max = ${feeback.rated}; i <= max; i++) {
                                                                document.getElementById("starF_" + i + "_${feeback.feID}").style.color = '#FFD700';
                                                            }
                                                        }
                                                        ratedStarShowFunction();
                                                    </script>
                                                    
                                                    <div class="mic-info">
                                                        By: <strong>${feeback.customer.cusName}</strong> | ${feeback.dateCreate}
                                                    </div>
                                                </div>
                                                    
                                                <div class="comment-text" id="feedback">Nội dung: ${feeback.content}</div>
                                                
                                                <c:if test="${feeback.customer.cusID==currentCus.cusID}">
                                                    <div class="action">
                                                        <a href="editFeedback?feID=${feeback.feID}&proID=${feeback.product.proID}">
                                                            <button type="button" class="btn btn-primary btn-xs" title="Edit">
                                                                <span class="glyphicon glyphicon-pencil"></span>
                                                            </button>
                                                        </a>
                                                        <a href="deletefeedback?feID=${feeback.feID}&proID=${feeback.product.proID}">
                                                            <button type="button" class="btn btn-danger btn-xs" title="Delete">
                                                                <span class="glyphicon glyphicon-trash"></span>
                                                            </button>
                                                        </a>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                        <hr>
                                    </c:forEach>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!---->

                    <div class = "feedback_container">
                        <h3>Ðánh giá sản phẩm</h3><br>
                        <label id = "feedback_alert"> Bạn phải đăng nhập để có thể đánh giá sản phẩm </label>
                        <c:if test="${requestScope.currentCus == null}">
                            <style>
                                #feedback_form{
                                    display: none;
                                }
                                #feedback_alert{
                                    display: block;
                                }
                            </style>
                        </c:if>
                        <form id ="feedback_form" action="feedback?feID=${feedback.feID}" method="POST">
                            <input type="hidden" id="currentCusID" name="currentCusID" value=${requestScope.currentCus.cusID}>
                            <input type="hidden" id="choosedProductID" name = "choosedProductID" value=${requestScope.choosedP.proID}>
                            <table>
                                <tr>
                                    <th>Ðánh giá*</th>
                                    <th>
                                        <div class="rating">
                                            <input type="radio" name="rating" value="5" id="5"><label for="5">☆</label>
                                            <input type="radio" name="rating" value="4" id="4"><label for="4">☆</label>
                                            <input type="radio" name="rating" value="3" id="3"><label for="3">☆</label>
                                            <input type="radio" name="rating" value="2" id="2"><label for="2">☆</label>
                                            <input type="radio" name="rating" value="1" id="1"><label for="1">☆</label>
                                        </div>
                                    </th>
                                </tr>
                                <tr>
                                    <th>Khách hàng*</th>
                                    <td>
                                        <input type="text" id ="currentCusName" name="currentCusName" size="40" disabled value=${requestScope.currentCus.cusName}>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Email*</th>
                                    <td>
                                        <input type="email" id ="currentCusEmail" name="currentCusEmail" size="40" disabled value=${requestScope.currentCus.email}>
                                    </td>
                                </tr>

                                <tr>
                                    <th>Điện thoại*</th>
                                    <td>
                                        <input type="tel" id ="currentCusPhoneNum" name="currentCusPhoneNum" size="40" disabled value=${requestScope.currentCus.cusPhone}>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Nội dung</th>
                                    <td>
                                        <textarea id="feedback_content" name="feedback_content" rows="4" cols="50">${feedback.content}</textarea>
                                    </td>
                                </tr>
                            </table>
                            <input class="feedback_button" type="submit" value="Ðánh giá">
                        </form>
                    </div>

                    <div class="related_product">
                        <h4>Sản phẩm liên quan</h4>
                        <c:forEach items="${requestScope.products}" var="p">                              
                            <div class="col-lg-3 col-md-3 col-sm-3">
                                <div class="productTag">
                                    <div class="productTop">
                                        <a href="productDetail?proid=${p.proID}">
                                            <img class="cropImgProduct" src="${p.image}">
                                            <h4 class="productName">${p.proName}</h4>
                                        </a>
                                    </div>
                                    <div class="productBottom">
                                        <p class="productPrice">
                                            <fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${requestScope.choosedP.price}"/> VND
                                        </p>
                                        <a href="#"><button class="addToCart-btn">Thêm vào giỏ</button></a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class = "col-md-1"></div>
            </div>
        </div>

        <jsp:include page="footer.jsp"></jsp:include>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>

</html>
