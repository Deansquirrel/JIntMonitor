<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../layouts/library.jsp"%>
<div class="panel">
	<div class="panel-heading">
		<h3 class="panel-title">MessageConfigAdd</h3>
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
				<label class="col-sm-2 control-label">RobotToken</label>
				<div class="col-sm-10">
					<input id="i-robottoken" type="text" class="form-control" name="i-robottoken"  placeholder="RobotToken">
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
<c:url var="urlStr" value="/MessageSender/Ding/Add" />
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
		$("#i-robottoken").val("");
		
		$("#btn-add").attr("disabled",false);
		$("#i-title").focus();
	}
	pageInit();

	updateMenuPath(${menulist });
</script>