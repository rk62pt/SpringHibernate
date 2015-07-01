<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="dialog_editLabel">編輯公告資訊</h4>
      </div>
      <div class="modal-body">
  <form class="form-horizontal" id="formBulletin">
  <div class="form-group">
    <label class="col-sm-2 control-label">標題</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="txbTitle" name="title" placeholder="請輸入標題" value="${vo.title}">
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label">內容</label>
    <div class="col-sm-10">
      <textarea class="form-control" id="txbDescription" name="description" rows="5" placeholder="內容描述">${vo.description}</textarea>
    </div>
  </div>
  <input type="hidden" id="hidId" name="id" value="${vo.id}" /> 
  </form>
      </div>
      <div class="modal-footer">    
      	   
        <button type="button" class="btn btn-primary" id="btnEditData">編輯</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div>
  </div>
  
  <script>
  $(function(){
	  $("#btnEditData").on("click",function(){
		  $.ajax({
			   url: 'rest/bulletin/edit',
			   type: 'POST',
			   data: $("#formBulletin").serialize(),
			   success: function(response) {
				 $(".modal").modal('hide');
				 loadContent();
			     console.log(response);
			   }
		  });
	  });
	  
  });
  </script>