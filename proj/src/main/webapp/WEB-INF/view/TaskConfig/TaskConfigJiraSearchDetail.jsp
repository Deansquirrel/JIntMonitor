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
			<c:url var="urlStr" value="/TaskConfig/JiraSearch/List" />
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
				<label class="col-sm-2 control-label">JQL</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.jql }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Jira User</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.user }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Jira Password</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.pwd }</label>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Cron</label>
				<label class="col-sm-10 control-label" style="text-align: left;">${config.cron }</label>
			</div>
		</form>
		
	</div>
</div>