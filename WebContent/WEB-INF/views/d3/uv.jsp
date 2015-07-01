<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首頁</title>
<link rel="stylesheet" type="text/css" href="../jss/plugin/kickstart/css/kickstart.css" media="all" /><!-- KICKSTART -->
</head>
<body>
<div class="col_3 visible column">col_3</div><div class="col_9 visible column">col_9</div>
<script type="text/javascript" src="../jss/jquery-1.11.3.js"></script>
<script type="text/javascript" src="../jss/plugin/kickstart/js/kickstart.js"></script>
<script src="http://d3js.org/d3.v3.min.js"></script>
<script>
$(document).ready(function() {
	 d3.json("rest/uvList", function(data) {
		  console.log(data);
		  
	 });
});
</script>
</body>
</html>