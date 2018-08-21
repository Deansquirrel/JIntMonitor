<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="panel">
	<div class="panel-heading">
		<h3 class="panel-title">TaskConfigAdd</h3>
	</div>
	<div class="panel-body">
		<form class="form-horizontal">
			<div class="form-group">
				<label for="ticket-subject" class="col-sm-2 control-label">Subject</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="ticket-subject" id="ticket-subject-1" placeholder="Subject">
				</div>
			</div>
			<div class="form-group">
				<label for="ticket-subject" class="col-sm-2 control-label">Subject</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="ticket-subject" id="ticket-subject-2" placeholder="Subject">
				</div>
			</div>
			<div class="form-group">
				<label for="ticket-subject" class="col-sm-2 control-label">Subject</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="ticket-subject" id="ticket-subject-3" placeholder="Subject">
				</div>
			</div>
			<div class="form-group">
				<label for="ticket-subject" class="col-sm-2 control-label">Subject</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="ticket-subject" id="ticket-subject-4" placeholder="Subject">
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
<script type="text/javascript">
	$("#btn-add").on('click',function(){
		showLoading();
		setTimeout(function(){
			closeLoading();
		},3000);
	});

	function pageInit(){
		$("#btn-add").attr("disabled",false);
		$("#ticket-subject-1").focus();
	}
	pageInit();


	function showLoading(){
		swal({
			text:"处理中 ，请稍后",
			allowEscapeKey:false,
			allowOutsideClick:false,
			showConfirmButton:true,
			showCancelButton:false,
			showLoaderOnConfirm:true
		});
		swal.enableLoading();
	}
	function closeLoading(){
		swal.close();
	}
</script>