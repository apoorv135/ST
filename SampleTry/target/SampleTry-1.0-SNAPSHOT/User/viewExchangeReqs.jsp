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
                            <h4 class="pull-left page-title">Requests for room swap</h4>
                            <!-- <ol class="breadcrumb pull-right">
                                <li><a href="#">Xadmino</a></li>
                                <li><a href="#">Tables</a></li>
                                <li class="active">Datatables</li>
                            </ol> -->
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-primary">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <table id="datatable"
                                               class="table table-striped table-bordered">
                                            <thead>
                                            <tr>
                                                <th>Request number</th>
                                                <th>From SID</th>
                                                <th>Date</th>
                                                <th>Hostel number</th>
                                                <th>Room number</th>
                                                <th>Comment</th>
                                                <th>Action</th>
                                                <!-- <th>Position</th>
                                                <th>Office</th>
                                                <th>Age</th>
                                                <th>Start date</th>
                                                <th>Salary</th> -->
                                            </tr>
                                            </thead>
<%--                                            <c:forEach items="${sessionScope.viewComplain }" var="i" varStatus="j">--%>
<%--                                            <tbody>--%>
<%--                                            <tr>--%>
<%--                                                <td>${i.complainName }</td>--%>
<%--                                                <td>${i.complainDescription }</td>--%>
<%--                                                <td>${i.complainDate }</td>--%>
<%--                                                <td>${i.complainTime }</td>--%>
<%--                                                <td>${i.replyDescription }</td>--%>
<%--                                                <td>${i.replyDate }</td>--%>
<%--                                                <td>${i.replyTime }</td>--%>
<%--                                            </tr>--%>

<%--                                            </c:forEach>--%>
                                                <c:forEach items="${currReqToMe}"
                                                           var = "i">
                                                    <tbody>
                                                        <td>${i.requestNumber}</td>
                                                        <td>${i.from_SID}</td>
                                                        <td>${i.request_date}</td>
                                                        <td>${i.host_room.getHostelNo()}</td>
                                                        <td>${i.host_room.getRoom()}</td>
                                                        <td>${i.comment}</td>
                                                        <td>


                                                            <form action = "requestHandle"
                                                                  method = "post">
                                                                <input type="hidden" value = "approve"
                                                                       name = "flag">
                                                                <input type = "hidden" name =
                                                                        "fromRno"
                                                                       value="${i.host_room.getRno()}">
                                                                <input type = "hidden" name =
                                                                        "fromSID"
                                                                       value="${i.from_SID}">
                                                                <input type = "hidden" name =
                                                                        "requestNumber"
                                                                       value="${i.requestNumber}">
                                                                <div class="form-group text-center m-t-20">
                                                                    <div class="col-xs-12">
                                                                        <button class="btn btn-primary w-md waves-effect waves-light"
                                                                                type="submit">Approve
                                                                        </button>
                                                                    </div>
                                                                </div>


                                                            </form>
                                                            <form action = "requestHandle" method = "post">
                                                                <input type="hidden" value =
                                                                        "reject"
                                                                       name = "flag">
                                                                <input type = "hidden" name =
                                                                        "requestNumber"
                                                                       value="${i.requestNumber}">
                                                                <div class="form-group text-center m-t-20">
                                                                    <div class="col-xs-12">
                                                                        <button class="btn btn-primary w-md waves-effect waves-light"
                                                                                type="submit">Reject
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </form>
                                                         </td>

                                                    </tbody>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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