<%-- 
    Document   : edtPost
    Created on : Jul 21, 2022, 4:06:20 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit post</title>
        <link rel="stylesheet" href="css/editPostStyle.css">
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>

            <div class="post-container">
                <div class="screenName">                    
                    <h1>Edit Post</h1>
                    <!--<button>Xóa Post</button>-->
                </div>
                <form id="myForm" action="/FreshFoodMarket/editPost" method="POST">
                    <label for="">Tiêu đề: </label>
                    <input name="title" class="title" value="${requestScope.post.title}">
                    <img class="thumbnail" src="${requestScope.post.thumbnail}">
                    <label for="">ID: </label> 
                    <input name="postID" class="" disabled value="${requestScope.post.postID}">
                    <input name="postID" class="" type="hidden" value="${requestScope.post.postID}">
                    <br>
                    <label for="">Đường dẫn ảnh: </label>
                    <input name="in" class="in" type="text" name="" id="" value="${requestScope.post.thumbnail}" placeholder="imgurl">
                    <select class="tags-post" name="flag" id="">

                        <option value="1" ${(requestScope.post.flag == true)?"selected":""}>Nổi bật</option>
                        <option value="0" ${(requestScope.post.flag == false)?"selected":""}>Không nổi bật</option>
                    </select>
                    <select class="status-post" name="status" id="">
                        <option value="1" ${(requestScope.post.status == true)?"selected":""} >Hoạt động</option>
                        <option value="0" ${(requestScope.post.status == false)?"selected":""}>Không hoạt động</option>
                    </select>
                    <label for="">Nội dung: </label>
                    <textarea name="content" class="content" >${requestScope.post.description}</textarea>
                    <label for="">Người viết: </label>
                    <input name="author" class="author" value="${requestScope.post.author}">
                </form>
                <div class="btn-post">
                    <button onclick="my1()">Quay lại</button>
                    <button onclick="submitForm()">Lưu</button>
                </div>
            </div>
        <jsp:include page="footer.jsp"></jsp:include>

        <script src="js/editPost.js"></script>
    </body>
</html>
