<%-- 
    Document   : AdminDash
    Created on : Jun 18, 2022, 1:04:50 PM
    Author     : bao nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <!-- Css -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="css/adminDash.css">
        <link rel="stylesheet" href="css/all.css">
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400i,600,600i,700,700i" rel="stylesheet">
        <title>Admin Dashboard</title>

    </head>

    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
            <!--Container -->
            <div class="mx-auto bg-grey-400">
                <!--Screen-->
                <div class="min-h-screen flex flex-col">
                    <div class="flex flex-1">
                        <!--Sidebar-->
                        <aside id="sidebar"
                               class="bg-side-nav w-1/2 md:w-1/6 lg:w-1/6 border-r border-side-nav hidden md:block lg:block">

                            <ul class="list-reset flex flex-col">
                                <li class="w-full h-full py-3 px-2 border-b border-light-border bg-white">
                                    <a href="/FreshFoodMarket/adminDash"
                                       class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline">
                                        <i class="fas fa-tachometer-alt float-left mx-2"></i>
                                        Admin Dashboard
                                        <span><i class="fa fa-angle-right float-right"></i></span>
                                    </a>
                                </li>
                                <li class=" w-full h-full py-3 px-2 border-b border-light-border">
                                    <a href="/FreshFoodMarket/userlist"
                                       class="font-sans font-hairline hover:font-normal text-sm text-nav-item no-underline">
                                        <i class="fas fa-address-card float-left mx-2"></i>
                                        Danh sách tài khoản
                                        <span><i class="fas fa-angle-right float-right"></i></span>
                                    </a>
                                </li>

                            </ul>
                        </aside>
                        <!--/Sidebar-->
                        <!--Main-->
                        <main class="bg-white-300 flex-1 p-3 overflow-hidden">

                            <div class="flex flex-col">
                                <div flex flex-1 flex-col md:flex-row lg:flex-row mx-2>
                                    <div class="selectMonth" 
                                         style="margin-bottom: 16px; margin-left: 16px;float: left; ">
                                        <form action="adminDash" id="f">
                                            <select name="monthC" onchange="document.getElementById('f').submit()">
                                            <c:forEach items="${listM}" var="m">
                                                <option value="${m}"
                                                        <c:if test="${num == m}">selected</c:if>>
                                                    tháng ${m}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </form>
                                </div>
                                <div class="selectMonth" style="margin-bottom: 16px; margin-left: 16px;float: left;">
                                    <select style="border: solid; border-radius: 10px">
                                        <option selected>năm 2022</option>
                                    </select>
                                </div>
                            </div>

                            <!-- Stats Row Starts Here -->
                            <div class="flex flex-1 flex-col md:flex-row lg:flex-row mx-2">
                                <div
                                    class="shadow-lg bg-red-vibrant border-l-8 hover:bg-red-vibrant-dark border-red-vibrant-dark mb-2 p-2 md:w-1/3 mx-2">
                                    <div class="p-4 flex flex-col">
                                        <a href="#" class="no-underline text-white text-2ml">
                                            Số khác hàng tháng ${num} tăng thêm:
                                        </a>
                                        <a href="#" class="no-underline text-white text-lg">
                                            ${pGrowth}%
                                        </a>
                                    </div>
                                </div>

                                <div
                                    class="shadow bg-warning border-l-8 hover:bg-warning-dark border-warning-dark mb-2 p-2 md:w-1/3 mx-2">
                                    <div class="p-4 flex flex-col">
                                        <a href="#" class="no-underline text-white text-2ml">
                                            Số đơn hàng trong tháng ${num}
                                        </a>
                                        <a href="#" class="no-underline text-white text-lg">
                                            ${totalOrder} 
                                        </a>
                                    </div>
                                </div>

                                <div
                                    class="shadow bg-success border-l-8 hover:bg-success-dark border-success-dark mb-2 p-2 md:w-1/3 mx-2">
                                    <div class="p-4 flex flex-col">
                                        <a href="#" class="no-underline text-white text-2ml">
                                            Tiền bán tháng ${num}
                                        </a>
                                        <a href="#" class="no-underline text-white text-lg">
                                            ${sumMon} VND
                                        </a>
                                    </div>
                                </div>
                            </div>

                            <!-- /Stats Row Ends Here -->

                            <!-- Card Sextion Starts Here -->
                            <div class="flex flex-1 flex-col md:flex-row lg:flex-row mx-2">

                                <!-- card -->

                                <div class="rounded overflow-hidden shadow bg-white mx-2 w-full">
                                    <div class="px-6 py-2 border-b border-light-grey">
                                        <div class="font-bold text-xl">Đơn hàng đã đặt</div>
                                    </div>
                                    <div class="table-responsive" style="overflow-y: scroll; max-height: 200px">
                                        <table class="table text-grey-darkest" >
                                            <thead class="bg-grey-dark text-white text-normal">
                                                <tr>
                                                    <th scope="col">Ngày đặt</th>
                                                    <th scope="col">Tài khoản mua</th>
                                                    <th scope="col">Tổng tiền</th>
                                                </tr>
                                            </thead>
                                            <c:forEach items="${listS}" var="o">
                                                <tbody>
                                                    <tr>
                                                        <th>${o.dateCreate}</th>
                                                        <td>${o.customer.cusName}</td>
                                                        <td>${o.total} VND</td>
                                                    </tr>
                                                </tbody>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </div>
                                <div class="rounded overflow-hidden shadow bg-white mx-2 w-full">
                                    <div class="px-6 py-2 border-b border-light-grey">
                                        <div class="font-bold text-xl">Đơn hàng đã hủy</div>
                                    </div>
                                    <div class="table-responsive" style="overflow-y: scroll; max-height: 200px">
                                        <table class="table text-grey-darkest">
                                            <thead class="bg-grey-dark text-white text-normal">
                                                <tr>
                                                    <th scope="col">Ngày đặt</th>
                                                    <th scope="col">Tài khoản mua</th>
                                                    <th scope="col">Tổng tiền</th>
                                                </tr>
                                            </thead>
                                            <c:forEach items="${listC}" var="o">
                                                <tbody>
                                                    <tr>
                                                        <th>${o.dateCreate}</th>
                                                        <td>${o.customer.cusName}</td>
                                                        <td>${o.total}VND</td>
                                                    </tr>
                                                </tbody>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <!--Tỉ lệ hàng được bán-->
                            <div class="flex flex-1 flex-col md:flex-row lg:flex-row mx-2 mt-2">
                                <div class="rounded overflow-hidden shadow bg-white mx-2 w-full pt-2">
                                    <div class="px-6 py-2 border-b border-light-grey">
                                        <div class="font-bold text-xl">Tỉ lệ hàng được mua</div>
                                    </div>
                                    <div class="">
                                        <div class="w-full" style="width: 20%; float: left">
                                            <c:forEach items="${listCate}" var="c">
                                                <h6>${c.cateName}</h6>
                                            </c:forEach>
                                        </div>
                                        <div class="w-full" style="width: 80%; float: left">
                                            <c:forEach items="${listPercent}" var="p">                                 
                                                <div class="shadow w-full bg-grey-light">
                                                    <div class="bg-blue-500 text-xs leading-none py-1 text-center text-black"
                                                         style="width: ${p}%">${p}%
                                                    </div>
                                                </div>
                                                <hr>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--/Tỉ lệ hàng được bán-->
                    </main>
                    <!--/Main-->
                </div>

            </div>

        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
                integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
                integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>
    </body>

</html>
