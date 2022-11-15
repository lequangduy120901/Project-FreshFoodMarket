<%-- 
    Document   : UserList
    Created on : Jun 21, 2022, 10:52:49 AM
    Author     : HuyCQ
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <!-- Css -->
        <link rel="stylesheet" href="./css/stylesUser.css">
        <link rel="stylesheet" href="./css/allUser.css">
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400i,600,600i,700,700i" rel="stylesheet">

        <link rel="shortcut icon" href="img/logo/Icon-logo.png">
        <title>Danh sách người dùng</title>
    </head>
    <body>
        <c:set value="${userID}" var="uID"/>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
            <!--Container -->
            <div class="mx-auto bg-grey-lightest">
                <div class="flex flex-1">
                    <!--Sidebar-->
                    <aside id="sidebar"
                           class="bg-side-nav w-1/2 md:w-1/6 lg:w-1/6 border-r border-side-nav hidden md:block lg:block">
                        <div class="flex"></div>
                        <ul class="list-reset flex flex-col">
                            <li class="w-full h-full py-3 px-2">
                                <ul class="list-reset flex flex-col">
                                    <li class="w-full h-full py-3 px-2 border-b border-light-border">
                                        <a href="adminDash"
                                           class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline">
                                            <i class="fas fa-tachometer-alt float-left mx-2"></i>
                                            Admin Dashboard
                                            <span><i class="fa fa-angle-right float-right"></i></span>
                                        </a>
                                    </li>
                                    <li class=" w-full h-full py-3 px-2 border-b border-light-border">
                                        <a href="#"
                                           class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline">
                                            <i class="fas fa-address-card float-left mx-2"></i>
                                            Danh sách tài khoản
                                            <span><i class="fas fa-angle-down float-right"></i></span>
                                        </a>
                                    </li>
                                </ul>
                                <ul class="list-reset -mx-2 bg-white-medium-dark">
                                    <li class="border-t mt-2 border-light-border w-full h-full px-2 py-3 <c:if test="${uID==1}">bg-white</c:if>" >
                                        <a href="userlist?userID=1"
                                           class="mx-4 font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline">
                                            Customer
                                            <span><i class="fa fa-angle-right float-right"></i></span>
                                        </a>
                                    </li>
                                    <li class="border-t border-light-border w-full h-full px-2 py-3 <c:if test="${uID==2}">bg-white</c:if>" >
                                        <a href="userlist?userID=2"
                                           class="mx-4 font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline">
                                            Employee
                                            <span><i class="fa fa-angle-right float-right"></i></span>
                                        </a>
                                    </li>
                                </ul>
                                <ul style="margin-left: 5px">
                                    <li>
                                        <form action="userlist?userID=${uID}" method="post">
                                            <input type="text" name="key" placeholder="Tìm kiếm" value="${key}">
                                            <button type="submit" value="Tìm Kiếm" style="margin-left: 2px; margin-top: 10px;">
                                                <i class="fa fa-search" aria-hidden="true"></i>
                                            </button>
                                            
                                        </form>
                                    </li>
                                </ul>
                            </li>
                        </ul>


                    </aside>
                    <!--/Sidebar-->
                    <!--Main-->
                
                <main class="bg-white-500 flex-1 p-3 overflow-hidden">
                    
                    <div class="flex flex-col">

                        <!--Grid Form-->

                        <div class="flex flex-1  flex-col md:flex-row lg:flex-row mx-2">
                            <div class="mb-2 border-solid border-gray-300 rounded border shadow-sm w-full">
                                <div class="bg-gray-200 px-2 py-3 border-solid border-gray-200 border-b">
                                    <div class="font-bold text-xl">Danh sách <c:if test="${uID==1}">Khách hàng
                                        </c:if>
                                        <c:if test="${uID==2}">Nhân viên
                                        </c:if></div>
                                </div>
                                <div class="p-3">
                                    <table class="table-responsive w-full rounded" style="overflow-y: scroll; max-height: 500px">
                                        <thead>
                                            <tr>
                                                <th class="border w-1/8 px-4 py-2">Mã số</th>
                                                <th class="border w-1/8 px-4 py-2">Tên</th>
                                                <th class="border w-1/8 px-4 py-2">Giới tính</th>
                                                <th class="border w-1/8 px-4 py-2">Email</th>
                                                <th class="border w-1/8 px-4 py-2">Số điện thoại</th>
                                                <th class="border w-1/8 px-4 py-2">Địa chỉ</th>
                                                <th class="border w-1/8 px-4 py-2">Ngày tạo</th>
                                                <th class="border w-1/8 px-4 py-2">Chức vụ</th>
                                                <th class="border w-1/8 px-4 py-2">Xem thông tin</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:if test="${uID == 1}">
                                                <c:forEach items="${list}" var="o">
                                                    <tr>
                                                        <td class="border px-4 py-2">CUS${o.cusID}</td>
                                                        <td class="border px-4 py-2">${o.cusName}</td>
                                                        <td class="border px-4 py-2">
                                                            <c:if test="${o.gender == true}">Nam</c:if>
                                                            <c:if test="${o.gender == false}">Nữ</c:if>
                                                            </td>
                                                            <td class="border px-4 py-2">${o.email}</td>
                                                        <td class="border px-4 py-2">${o.cusPhone}</td>
                                                        <td class="border px-4 py-2">${o.cusAddress}</td>
                                                        <td class="border px-4 py-2">${o.updateDate}</td>
                                                        <td class="border px-4 py-2">
                                                            <c:if test="${o.account.role == 0}">Khách hàng</c:if>
                                                            </td>

                                                            <td class="border px-4 py-2">
                                                                <a href="userdetail?cusID=${o.cusID}&uID=${uID}" class="bg-teal-300 cursor-pointer rounded p-1 mx-1 text-white">
                                                                    <i class="fas fa-eye"></i>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                </c:forEach>
                                            </c:if>      
                                            <c:if test="${uID == 2}">
                                                <c:forEach items="${list}" var="o">
                                                    <tr>
                                                        <td class="border px-4 py-2">${o.emID}</td>
                                                        <td class="border px-4 py-2">${o.emName}</td>
                                                        <td class="border px-4 py-2">
                                                            <c:if test="${o.gender == true}">Nam</c:if>
                                                            <c:if test="${o.gender == false}">Nữ</c:if>
                                                            </td>
                                                            <td class="border px-4 py-2">${o.email}</td>
                                                        <td class="border px-4 py-2">${o.emPhone}</td>
                                                        <td class="border px-4 py-2">${o.emAddress}</td>
                                                        <td class="border px-4 py-2"></td>
                                                        <td class="border px-4 py-2">
                                                            <c:if test="${o.account.role == 1}">Sale</c:if>
                                                            <c:if test="${o.account.role == 2}">Admin</c:if>
                                                            </td>

                                                            <td class="border px-4 py-2">
                                                                <a href="userdetail?emID=${o.emID}&uID=${uID}" class="bg-teal-300 cursor-pointer rounded p-1 mx-1 text-white">
                                                                    <i class="fas fa-eye"></i>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                </c:forEach>
                                            </c:if>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>

        </div>

    </div>

    <script src="./main.js"></script>


</body>
</html>
