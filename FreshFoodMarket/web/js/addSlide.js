/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function changeImg(fileInput) {
    if (fileInput.files && fileInput.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            document.getElementById("img-slide").src = e.target.result;
        };
        reader.readAsDataURL(fileInput.files[0]);
    }
}

function save() {
    document.getElementById("slideForm").submit();
}

function cancel() {
    window.location = "/FreshFoodMarket/sliderList";
}