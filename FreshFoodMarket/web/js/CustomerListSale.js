/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function clearFilter() {
    window.location = "/FreshFoodMarket/productListSale";
}

function detail(cusID) {
    window.location = "/FreshFoodMarket/CustomerDetailSaleController?cusID="+cusID;
    }
    
function addProduct() {
    window.location = "/FreshFoodMarket/addProduct";
}