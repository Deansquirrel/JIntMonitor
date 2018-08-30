<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../layouts/library.jsp"%>
<div class="panel">
	<div class="panel-heading">
		<h3 class="panel-title">TaskConfigAdd</h3>
	</div>
	<div class="panel-body">
		<form id="f-add" class="form-horizontal">
			<div class="form-group">
				<label class="col-sm-2 control-label">Title</label>
				<div class="col-sm-10">
					<input id="i-title" type="text" class="form-control" name="i-title" placeholder="Title">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Remark</label>
				<div class="col-sm-10">
					<input id="i-remark" type="text" class="form-control" name="i-remark"  placeholder="Remark">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Server</label>
				<div class="col-sm-10">
					<input id="i-server" type="text" class="form-control" name="i-server"  placeholder="Server">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Port</label>
				<div class="col-sm-10">
					<input id="i-port" type="text" class="form-control" name="i-port"  placeholder="Port">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">DbName</label>
				<div class="col-sm-10">
					<input id="i-dbname" type="text" class="form-control" name="i-dbname"  placeholder="DbName">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">User</label>
				<div class="col-sm-10">
					<input id="i-user" type="text" class="form-control" name="i-user"  placeholder="User">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Pwd</label>
				<div class="col-sm-10">
					<input id="i-pwd" type="text" class="form-control" name="i-pwd"  placeholder="Pwd">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Search</label>
				<div class="col-sm-10">
					<input id="i-search" type="text" class="form-control" name="i-search"  placeholder="Search">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Cron</label>
				<div class="col-sm-10">
					<input id="i-cron" type="text" class="form-control" name="i-cron"  placeholder="Cron">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">CheckMax</label>
				<div class="col-sm-10">
					<input id="i-checkmax" type="text" class="form-control" name="i-checkmax"  placeholder="CheckMax">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">CheckMin</label>
				<div class="col-sm-10">
					<input id="i-checkmin" type="text" class="form-control" name="i-checkmin"  placeholder="CheckMin">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">MsgTitle</label>
				<div class="col-sm-10">
					<input id="i-msgtitle" type="text" class="form-control" name="i-msgtitle"  placeholder="MsgTitle">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">MsgContent</label>
				<div class="col-sm-10">
					<input id="i-msgcontent" type="text" class="form-control" name="i-msgcontent"  placeholder="MsgContent">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" id="btn-add" class="btn btn-primary btn-block">Submit</button>
				</div>
			</div>
		</form>
		
	</div>
</div>
<!-- END TABLE HOVER -->
<c:url var="urlStr" value="/TaskConfig/Int/Add" />
<script type="text/javascript">
	$("#btn-add").click(function(){
		$.ajax({
			url:"${urlStr }",
			type:"POST",
			cache:false,
			data:$("#f-add").serialize(),
			beforeSend:function(){
				showLoading();
			},
			complete:function(){
				// closeLoading();
			},
			success:function(data){
				var d = JSON.parse(data);
				if(d != null){
					if(d.errCode == "0"){
						swal({
							text:"添加成功",
							type:"success",
							confirmButtonText: '确定',
							showCancelButton: false,
							allowEscapeKey:false,
							allowOutsideClick:false
						}).then(function(){
							pageInit();
						}).catch(swal.noop);
					}
					else{
						swal({
							text:d.errDesc,
							type:"error",
							confirmButtonText: '确定',
							showCancelButton: false,
							allowEscapeKey:false,
							allowOutsideClick:false
						}).catch(swal.noop);
					}
				}
				else{
					swal({
							text:"返回数据解析失败，请通过列表查询执行结果",
							type:"error",
							confirmButtonText: '确定',
							showCancelButton: false,
							allowEscapeKey:false,
							allowOutsideClick:false
						}).catch(swal.noop);
				}
				
				
			},
			error:function(){
				swal({
					text:"提交遇到错误，请通过列表查询执行结果",
					type:"warning",
					confirmButtonText: '确定',
					showCancelButton: false,
					allowEscapeKey:false,
					allowOutsideClick:false
				}).catch(swal.noop);
			}
		});

	});

	function pageInit(){
		$("#i-title").val("");
		$("#i-remark").val("");
		$("#i-server").val("");
		$("#i-port").val("");
		$("#i-dbname").val("");
		$("#i-user").val("");
		$("#i-pwd").val("");
		$("#i-search").val("");
		$("#i-cron").val("");
		$("#i-checkmax").val("");
		$("#i-checkmin").val("");
		$("#i-msgtitle").val("");
		$("#i-msgcontent").val("");
		
		$("#btn-add").attr("disabled",false);
		$("#i-title").focus();
	}
	pageInit();

	updateMenuPath(${menulist });
</script>