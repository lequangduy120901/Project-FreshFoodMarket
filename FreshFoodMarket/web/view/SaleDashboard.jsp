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
                            <p><button>Xem chi ti???t</button></p>
                        </form>
                    </div>
                    <div class="order-top-right">
                        <form action="" method="" class="">
                            <p><input type="date" name="datePro1" value="<%=xdatePro1%>"> - <input type="date" name="datePro2" value="<%=xdatePro2%>">
                                <button>L???c</button>
                            </p>
                        </form>
                    </div>
                </div>
                
                <div class="order-center">
                    
                    <div>
                        <p>T???ng lo???i s???n ph???m ???? b??n</p>
                        <p><%=count%></p>
                        
                    </div>
                    <div>
                        <p>S??? l?????ng ???? b??n</p>
                        <p><%=soluong%></p>
                        </div>
                    <div>
                        <p>S??? l?????ng c??n l???i</p>
                        <p><%=quantity-soluong%></p>
                    </div>                  
                </div>
                
                <div class="order-bottom">
                    <div>
                        <h4>S???n ph???m b??n ch???y nh???t</h4>
                    </div>
                    <div class="overflow">
                       
                        <table class="table-list">
                            <tr>
                                <th>Lo???i s???n ph???m</th>
                                <th>S??? l?????ng ???? b??n</th>
                                <th>S??? l?????ng c??n l???i</th>
                                
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
                            <p><button>Xem chi ti???t</button></p>
                        </form>
                    </div>
                    <div class="order-top-right">
                        <form action="" method="" class="">
                            <p><input type="date" name="dateOrder1" value="<%=xdateOrder1%>"> - <input type="date" name="dateOrder2" value="<%=xdateOrder2%>">
                                <button>L???c</button>
                            </p>
                        </form>
                    </div>
                </div>
                <div class="order-center">
                    <div>
                        <p>T???ng s??? ????n h??ng</p>
                        <p><%=NOrders%></p>
                    </div>
                    <div>
                        <p>????n th??nh c??ng</p>
                        <p><%=SOrders%></p>
                    </div>
                    <div>
                        <p>????n th???t b???i</p>
                        <p><%=FOrders%></p>
                    </div>
                    <div>
                        <p>????n ??ang x??? l??</p>
                        <p><%=NOrders-SOrders-FOrders%></p>
                    </div>
                </div>
                <div class="order-bottom">
                    <div>
                        <h4>Danh s??ch ????n c???n x??? l??</h4>
                    </div>
                    <div class="overflow">
                        <table class="table-list">
                            <tr>
                                <th>OrderID</th>
                                <th>T??n</th>
                                <th>?????a ch??? ?????t h??ng</th>
                                <th>Ng??y ?????t</th>
                                <th>T???ng ti???n</th>
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
                            <p><button>Xem chi ti???t</button></p>
                        </form>
                    </div>
                </div>
                <div class="order-center">
                    <div>
                        <p>T???ng s??? post</p>
                        <p><%=NPosts%></p>
                    </div>
                    <div>
                        <p>Post n???i b???t</p>
                        <p><%=FPosts%></p>
                    </div>
                    <div>
                        <p>Post kh??ng ho???t ?????ng</p>
                        <p><%=IPosts%></p>
                    </div>

                </div>
            
            </td>
            <td class="test" style="width: 47.35%; float: right;">
                <h2>Slider</h2>
                <div class="order-top">
                    <div class="order-top-left">
                        <form action="/FreshFoodMarket/sliderList" method="GET" class="">
                            <p><button>Xem chi ti???t</button></p>
                        </form>
                    </div>
                </div>
                <div class="order-center">
                    <div>
                        <p>T???ng s??? slider</p>
                        <p><%=NSlide%></p>
                    </div>
                    <div>
                        <p>Slider ho???t ?????ng</p>
                        <p><%=ASlide%></p>
                    </div>
                    <div>
                        <p>Slider kh??ng ho???t ?????ng</p>
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
                            <p><button>Xem chi ti???t</button></p>
                        </form>
                    </div>
                    <div class="order-top-right">
                        <form action="" method="" class="">
                            <p><input type="date" name="dateFB1" value="<%=xdateFB1%>"> - <input type="date" name="dateFB2" value="<%=xdateFB2%>">
                                <button>L???c</button>
                            </p>
                        </form>
                    </div>
                </div>
                <div class="order-center">
                    <div>
                        <p>T???ng s??? feedback</p>
                        <p><%=NFb%></p>
                    </div>
                </div>
                <div class="order-bottom">
                    <div>
                        <h4>Danh s??ch feedback m???i nh???t </h4>
                    </div>
                    <div class="overflow">
                        <table class="table-list">
                            <tr>
                                <th>S???n ph???m ????nh gi??</th>
                                <th>M???c ????? h??i l??ng</th>
                                <th>N???i dung</th>
                                <th>Ng??y ????ng</th>
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
                            <p><button>Xem chi ti???t</button></p>
                        </form>
                    </div>
                    <div class="order-top-right">
                        <form action="" method="" class="">
                            <p><input type="date" name="dateCus1" value="<%=xdateCus1%>"> - <input type="date" name="dateCus2"value="<%=xdateCus2%>">
                                <button>L???c</button>
                            </p>
                        </form>
                    </div>
                </div>
                <div class="order-center">
                    <div>
                        <p>T???ng</p>
                        <p><%=NCustomers%></p>
                    </div>
                    <div>
                        <p>Kh??ch ???? ?????t h??ng</p>
                        <p><%=CushasOrder%></p>
                    </div>
                    <div>
                        <p>Kh??ch h??ng ti???m n??ng</p>
                        <p><%=NCustomers-CushasOrder%></p>
                    </div>
                    
                </div>
                <div class="order-bottom">
                    <div>
                        <h4>Kh??ch h??ng m???i ????ng k??</h4>
                    </div>
                    <div class="overflow">
                        <table class="table-list">
                            <tr>
                                <th>CusID</th>
                                <th>T??n kh??ch h??ng</th>
                                <th>Ch??? ???</th>
                                <th>Ng??y ????ng k??</th>
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
                    <p class="footer-text">?????a ch??? 1: No 1, Hacker way, Menlo Park, CA</p>
                    <p class="footer-text">?????a ch??? 2: No 1 Rathbone Square, Fitzrovia, London</p>
                    <p class="footer-text">?????a ch??? 3: 4 Grand Canal Square, Grand Canal Harbour</p>
                </div>
                <div class="">
                    <h4 style="font-weight: bold; color:#fff">Th??ng tin li??n h???</h4>
                    <p class="footer-text">??i???n tho???i: 0123456789</p>
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

