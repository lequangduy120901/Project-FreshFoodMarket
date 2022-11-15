

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page import="util.*"%>

<%@page import="java.util.List"%>
<!DOCTYPE html>
<%
    Post post = (Post) request.getAttribute("post");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View post</title>
        <link rel="stylesheet" href="css/PostDetail.css">
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>

            <div class="post-container">
                <form id="myForm" action="/FreshFoodMarket/editPost" method="POST">
                    <h1> ${post.title} </h1>
                    <img class="thumbnail" src="${post.thumbnail}">
                    
                    
                    <div name="content" class="content" >${post.description}</div>
                    <div name="author" class="author">Tác giả: ${post.author}</div>
                </form>
                <div class="btn-post">
                    <a href="PostListController"><button >Quay lai</button></a> 
                   
                </div>
            </div>
        
            <jsp:include page="footer.jsp"></jsp:include>
        <script src="js/editPost.js"></script>
    </body>
</html>
