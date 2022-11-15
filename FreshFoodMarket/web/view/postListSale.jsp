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
        <title>Bài đăng</title>
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">
        <link rel="stylesheet" type="text/css" href="css/postList.css"/>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.bundle.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <style>
            .pagination {
                display: inline-block;
            }
            .pagination a {
                color: black;
                font-size: 22px;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
            }
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
            }
            .pagination a:hover:not(.active) {
                background-color: chocolate;
            }
        </style>
    </head>

    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
            <div class="container" style="margin-top: 10px">
                <div class="col-md-12 col-lg-12">
                    <div class="row" style="margin-bottom: 10px">
                        <div class="col">
                            <form action="searchpost" method="get">
                                <input type="text" name="key" placeholder="Tìm kiếm bài đăng" value="${key}">
                                <button>Tìm Kiếm</button>
                            </form>
                        </div>
                    <!--</div>-->
                    <c:if test="${accR ==1}">
                        <div class="col">
                            <a style="text-decoration: none" href="addpost">Bài đăng mới</a>
                        </div>
                    </c:if>
                </div>
                <c:forEach items="${listPost}" var="p">
                    <article class="post vt-post">
                        <div class="row">
                            <div class="col-xs-12 col-sm-5 col-md-5 col-lg-4">
                                <div class="post-type post-img">
                                    <a href="/FreshFoodMarket/editPost?postID=${p.postID}">
                                        <img src="${p.thumbnail}" class="img-responsive" alt="image post">
                                    </a>
                                </div>
                                <div class="author-info author-info-2">
                                    <ul class="list-inline">
                                        <li>
                                            <div class="info">
                                                <p>Author:</p>
                                                <strong>${p.author}</strong>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-7 col-md-7 col-lg-8">
                                <div class="caption">
                                    <h3 class="md-heading"><a style="text-decoration: none; color: green" href="/FreshFoodMarket/editPost?postID=${p.postID}">${p.title}</a></h3>
                                    <p class="des">${p.description}</p>
                                    <script>
                                        $(".des").each(function ()
                                        {
                                            if ($(this).text().length > 50) {
                                                $(this).text($(this).text().substr(0, 50));
                                                $(this).append('...');
                                            }
                                        });
                                    </script>
                                    <a class="btn btn-default" href="/FreshFoodMarket/editPost?postID=${p.postID}" role="button">Xem thêm..</a> 
                                </div>
                            </div>
                        </div>
                    </article>
                </c:forEach>



                <div class="pagination-wrap">
                    <c:set var="page" value="${page}"/>
                    <div class="pagination" style="margin-bottom: 0;">
                        <c:forEach begin="${1}" end="${num}" var="i">
                            <a class="${i==page?"active":""}"href="managePost?&page=${i}">${i}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>

