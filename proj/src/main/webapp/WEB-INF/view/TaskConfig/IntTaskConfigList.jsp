<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../layouts/library.jsp"%>    
 <div class="panel">
<div class="panel-heading">
	<h3 class="panel-title">List</h3>
	<div class="right">
		<c:url var="urlStr" value="/TaskConfig/IntTaskConfig/Add" />
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
			<tr>
				<td>1<p style="display: none">GUID</p></td>
				<td>通道数据检查</td>
				<td>通道数据检查通道</td>
				<td>
					<div class="col-xs-4">
						<a href="index-taskcofnigdetail.html"><span>查看</span></a>
					</div>
					<div class="col-xs-4">
						<a href=""><span>删除</span></a>
					</div>
					<!--
					<div class="col-xs-4">
						<a href=""><span>修改</span></a>
					</div>
					
					-->
				</td>
			</tr>
			<tr>
				<td>2<p style="display: none">GUID</p></td>
				<td>通道数据检查</td>
				<td>TTTTTTTTTTTTTTTTTTTTTTT</td>
				<td>
					<div class="col-xs-4">
						<a href="index-taskcofnigdetail.html"><span>查看</span></a>
					</div>
					<!--
					<div class="col-xs-4">
						<a href=""><span>修改</span></a>
					</div>
					<div class="col-xs-4">
						<a href=""><span>删除</span></a>
					</div>
					-->
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>