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

function showContent() {
    const x = document.getElementById("edit").innerHTML;
    if (x == "Chỉnh sửa") {
        document.getElementById("id").style.color = "#a8a8a8";
        document.getElementById("title").disabled = false;
        document.getElementById("backlink").disabled = false;
        document.getElementById("des").disabled = false;
        document.getElementById("note").disabled = false;

        document.getElementById("status").style.pointerEvents = "";

        document.getElementById("imgCont").style.display = "block";

        document.getElementById("edit").innerHTML = "Lưu";
        document.getElementById("back").innerHTML = "Hủy";
    } else {
         document.getElementById("slideForm").submit();
    }

}

function submit() {
    const y = document.getElementById("back").innerHTML;
    if (y == "Quay Lại") {
        window.location = "/FreshFoodMarket/sliderList";
    } else {
        if (confirm("Bạn có thực sự muốn hủy thay đổi?")) {
            window.location.reload();
        } else {}
    }
}

function deleteSlide() {
    if (confirm("Bạn có muốn xóa slide này?")) {
        document.getElementById("asl").click();
    }
}