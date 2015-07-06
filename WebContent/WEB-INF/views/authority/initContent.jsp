<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<h4>2015</h4>
<hr/>
<div class="row">
<div class="col-md-8">
<!-- Blockquote Small -->
<p class="lead bg-info">
網站資訊
</p>
<ul class="lead bg-info">
<li>Spring MVC</li>
<li>Hibernate</li>
<li>JQuery</li>
<li>Bootstript</li>
<li>webSocket</li>
</ul>



<span>By Ryan.</span>
</div>
<div class="col-md-4">
<p class="bg-primary">最新資訊（公佈欄）</p>
<div id="divNew" class="bg-info row" style="position: relative; overflow: hidden;">
			<c:forEach var="vo" items="${voList}">
            <div>
                <h4><span class="label label-default">New</span>${vo.title}</h4> 
                ${vo.description}
            </div>
            </c:forEach>

 </div>
</div>
</div>
<script type="text/javascript" src="./jss/plugin/jquery.cycle.js"></script>
<script>
    
$('#divNew').cycle({ 
    fx:    'scrollLeft', 
    delay: -1500 
});
   
</script>