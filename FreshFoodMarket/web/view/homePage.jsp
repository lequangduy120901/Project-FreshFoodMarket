<%-- 
    Document   : homePage
    Created on : May 28, 2022, 8:48:40 PM
    Author     : Dinh Nam
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.Locale"%>
<%@page import="util.Slider"%>
<%@page import="util.Post"%>
<%@page import="util.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<Slider> sList = (List<Slider>) request.getAttribute("slideList");
    List<Post> pList = (List<Post>) request.getAttribute("postList");
    List<Product> proList = (List<Product>) request.getAttribute("proList");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA_Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, intial-scale=1.0">
        <title>Fresh Food Market</title>

        <link rel="shortcut icon" href="img/logo/Icon-logo.png">

        <link rel="stylesheet" type="text/css" href="css/lightslider.css">
        <link rel="stylesheet" type="text/css" href="css/w3.css">
        <link rel="stylesheet" type="text/css" href="css/productStyle.css">
        <link rel="stylesheet" type="text/css" href="css/homePageStyle.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">

        <script type="text/javascript" src="js/Jquery.js"></script>
        <script type="text/javascript" src="js/lightslider.js"></script>
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
            <!---->
            <div class="bodyHomePage" style="max-width: 1180px; margin: 0 auto;">
                <div class="slide" style="margin: 5px 0;">
                    <ul class="w3-content w3-display-container" style="max-width: 1180px; list-style: none; padding: 0;">
                    <%
                    for (Slider o : sList) {
                    %>
                    <li>
                        <a href="<%= o.getBacklink()%>" class="mySlides">
                            <img class="cropImgSlider" src="<%= o.getSlideImage()%>" style="width:100%">
                            <div class="w3-display-topleft slideText w3-animate-top">
                                <div class="slideTitle"><%= o.getTitle()%></div>
                                <div class="slideContent"><%= o.getDescription()%></div>
                            </div>
                        </a>
                    </li>
                    <%}%>


                    <div class="w3-center w3-container w3-section w3-large w3-text-white w3-display-bottommiddle" style="width:100%">
                        <%
                        for (Slider o : sList) {
                        %>
                        <span class="w3-badge demo w3-border w3-transparent w3-hover-white"></span>
                        <%}%>
                    </div>
                </ul>
            </div>

            <h3 style="font-weight: bold; background-color: #2a8738; color: #fff; padding: 5px 7px;">Sản phẩm nổi bật</h3>

            <div class="featureProduct container" style="background-color: #fff; padding: 0;">
                <%
                for (Product i : proList) {
                    String price = String.format(Locale.GERMAN, "%,.0f", i.getPrice());
                %>
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="productTag">
                        <div class="productTop">
                            <a href="productDetail?proid=<%= i.getProID()%>">
                                <img class="cropImgProduct" src="<%= i.getImage()%>">
                                <h4 class="productName"><%= i.getProName()%></h4>
                            </a>
                        </div>
                        <div class="productBottom">
                            <p class="productPrice"><%= price%> VND</p>
                            <a href="cartcontroller?action=addtocart&pid=<%= i.getProID()%>&quantity=1"><button class="addToCart-btn">Thêm vào giỏ</button></a>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
            </div>

            <div class="container" style="margin: 20px 0 30px 0; width: 100%; background-color: #fff;">
                <a href="categories?idC=0" style="clear: both; display: flex; align-items: center;">
                    <button style="width: 200px; margin: auto; height: 40px; background-color: #2a8738; color: #fff; font-weight: bold; border-radius: 5px; border: none;">Xem tất cả</button>
                </a>
            </div>

            <div class="postSlider" style="max-width: 1180px; margin: 10px 0;">
                <div class="postTop">
                    <p style="font-size: x-large; font-weight: bold; float: left;">Bài đăng mới</p>
                    <a href="/FreshFoodMarket/PostListController" style="float: right; color: #000;">Xem tất cả ></a>
                </div>
                <div class="postBottom" style="background-color: #fff; clear: both;">
                    <section class="slider">
                        <ul id="autoplay" class="cs-hidden">
                            <%
                            for (Post u : pList) {
                            %>
                            <li class="item-a">
                                <div class="box">
                                    <div class="slide-img">
                                        <img alt="1" src="<%= u.getThumbnail()%>">
                                        <div class="overlay">
                                            <a href="/FreshFoodMarket/PostDetailController?postID=<%= u.getPostID()%>" class="view-btn">Xem chi tiết</a>
                                        </div>
                                    </div>
                                    <div class="detail-box">
                                        <div class="type">
                                            <a style="height: 40px" href="#"><%= u.getTitle()%></a>
                                            <span><%= u.getAuthor()%></span>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <%
                                }
                            %>
                        </ul>
                    </section>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp"></jsp:include>

        <script type="text/javascript" src="js/homePage.js"></script>
    </body>
</html>
