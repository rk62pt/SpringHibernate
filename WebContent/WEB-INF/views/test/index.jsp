<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>首頁</title>
<link rel="stylesheet" type="text/css" href="./jss/plugin/jquery-ui/jquery-ui.min.css"/>
<link rel="stylesheet" type="text/css" href="./jss/plugin/jquery-ui/jquery-ui.structure.min.css"/>
<link rel="stylesheet" type="text/css" href="./jss/plugin/jquery-ui/jquery-ui.theme.min.css"/>
<link rel="stylesheet" type="text/css" href="./jss/plugin/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="./jss/plugin/bootstrap/css/bootstrap-theme.min.css"/>

</head>
<body>
<!--navigation-->
<ul id="menu" class="nav nav-pills">
  <li role="presentation" class="active"><a href="#initContent">首頁</a></li>
  <li role="presentation"><a href="#bulletin">公佈欄</a></li>
  <li role="presentation"><a href="#">Messages</a></li>
</ul>
<hr>
<div id="content">
	
</div>
<script type="text/javascript" src="./jss/jquery-1.11.3.js"></script>
<script type="text/javascript" src="./jss/plugin/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="./jss/plugin/bootstrap/js/bootstrap.min.js"></script> 
<script>
		$(document).ready(function(e){
        	
            initialiseStateFromURL();
            
            $(".nav a").on("click", function(){
            	   $(".nav").find(".active").removeClass("active");
            	   $(this).parent().addClass("active");
            });
            
            $.ajaxSetup({
                //async : false, //設置AJAX的同步
                cache : false, //關閉 AJAX cache
                timeout : 60000,
            });
            //if ("onhashchange" in window) {
                //window.onhashchange = initialiseStateFromURL;
            //}
            if (("onhashchange" in window) && ($.support.leadingWhitespace)) { 
    			window.onhashchange = function () { 
    				//alert(window.location.hash);
    				initialiseStateFromURL()
    			} 
    		} else { 
    			/*var prevHash = window.location.hash;
    			window.setInterval(function () { 
    				if (window.location.hash != prevHash) { 
    					storedHash = window.location.hash; 
    					alert(window.location.hash);
    				} 
    			}, 100);*/ 
    			window.onhashchange = function () { 
    				//alert(window.location.hash);
    				initialiseStateFromURL()
    			} 
    		}
            
            
        });
        
		
		
        
        function initialiseStateFromURL() {
            var hash = window.location.hash;
            // ...判斷state並執行對應(ajax)動作
            console.log(hash);
            if (hash) {
            	var url = hash.substring(1);
            	if(url === "index"){
            		window.location.href = "index";
            	}else{
            		$("#content").load(url);
            		$(".nav a").each(function(){
                		if($(this).attr("href")===hash){
                			$(".nav").find(".active").removeClass("active");
                     	   	$(this).parent("li").addClass("active");
                		}
                	});
            		
            	}
            	
                

            } else {
                // 如果沒有回傳值，代表已經回到 AJAX 的初始狀態頁，清空顯示的內容
                //$("#content").load('index');
            	$("#content").load("initContent");
            }
        }
</script>

</body>
</html>