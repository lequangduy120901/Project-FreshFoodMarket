/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function goBack() {
    const noti = document.getElementById("input").innerHTML;
    if (noti == 1) {
        window.location = "/FreshFoodMarket/homeController";
    } else {
        window.location = "cartcontroller?action=";
    }
}

function checkNoties() {
    const noti = document.getElementById("input").innerHTML;
    if (noti == 1) {
        document.getElementById("notiesText").style.color = "green";
        document.getElementById("failed").style.display = "none";
        document.getElementById("failed2").style.display = "none";
    } else if (noti == 2) {
        document.getElementById("success").style.display = "none";
        document.getElementById("failed2").style.display = "none";
        document.getElementById("notiesText").innerHTML = "ĐẶT HÀNG THẤT BẠI!";
        document.getElementById("notiesText").style.color = "red";
        document.getElementById("gobackbtn").innerHTML = "Giỏ Hàng";
    } else {
        document.getElementById("success").style.display = "none";
        document.getElementById("failed").style.display = "none";
        document.getElementById("notiesText").innerHTML = "ĐẶT HÀNG THẤT BẠI!";
        document.getElementById("notiesText").style.color = "red";
        document.getElementById("gobackbtn").innerHTML = "Giỏ Hàng";
    }
}

checkNoties();