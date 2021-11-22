<%@ page import="com.example.SampleTry.models.studentDetailsVO" %>
<%@ page import="com.example.SampleTry.models.hostelRoomVO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: dhagi
  Date: 12/21/2020
  Time: 8:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Hostel Room Swap</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta content="Admin Dashboard" name="description" />
    <meta content="ThemeDesign" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="shortcut icon" href="assets/images/favicon.ico">
    <link href="css/jquery.dataTables.min.css"
          rel="stylesheet" type="text/css" />
    <link href="css/buttons.bootstrap.min.css"
          rel="stylesheet" type="text/css" />
    <link href="css/fixedHeader.bootstrap.min.css"
          rel="stylesheet" type="text/css" />
    <link href="css/responsive.bootstrap.min.css"
          rel="stylesheet" type="text/css" />
    <link href="css/dataTables.bootstrap.min.css"
          rel="stylesheet" type="text/css" />
    <link href="css/scroller.bootstrap.min.css"
          rel="stylesheet" type="text/css" />
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
    <style>
        div.formExchange {
            width: 35%;
        }
    </style>
</head>
<body class="fixed-left">
<div id="wrapper">
    <jsp:include page="header.jsp"></jsp:include>
    <jsp:include page="menu.jsp"></jsp:include>
    <div class="content-page">
        <div class="content">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="page-header-title">
                            <h4 class="pull-left page-title">My Bucket</h4>
                            <!-- <ol class="breadcrumb pull-right">
                                <li><a href="#">Xadmino</a></li>
                                <li><a href="#">Tables</a></li>
                                <li class="active">Datatables</li>
                            </ol> -->
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
                <div class="formExchange">
                    <div class="col-md-12">
                        <div class="panel panel-primary">
                            <!-- <div class="panel-heading">
                                <h3 class="panel-title">Default Example</h3>
                            </div> -->
                            <div class="panel-body">
                                <div class="row">
                                    <div>
                                        <form class="form-horizontal m-t-20" action="exchangeRequests" method="post">
                                            <div>Available room requests : </div>

<%--                                            <% List<hostelRoomVO> availableRoomsForCurr =--%>
<%--                                                    (List<hostelRoomVO>)--%>
<%--                                                            session.getAttribute("availableRoomsForCurr");--%>

<%--                                            %>--%>

                                            <select name = "roomNumber" >
                                                <c:forEach items="${availableRoomsForCurr}" var =
                                                        "roomNumber" >
                                                    <option
                                                            value="${roomNumber.getRno()}">
                                                            Room number : ${roomNumber.getRoom()}
                                                        Hostel number
                                                                    ${roomNumber.getHostelNo()}</option>
                                                </c:forEach>
                                            </select>
                                            <textarea name = "comment" required="required">

                                            </textarea>

                                            <%--                <div class="form-group">--%>
                                            <%--                    <div class="col-xs-12">--%>
                                            <%--                        <div class="checkbox checkbox-primary">--%>
                                            <%--                            <input id="checkbox-signup" type="checkbox"> <label--%>
                                            <%--                                for="checkbox-signup"> Remember me </label>--%>
                                            <%--                        </div>--%>
                                            <%--                    </div>--%>
                                            <%--                </div>--%>



                                            <div class="form-group text-center m-t-20">
                                                <div class="col-xs-12">
                                                    <button class="btn btn-primary w-md waves-effect waves-light"
                                                            type="submit">Generate room
                                                        request</button>
                                                </div>
                                            </div>
                                            <%--                <div class="form-group text-center m-t-20">--%>
                                            <%--                    <div class="btn btn-primary w-md waves-effect waves-light">--%>
                                            <%--                        <a href="register.jsp" class="btn btn-primary w-md waves-effect waves-light">Sign Up</a>--%>
                                            <%--                    </div>--%>
                                            <%--                </div>--%>
                                            <div class="form-group m-t-30 m-b-0">
                                                <div class="col-sm-7">

                                                </div>

                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer class="footer"> </footer>
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
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.bootstrap.js"></script>
<script src="js/dataTables.buttons.min.js"></script>
<script src="js/buttons.bootstrap.min.js"></script>
<script src="js/jszip.min.js"></script>
<script src="js/pdfmake.min.js"></script>
<script src="js/vfs_fonts.js"></script>
<script src="js/buttons.html5.min.js"></script>
<script src="js/buttons.print.min.js"></script>
<script src="js/dataTables.fixedHeader.min.js"></script>
<script src="js/dataTables.keyTable.min.js"></script>
<script src="js/dataTables.responsive.min.js"></script>
<script src="js/responsive.bootstrap.min.js"></script>
<script src="js/dataTables.scroller.min.js"></script>
<script src="js/datatables.init.js"></script>
<script src="js/app.js"></script>
</body>
</html>
