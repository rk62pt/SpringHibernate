<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
  <div class="col-md-3"></div>
  <div class="col-md-6">
  	<div class="well" id="divMsgs">...</div>
	<div>
	<input type="text" id="txbMsg" />
	<button id="btnAddMsg" >送出</button>
	</div>
  </div>
  <div class="col-md-3"></div>
</div>


<script>
$(function(){
	$('#btnAddMsg').click(function(e){
        e.preventDefault();
        var msgUser = user;
        var content = $('#txbMsg').val();
        var jsonstr = JSON.stringify({ 'content': content ,'user':user});
        stompClient.send("/app/addMsg", {}, jsonstr);
        $('#txbMsg').val('');
        return false;
    });
	$('#divMsgs').empty();
	setTimeout(function(){
		//console.log("10000");
		//console.log(stompClient);
		stompClient.subscribe('/topic/msg', renderUserMsg);
		stompClient.send("/app/updateMsg");
	},100);
	
	
	//stompClient.subscribe('/topic/msg', renderUserMsg);
	//stompClient.send("/app/updateMsg");
});

function renderUserMsg(frame) {
    var msgs = JSON.parse(frame.body);
    //alert("test");
    $('#divMsgs').empty();
    for(var i in msgs) {
      var msg = msgs[i];
      $('#divMsgs').append(
    	$("<div>").html(msg.user + " > " + msg.content)
      );
    }
    
}

</script>