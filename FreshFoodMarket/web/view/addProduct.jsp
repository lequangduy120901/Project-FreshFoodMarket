<%-- 
    Document   : addProduct
    Created on : Jul 6, 2022, 2:37:29 AM
    Author     : Dinh Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

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
        
        <title>Thêm Sản Phẩm</title>
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">
        
        <link rel="stylesheet" type="text/css" href="css/productDetailSaleStyle.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
        
        <div class="content-container">
            <div class="update-time">
                <p style="display: none;" id="status">${requestScope.status}</p>
                <p style="color: red;">${requestScope.mess}</p>
            </div>
            <form id="proForm" action="/FreshFoodMarket/addProduct" method="post">
                <div class="content-infor">
                    <div class="content-left">
                        <p class="imgBorder"><img id="img-pro" class="proImg" alt="product's image"></p>
                        <p >
                            <input id="img-input" class="file-input" type="file" accept="image/*" name="imgInput" onchange="changeImg(this)">
                        </p>
                    </div>
                    <div class="content-right">
                        <table class="infor-table">
                            <tr>
                                <td>ID</td>
                                <td><input id="id" name="proID" type="text"></td>
                            </tr>
                            <tr>
                                <td>Category</td>
                                <td>
                                    <select name="category" id="">
                                        <%
                                            for (Category c : cateList) {
                                        %>
                                        <option value="<%= c.getCateID()%>"><%= c.getCateName()%></option>
                                        <%}%>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Tên sản phẩm</td>
                                <td><input id="proName" name="proName" type="text"></td>
                            </tr>
                            <tr>
                                <td>Giá</td>
                                <td><input id="price" name="price" type="text"></td>
                            </tr>
                            <tr>
                                <td>Đơn vị</td>
                                <td><input id="type" name="type" type="text"></td>
                            </tr>
                            <tr>
                                <td>Số lượng</td>
                                <td><input id="quant" name="quantity" type="text"></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="content-des">
                    <h3>Mô tả sản phẩm</h3>
                    <textarea id="des-text" name="description" placeholder="Nhập mô tả sản phẩm tại đây..."></textarea>
                </div>
            </form>
            <div class="button-ctn">
                <button type="button" onclick="cancelAdd()">Hủy</button>
                <button type="button" onclick="addPro()">Lưu</button>
            </div>
        </div>
        <script>
            function changeImg(fileInput) {
                if (fileInput.files && fileInput.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function(e) {
                        document.getElementById("img-pro").src = e.target.result;
                        document.getElementById("img-pro").alt = e.target.result;
                    };
                    reader.readAsDataURL(fileInput.files[0]);
                }
            }

            function cancelAdd() {
                window.location = "/FreshFoodMarket/productListSale";
            }

            function addPro() {
                document.getElementById("proForm").submit();
            }
            
            function checkAdd() {
                const s = document.getElementById("status").innerHTML;
                if (s == "1") {
                    alert("Thêm sản phẩm mới thành công!");
                    window.location = "/FreshFoodMarket/productListSale";
                } else if (s == "-1"){
                    alert("Thêm sản phẩm mới thất bại!");
                }
            }
            
            checkAdd();
        </script>                      
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
