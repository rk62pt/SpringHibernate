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
<style type="text/css">
.notification-icon .glyphicon {
    font-size: 20px;
}

.notification-icon .badge {
    font-size: 12px;
}
</style>
</head>
<body>
<div class="row">
<div class="col-md-9">
<!--navigation-->
<ul id="menu" class="nav nav-pills">
  <li role="presentation" class="active"><a href="#initContent">首頁</a></li>
  <li role="presentation"><a href="#bulletin">公佈欄</a></li>
  <li role="presentation"><a href="#message">訊息</a></li>
</ul>
</div>
<div class="col-md-1">
	<div class="notification-icon" id="divMsgList">
    	<a href="javascript:void(0);" id="linkMsg" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="1000" data-close-others="false" role="button" aria-haspopup="true" aria-expanded="false">
    	<span class="glyphicon glyphicon-envelope"></span>
    	</a>
    	<span class="badge" id="spanMsg">0</span>
    	<ul id="ulMsg" class="dropdown-menu" aria-labelledby="linkMeg">
    		<li>無訊息</li>
        </ul>
	</div>
</div>
<div class="col-md-2">
	<div class="notification-icon " title="輸入一個名字">
    	<a href="" id="linkUser" data-toggle="modal" data-target="#userModal">
    		<span class="glyphicon glyphicon-user"></span>
    	</a>
    	<span class="label label-info" id="spanUser">
    		訪客
    	</span>
    	
	</div>
</div>
</div>
<hr>
<div id="content" class="col-md-12">
	
</div>
<!-- Modal -->
<div class="modal fade" id="userModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">換一個名字</h4>
      </div>
      <div class="modal-body">
        <input type="text" class="form-control" id="txtName" placeholder="John">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
        <button type="button" class="btn btn-primary" id="btnChangeName">儲存</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript" src="./jss/jquery-1.11.3.js"></script>
<script type="text/javascript" src="./jss/plugin/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="./jss/plugin/bootstrap/js/bootstrap.min.js"></script> 
<script type="text/javascript" src="./jss/plugin/bootstrap/js/bootstrap-hover-dropdown.js"></script>
<!-- script src="jss/sockjs.js"></script -->
<script src="jss/stomp.js"></script>
<script>
var wsUrl = "ws://localhost:8080/SpringHibernate";
//var socket = new SockJS("/SpringHibernate/ws");
var socket = new WebSocket(wsUrl+"/ws/websocket");
var stompClient = Stomp.over(socket);
var user = "訪客";
var msgTime = new Date().getTime();
function renderMsg(frame) {
    var msgs = JSON.parse(frame.body);
    $('#ulMsg').empty();
    var msgsLength = 0;
    for(var i in msgs) {
      var msg = msgs[i];
      if(msg.create_time>=msgTime){
	      $('#ulMsg').append(
	        $('<li>').append(
	          $('<a>').html(msg.user + " > " + msg.content)
	        )
	      );
	      msgsLength++;
      }
    }
    $("#spanMsg").html(msgsLength)
  }
var msgConnectCallback = function() {
	//console.log("--------連線中----");
    stompClient.subscribe('/topic/msg', renderMsg);
  //initLoadMsgCount
    stompClient.send("/app/updateMsg");
    
}; 
var msgErrorCallback = function(error) {
    alert(error.headers.message);
};
    // Connect to server via websocket
stompClient.connect("guest", "guest", msgConnectCallback, msgErrorCallback);
    
    
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
            //initLoadMsgCount
            //stompClient.send("/app/updateMsg");
            $('#btnChangeName').on("click",function(e){
                //e.preventDefault();
                /* var user = $(this).val();
                var jsonstr = JSON.stringify({ 'user': user });
                stompClient.send("/app/updateUser", {}, jsonstr); */
                if($("#txtName").val()!==""){
                	user = $("#txtName").val();
                }                
                $("#spanUser").html(user);
                $("#userModal").modal('hide');
                //return false;
            });
            
            $("#linkMsg").on("click",function(){
            	msgTime = new Date().getTime();
            	//stompClient.send("/app/updateMsg");
            });
            $("#divMsgList").on({"hide.bs.dropdown":  function() { 
            		stompClient.send("/app/updateMsg");
            		return this.closable; 
            	}
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