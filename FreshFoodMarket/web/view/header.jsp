<%-- 
    Document   : header
    Created on : May 26, 2022, 3:20:44 AM
    Author     : Dinh Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="util.Category"%>
<%@page import="DAO.CategoryDAO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <!-- To import header into an other jsp file, copy 3 lines below and paste to your file -->
        <!--        <div style="position: sticky; top: 0; z-index: 1;">                         -->
        <%--            <jsp:include page="view/header.jsp"></jsp:include>                      --%>
        <!--        </div>                                                                      -->

        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Fresh Food Market</title>
        <style>
            .p-btn {
                margin: 0;
                color: #000;
            }

            body {
                margin: 0;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
                z-index: 1;
                padding-left: 0;
                position: absolute;
                top: -.5px;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
                font-family: Roboto, sans-serif;
            }

            .dropdown-content a:hover {
                background-color: #ddd;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .greenHeader {
                display: flex;
                -webkit-box-align: center;
                align-items: center;
                -webkit-box-pack: justify;
                justify-content: space-between;
                position: relative;
                z-index: 2;
                top: 0px;
                left: 0px;
                max-width: 1180px;
                height: 100px;
                margin: auto;
            }

            .searchBox {
                position: relative;
                -webkit-box-flex: 1;
                flex-grow: 1;
                margin-left: 20px;
            }

            .searchForm {
                display: flex;
                -webkit-box-align: center;
                align-items: center;
                overflow: hidden;
                width: 100%;
                background-color: rgb(247, 247, 247);
                border-style: none;
            }

            .searchInput {
                -webkit-box-flex: 1;
                flex-grow: 1;
                font-size: 12px;
                padding-left: 15px;
                height: 36px;
                color: rgb(51, 51, 51);
                background-color: transparent;
                appearance: none;
                word-spacing: -1px;
                border-style: none;
            }

            .searchInput:focus {
                outline: none;
            }

            .cartAcc {
                display: flex;
                -webkit-box-align: center;
                align-items: center;
                flex-shrink: 0;
                -webkit-box-pack: end;
                justify-content: flex-end;
                width: 30%;
            }

            .btn-ca {
                font-size: 13px;
                cursor: pointer;
                appearance: none;
                display: flex;
                -webkit-box-align: center;
                align-items: center;
                -webkit-box-pack: center;
                justify-content: flex-end;
                flex-shrink: 0;
                text-align: center;
                height: 38px;
                text-decoration: none;
                font-family: inherit;
                border: 0px;
                background-color: transparent;
            }

            .btn-text {
                color: rgb(255, 255, 255);
                flex-direction: column;
                align-items: flex-start;
                display: flex !important;
                font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
                font-weight: bold;
            }

            .navigation-bar {
                background: #f1f0f1;
                -webkit-box-shadow: 0 2px 1px -1px rgb(0 0 0 / 25%);
                box-shadow: 0 2px 1px -1px rgb(0 0 0 / 25%);
                position: relative;
                /******************/
                padding-top: 10px;
            }

            .navigation-bar .navigation-bar-item {
                text-transform: none;
                font-size: 12px;
                margin-top: 0;
                cursor: pointer;
                border: none;
                margin-top: 2px;
            }

            .v-btn__content {
                display: -webkit-flex;
                display: -moz-box;
                display: flex;
                -webkit-align-items: center;
                -moz-box-align: center;
                align-items: center;
                margin-right: 5px;
            }

            .v-btn-menu {
                border: none;
                z-index: 1;
                /* margin-top: -2px; */
                position: relative;
                background-color: rgba(255, 255, 255, 0);
            }

            .navigation-bar .nb-container {
                height: 30px;
                max-width: 1180px;
                margin: auto;
            }

            .MuiSvgIcon-root {
                fill: currentColor;
                width: 1em;
                height: 1em;
                display: inline-block;
                font-size: 1.5rem;
                transition: fill 200ms cubic-bezier(0.4, 0, 0.2, 1) 0ms;
                flex-shrink: 0;
                user-select: none;
            }

            .fcb {
                margin-left: 2vw;
            }
        </style>
    </head>
    <body>
        <div style="background-color: #2a8738;">
            <header class="greenHeader">
                <div style="display: flex; align-items: center; width: 70%;">
                    <div>
                        <a href="/FreshFoodMarket/homeController"><img src="img/logo/Logo.png" style="width: 190px;"></a>
                    </div>

                    <!-- search product -->
                    <div class="searchBox">
                        <form class="searchForm" action="search" method="get">
                            <input name="search" placeholder="Tìm kiếm sản phẩm" class="searchInput">
                            <button style="border: none; background-color: rgba(0, 0, 0, 0);">
                                <svg xmlns="http://www.w3.org/2000/svg" width="22px" height="22px" viewBox="0 0 14 14" style="margin-left: 16px; margin-right: 12px; cursor: pointer;">
                                <path d="M14.771,12.752,11.32,9.286a5.519,5.519,0,0,0,1.374-3.634A5.763,5.763,0,0,0,6.839,0,5.763,5.763,0,0,0,.984,5.652,5.763,5.763,0,0,0,6.839,11.3a5.936,5.936,0,0,0,3.354-1.023l3.477,3.492a.783.783,0,0,0,1.08.02A.72.72,0,0,0,14.771,12.752ZM6.839,1.475a4.259,4.259,0,0,1,4.327,4.178A4.259,4.259,0,0,1,6.839,9.83,4.259,4.259,0,0,1,2.511,5.652,4.259,4.259,0,0,1,6.839,1.475Z" transform="translate(-0.984)" fill="#2a8738"></path>
                                </svg>
                            </button>
                        </form>
                    </div>
                </div>

                <div class="cartAcc">
                    <button class="btn-ca" style="width: 50%;">
                        <a href="cartcontroller">
                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="1.2em" height="1.2em" fill="currentColor" style="font-size: 26px; color: #86d636; margin-right: 5px;">
                            <path d="M5.757 1.071a.5.5 0 0 1 .172.686L3.383 6h9.234L10.07 1.757a.5.5 0 1 1 .858-.514L13.783 6H15a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1v4.5a2.5 2.5 0 0 1-2.5 2.5h-9A2.5 2.5 0 0 1 1 13.5V9a1 1 0 0 1-1-1V7a1 1 0 0 1 1-1h1.217L5.07 1.243a.5.5 0 0 1 .686-.172zM2 9v4.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V9H2zM1 7v1h14V7H1zm3 3a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 4 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 6 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3A.5.5 0 0 1 8 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 0 1-1 0v-3a.5.5 0 0 1 .5-.5z"></path>
                            </svg>
                        </a>
                        <a href="cartcontroller?action=">
                            <p class="p-btn btn-text" style="font-size: 15px; margin: 0 5px;">Giỏ hàng</p>
                        </a>
                    </button>

                    <button class="btn-ca" style="width: 50%;">
                        <c:if test="${currUser == null}">
                            <a href="/FreshFoodMarket/loginController?action=0">
                            </c:if>
                            <c:if test="${currUser != null}">
                                <a href="#">
                                </c:if>
                                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" width="1.1em" height="1.1em" fill="currentColor" style="font-size: 30px; color: #86d636; margin-right: 5px;">
                                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"></path>
                                <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"></path>
                                </svg>
                            </a>
                            <c:if test="${currUser == null}">
                                <a href="/FreshFoodMarket/loginController?action=0">
                                </c:if>
                                <c:if test="${currUser != null}">
                                    <a href="/FreshFoodMarket/userProfile">
                                    </c:if>
                                    <p class="p-btn btn-text" style="font-size: 15px; margin: 0 5px;">Tài khoản</p>
                                </a>
                                </button>
                                </div>
                                </header>
                                </div>

                                <div class="navigation-bar">
                                    <div class="nb-container" style="justify-content: flex-start; display: flex;">
                                        <div class="dropdown" style="width: 20%;">
                                            <button type="button" class="v-btn-menu v-btn__content" style="pointer-events: auto;">
                                                <img src="img/logo/menu.png">
                                                <p class="p-btn" style="margin-left: 5px; font-size: 13px;">Danh sách sản phẩm</p>
                                            </button>

                                            <%
                                                CategoryDAO cDao = new CategoryDAO();
                                                List<Category> cateList = cDao.getAllCate();
                                            %>
                                            <div class="dropdown-content">
                                                <a href="categories?idC=0">Tất cả</a>
                                                <%
                                                for (Category c : cateList) {
                                                %>
                                                <a href="categories?idC=<%= c.getCateID()%>"><%= c.getCateName()%></a>
                                                <%}%>
                                            </div>
                                        </div>

                                        <div style="justify-content: flex-end; display: flex; width: 80%;">
                                            <c:if test="${currUser != null}">
                                                <div class="fcb">
                                                    <button type="button" class="navigation-bar-item v-btn__content">
                                                        <a href="/FreshFoodMarket/loginController?action=logout" class="p-btn">Đăng xuất</a>
                                                    </button>
                                                </div>
                                                <c:if test="${currUser.getRole() == 0}">
                                                    <div class="fcb">
                                                        <button type="button" class="navigation-bar-item v-btn__content">
                                                            <a href="/FreshFoodMarket/myOrders" class="p-btn">Đơn hàng của tôi</a>
                                                        </button>
                                                    </div>
                                                </c:if>
                                                <c:if test="${currUser.getRole() == 1}">
                                                    <div class="fcb">
                                                        <button type="button" class="navigation-bar-item v-btn__content">
                                                            <a href="/FreshFoodMarket/SaleDashboardController" class="p-btn">Sale Dashboard</a>
                                                        </button>
                                                    </div>
                                                </c:if>
                                                <c:if test="${currUser.getRole() == 2}">
                                                    <div class="fcb">
                                                        <button type="button" class="navigation-bar-item v-btn__content">
                                                            <a href="adminDash" class="p-btn">Admin Dashboard</a>
                                                        </button>
                                                    </div>
                                                </c:if>
                                            </c:if> 

                                            <div class="fcb">
                                                <button type="button" class="navigation-bar-item v-btn__content">
                                                    <a href="#" class="p-btn">Liên hệ</a>                         
                                                </button>
                                            </div>

                                            <div class="fcb">
                                                <button type="button" class="navigation-bar-item v-btn__content">
                                                    <a href="/FreshFoodMarket/PostListController" class="p-btn">Bài đăng</a>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </body>

                                </html>