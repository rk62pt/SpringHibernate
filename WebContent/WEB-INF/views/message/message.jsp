<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
<input type="text" id="txbMsg" />
<br/>
<button id="btnAddMsg" >送出</button>
</div>

<script>
$(function(){
	$('#btnAddMsg').click(function(e){
        e.preventDefault();
        var content = $('#txbMsg').val();
        var jsonstr = JSON.stringify({ 'content': content });
        stompClient.send("/app/addMsg", {}, jsonstr);
        return false;
    });
});
</script>