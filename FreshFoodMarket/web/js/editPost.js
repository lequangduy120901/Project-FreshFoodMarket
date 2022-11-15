var textarea = document.querySelector('textarea');

textarea.addEventListener('click', autosize);
             
function autosize(){
  var el = this;
  setTimeout(function(){
    el.style.cssText = 'height:auto; padding:0';
    // for box-sizing other than "content-box" use:
    //el.style.cssText = '-moz-box-sizing:content-box';
    el.style.cssText = 'height:' + el.scrollHeight + 'px';
  },0);
}

function my1(){
    window.location = "/FreshFoodMarket/managePost";
}

function submitForm(){
    document.getElementById("myForm").submit();
}