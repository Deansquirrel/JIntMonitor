<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../layouts/library.jsp"%>    
<script type="text/javascript">
	updateMenuPath(${menulist });
</script>
<div class="panel">
	<div class="panel-heading">
		<h3 class="panel-title">Detail</h3>
		<div class="right">
			<c:url var="urlStr" value="/MessageSender/Ding/List" />
			<a href="${urlStr }"><button id="btn-config-add" type="button" class="btn btn-primary">Back To List</button></a>
		</div>
	</div>
	<div class="panel-body">
		<form class="form-horizontal" role="form">
			<div class="form-group">
				<label class="col-sm-2 control-label">ID</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.id }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Title</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.title }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Remark</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.remark }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">RobotToken</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.robotToken }</label>
			</div>
		</form>
		
	</div>
</div>