<%-- 
    Document   : Sale dashboard
    Created on : Jun 18, 2022, 1:15:31 PM
    Author     : THAI BAO
--%>
<%@page import="util.Product"%>
<%@page import="java.util.List"%>
<%@page import="DAO.ProductDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sale Dashboard</title>

    <link rel="stylesheet" href="css/saleDashboardStyle.css">
    
</head>
 
<body>
       
     <%
        //Product
        ResultSet rsPro2 = (ResultSet) request.getAttribute("rsPro2");
        int soluong = (Integer) request.getAttribute("soluong");
        int count = (Integer) request.getAttribute("count");
        int quantity = (Integer) request.getAttribute("quantity");
        String xdatePro1 = (String) request.getAttribute("xdatePro1");
        String xdatePro2 = (String) request.getAttribute("xdatePro2");
        
        
        
        //Order
        ResultSet rsOrder = (ResultSet) request.getAttribute("rsOrder"); 
        int NOrders = (Integer) request.getAttribute("NOrders");
        int SOrders = (Integer) request.getAttribute("SOrders");
        int FOrders = (Integer) request.getAttribute("FOrders");
        String xdateOrder1 = (String) request.getAttribute("xdateOrder1");
        String xdateOrder2 = (String) request.getAttribute("xdateOrder2");
       
        
        
        //Post
        int NPosts = (Integer) request.getAttribute("NPosts");
        int FPosts = (Integer) request.getAttribute("FPosts");
        int IPosts = (Integer) request.getAttribute("IPosts");
        
        
        
        //Slide
        int NSlide = (Integer) request.getAttribute("NSlide");
        int ASlide = (Integer) request.getAttribute("ASlide");
        
        
        
        //Feedback
        int NFb = (Integer) request.getAttribute("NFb");
        ResultSet rsFB = (ResultSet) request.getAttribute("rsFB"); 
        String xdateFB1 = (String) request.getAttribute("xdateFB1");
        String xdateFB2 = (String) request.getAttribute("xdateFB2"); 
        
        
        //Customer
        int NCustomers = (Integer) request.getAttribute("NCustomers");
        int CushasOrder = (Integer) request.getAttribute("CushasOrder");
        ResultSet rsCus = (ResultSet) request.getAttribute("rsCus");
        String xdateCus1 = (String) request.getAttribute("xdateCus1");
        String xdateCus2 = (String) request.getAttribute("xdateCus2"); 

        

        %>
      <div style="position: sticky; top: 0; z-index: 1;">                         
                <jsp:include page="header.jsp"></jsp:include>                      
            </div>
            <div class="saledashboard">
    <table  class="tableBoard">
        <tr>
            <td class="test">
                <h2>Product</h2>
                <div class="order-top">
                    <div class="order-top-left">
                        <form action="/FreshFoodMarket/productListSale" method="GET" class="">
                            <p><button>Xem chi tiết</button></p>
                        </form>
                    </div>
                    <div class="order-top-right">
                        <form action="" method="" class="">
                            <p><input type="date" name="datePro1" value="<%=xdatePro1%>"> - <input type="date" name="datePro2" value="<%=xdatePro2%>">
                                <button>Lọc</button>
                            </p>
                        </form>
                    </div>
                </div>
                
                <div class="order-center">
                    
                    <div>
                        <p>Tổng loại sản phẩm đã bán</p>
                        <p><%=count%></p>
                        
                    </div>
                    <div>
                        <p>Số lượng đã bán</p>
                        <p><%=soluong%></p>
                        </div>
                    <div>
                        <p>Số lượng còn lại</p>
                        <p><%=quantity-soluong%></p>
                    </div>                  
                </div>
                
                <div class="order-bottom">
                    <div>
                        <h4>Sản phẩm bán chạy nhất</h4>
                    </div>
                    <div class="overflow">
                       
                        <table class="table-list">
                            <tr>
                                <th>Loại sản phẩm</th>
                                <th>Số lượng đã bán</th>
                                <th>Số lượng còn lại</th>
                                
                            </tr>
                             <%while (rsPro2.next()) {
                             ProductDAO daoP = new ProductDAO();
                             Product p = daoP.getProductbyID(rsPro2.getString(1));%>
                            <tr>
                                <td><%=p.getProName()%></td>
                                <td><%=rsPro2.getInt(2)%></td>
                                <td><%=p.getQuantity()-rsPro2.getInt(2)%></td>
                                
                            </tr>
                            <%}%>
                        </table>
                       
                    </div>
                </div>
            </td>
        </tr>
        
        <tr>
            <td class="test">
                <h2>Order</h2>
                <div class="order-top">
                    <div class="order-top-left">
                        <form action="/FreshFoodMarket/manageOrder" method= "get">
                            <p><button>Xem chi tiết</button></p>
                        </form>
                    </div>
                    <div class="order-top-right">
                        <form action="" method="" class="">
                            <p><input type="date" name="dateOrder1" value="<%=xdateOrder1%>"> - <input type="date" name="dateOrder2" value="<%=xdateOrder2%>">
                                <button>Lọc</button>
                            </p>
                        </form>
                    </div>
                </div>
                <div class="order-center">
                    <div>
                        <p>Tổng số đơn hàng</p>
                        <p><%=NOrders%></p>
                    </div>
                    <div>
                        <p>Đơn thành công</p>
                        <p><%=SOrders%></p>
                    </div>
                    <div>
                        <p>Đơn thất bại</p>
                        <p><%=FOrders%></p>
                    </div>
                    <div>
                        <p>Đơn đang xử lý</p>
                        <p><%=NOrders-SOrders-FOrders%></p>
                    </div>
                </div>
                <div class="order-bottom">
                    <div>
                        <h4>Danh sách đơn cần xử lý</h4>
                    </div>
                    <div class="overflow">
                        <table class="table-list">
                            <tr>
                                <th>OrderID</th>
                                <th>Tên</th>
                                <th>Địa chỉ đặt hàng</th>
                                <th>Ngày đặt</th>
                                <th>Tổng tiền</th>
                            </tr>
                            <%--<c:forEach items="${listCC}" var="o">--%>
                            <%while (rsOrder.next()) {%>
                            <tr>
                                <td><%=rsOrder.getString(1)%></td>
                                <td><%=rsOrder.getString("cusName")%></td>
                                <td><%=rsOrder.getString("cusAddress")%></td>
                                <td><%=rsOrder.getDate("dateCreate")%></td>
                                <td><%=rsOrder.getDouble("total")%></td>
                            </tr>
                          <%}%>
                           <%--</c:forEach>--%>
                        </table>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td class="test" style="width: 47.35%; float: left;">
                <h2>Post</h2>
                <div class="order-top">
                    <div class="order-top-left">
                        <form action="/FreshFoodMarket/managePost" method="GET">
                            <p><button>Xem chi tiết</button></p>
                        </form>
                    </div>
                </div>
                <div class="order-center">
                    <div>
                        <p>Tổng số post</p>
                        <p><%=NPosts%></p>
                    </div>
                    <div>
                        <p>Post nổi bật</p>
                        <p><%=FPosts%></p>
                    </div>
                    <div>
                        <p>Post không hoạt động</p>
                        <p><%=IPosts%></p>
                    </div>

                </div>
            
            </td>
            <td class="test" style="width: 47.35%; float: right;">
                <h2>Slider</h2>
                <div class="order-top">
                    <div class="order-top-left">
                        <form action="/FreshFoodMarket/sliderList" method="GET" class="">
                            <p><button>Xem chi tiết</button></p>
                        </form>
                    </div>
                </div>
                <div class="order-center">
                    <div>
                        <p>Tổng số slider</p>
                        <p><%=NSlide%></p>
                    </div>
                    <div>
                        <p>Slider hoạt động</p>
                        <p><%=ASlide%></p>
                    </div>
                    <div>
                        <p>Slider không hoạt động</p>
                        <p><%=NSlide-ASlide %></p>
                    </div>
<!--                    <div>
                        <p>Title</p>
                        <p>12</p>
                    </div>-->
                </div>
               
            </td>
        </tr>
        <tr>
            <td class="test">
                <h2>Feedback</h2>
                <div class="order-top">
                    <div class="order-top-left">
                        <form action="/FreshFoodMarket/feedbackManager" method="GET">
                            <p><button>Xem chi tiết</button></p>
                        </form>
                    </div>
                    <div class="order-top-right">
                        <form action="" method="" class="">
                            <p><input type="date" name="dateFB1" value="<%=xdateFB1%>"> - <input type="date" name="dateFB2" value="<%=xdateFB2%>">
                                <button>Lọc</button>
                            </p>
                        </form>
                    </div>
                </div>
                <div class="order-center">
                    <div>
                        <p>Tổng số feedback</p>
                        <p><%=NFb%></p>
                    </div>
                </div>
                <div class="order-bottom">
                    <div>
                        <h4>Danh sách feedback mới nhất </h4>
                    </div>
                    <div class="overflow">
                        <table class="table-list">
                            <tr>
                                <th>Sản phẩm đánh giá</th>
                                <th>Mức độ hài lòng</th>
                                <th>Nội dung</th>
                                <th>Ngày đăng</th>
                            </tr>
                            <%while (rsFB.next()) {
                            ProductDAO daoP = new ProductDAO();
                            Product p = daoP.getProductbyID(rsFB.getString(3));%>
                            <tr>
                                <td><%=p.getProName()%></td>
                                <td><%=rsFB.getInt("rated")%>/5 (sao)</td>
                                <td><%=rsFB.getString("content")%></td>
                                <td><%=rsFB.getString("dateCreate")%></td>
                            </tr>
                            <%}%>
                        </table>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td class="test">
                <h2>Customer</h2>
                <div class="order-top">
                    <div class="order-top-left">
                        <form action="CustomerListSaleController" method="GET" class="">
                            <p><button>Xem chi tiết</button></p>
                        </form>
                    </div>
                    <div class="order-top-right">
                        <form action="" method="" class="">
                            <p><input type="date" name="dateCus1" value="<%=xdateCus1%>"> - <input type="date" name="dateCus2"value="<%=xdateCus2%>">
                                <button>Lọc</button>
                            </p>
                        </form>
                    </div>
                </div>
                <div class="order-center">
                    <div>
                        <p>Tổng</p>
                        <p><%=NCustomers%></p>
                    </div>
                    <div>
                        <p>Khách đã đặt hàng</p>
                        <p><%=CushasOrder%></p>
                    </div>
                    <div>
                        <p>Khách hàng tiềm năng</p>
                        <p><%=NCustomers-CushasOrder%></p>
                    </div>
                    
                </div>
                <div class="order-bottom">
                    <div>
                        <h4>Khách hàng mới đăng kí</h4>
                    </div>
                    <div class="overflow">
                        <table class="table-list">
                            <tr>
                                <th>CusID</th>
                                <th>Tên khách hàng</th>
                                <th>Chỗ ở</th>
                                <th>Ngày đăng kí</th>
                            </tr>
                           <%while (rsCus.next()) {%>
                            <tr>
                                <td><%=rsCus.getString(1)%></td>
                                <td><%=rsCus.getString("cusName")%></td>
                                <td><%=rsCus.getString("cusAddress")%></td>
                                <td><%=rsCus.getString("updateDate")%></td>
                            </tr>
                           <%}%>
                        </table>
                    </div>
                </div>
            </td>
        </tr>
    </table>
    </div>    
    <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="">
                    <div class="footer__about">
                        <div class="footer__logo">
                            <a href="/FreshFoodMarket/homeController"><img class="croplogo" style="display: block; margin: 0 auto;" src="img/logo/Logo.png"></a>
                        </div>
                    </div>
                </div>
                <div class="">
                    <h4 style="font-weight: bold; color:#fff">Fresh Food Co Ltd</h4>
                    <p class="footer-text">Địa chỉ 1: No 1, Hacker way, Menlo Park, CA</p>
                    <p class="footer-text">Địa chỉ 2: No 1 Rathbone Square, Fitzrovia, London</p>
                    <p class="footer-text">Địa chỉ 3: 4 Grand Canal Square, Grand Canal Harbour</p>
                </div>
                <div class="">
                    <h4 style="font-weight: bold; color:#fff">Thông tin liên hệ</h4>
                    <p class="footer-text">Điện thoại: 0123456789</p>
                    <p class="footer-text">Email: freshfoodmarket@gmail.com</p>
                    <p class="footer-text">Facebook: freshfoodmarket.facebook.com</p>
                </div>
            </div>
        </div>
    </footer>
    <style>
        .croplogo {
            width: 190px;
            height: 95px;
            object-fit: cover;
        }
        
        footer {
            background-color: #333;
            padding-top: 15px;
            padding-bottom: 15px;
            font-family: Roboto, sans-serif;
            width: 100%;
        }
        
        .container {
            max-width: 1180px;
            background-color: #333;
            margin: 0 auto;
        }
        
        .footer-text {
            font-weight: bold;
            color: rgb(255, 255, 255);
            margin: 0;
        }
        
        .row {
            display: flex;
            justify-content: space-between;
            padding: 0 100px;
        }
        
        
        .footer__logo {
            padding-top: 15px;
        }
    </style>
</body>

</html>

