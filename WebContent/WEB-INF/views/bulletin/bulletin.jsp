<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#divDialog" id="btnBulletin_new">
  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 新增 
</button>
<table class="table table-striped" cellspacing="0" cellpadding="0">
	<thead><tr class="alt first last">
		<th>標題</th>
		<th>描述</th>
		<th>建立時間</th>
		<th>建立者</th>
		<th></th>
		<th></th>
	</tr></thead>
	<tbody id="tbodyContent">
	
	</tbody>
	</table>
	
<!-- add and edit dialog -->
<div class="modal fade" id="divDialog" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel">
  
</div>
<script>
var loadContent = function(){
	$("#tbodyContent").load("bulletin/content");	
};

var loadDialog = function(id){
	$("#divDialog").load("bulletin/edit",{"id":id});
};

$(function(){
	
	$("#btnBulletin_new").on("click",function(){
		$("#divDialog").load("bulletin/add");
	});
	loadContent();
});

</script>