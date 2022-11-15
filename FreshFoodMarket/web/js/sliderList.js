/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function view(slideID) {
    window.location = "/FreshFoodMarket/sliderDetail?id=" + slideID;
}

function clearFilter() {
    window.location = "/FreshFoodMarket/sliderList";
}

function addSlide() {
    window.location = "/FreshFoodMarket/addSlide";
}
