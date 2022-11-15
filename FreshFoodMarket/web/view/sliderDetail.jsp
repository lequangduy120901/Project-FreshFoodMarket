<%-- 
    Document   : sliderDetail
    Created on : Jul 7, 2022, 4:23:18 PM
    Author     : Dinh Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA_Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, intial-scale=1.0">
        
        <title>Thông Tin Slider</title>
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">
        
        <link rel="stylesheet" type="text/css" href="css/sliderDetailStyle.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
        
        <div class="slideContainer">
            <div class="top">
                <h3 class="bigTitle">Thông Tin Slider</h3>
                <a href="/FreshFoodMarket/deleteSlide?id=${requestScope.slide.slideID}" id="asl"></a>
                <button type="button" class="del" id="del" name="del" onclick="deleteSlide()">Xóa</button>
            </div>
            <div style="clear: both;">
                <p style="color: red;">${requestScope.mess}</p>
                <p style="display: none;" id="slID">${requestScope.slide.slideID}</p>
            </div>
            <form id="slideForm" action="/FreshFoodMarket/sliderDetail" method="POST">
                <div class="left">
                    <p class="bigImg"><img id="img-slide" class="slideImg" src="${requestScope.slide.slideImage}" alt=""></p>
                    <p id="imgCont"><input name="img" value="" class="file-input" type="file" accept="image/*" onchange="changeImg(this)"></p>
                </div>
                <div class="right">
                    <table class="infor">
                        <tr>
                            <td>ID</td>
                            <td>
                                <input id="id" name="id" type="text" value="${requestScope.slide.slideID}" disabled>
                                <input type="hidden" name="id" value="${slide.slideID}">
                            </td>
                        </tr>
                        <tr>
                            <td>Title</td>
                            <td>
                                <input id="title" name="title" type="text" value="${requestScope.slide.title}" disabled>
                            </td>
                        </tr>
                        <tr>
                            <td>Backlink</td>
                            <td>
                                <input id="backlink" name="backlink" type="text" value="${requestScope.slide.backlink}" disabled>
                            </td>
                        </tr>
                        <tr>
                            <td>Status</td>
                            <td>
                                <select name="status" id="status" style="pointer-events: none;">
                                    <option value="1" ${(requestScope.slide.status == true)?"selected":""}>Hoạt động</option>
                                    <option value="0" ${(requestScope.slide.status == false)?"selected":""}>Không hoạt động</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="content">
                    <label for="">Description</label>
                    <textarea name="des" id="des" class="des" disabled>${requestScope.slide.description}</textarea>
                    <label for="">Note</label>
                    <textarea name="note" id="note" class="note" disabled>${requestScope.slide.notes}</textarea>
                </div>
            </form>
            <div class="bottom">
                <button type="button" id="back" name="back" onclick="submit()">Quay Lại</button>
                <button type="button" id="edit" name="edit" onclick="showContent()">Chỉnh sửa</button>
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
        <script type="text/javascript" src="js/sliderDetail.js"></script>
    </body>
</html>
