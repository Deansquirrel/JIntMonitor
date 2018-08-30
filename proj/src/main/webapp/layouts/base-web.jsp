<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="library.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>JMonitor</title>
		<meta http-equiv="deansquirrel" content="IE=edge, chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
		
		<c:url var="urlStr" value="/layouts/assets/vendor/bootstrap/css/bootstrap.min.css" />
		<link class="default-style" rel="stylesheet" href="${urlStr }">
		
		<c:url var="urlStr" value="/layouts/assets/vendor/font-awesome/css/font-awesome.min.css" />
		<link class="default-style" rel="stylesheet" href="${urlStr }">
		
		<c:url var="urlStr" value="/layouts/assets/vendor/themify-icons/css/themify-icons.css" />
		<link class="default-style" rel="stylesheet" href="${urlStr }">
		
		<c:url var="urlStr" value="/layouts/assets/css/main.min.css" />
		<link class="default-style" id="main-style" rel="stylesheet" href="${urlStr }">

		<c:url var="urlStr" value="/layouts/assets/css/skins/sidebar-nav-darkgray.css" />
		<link class="default-style" rel="stylesheet" href="${urlStr }" type="text/css">
		
		<c:url var="urlStr" value="/layouts/assets/css/skins/navbar3.css" />
		<link class="default-style" rel="stylesheet" href="${urlStr }" type="text/css">

		<c:url var="urlStr" value="/layouts/assets/vendor/sweetalert2/sweetalert2.css" />
		<link class="default-style" rel="stylesheet" href="${urlStr }">
	
		<c:url var="urlStr" value="/layouts/assets/img/fav.ico" />
		<link rel="icon" type="image/png" href="${urlStr }">
		
		<c:url var="urlStr" value="/layouts/assets/img/fav.ico" />
		<link rel="apple-touch-icon" href="${urlStr }">

		<c:url var="urlStr" value="/layouts/assets/vendor/jquery/jquery.min.js" />
		<script language="javascript"src="${urlStr }"></script>
		
		<c:url var="urlStr" value="/layouts/assets/vendor/bootstrap/js/bootstrap.min.js" />
		<script language="javascript" src="${urlStr }"></script>

		<c:url var="urlStr" value="/layouts/assets/vendor/sweetalert2/sweetalert2.js" />
		<script language="javascript" src="${urlStr }"></script>

		<c:url var="urlStr" value="/layouts/assets/script/common.js" />
		<script language="javascript" src="${urlStr }"></script>
		
	</head>
	<body>
		<!-- WRAPPER -->
	<div id="wrapper">

		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<!--<a href="index.html"><span style="color: #FFFFFF">JMonitor</span></a>-->
				<span style="color: #FFFFFF">JMonitor</span>
			</div>
		</nav>
		<!-- END NAVBAR -->

		<tiles:insertAttribute name="menu" />

		<!-- MAIN -->
		<div class="main">
			
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="content-heading clearfix">
				
					<div class="heading-left">
						<!--
						<h1 class="page-title" id="view-title">AAA</h1>
						<p class="page-subtitle" id="view-description">BBB</p>
					-->
						<ul class="breadcrumb" id="view-breadcrumb">
							<li>Home</li>
						</ul>
					</div>
				
					
				</div>
				<div class="container-fluid">
					<tiles:insertAttribute name="content" />
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		<div class="clearfix"></div>

		<footer>
			<div class="container-fluid">
				<p class="copyright">JMonitor</p>
			</div>
		</footer>
	</div>
	<!-- END WRAPPER -->
	
	</body>
</html>