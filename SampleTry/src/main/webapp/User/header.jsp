<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<div class="topbar">
			<div class="topbar-left">
				<div class="text-center">
					<a  class="logo"><img
						src="images/logo_white_2.png" height="62"></a> <a
						href="index.html" class="logo-sm"><img
						src="images/logo_sm.png" height="36"></a>
				</div>
			</div>
			<div class="navbar navbar-default" role="navigation">
				<div class="container">
					<div class="">
						<div class="pull-left">
							<button type="button"
								class="button-menu-mobile open-left waves-effect waves-light">
								<i class="ion-navicon"></i>
							</button>
							<span class="clearfix"></span>
						</div>
<%--						<form class="navbar-form pull-left" role="search">--%>
<%--							<div class="form-group">--%>
<%--								<input type="text" class="form-control search-bar"--%>
<%--									placeholder="Search...">--%>
<%--							</div>--%>
<%--							<button type="submit" class="btn btn-search">--%>
<%--								<i class="fa fa-search"></i>--%>
<%--							</button>--%>
<%--						</form>--%>
						<ul class="nav navbar-nav navbar-right pull-right">
						
							<li class="dropdown hidden-xs">
							<a href="viewSharefile.jsp" data-target="#"
								class="dropdown-toggle waves-effect waves-light"
								data-toggle="dropdown" aria-expanded="true"> 
								<i class="fa fa-bell"> </i> 
								<span class="badge badge-xs badge-danger"> ${sessionScope.notificationSize}</span>
							</a>
							
							<ul class="dropdown-menu dropdown-menu-lg">
							
									<li class="text-center notifi-title">Notification 
									<span class="badge badge-xs badge-success"> </span>
													<div class="media-heading">New Message received</div> 
													<c:forEach items="${sessionScope.Notification }" var="i">
													<a style="color:white" href = "<%=request.getContextPath()%>/BucketController?flag=viewShare&p=${i.shareId}"> 
													<div class="media-heading"> ${i.fromUser.userName } shared  ${i.objectName.objectName}
													</div>
													</a>
													</c:forEach> 													
												<small class="text-primary">See all notifications</small>
									</li>
								</ul>
								</li>
							<li class="hidden-xs"><a href="#" id="btn-fullscreen"
								class="waves-effect waves-light"><i class="fa fa-crosshairs"></i></a></li>
							<li class="dropdown"><a href=""
								class="dropdown-toggle profile waves-effect waves-light"
								data-toggle="dropdown" aria-expanded="true"><img
									src="images/logo_2.jpg" alt="user-img"
									class="img-circle"> </a>
							<ul class="dropdown-menu">
									<!-- <li><a href="javascript:void(0)"> Profile</a></li>
									<li><a href="javascript:void(0)"><span
											class="badge badge-success pull-right">5</span> Settings </a></li>
									<li><a href="javascript:void(0)"> Lock screen</a></li>
									<li class="divider"></li> -->
									<li><a href="loginUserStudent?flag=logout"> Logout</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
			</div>
		</div>