<%-- 
    Document   : UserDetail
    Created on : Jun 21, 2022, 10:46:46 PM
    Author     : HuyCQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>User Details</title>
        <meta charset="utf-8">

        <!-- <link rel="stylesheet" href="./dist/bootstrap.css">
        <link rel="stylesheet" href="./dist/bootstrap.min.css"> -->

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->


        <link rel="stylesheet" href="https://bootswatch.com/4/simplex/bootstrap.min.css" />
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">
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
    </head>

    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                        <c:set value="${cus}" var="cus"/>
                        <c:set value="${em}" var="em"/>
                        <c:set value="${uID}" var="uID"/>
                        <form action="userdetail?cusID=${cus.cusID}&emID=${em.emID}&uID=${uID}" method="post">
                            <div class="card-body">
                                <div class="card-title mb-4">
                                    <div class="d-flex justify-content-start">
                                        
                                        <div class="image-container">
                                            <img src="<c:if test="${em.emID == null}">${cus.cusImage}</c:if>${em.emImage}" 
                                                 id="avatar" style="width:150px;height:150px; margin-bottom: 10px;">
                                            <div class="middle">
                                                <input type="file" id="avatar_image" name="image" value=""
                                                       onchange="chooseFile(this)" accept=".png, .jpeg, .jpg" >
                                            </div>
                                        </div>
                                                 
                                        <div class="userData ml-3">
                                            <h2 class="d-block" style="color: green; font-size: 1.5rem; font-weight: bold"><c:if test="${em.emID == null}">${cus.cusName}</c:if>${em.emName}</h2>
                                        </div>
                                        <div class="ml-auto">
                                            <input type="button" class="btn btn-primary d-none" id="btnDiscard"
                                                   value="Discard Changes" />
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-12">
                                        <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link active" id="basicInfo-tab" data-toggle="tab"
                                                   href="#basicInfo" role="tab" aria-controls="basicInfo"
                                                   aria-selected="true">Thông tin cá nhân</a>
                                            </li>

                                        </ul>
                                        <div class="tab-content ml-1" id="myTabContent">
                                            <div class="tab-pane fade show active" id="basicInfo" role="tabpanel"
                                                 aria-labelledby="basicInfo-tab">

                                                <div class="row">
                                                    <div class="col-sm-3 col-md-2 col-5">
                                                        <label style="font-weight:bold;">Giới tính</label>
                                                    </div>
                                                    <div class="col-md-8 col-6">
                                                        <input type="radio" name="gender" value="male" <c:if test="${cus.gender==true || em.gender==true}">checked</c:if>>Nam &nbsp;
                                                        <input type="radio" name="gender" value="female" <c:if test="${cus.gender==false || em.gender==false}">checked</c:if>>Nữ
                                                        </div>
                                                    </div>
                                                    <hr />
                                                    <div class="row">
                                                        <div class="col-sm-3 col-md-2 col-5">
                                                            <label style="font-weight:bold;">Email</label>
                                                        </div>
                                                        <div class="col-md-8 col-6">
                                                            <input type="text" name="email" value="<c:if test="${em.emID == null}">${cus.email}</c:if>${em.email}"
                                                               style="border: none;width: 300px; overflow: scroll; max-height:  100px">

                                                    </div>
                                                </div>
                                                <hr />

                                                <div class="row">
                                                    <div class="col-sm-3 col-md-2 col-5">
                                                        <label style="font-weight:bold;">Số điện thoại</label>
                                                    </div>
                                                    <div class="col-md-8 col-6">
                                                        <input type="text" name="phone" value="<c:if test="${em.emID == null}">${cus.cusPhone}</c:if>${em.emPhone}" style="border: none">

                                                    </div>
                                                </div>
                                                <hr />


                                                <div class="row">
                                                    <div class="col-sm-3 col-md-2 col-5">
                                                        <label style="font-weight:bold;">Địa chỉ</label>
                                                    </div>
                                                    <div class="col-md-8 col-6">
                                                        <input type="text" name="address" value="<c:if test="${em.emID == null}">${cus.cusAddress}</c:if>${em.emAddress}" 
                                                               style="border: none; width: 500px; overflow: scroll; max-height:  100px">

                                                    </div>
                                                </div>
                                                <c:if test="${uID==1}">
                                                    <hr />
                                                    <div class="row">
                                                        <div class="col-sm-3 col-md-2 col-5">
                                                            <label style="font-weight:bold;">Số đơn hàng đã mua trong tháng ${month}</label>
                                                        </div>
                                                        <div class="col-md-8 col-6">
                                                            ${numOrder}
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <button style="float: right" type="submit" class="btn btn-success">Lưu</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div><jsp:include page="footer.jsp"></jsp:include></div>
    </body>
</html>
