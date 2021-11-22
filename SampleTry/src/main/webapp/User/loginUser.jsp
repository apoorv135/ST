<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/User/">
    <meta charset="utf-8" />
    <title>Hostel Room Swap</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta content="Admin Dashboard" name="description" />
    <meta content="ThemeDesign" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="shortcut icon" href="images/favicon.ico">
    <link href="css/bootstrap.min.css" rel="stylesheet"
          type="text/css">
    <link href="css/icons.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <script>
        (function(i, s, o, g, r, a, m) {
            i['GoogleAnalyticsObject'] = r;
            i[r] = i[r] || function() {
                (i[r].q = i[r].q || []).push(arguments)
            }, i[r].l = 1 * new Date();
            a = s.createElement(o), m = s.getElementsByTagName(o)[0];
            a.async = 1;
            a.src = g;
            m.parentNode.insertBefore(a, m)
        })(window, document, 'script',
            'https://www.google-analytics.com/analytics.js', 'ga');
        ga('create', 'UA-86308552-1', 'auto');
        ga('send', 'pageview');
    </script>
</head>
<body>
<div class="accountbg"></div>
<div class="wrapper-page">
    <div class="panel panel-color panel-primary panel-pages">
        <div class="panel-body">
            <h3 class="text-center m-t-0 m-b-30">
                <span class=""><img src="images/logo_dark.png" alt="Fly Bucket" height="80"></span>
            </h3>
            <h4 class="text-muted text-center m-t-0">
                <b>Sign In</b>
            </h4>
            <form class="form-horizontal m-t-20" action="loginUserStudent" method="post">

                <div class="form-group">
                    <div class="col-xs-12">
                        <input class="form-control" type="text" required="required"
                               placeholder="SID" name="sid">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-12">
                        <input class="form-control" type="password" required=""
                               placeholder="Password" name="password">
                    </div>
                </div>

<%--                <div class="form-group">--%>
<%--                    <div class="col-xs-12">--%>
<%--                        <div class="checkbox checkbox-primary">--%>
<%--                            <input id="checkbox-signup" type="checkbox"> <label--%>
<%--                                for="checkbox-signup"> Remember me </label>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>


                <div class="checkbox checkbox-primary">
                    <%
                        String error = (String)session.getAttribute("studentLoginError");
                        if(error!=null){%>
                    <span style="color: red"><%out.println(error);
                    session.removeAttribute("error");
                    %></span>
                    <% }
                    else{
                        out.println("");
                    }
                    %>
                </div>
                <div class="form-group text-center m-t-20">
                    <div class="col-xs-12">
                        <button class="btn btn-primary w-md waves-effect waves-light"
                                type="submit">Log In</button>
                    </div>
                </div>
<%--                <div class="form-group text-center m-t-20">--%>
<%--                    <div class="btn btn-primary w-md waves-effect waves-light">--%>
<%--                        <a href="register.jsp" class="btn btn-primary w-md waves-effect waves-light">Sign Up</a>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="form-group m-t-30 m-b-0">
                    <div class="col-sm-7">
                        <a href="recoverPassword.jsp" class="text-muted"><i
                                class="fa fa-lock m-r-5"></i> Forgot your password?</a>
                    </div>
<%--                    <div class="col-sm-5 text-right">--%>
<%--                        <a href="register.jsp" class="text-muted">Create an--%>
<%--                            account</a>--%>
<%--                    </div>--%>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/modernizr.min.js"></script>
<script src="js/detect.js"></script>
<script src="js/fastclick.js"></script>
<script src="js/jquery.slimscroll.js"></script>
<script src="js/jquery.blockUI.js"></script>
<script src="js/waves.js"></script>
<script src="js/wow.min.js"></script>
<script src="js/jquery.nicescroll.js"></script>
<script src="js/jquery.scrollTo.min.js"></script>
<script src="js/app.js"></script>
</body>
</html>