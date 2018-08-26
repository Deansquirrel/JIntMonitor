<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../layouts/library.jsp"%>    
<script type="text/javascript">
	setCurrMenuList("#aint");
	setCurrMenuPanel("#int");
	setCurrMenuNode("#ataskintlist");
	updateMenuPath(${menulist });
</script>
<div class="panel">
	<div class="panel-heading">
		<h3 class="panel-title">Detail</h3>
		<div class="right">
			<c:url var="urlStr" value="/TaskConfig/Int/List" />
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
				<label class="col-sm-2 control-label">Server</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.server }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Port</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.port }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">DbName</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.dbName }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">User</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.user }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Pwd</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.pwd }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Search</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.search }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Corn</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.corn }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">CheckMax</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.checkMax }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">CheckMin</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.checkMin }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">MsgTitle</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.msgTitle }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">MsgContent</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.msgContent }</label>
			</div>
		</form>
		
	</div>
</div>