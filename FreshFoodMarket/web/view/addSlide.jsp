<%-- 
    Document   : addSlide
    Created on : Jul 7, 2022, 6:11:03 PM
    Author     : Dinh Nam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA_Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, intial-scale=1.0">
        
        <title>Thêm Slider</title>
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">
        
        <link rel="stylesheet" type="text/css" href="css/sliderDetailStyle.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
        
        <div class="slideContainer">
            <div style="clear: both;">
                <p style="color: red;">${requestScope.mess}</p>
            </div>
            <form id="slideForm" action="/FreshFoodMarket/addSlide" method="POST">
                <div class="left">
                    <p class="bigImg"><img id="img-slide" class="slideImg" src="" alt="slide image here"></p>
                    <input name="img" class="file-input" type="file" accept="image/*" onchange="changeImg(this)" value="">
                </div>
                <div class="right">
                    <table class="infor">
                        <tr>
                            <td>Tiêu đề</td>
                            <td>
                                <input id="title" name="title" type="text" value="" placeholder="Tối đa 50 ký tự">
                            </td>
                        </tr>
                        <tr>
                            <td>Liên kết</td>
                            <td>
                                <input id="backlink" name="backlink" type="text" value="">
                            </td>
                        </tr>
                        <tr>
                            <td>Trạng thái</td>
                            <td>
                                <select name="status" id="status">
                                    <option value="1" selected>Hoạt động</option>
                                    <option value="0">Không hoạt động</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="content">
                    <label for="">Mô tả</label>
                    <textarea name="des" id="des" class="des" placeholder="Tối đa 255 ký tự"></textarea>
                    <label for="">Ghi chú</label>
                    <textarea name="note" id="note" class="note"></textarea>
                </div>
            </form>
            <div class="bottom">
                <button type="button" name="cancel" onclick="cancel()">Hủy</button>
                <button type="button" name="save" onclick="save()">Lưu</button>
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
        <script type="text/javascript" src="js/addSlide.js"></script>
    </body>
</html>