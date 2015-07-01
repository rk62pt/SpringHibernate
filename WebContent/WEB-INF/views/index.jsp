<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>首頁</title>
<link rel="stylesheet" type="text/css" href="./jss/plugin/kickstart/css/kickstart.css" media="all" />                  <!-- KICKSTART -->
</head>
<body>
<!--navigation-->
<ul class="menu">
<li class="current"><a href="#initContent">首頁</a></li>
<li><a href="#bulletin">公佈欄</a></li>
<li><a href=""><i class="fa fa-inbox"></i> Item 3</a>
	<ul>
	<li><a href=""><i class="fa fa-cog"></i> Sub Item</a></li>
	<li><a href=""><i class="fa fa-envelope"></i> Sub Item</a>
		<ul>
		<li><a href=""><i class="fa fa-wrench"></i> Sub Item</a></li>
		<li><a href=""><i class="fa fa-camera-retro"></i> Sub Item</a></li>
		<li><a href=""><i class="fa fa-coffee"></i> Sub Item</a></li>
		<li><a href=""><i class="fa fa-twitter"></i> Sub Item</a></li>
		</ul>
	</li>
	<li class="divider"><a href=""><i class="fa fa-trash"></i> li.divider</a></li>
	</ul>
</li>
<li><a href="">Item 4</a></li>
</ul>
<div id="content">
	index
</div>
<script type="text/javascript" src="./jss/jquery-1.11.3.js"></script>
<script type="text/javascript" src="./jss/plugin/kickstart/js/kickstart.js"></script> 
<script>
		$(document).ready(function(e){
        	
            initialiseStateFromURL();

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
            
            $(".menu li").on("click",function(){
            	$(".menu li").removeClass("current");
            	$(this).addClass("current");
            });
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
            		$(".menu li a").each(function(){
                		if($(this).attr("href")===hash){
                			
                			$(".menu li").removeClass("current");
                        	$(this).parent("li").addClass("current");
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