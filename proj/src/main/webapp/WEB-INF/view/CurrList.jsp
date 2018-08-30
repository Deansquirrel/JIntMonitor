<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../../../layouts/library.jsp"%> 
<script type="text/javascript">
	updateMenuPath(${menulist });
</script>   
<div class="panel">
	<div class="panel-heading">
		<h3 class="panel-title">Current List</h3>
	</div>
	<div class="panel-body">
		<table class="table table-hover">
			<thead>
				<tr>
					<th style="width: 50px">序号</th>
					<th style="min-width: 200px">名称</th>
					<th style="min-width: 200px">说明</th>
					<th>类型</th>
					<th style="width: 200px">操作</th>
				</tr>
			</thead>
			<tbody id="tb-list">
				<c:forEach items="${list }" var="item" varStatus="status">
					<tr>
						<td>${status.index + 1 }<p style="display: none">${item.id }</p></td>
						<td>${item.title }</td>
						<td>${item.remark }</td>
						<td>${item.type }</td>
						<td>
							<div class="col-xs-12">
								<c:url var="urlStr" value="${item.configUrl }" />
								<a href="${urlStr }"><span>查看配置</span></a>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>