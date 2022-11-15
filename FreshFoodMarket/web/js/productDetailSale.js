/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function showContent() {
    const x = document.getElementById("edit").innerHTML;
    if (x == "Chỉnh sửa") {
        document.getElementById("cate").style.pointerEvents = "";
        document.getElementById("proName").disabled = false;
        document.getElementById("price").disabled = false;
        document.getElementById("type").disabled = false;
        document.getElementById("quant").disabled = false;
        document.getElementById("des-text").disabled = false;

        document.getElementById("imgCont").style.display = "block";

        document.getElementById("edit").innerHTML = "Lưu";
        document.getElementById("back").innerHTML = "Hủy";
    } else {
        document.getElementById("proForm").submit();
    }

}

function submit() {
    const y = document.getElementById("back").innerHTML;
    if (y == "Quay lại") {
        window.location = "/FreshFoodMarket/productListSale";
    } else {
        if (confirm("Bạn có thực sự muốn hủy thay đổi?")) {
            window.location.reload();
        } else {}
    }
}

function changeImg(fileInput) {
    if (fileInput.files && fileInput.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            document.getElementById("img-pro").src = e.target.result;
            document.getElementById("img-pro").alt = e.target.result;
        };
        reader.readAsDataURL(fileInput.files[0]);
    }
}

function deletePro(proID) {
    if (confirm("Bạn có muốn xóa sản phẩm này?")) {
        window.location = "/FreshFoodMarket/deleteProduct?proID=" + proID;
    }
}