<%-- 
    Document   : productListSale
    Created on : Jul 4, 2022, 11:47:07 PM
    Author     : Dinh Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page import="util.Category"%>
<%@page import="DAO.CategoryDAO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<%
    CategoryDAO cDao = new CategoryDAO();
    List<Category> cateList = cDao.getAllCate();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA_Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, intial-scale=1.0">
        
        <title>Quản Lý Sản Phẩm</title>
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">
        
        <link rel="stylesheet" type="text/css" href="css/productListSaleStyle.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>

        <div class="proContainer">
            <div class="list-top">
                <div class="list-top-top">
                    <h3>Quản Lý Sản Phẩm</h3>
                </div>
                <div class="list-top-bottom">
                    <div class="ltb-left">
                        <form action="/FreshFoodMarket/productListSale" method="POST">
                            <input name="key" type="text" value="${requestScope.key}" placeholder="Nhập tên sản phẩm">
                            <button>Tìm</button>
                        </form>
                        <br>
                        <button onclick="addProduct()">Thêm sản phẩm</button>
                    </div>
                    <div class="ltb-right">
                        <form action="/FreshFoodMarket/productListSale" method="POST">
                            <input name="key" type="hidden" value="${requestScope.key}">
                            <select name="proName" id="">
                                <option value="" selected disabled>Tên sản phẩm</option>
                                <option value="pro1" ${(requestScope.proName.equals("pro1"))?"selected":""}>A-Z</option>
                                <option value="pro2" ${(requestScope.proName.equals("pro2"))?"selected":""}>Z-A</option>
                            </select>
                            <select name="category" id="">
                                <option value="" selected disabled>Category</option>
                                <%
                                    for (Category c : cateList) {
                                %>
                                <c:set var="cate" value="<%= c.getCateID()%>"></c:set>
                                <option value="<%= c.getCateID()%>" ${(requestScope.category.equals(cate))?"selected":""}><%= c.getCateName()%></option>
                                <%}%>
                            </select>
                        
                            <select name="price" id="">
                                <option value="" selected disabled>Giá</option>
                                <option value="p1" ${(requestScope.price.equals("p1"))?"selected":""}>↑</option>
                                <option value="p2" ${(requestScope.price.equals("p2"))?"selected":""}>↓</option>
                            </select>
                            <select name="quantity" id="">
                                <option value="" selected disabled>Số lượng</option>
                                <option value="q1" ${(requestScope.quant.equals("q1"))?"selected":""}>↑</option>
                                <option value="q2" ${(requestScope.quant.equals("q2"))?"selected":""}>↓</option>
                            </select>
                            <button>Lọc</button>
                        </form>
                            <br>
                            <button style="float: right;" onclick="clearFilter()">Xóa bộ lọc</button>
                    </div>
                    <div style="clear: both;"></div>
                </div>
            </div>
            <div class="list-bottom">
                <c:if test="${listP.isEmpty()}">
                    <h3 style="text-align: center;">Không tìm thấy sản phẩm phù hợp!</h3>
                </c:if>
                <c:if test="${!listP.isEmpty()}">
                    <table class="proTable">
                        <tr>
                            <th>Ảnh</th>
                            <th style="min-width: 80px;">ID</th>
                            <th>Tên sản phẩm</th>
                            <th style="min-width: 120px;">Category</th>
                            <th style="min-width: 90px;">Giá</th>
                            <th style="width: 90px;">Số lượng</th>
                            <th style="width: 100px;">Cập nhật</th>
                            <th style="width: 80px;">Xem</th>
                        </tr>
                    <c:forEach items="${listP}" var="o">
                        <tr>
                            <td><img class="proImg" src="${o.image}" alt=""></td>
                            <td>${o.proID}</td>
                            <td>${o.proName}</td>
                            <td>${o.category.cateName}</td>
                            <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${o.price}"/> đ</td>
                            <td>${o.quantity}</td>
                            <td><fmt:formatDate value="${o.updateDate}" pattern="dd-MM-yyyy"/></td>
                            <td>
                                <button onclick="detail(${o.proID})">Xem</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
        <script type="text/javascript" src="js/productListSale.js"></script>
    </body>
</html>
