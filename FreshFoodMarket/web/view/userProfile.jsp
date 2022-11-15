<%-- 
    Document   : userProfile
    Created on : Jun 15, 2022, 10:33:05 PM
    Author     : zedqu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tài Khoản</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>

        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css" rel="stylesheet"> 
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
            table {
                margin-top: 50px;
            }

            td {
                padding-top: 25px;

            }

            .input-element {
                padding: 6px;
                margin-top: 25px;
            }

            .update_Button{
                border:1px solid green;
                background-color: green;
                width: 150px;
                height: 50px;
                margin-top: 100px;
                margin-bottom: 100px;
                color: white;
            }

            select, option{
                width: 100%;
            }


        </style>

    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
            <div style="background-color: #fff; margin: 0 auto; max-width: 1180px;">
                <h3>Screen For Customer</h3>
                <div class="row" id="screen_for_customer">

                    <div class="col-lg-3 col-md-3 col-sm-3 column margintop20" id="left_menu">

                        <ul class="nav nav-pills nav-stacked">
                            <li class="active text-center">
                            </li> 
                            <li><a href="/FreshFoodMarket/userProfile" class="active2"><span class="glyphicon glyphicon-chevron-right"></span>Tài khoản</a>
                            </li> 
                            <li><a href="/FreshFoodMarket/myOrders" ><span class="glyphicon glyphicon-chevron-right"></span>Quản lý đơn hàng</a>
                            </li> 
                            <li><a href="ChangePass"><span class="glyphicon glyphicon-chevron-right"></span>Đổi mật khẩu</a>
                            </li> 
                            <!--<li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span>Lịch sử giao dịch</a>-->
                            </li>
                            <li><a href="/FreshFoodMarket/loginController?action=logout"><span class="glyphicon glyphicon-chevron-right"></span>Đăng xuất</a>
                            </li>
                        </ul>

                    </div>


                    <div class="col-lg-9 col-md-12 col-sm-12" id = right_form>

                        <form action ="userProfile" method="POST">
                            <table>
                                <input type="hidden" name="cus_id" id="cus_id" value="${requestScope.cus.cusID}">
                            <input type="hidden" name="accID" id="accID" value="${sessionScope.currUser.accID}">
                            <input type ="hidden"  id="current-time" name="updateDate"></div>
                            <input type="hidden" name="last_avatar" id="last_avatar" value="${requestScope.cus.cusImage}">
                            <th>
                                <h3>Thông tin tài khoản</h3>
                            </th>
                            <tr>
                                <td>Ảnh đại diện</td>
                                <td>

                                    <img src="${requestScope.cus.cusImage}" alt="ảnh của ${requestScope.cus.cusImage}" id="avatar" style="width:250px;height:250px; object-fit: cover; margin-bottom: 10px;">
                                    <input type="file" id="avatar_image" name="avatar_image" value="" onchange="chooseFile(this)" accept=".png, .jpeg, .jpg" >
                                </td>
                            </tr>
                            <script>
                                function chooseFile(fileInput) {
                                    if (fileInput.files && fileInput.files[0]) {
                                        var reader = new FileReader();

                                        reader.onload = function (e) {
                                            $('#avatar').attr('src', e.target.result);
                                        }
                                        reader.readAsDataURL(fileInput.files[0]);
                                    }
                                }
                            </script>
                            <tr>
                                <td>Họ tên*</td>
                                <td><input class="input-element" type="text" id ="cus_import" name="cus_name" size=65% width="50%" value="${requestScope.cus.cusName}" > </td>
                            </tr>

                            <tr>
                                <td>Số điện thoại*</td>
                                <td><input class="input-element" type="tel" id ="cus_import" name="cus_phone" size=65% value=${requestScope.cus.cusPhone}></td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td><input class="input-element" type="email" id ="cus_import" size=65% disabled value=${requestScope.cus.email}>
                                    <p>(Bạn không thể thay đổi email)</p>
                                </td>
                            </tr>
                            <tr>
                                <td>Giới tính</td>
                                <td>
                                    <input type="radio" id="cus_import_F" name="cus_gender" value="false" checked>
                                    <label for="cus_gender_F">Nữ</label>
                                    <input type="radio" id="cus_import_M" name="cus_gender" value= "true" >
                                    <label for="cus_gender_M">Nam</label>
                                    <c:if test="${requestScope.cus.gender == true}">
                                        <script>
                                            document.getElementById("cus_import_M").checked = true;
                                        </script>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>Tỉnh/Thành Phố</td>
                                <td>
                                    <select class="input-element" required="true" id="cus_tinh" name="cus_tinh" onchange="tinhChangeFunction(this)">
                                        <c:forEach items="${requestScope.capTinhs}" var="tinh">
                                            <option value="${tinh.maDVHC}" 
                                                    <c:if test="${requestScope.choosedTinh eq tinh.maDVHC}">
                                                        selected
                                                    </c:if>>
                                                ${tinh.ten}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <script>
                                function tinhChangeFunction(cus_tinh) {
                                    if (cus_tinh.value) {
                                        window.location = "userProfile?choosedTinh=" + cus_tinh.value;
                                    }

                                }
                            </script>
                            <tr>
                                <td>Quận/Huyện</td>
                                <td>
                                    <select class="input-element" id="cus_huyen" name="cus_huyen" onchange="huyenChangeFunction(this)">
                                        <c:forEach items="${requestScope.capHuyens}" var="huyen">
                                            <option value="${huyen.maDVHC}" <c:if test="${requestScope.choosedHuyen eq huyen.maDVHC}">
                                                    selected
                                                </c:if>>
                                                ${huyen.ten}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <script>
                                function huyenChangeFunction(cus_huyen) {
                                    if (cus_huyen.value) {
                                        window.location = "userProfile?choosedTinh=" + cus_tinh.value + "&choosedHuyen=" + cus_huyen.value;
                                    }
                                }
                            </script>
                            <tr>
                                <td>Xã/Thị Trấn</td>
                                <td>
                                    <select class="input-element" id="cus_xa" name="cus_xa" onchange="xaChangeFunction(this)">
                                        <c:forEach items="${requestScope.capXas}" var="xa">
                                            <option value="${xa.maDVHC}" <c:if test="${requestScope.choosedXa eq xa.maDVHC}">
                                                    selected
                                                </c:if>>
                                                ${xa.ten}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <script>
                                function xaChangeFunction(cus_xa) {
                                    if (cus_xa.value) {
                                        window.location = "userProfile?choosedTinh=" + cus_tinh.value + "&choosedHuyen=" + cus_huyen.value + "&choosedXa=" + cus_xa.value;
                                    }
                                }
                            </script>
                            <tr>
                                <td>Số nhà</td>
                                <td><input class="input-element" type="text" id ="cus_sonha" name="cus_sonha" size=65% width="50%" value="${requestScope.cus_soNha}"> </td>
                            </tr>
                        </table>
                        <input type="submit" class="update_Button" name="update_Button" value="Cập Nhật">

                    </form>
                </div>

            </div>

            <div class="row" id="screen_for_employee">
                <h3>Screen For Employee</h3>

                <div class="col-lg-3 col-md-3 col-sm-3 column margintop20" id="left_menu">

                    <ul class="nav nav-pills nav-stacked">
                        <li class="active text-center"></a>
                        </li> 
                        <li><a href="/FreshFoodMarket/userProfile" class="active2"><span class="glyphicon glyphicon-chevron-right"></span>Tài khoản</a>
                        </li> 
                        <!--<li><a href="/FreshFoodMarket/userProfile" ><span class="glyphicon glyphicon-chevron-right"></span>Quản lý đơn hàng</a>-->
                        </li> 
                        <li><a href="ChangePass"><span class="glyphicon glyphicon-chevron-right"></span>Đổi mật khẩu</a>
                        </li> 
                        <!--<li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span>Lịch sử giao dịch</a>-->
                        </li>
                        <li><a href="/FreshFoodMarket/loginController?action=logout"><span class="glyphicon glyphicon-chevron-right"></span>Đăng xuất</a>
                        </li>
                    </ul>

                </div>


                <div class="col-lg-9 col-md-12 col-sm-12" id = right_form>

                    <form action ="userProfile" method="POST">
                        <table>
                            <input type="hidden" name="em_id" id="em_id" value="${requestScope.em.emID}">
                            <input type="hidden" name="accID" id="accID" value="${sessionScope.currUser.accID}">
                            <input type ="hidden"  id="current-time" name="updateDate"></div>
                            <th>
                                <h3>Thông tin tài khoản</h3>
                            </th>
                            <tr>
                                <td>Ảnh đại diện</td>
                                <td>
                                    <img src="${requestScope.em.emImage}" alt="ảnh của ${requestScope.em.emImage}" id="avatar" style="width:250px;height:250px; object-fit: cover; margin: 10px;">
                                    <input type="file" id="avatar_image" name="avatar_image" onchange="chooseFile(this)" accept=".png, .jpeg, .jpg" >
                                </td>
                            </tr>
                            <script>
                                function chooseFile(fileInput) {
                                    if (fileInput.files && fileInput.files[0]) {
                                        var reader = new FileReader();

                                        reader.onload = function (e) {
                                            $('#avatar').attr('src', e.target.result);
                                        }
                                        reader.readAsDataURL(fileInput.files[0]);
                                    }
                                }
                            </script>
                            <tr>
                                <td>Họ tên*</td>
                                <td><input class="input-element" type="text" id ="em_import" name="em_name" size=65% width="50%" value="${requestScope.em.emName}" > </td>
                            </tr>

                            <tr>
                                <td>Số điện thoại*</td>
                                <td><input class="input-element" type="tel" id ="em_import" name="em_phone" size=65% value=${requestScope.em.emPhone}></td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td><input class="input-element" type="email" id ="em_import" size=65% disabled value=${requestScope.em.email}>
                                    <p>(Bạn không thể thay đổi email)</p>
                                </td>
                            </tr>
                            <tr>
                                <td>Giới tính</td>
                                <td>
                                    <input type="radio" id="em_import_F" name="em_gender" value="false" checked>
                                    <label for="em_gender_F">Nữ</label>
                                    <input type="radio" id="em_import_M" name="em_gender" value= "true" >
                                    <label for="em_gender_M">Nam</label>
                                    <c:if test="${requestScope.em.gender == true}">
                                        <script>
                                            document.getElementById("em_import_M").checked = true;
                                        </script>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>Địa chỉ</td>
                                <td>
                                    <input class="input-element" type="text" id ="cus_import" name="cus_address"  size=65% value="${requestScope.em.emAddress}"> 
                                </td>
                            </tr>                            
                        </table>
                        <input type="submit" class="update_Button" name="update_Button" value="Cập Nhật">

                    </form>
                </div>

            </div>             
        </div>
        <script>
            if (${sessionScope.currUser.role} === 0) {
                document.getElementById('screen_for_employee').style.display = 'none';
            } else {
                document.getElementById('screen_for_customer').style.display = 'none';

            }
        </script>
        <jsp:include page="footer.jsp"></jsp:include>   
        <script src="//code.jquery.com/jquery.js"></script>
        <!-- Bootstrap JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script>
            var curDate = new Date();

            // Ngày hiện tại
            var curDay = curDate.getDate();

            // Tháng hiện tại
            var curMonth = curDate.getMonth() + 1;

            // Năm hiện tại
            var curYear = curDate.getFullYear();

            // Gán vào thẻ HTML
            document.getElementById('current-time').value = curYear + "-" + curMonth + "-" + curDay;
        </script>
    </body>
</html>
