package com.example.SampleTry.servlets;

import com.example.SampleTry.dao.exchgreqstDAO;
import com.example.SampleTry.dao.hostelRoomDAO;
import com.example.SampleTry.dao.studentDAO;
import com.example.SampleTry.models.exchgreqstVO;
import com.example.SampleTry.models.hostelRoomVO;
import com.example.SampleTry.models.studentDetailsVO;
import com.example.SampleTry.models.studentVO;
import com.example.SampleTry.dao.studentDetailsDAO;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sound.midi.SysexMessage;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Date;

@WebServlet(name = "requestHandleServlet", value = "/User/requestHandle")
public class requestsHandle extends HttpServlet{
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String param = request.getParameter("flag");
        HttpSession session =request.getSession();
        System.out.println("requestHandleServlet :: post :: " + param);
        int page = 0;
        if(param.equals("approve")) {
            System.out.println("requestHandleServlet :: approve");
            studentDetailsVO currStudent = (studentDetailsVO) session.getAttribute("currStudent");
            String currSID = currStudent.getSid();
            String fromSID = request.getParameter("fromSID");
            hostelRoomVO chrv = new hostelRoomVO();
            chrv.setRno(currStudent.getHost_room().getRno());
            hostelRoomVO fhrv = new hostelRoomVO();
            fhrv.setRno(Integer.parseInt(request.getParameter("fromRno")));

            System.out.println("requestHandleServlet :: fromSID "+fromSID);
            System.out.println("requestHandleServlet :: currSID "+currSID);
            System.out.println("requestHandleServlet :: fromRno"+Integer.parseInt(request.getParameter("fromRno")));
            System.out.println("requestHandleServlet :: fromRno"+currStudent.getHost_room().getRno());

            exchgreqstDAO updateDetails = new exchgreqstDAO();
            updateDetails.approveExchangeReq(request.getParameter("requestNumber"), "Approve");

            studentDetailsDAO updateStudentDetails = new studentDetailsDAO();

            updateStudentDetails.updateRno(currSID, fromSID, chrv, fhrv);
            page = 1;
        } else if (param.equals("reject")){
            System.out.println("requestHandleServlet :: reject");
            exchgreqstDAO updateDetails = new exchgreqstDAO();
            updateDetails.approveExchangeReq(request.getParameter("requestNumber"), "reject");
            page = 2;
        }
        studentDetailsDAO xx = new studentDetailsDAO();
        studentDetailsVO currStudent = (studentDetailsVO) session.getAttribute("currStudent");

        xx.RefreshIt(currStudent);
        exchgreqstDAO excdao = new exchgreqstDAO();
        List<exchgreqstVO> isactivereq = excdao.activeRequestCheck(currStudent.getSid(), currStudent);
        if(isactivereq.size() >= 1) {
            session.setAttribute("currRequest", isactivereq.get(0));
        }
        if(page == 1) {
            response.sendRedirect("studentProfile.jsp");
        } else if (page == 2) {
            response.sendRedirect("exchangeRequests?flag=viewExchangeRequests");
        }
    }
}
