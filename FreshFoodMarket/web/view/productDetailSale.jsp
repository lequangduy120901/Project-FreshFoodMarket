<%-- 
    Document   : productDetailSale
    Created on : Jul 5, 2022, 12:50:42 AM
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
        
        <title>Thông Tin Sản Phẩm</title>
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">
        
        <link rel="stylesheet" type="text/css" href="css/productDetailSaleStyle.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
        
        <div class="content-container">
            <div class="top">
                <h3 class="bigTitle">Thông Tin Sản Phẩm</h3>
                <button type="button" class="del" id="del" name="del" onclick="deletePro(${requestScope.product.proID})">Xóa</button>
            </div>
            <div class="update-time">
                <c:if test="${requestScope.mess != null}">
                    <p style="color: red;">${requestScope.mess}</p>
                </c:if>
                <p>Lần cập nhật cuối: <fmt:formatDate value="${requestScope.product.updateDate}" pattern="dd-MM-yyyy"/></p>
            </div>
            <form id="proForm" action="/FreshFoodMarket/productDetailSale" method="POST">
                <div class="content-infor">
                    <div class="content-left">
                        <img id="img-pro" class="proImg" src="${requestScope.product.image}" alt="">
                        <p id="imgCont">
                            <input id="img-input" class="file-input" type="file" accept="image/*" name="imgInput" onchange="changeImg(this)">
                        </p>
                    </div>
                    <div class="content-right">
                        <table class="infor-table">
                            <tr>
                                <td>ID</td>
                                <td><input id="id" name="proID" type="text" value="${requestScope.product.proID}" style="pointer-events: none;"></td>
                            </tr>
                            <tr>
                                <td>Category</td>
                                <td>
                                    <select name="category" id="cate" style="pointer-events: none;">
                                        <%
                                            for (Category c : cateList) {
                                        %>
                                        <c:set var="cate" value="<%= c.getCateID()%>"></c:set>
                                        <option value="<%= c.getCateID()%>" ${(requestScope.product.category.cateID.equals(cate))?"selected":""}><%= c.getCateName()%></option>
                                        <%}%>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Tên sản phẩm</td>
                                <td><input id="proName" name="proName" type="text" value="${requestScope.product.proName}" disabled></td>
                            </tr>
                            <tr>
                                <td>Giá</td>
                                <td><input id="price" name="price" type="text" value="<fmt:formatNumber type="number" maxFractionDigits="0" value="${requestScope.product.price}"/>" disabled></td>
                            </tr>
                            <tr>
                                <td>Đơn vị</td>
                                <td><input id="type" name="type" type="text" value="${requestScope.product.type}" disabled></td>
                            </tr>
                            <tr>
                                <td>Số Lượng</td>
                                <td><input id="quant" name="quantity" type="text" value="${requestScope.product.quantity}" disabled></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="content-des">
                    <h3>Mô tả</h3>
                    <textarea id="des-text" name="description" disabled>${requestScope.product.description}</textarea>
                </div>
            </form>
            <div class="button-ctn">
                <button type="button" id="back" name="back" onclick="submit()">Quay lại</button>
                <button type="button" id="edit" name="edit" onclick="showContent()">Chỉnh sửa</button>
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
        <script type="text/javascript" src="js/productDetailSale.js"></script>
    </body>
</html>
