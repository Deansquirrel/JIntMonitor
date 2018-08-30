<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../layouts/library.jsp"%> 
<script type="text/javascript">
	updateMenuPath(${menulist });
</script>   
<div class="panel">
	<div class="panel-heading">
		<h3 class="panel-title">List</h3>
		<div class="right">
			<c:url var="urlStr" value="/MessageSender/Ding/Add" />
			<a href="${urlStr }"><button id="btn-config-add" type="button" class="btn btn-primary">Add</button></a>
		</div>
	</div>
	<div class="panel-body">
		<table class="table table-hover">
			<thead>
				<tr>
					<th style="width: 50px">序号</th>
					<th style="min-width: 200px">名称</th>
					<th style="min-width: 200px">说明</th>
					<th style="width: 200px">操作</th>
				</tr>
			</thead>
			<tbody id="tb-list">
				<c:forEach items="${list }" var="item" varStatus="status">
					<tr>
						<td>${status.index + 1 }<p style="display: none">${item.id }</p></td>
						<td>${item.title }</td>
						<td>${item.remark }</td>
					
						<td>
							<div class="col-xs-4">
								<c:url var="urlStr" value="/MessageSender/Ding/Detail/${item.id }" />
								<a href="${urlStr }"><span>查看</span></a>
							</div>
							<div class="col-xs-4">
								<a href="javascript:delItem('${item.id }')"><span>删除</span></a>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<c:url var="urlStr" value="/MessageSender/Ding/Del" />
<script type="text/javascript">
	function delItem(id){
		$.ajax({
			url:"${urlStr }",
			type:"POST",
			cache:false,
			data:"i-id=" + id,
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
							text:"删除成功",
							type:"success",
							confirmButtonText: '确定',
							showCancelButton: false,
							allowEscapeKey:false,
							allowOutsideClick:false
						}).then(function(){
							window.location.reload(true);
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
					text:"提交遇到错误，请通过列表查询添加结果",
					type:"warning",
					confirmButtonText: '确定',
					showCancelButton: false,
					allowEscapeKey:false,
					allowOutsideClick:false
				}).catch(swal.noop);
			}
		});
	}
</script>