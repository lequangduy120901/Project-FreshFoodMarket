<%-- 
    Document   : CustomerListSale
    Created on : Jul 20, 2022, 12:26:08 PM
    Author     : THAI BAO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./css/CustomerListStyle.css">
    <link rel="stylesheet" href="./css/bootstrap.css">
</head>

<body>
    <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>

    <div class="slideContainer">
        <div class="list-top">
            <div class="list-top-top">
                <h3>Quản Lý Khách Hàng</h3>
            </div>
            <div class="list-top-bottom">
                <div class="ltb-left">
                    <form action="CustomerListSaleController" method="POST">
                        <input name="search" type="text" placeholder="Tìm kiếm">
                        <button>Tìm</button>
                    </form>
                    <br>
                   
                </div>
                <div class="ltb-right">
                    <form action="CustomerListSaleController" method="POST">
                        <select name="cusName" id="">
                            <option value="" disabled selected>CusName</option>
                            <option value="AZ">A-Z</option>
                            <option value="ZA">Z-A</option>
                        </select>
                        <select name="cusID" id="">
                            <option value="" disabled selected>CusID</option>
                            <option value="MintoMax">Nhỏ đến lớn</option>
                            <option value="MaxtoMin">Lớn đến nhỏ</option>
                        </select>
                        <button>Lọc</button>
                    </form>
                    <br>
                    <a href="CustomerListSaleController" style="float: right;"><button>Xóa bộ lọc</button></a>
                </div>
                <div style="clear: both;"></div>
            </div>
        </div>
        <c:if test="${listC.isEmpty()}">
                    <h3 style="text-align: center; color: red;">Không tìm thấy khách hàng phù hợp!</h3>
        </c:if>
         
          <h3 style="text-align: center; color: red;">${mess}</h3>
        
        <div class="list-bottom">
            <table class="slideTable">
                <tr>
                    <th>Ảnh</th>
                    <th>Mã Khách Hàng</th>
                    <th>Họ và Tên</th>
                    <th>Địa chỉ</th>
                    <th>SĐT</th>
                    <th>Email</th>
                    <th style="width: 80px;">Chi tiết</th>
                </tr>
             <c:forEach items="${listC}" var="o">
                <tr>
                    <td><img class="slideImg" src="${o.cusImage}" alt=""></td>
                    <td>${o.cusID}</td>
                    <td>${o.cusName}</td>
                    <td>${o.cusAddress}</td>
                    <td>${o.cusPhone}</td>
                    <td>${o.email}</td>
                    <td>
                        <a href="CustomerDetailSaleController?cusID=${o.cusID}" >Chi tiết</a>
                    </td>
                </tr>
              </c:forEach>  
            </table>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
    <script src="./js/CustomerListSale.js"></script>
    
</body>

</html>
