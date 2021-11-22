<%@ page import="com.example.SampleTry.models.studentDetailsVO" %>
<%@ page import="com.example.SampleTry.models.exchgreqstVO" %><%--
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

        function alertBox() {
            <%
            String errorMsg = (String)session.getAttribute("cantGenerateReq");
            if(errorMsg != null) {
            %>
            alert("Sorry !! Already active request ");
            <%
            session.removeAttribute("cantGenerateReq");
            }
            %>

        }
    </script>
</head>
<body class="fixed-left" onload="alertBox()">
<div id="wrapper">
    <jsp:include page="header.jsp"></jsp:include>
    <jsp:include page="menu.jsp"></jsp:include>
    <div class="content-page">
        <div class="content">
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="page-header-title">
                            <h4 class="pull-left page-title">Profile</h4>
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
                            <!-- <div class="panel-heading">
                                <h3 class="panel-title">Default Example</h3>
                            </div> -->
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <% studentDetailsVO temp =
                                                (studentDetailsVO) session.getAttribute("currStudent");%>
                                        <table id="datatable"
                                               class="table table-striped table-bordered">
                                        <tr>
                                            <th>Name</th>
                                            <td><% out.print(temp.getFirstName() + " " +
                                                    temp.getLastName());
                                            %></td>
                                        </tr>
                                        <tr>
                                            <th>SID</th>
                                            <td><% out.print(temp.getSid()); %></td>
                                        </tr>
                                        <tr>
                                            <th>Birthdate</th>
                                            <td><% out.print(temp.getBirthday()); %></td>
                                        </tr>
                                        <tr>
                                            <th>Address</th>
                                            <td><% out.print(temp.getAddress());
                                            %></td>
                                        </tr>
                                        <tr>
                                            <th>Sex</th>
                                            <td><% out.print(temp.getSex());
                                            %></td>
                                        </tr>
                                        </table>
                                        Room Details:
                                        <table id="datatable"
                                               class="table table-striped table-bordered">
                                            <tr>
                                                <th>Hostel number</th>
                                                <td>
                                                    <%out.print(temp.getHost_room().getHostelNo()); %>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Room number</th>
                                                <td>
                                                    <%out.print(temp.getHost_room().getRoom()); %>
                                                </td>
                                            </tr>

                                        </table>

                                        <%
                                            exchgreqstVO excReq = (exchgreqstVO)
                                                    session.getAttribute("currRequest");

                                            if(excReq != null) {
                                                out.println("Current Request: \n"+
                                                "<table id=\"datatable\" class=\"table table-striped table-bordered\">" +
                                                       "<tr> <th>Request number  </th> <td>"+excReq.getRequestNumber()+"</td></tr>" +
                                                       "<tr> <th>Date  </th> <td>"+excReq.getRequest_date()+"</td></tr>" +
                                                       "<tr> <th>Room number   </th> <td>"+excReq.getHost_room().getRoom()+"</td></tr>" +
                                                       "<tr> <th>Hostel number  </th> <td>"+excReq.getHost_room().getHostelNo()+"</td></tr>" +
                                                       "<tr> <th>Comment  </th> <td>"+excReq.getComment()+"</td></tr>" +
                                                       "<tr> <th>Status  </th> <td>"+excReq.getRequest_status()+"</td></tr>" +
                                                       "<tr><td>"+"<div class=\"form-group text-center m-t-20\">\n" +
                                                                "                    <div class=\"col-xs-12\">\n" +
                                                                "                    <form action = \"exchangeRequests\" method = \"get\"><input type=\"hidden\" name=\"flag\" value=\"editRequest\">    <button class=\"btn btn-primary w-md waves-effect waves-light\"\n" +
                                                                "                                type=\"submit\">Edit request</button></form>\n" +
                                                                "                    </div>\n" +
                                                                "                </div>"+"</td> <td>"+"<div class=\"form-group text-center m-t-20\">\n" +
                                                                "                    <div class=\"col-xs-12\">\n" +
                                                                "                    <form action = \"exchangeRequests\" method = \"get\"><input type=\"hidden\" name=\"flag\" value=\"deleteRequest\"> <input type = \"hidden\" name = \"requestNumber\" value=\""+excReq.getRequestNumber()+"\">   <button class=\"btn btn-primary w-md waves-effect waves-light\"\n" +
                                                                "                                type=\"submit\">Delete request</button></form>\n" +
                                                                "                    </div>\n" +
                                                                "                </div>"+"</td> </tr> </table>"
//                                                        "<div class=\"form-group text-center m-t-20\">\n" +
//                                                                "                    <div class=\"col-xs-12\">\n" +
//                                                                "                    <form action = \"exchangeRequests\" method = \"get\"><input type=\"hidden\" name=\"flag\" value=\"editRequest\">    <button class=\"btn btn-primary w-md waves-effect waves-light\"\n" +
//                                                                "                                type=\"submit\">Edit request</button></form>\n" +
//                                                                "                    </div>\n" +
//                                                                "                </div>"


                                                );
                                            } else {
                                                out.println("<div class=\"form-group text-center m-t-20\">\n" +
                                                        "                  <form action = \"exchangeRequests\" method = \"get\">  <div class=\"col-xs-12\">\n" +
                                                        "                        <input type=\"hidden\" name=\"flag\" value=\"newReq\"><button class=\"btn btn-primary w-md waves-effect waves-light\"\n" +
                                                        "                                type=\"submit\">New room swap request</button></form>\n" +
                                                        "                    </div>\n" +
                                                        "                </div>");
                                            }
                                        %>

                                        <%
                                            String folderError = (String)session.getAttribute("foldererror");
                                            if(folderError!=null)
                                            {
                                        %>
                                        <span style="color: red"><%out.println(folderError);%></span>
                                        <%
                                                session.removeAttribute(folderError);

                                            }

                                            else{
                                                out.println("");
                                            }

                                        %>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
<%--        <footer class="footer"> Fly Bucket</footer>--%>
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
