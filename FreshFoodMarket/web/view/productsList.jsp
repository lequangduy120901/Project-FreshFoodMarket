<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sản phẩm</title>
        
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">

        <link rel="stylesheet" href="css/productStyle.css">
        <link rel="stylesheet" href="css/productsListStyle.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <style>
            .pagination {
                display: inline-block;
            }
            .pagination a {
                color: black;
                font-size: 22px;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
            }
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
            }
            .pagination a:hover:not(.active) {
                background-color: chocolate;
            }
        </style>
    </head>

    <body>
        <a name="top"></a>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>

            <div style="background-color: #fff; margin: 0 auto; max-width: 1180px;">
                <div class="leftBackground"></div>
                <div class="productsListLeft">
                    <div class="categoryList">
                        <h3 class="leftTitle">Loại sản phẩm</h3>
                        <a href="categories?idC=0">Tất cả</a>
                    <c:forEach items="${listCate}" var="o">
                        <a href="categories?idC=${o.cateID}">${o.cateName}</a>
                    </c:forEach>
                </div>

                <c:set value="${idC}" var="idC"/>
                <div class="filterList">
                    <h3 class="leftTitle">Bộ lọc</h3>
                    <p>Khoảng giá (VND)</p>
                    <form action="sort" method="post">
                        <div class="inputPrice">
                            <p><input style="color: black" name="from" type="text" placeholder="Từ" value="${from}"> -
                                <input style="color: black" name="to" type="text" placeholder="Đến" value="${to}"></p>
                        </div>
                        <div style="width: 100%; display: flex; justify-content: space-around;">
                            <button style="background-color: white; color: #2a8738; border: none; border-radius: 5px; font-weight: bold;">Tìm kiếm</button>
                        </div>
                    </form>
                    <a href="sort?idS=1&idC=${idC}">Giá giảm dần</a>
                    <a href="sort?idS=2&idC=${idC}">Giá tăng dần</a>
                </div>
            </div>

            <div class="productsListRight" style="background-color: #fff; margin: 0; padding: 0;">
                
                <c:set var="page" value="${page}"/>
                <div class="pagination" style="margin-bottom: 0;">
                    <c:forEach begin="${1}" end="${num}" var="i">
                        <a class="${i==page?"active":""}"href="categories?idC=${idC}&page=${i}">${i}</a>
                    </c:forEach>
                </div>

                <c:set value="${mess}" var="mess"/>
                <h2>${mess}</h2>

                <c:forEach items="${listP}" var="o">
                    <div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="productTag">
                            <div class="productTop">
                                <!--link to the product details-->
                                <a href="productDetail?proid=${o.proID}">
                                    <img class="cropImgProduct" src="${o.image}">
                                    <h4 class="productName">${o.proName}</h4>
                                </a>
                            </div>
                            <div class="productBottom">
                                <p class="productPrice"><fmt:formatNumber value = "${o.price}"/>đ</p>
                                <!--link to add to cart-->
                                <a href="cartcontroller?action=addtocart&pid=${o.proID}&quantity=1"><button class="addToCart-btn">Thêm vào giỏ</button></a>
                            </div>
                        </div>
                    </div>  
                </c:forEach>
            </div>
        </div>
        <div style="clear: both;"></div>
        <div><jsp:include page="footer.jsp"></jsp:include></div>
    </body>

</html>