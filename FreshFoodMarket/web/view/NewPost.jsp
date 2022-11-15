<%-- 
    Document   : NewPost
    Created on : Jul 19, 2022, 10:00:16 AM
    Author     : bao nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bài đăng mới</title>
        <link rel="shortcut icon" href="img/logo/Icon-logo.png">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <style>
            .require {
                color: #666;
            }
            label small {
                color: #999;
                font-weight: normal;
            }
        </style>
    </head>
    <body>
        <div style="position: sticky; top: 0; z-index: 1;"><jsp:include page="header.jsp"></jsp:include></div>
        <div class="container">
            <div class="row">

                <div class="col-md-9 col-md-offset-1">

                    <h1>Bài đăng mới</h1>

                    <form action="/FreshFoodMarket/addpost" method="POST">
                        <h4 style="color: red;">${mess}</h4>
                        <div class="form-group has-error">
                            <label for="slug">Ảnh <span class="require">*</span></label>
                            <img src=""
                             id="avatar" style="width:847.5px;height:250px; margin-bottom: 10px;">
                            <input type="file" id="avatar_image" name="image"
                                                       onchange="chooseFile(this)" accept=".png, .jpeg, .jpg" >
                        </div>
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
                        <div class="form-group">
                            <label for="title">Tiêu đề <span class="require">*</span></label>
                            <input type="text" class="form-control" name="title" />
                        </div>

                        <div class="form-group">
                            <label for="description">Nội dung</label>
                            <textarea rows="5" class="form-control" name="description" ></textarea>
                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">
                                Tạo mới
                            </button>
                            <button class="btn btn-default">
                                <a href="/FreshFoodMarket/managePost">Hủy</a>
                            </button>
                        </div>

                    </form>
                </div>

            </div>
        </div>
    </body>
</html>
