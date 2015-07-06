<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:forEach var="vo" items="${voList}">
	<tr class="first trBulletin" >
		<td>${vo.title}</td>
		<td>${(fn:length(vo.description)>5)?fn:substring(vo.description,0,5):vo.description}...<a href="javascript:void(0);" class="linkDescript" data-toggle="popover" data-content="${vo.description }">more</a></td>
		<td>${vo.create_time}</td>
		<td>${vo.creator}</td>
		<td>
		<button type="button" class="btn btn-default" data-toggle="modal" data-target="#divDialog" onclick="loadDialog(${vo.id})">
		  <span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 修改 
		</button>	
		</td>
		<td>
		<button type="button" class="btn btn-default" data-toggle="modal" data-target="#dialog_add" onclick="delData(${vo.id});">
		  <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 刪除 
		</button>
		</td>
	</tr>
</c:forEach>

<script>
$(function(){
	$('.linkDescript').popover({
	    trigger: 'hover',
	        'placement': 'top'
	});
	
});
var delData = function(id){
	$.confirm({
	    text: "是否確定刪除?",
	    confirmButton:"是",
	    cancelButton:"否",
	    confirm: function() {
	    	$.post("rest/bulletin/delete",{id:id},function(){
	    		loadContent();
	    	});	    	
	    },
	    cancel: function() {
	        // nothing to do
	    }
	});
	
	
}
</script>