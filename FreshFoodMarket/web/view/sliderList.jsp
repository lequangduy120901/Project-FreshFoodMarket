<%-- 
    Document   : sliderList
    Created on : Jul 7, 2022, 3:44:47 PM
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
        
        <title>Quản Lý Slider</title>
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">
        
        <link rel="stylesheet" type="text/css" href="css/sliderListStyle.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
        
        <div class="slideContainer">
            <div class="list-top">
                <div class="list-top-top">
                    <h3>Quản Lý Slider</h3>
                </div>
                <div class="list-top-bottom">
                    <div class="ltb-left">
                        <form action="/FreshFoodMarket/sliderList" method="POST">
                            <input name="key" type="text" placeholder="Nhập..." value="${key}">
                            <button>Tìm</button>
                        </form>
                        <br>
                        <button onclick="addSlide()">Thêm slider</button>
                    </div>
                    <div class="ltb-right">
                        <form action="/FreshFoodMarket/sliderList" method="POST">
                            <input name="key" type="hidden" value="${requestScope.key}">
                            <select name="title" id="">
                                <option value="" disabled selected>Tiêu đề</option>
                                <option value="1" ${(requestScope.title.equals("1"))?"selected":""}>A-Z</option>
                                <option value="0" ${(requestScope.title.equals("0"))?"selected":""}>Z-A</option>
                            </select>
                            <select name="status" id="">
                                <option value="" disabled selected>Trạng thái</option>
                                <option value="1" ${(requestScope.status.equals("1"))?"selected":""}>Hoạt động</option>
                                <option value="0" ${(requestScope.status.equals("0"))?"selected":""}>Không hoạt động</option>
                            </select>
                            <button>Lọc</button>
                        </form>
                        <br>
                        <button class="del" onclick="clearFilter()">Xóa bộ lọc</button>
                    </div>
                    <div style="clear: both;"></div>
                </div>
            </div>
            <div class="list-bottom">
                <c:if test="${listS.isEmpty()}">
                    <h3 style="text-align: center;">Không tìm thấy danh sách slider phù hợp!</h3>
                </c:if>
                <c:if test="${!listS.isEmpty()}">
                    <table class="slideTable">
                        <tr>
                            <th>Ảnh</th>
                            <th style="width: 100px;">ID</th>
                            <th>Tiêu đề</th>
                            <th>Liên kết</th>
                            <th style="width: 120px;">Trạng thái</th>
                            <th style="width: 80px;">Xem</th>
                        </tr>
                        <c:forEach items="${listS}" var="o">
                            <tr>
                                <td>
                                    <img class="slideImg" src="${o.slideImage}" alt="">
                                </td>
                                <td>${o.slideID}</td>
                                <td>${o.title}</td>
                                <td>${o.backlink}</td>
                                <td>${(o.status == true)?"Hoạt động":"Không hoạt động"}</td>
                                <td>
                                    <!--<form action="/FreshFoodMarket/sliderDetail?id={o.slideID}" method="GET">-->
                                    <a href="/FreshFoodMarket/sliderDetail?id=${o.slideID}"><button>Xem</button></a>
                                    <!--</form>-->
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
        <script type="text/javascript" src="js/sliderList.js"></script>
    </body>
</html>
