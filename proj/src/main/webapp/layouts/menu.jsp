<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="library.jsp"%>
<!-- LEFT SIDEBAR -->
<c:if test="${fn:contains(menulist,'Current')}" var="cCurrent"></c:if>
<c:if test="${fn:contains(menulist,'Int')}" var="cInt"></c:if>
<c:if test="${fn:contains(menulist,'Health')}" var="cHealth"></c:if>
<c:if test="${fn:contains(menulist,'WebState')}" var="cWebState"></c:if>
<c:if test="${fn:contains(menulist,'JiraSearch')}" var="cJiraSearch"></c:if>
<c:if test="${fn:contains(menulist,'Ding')}" var="cDing"></c:if>
<c:if test="${fn:contains(menulist,'List')}" var="cList"></c:if>
<c:if test="${fn:contains(menulist,'Add')}" var="cAdd"></c:if>


<div id="sidebar-nav" class="sidebar">
	<nav>
		<ul class="nav" id="sidebar-nav-menu">
			<c:url var="urlStr" value="/" />
			<li class="panel">
				<c:if test="${cCurrent }">
					<a href="${urlStr }" class="active" aria-expanded="true">
				</c:if>
				<c:if test="${!cCurrent }">
					<a href="${urlStr }">
				</c:if>
					<span class="title">Current</span>
				</a>
			</li>
			<li class="menu-group">TaskConfig</li>
			<li class="panel">
				<!--
				<a href="#taskconfig" data-toggle="collapse" data-parent="#sidebar-nav-menu" class="active" aria-expanded="true">
				-->
				<c:if test="${cInt }">
					<a id="aint" href="#int" data-toggle="collapse" data-parent="#sidebar-nav-menu" class="active" aria-expanded="true">
				</c:if>
				<c:if test="${!cInt }">
					<a id="aint" href="#int" data-toggle="collapse" data-parent="#sidebar-nav-menu" class="collapsed">
				</c:if>
					<c:if test="${cInt }">
						<span class="title active">Int</span>
					</c:if>
					<c:if test="${!cInt }">
						<span class="title">Int</span>
					</c:if>
					<i class="icon-submenu ti-angle-left"></i>
				</a>
				<!--<div id="taskconfig" class="collapse in">-->
				<c:if test="${cInt }">
					<div id="int" class="collapse in">
				</c:if>
				<c:if test="${!cInt }">
					<div id="int" class="collapse">
				</c:if>
					<ul class="submenu">
						<c:url var="urlStr" value="/TaskConfig/Int/List" />
						<c:if test="${cInt && cList }">
							<li><a id="ataskintlist" href="${urlStr }" class="active">List</a></li>
						</c:if>
						<c:if test="${!(cInt && cList) }">
							<li><a id="ataskintlist" href="${urlStr }">List</a></li>
						</c:if>
						<c:url var="urlStr" value="/TaskConfig/Int/Add" />
						<c:if test="${cInt && cAdd }">
							<li><a id="ataskintadd" href="${urlStr }" class="active">Add</a></li>
						</c:if>
						<c:if test="${!(cInt && cAdd) }">
							<li><a id="ataskintadd" href="${urlStr }">Add</a></li>
						</c:if>

					</ul>
				</div>
			</li>
			<li class="panel">
				<c:if test="${cJiraSearch }">
					<a id="ajirasearch" href="#jirasearch" data-toggle="collapse" data-parent="#sidebar-nav-menu" class="active" aria-expanded="true">
				</c:if>
				<c:if test="${!cJiraSearch }">
					<a id="ajirasearch" href="#jirasearch" data-toggle="collapse" data-parent="#sidebar-nav-menu" class="collapsed">
				</c:if>
					<span class="title">JiraSearch</span>
					<i class="icon-submenu ti-angle-left"></i>
				</a>
				<c:if test="${cJiraSearch }">
					<div id="jirasearch" class="collapse in">
				</c:if>
				<c:if test="${!cJiraSearch }">
					<div id="jirasearch" class="collapse">
				</c:if>
					<ul class="submenu">
						<c:url var="urlStr" value="/TaskConfig/JiraSearch/List" />
						<c:if test="${cJiraSearch && cList }">
							<li><a href="${urlStr }" class="active">List</a></li>
						</c:if>
						<c:if test="${!(cJiraSearch && cList) }">
							<li><a href="${urlStr }">List</a></li>
						</c:if>
						<c:url var="urlStr" value="/TaskConfig/JiraSearch/Add" />
						<c:if test="${cJiraSearch && cAdd }">
							<li><a href="${urlStr }" class="active">Add</a></li>
						</c:if>
						<c:if test="${!(cJiraSearch && cAdd) }">
							<li><a href="${urlStr }">Add</a></li>
						</c:if>
					</ul>
				</div>
			</li>
			<li class="panel">
				<c:if test="${cWebState }">
					<a id="awebstate" href="#webstate" data-toggle="collapse" data-parent="#sidebar-nav-menu" class="active" aria-expanded="true">
				</c:if>
				<c:if test="${!cWebState }">
					<a id="awebstate" href="#webstate" data-toggle="collapse" data-parent="#sidebar-nav-menu" class="collapsed">
				</c:if>
					<span class="title">WebState</span>
					<i class="icon-submenu ti-angle-left"></i>
				</a>
				<c:if test="${cWebState }">
					<div id="webstate" class="collapse in">
				</c:if>
				<c:if test="${!cWebState }">
					<div id="webstate" class="collapse">
				</c:if>
					<ul class="submenu">
						<c:url var="urlStr" value="/TaskConfig/WebState/List" />
						<c:if test="${cWebState && cList }">
							<li><a id="ataskwebstatelist" href="${urlStr }" class="active">List</a></li>
						</c:if>
						<c:if test="${!(cWebState && cList) }">
							<li><a id="ataskwebstatelist" href="${urlStr }">List</a></li>
						</c:if>
						<c:url var="urlStr" value="/TaskConfig/WebState/Add" />
						<c:if test="${cWebState && cAdd }">
							<li><a id="ataskwebstateadd" href="${urlStr }" class="active">Add</a></li>
						</c:if>
						<c:if test="${!(cWebState && cAdd) }">
							<li><a id="ataskwebstateadd" href="${urlStr }">Add</a></li>
						</c:if>
					</ul>
				</div>
			</li>
			<li class="panel">
				<c:if test="${cHealth }">
					<a id="ahealth" href="#health" data-toggle="collapse" data-parent="#sidebar-nav-menu" class="active" aria-expanded="true">
				</c:if>
				<c:if test="${!cHealth }">
					<a id="ahealth" href="#health" data-toggle="collapse" data-parent="#sidebar-nav-menu" class="collapsed">
				</c:if>
					<span class="title">Health</span>
					<i class="icon-submenu ti-angle-left"></i>
				</a>
				<c:if test="${cHealth }">
					<div id="health" class="collapse in">
				</c:if>
				<c:if test="${!cHealth }">
					<div id="health" class="collapse">
				</c:if>
					<ul class="submenu">
						<c:url var="urlStr" value="/TaskConfig/Health/List" />
						<c:if test="${cHealth && cList }">
							<li><a id="ataskhealthlist" href="${urlStr }" class="active">List</a></li>
						</c:if>
						<c:if test="${!(cHealth && cList) }">
							<li><a id="ataskhealthlist" href="${urlStr }">List</a></li>
						</c:if>

						<c:url var="urlStr" value="/TaskConfig/Health/Add" />
						<c:if test="${cHealth && cAdd }">
							<li><a id="ataskhealthadd" href="${urlStr }" class="active">Add</a></li>
						</c:if>
						<c:if test="${!(cHealth && cAdd) }">
							<li><a id="ataskhealthadd" href="${urlStr }">Add</a></li>
						</c:if>
					</ul>
				</div>
			</li>


			<li class="menu-group">MessageSender</li>
			<li class="panel">


				<c:if test="${cDing }">
					<a href="#ding" data-toggle="collapse" data-parent="#sidebar-nav-menu" class="active" aria-expanded="true">
				</c:if>
				<c:if test="${!cDing }">
					<a href="#ding" data-toggle="collapse" data-parent="#sidebar-nav-menu" class="collapsed">
				</c:if>
					<span class="title">Ding</span>
					<i class="icon-submenu ti-angle-left"></i>
				</a>
				<c:if test="${cDing }">
					<div id="ding" class="collapse in">
				</c:if>
				<c:if test="${!cDing }">
					<div id="ding" class="collapse">
				</c:if>
					<ul class="submenu">
						<c:url var="urlStr" value="/MessageSender/Ding/List" />
						<c:if test="${cDing && cList }">
							<li><a id="amessagedinglist" href="${urlStr }" class="active">List</a></li>
						</c:if>
						<c:if test="${!(cDing && cList) }">
							<li><a id="amessagedinglist" href="${urlStr }">List</a></li>
						</c:if>
						<c:url var="urlStr" value="/MessageSender/Ding/Add" />
						<c:if test="${cDing && cAdd }">
							<li><a id="amessagedingadd" href="${urlStr }" class="active">Add</a></li>
						</c:if>
						<c:if test="${!(cDing && cAdd) }">
							<li><a id="amessagedingadd" href="${urlStr }">Add</a></li>
						</c:if>
					</ul>
				</div>
			</li>
		</ul>
	</nav>
</div>
<!-- END LEFT SIDEBAR -->