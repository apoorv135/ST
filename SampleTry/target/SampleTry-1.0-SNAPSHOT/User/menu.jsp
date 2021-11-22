<div class="left side-menu">
			<div class="sidebar-inner slimscrollleft">
				<div class="user-details">
					<!-- <div class="text-center">
						<img src="images/avatar-1.jpg" alt=""
							class="img-circle">
					</div> -->
					<div class="user-info">
						<div class="dropdown">
							<a href="?flag=logout" class="dropdown-toggle" data-toggle="dropdown"
								aria-expanded="false">
								<%	session.getAttribute("userName");
								%>${userName }
								</a>
							<ul class="dropdown-menu">
								<!-- <li><a href="javascript:void(0)"> Profile</a></li>
								<li><a href="javascript:void(0)"> Settings</a></li>
								<li><a href="javascript:void(0)"> Lock screen</a></li>
								<li class="divider"></li> -->
								<li><a href="loginUserStudent?flag=logout"> Logout</a></li>
							</ul>
						</div>
						<p class="text-muted m-0">
							<i class="fa fa-dot-circle-o text-success"></i> Online
						</p>
					</div>
				</div>
				<div id="sidebar-menu">
					<ul>
<%--						<li><a href="<%=request.getContextPath()%>/BucketController?flag=viewBucket" class="waves-effect"><i--%>
<%--								class="ti-home"></i><span> Home </span></a></li>--%>
						<li><a href="loginUserStudent?flag=refreshProfile"
							   class="waves-effect"><i
								class="ti-user"></i><span> Profile </span></a></li>
						<!-- <li><a href="typography.html" class="waves-effect"><i
								class="ti-ruler-pencil"></i><span> Typography <span
									class="badge badge-primary pull-right">6</span></span></a></li> -->
						<li class="has_sub"><a href="javascript:void(0);"
							class="waves-effect"><i class="ti-agenda"></i> <span>
									Request </span> <span class="pull-right"><i
									class="mdi mdi-plus"></i></span></a>
						<ul class="list-unstyled">
								<li><a href="exchangeRequests?flag=newReq">New swap request</a></li>
								<li><a
										href="exchangeRequests?flag=viewExchangeRequests">View current requests</a></li>
<%--							<li><a--%>
<%--									href="<%=request.getContextPath()%>/BucketController?flag=showFolders">View all requests</a></li>--%>
<%--							</ul></li>--%>
		
<%--						<li class="has_sub"><a href="javascript:void(0);"--%>
<%--							class="waves-effect"><i class="ti-agenda"></i> <span>--%>
<%--									Complain </span> <span class="pull-right"><i--%>
<%--									class="mdi mdi-plus"></i></span></a>--%>
<%--						<ul class="list-unstyled">--%>
<%--								<li><a href="postComplain.jsp" class="waves-effect"><i--%>
<%--								class="ti-files"></i><span> Post Complain </span></a></li>--%>
<%--								<li><a href="<%=request.getContextPath()%>/ComplainController?flag=viewComplain" class="waves-effect"><i--%>
<%--								class="ti-files"></i><span> View Complains </span></a></li>--%>
<%--							</ul></li>--%>
<%--						<li><a href="postFeedback.jsp" class="waves-effect"><i--%>
<%--								class="ti-files"></i><span> Post Feedback</span></a></li>--%>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>