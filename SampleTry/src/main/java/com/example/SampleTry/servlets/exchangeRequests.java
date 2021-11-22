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

@WebServlet(name = "exchangeRequestsServlet", value = "/User/exchangeRequests")
public class exchangeRequests extends HttpServlet {
    private String message;
    studentDetailsDAO generalSDD;
    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String param = request.getParameter("flag");
        HttpSession session =request.getSession();
        System.out.println("exchangeRequests :: get :: "+param);
        if(param.equals("newReq")) {
            // Check active request
            studentDetailsVO sdvo = (studentDetailsVO) session.getAttribute("currStudent");
            exchgreqstDAO excdao = new exchgreqstDAO();
            List<exchgreqstVO> isactivereq = excdao.activeRequestCheck(sdvo.getSid(),sdvo);

            if (isactivereq.size() == 1) {
                session.setAttribute("cantGenerateReq", "Sorry !! Already active request ");
                response.sendRedirect("studentProfile.jsp");
            } else {
                hostelRoomDAO availRooms = new hostelRoomDAO();
                studentDetailsVO curr = (studentDetailsVO) session.getAttribute("currStudent");
                System.out.println(curr.getSid());
                System.out.println(curr.getHost_room().getRno());
                List<hostelRoomVO> temp = availRooms.availableRooms(curr.getSid(),
                        curr.getHost_room().getRno());

                session.setAttribute("availableRoomsForCurr", temp);

                response.sendRedirect("roomExchangeForm.jsp");
            }
        } else if (param.equals("editRequest")) {
            studentDetailsVO sdvo = (studentDetailsVO) session.getAttribute("currStudent");
            exchgreqstDAO excdao = new exchgreqstDAO();
            List<exchgreqstVO> isactivereq = excdao.activeRequestCheck(sdvo.getSid(), sdvo);
            hostelRoomDAO availRooms = new hostelRoomDAO();
            studentDetailsVO curr = (studentDetailsVO) session.getAttribute("currStudent");
            System.out.println(curr.getSid());
            System.out.println(curr.getHost_room().getRno());
            List<hostelRoomVO> temp = availRooms.availableRooms(curr.getSid(),
                    curr.getHost_room().getRno());
            session.setAttribute("availableRoomsForCurr", temp);
            session.setAttribute("updateRoomRequest", "yes");

            response.sendRedirect("roomExchangeForm.jsp");

        } else if (param.equals("viewExchangeRequests")) {

           try {
               System.out.println("try");
               exchgreqstVO currReqToMe = new exchgreqstVO();
               exchgreqstDAO getCurrReqToMe = new exchgreqstDAO();
               studentDetailsVO currStudent= (studentDetailsVO) session.getAttribute("currStudent");
               generalSDD = new studentDetailsDAO();
               generalSDD.RefreshIt(currStudent);
               session.setAttribute("currStudent", currStudent);
               List<exchgreqstVO> l = getCurrReqToMe.currRequestQuery(currStudent);
               hostelRoomDAO lrd = new hostelRoomDAO();

               for(exchgreqstVO ex : l) {
                   hostelRoomVO lh = lrd.idDetails(ex.getFrom_SID());
                   ex.setHost_room(lh);
               }


               session.setAttribute("currReqToMe", l);
               response.sendRedirect("viewExchangeReqs.jsp");
           } catch (Exception e) {
               System.out.println("exchangeRequestsServlet :: viewExchangeRequests"+e);
           }

        } else if (param.equals("deleteRequest")) {
            System.out.println("Delete request by user");
            exchgreqstDAO updateDetails = new exchgreqstDAO();
            updateDetails.approveExchangeReq(request.getParameter("requestNumber"), "delete");
            studentDetailsDAO xx = new studentDetailsDAO();
            studentDetailsVO currStudent = (studentDetailsVO) session.getAttribute("currStudent");
            xx.RefreshIt(currStudent);
            session.removeAttribute("currRequest");
            response.sendRedirect("studentProfile.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{

        HttpSession session =request.getSession();
        System.out.println("exchangeRequests :: post ");

        String isUpdate = (String)session.getAttribute("updateRoomRequest");

        if(isUpdate != null && isUpdate.equals("yes")) {
            exchgreqstDAO exda = new exchgreqstDAO();

            exchgreqstVO currReq = (exchgreqstVO) session.getAttribute("currRequest");
            int rno = Integer.parseInt(request.getParameter("roomNumber"));

            if(exda.updateExchangeRequest(currReq, rno,
                    request.getParameter("comment").toString() )) {

                try {
                    studentDetailsVO curr = (studentDetailsVO) session.getAttribute("currStudent");
                    exchgreqstDAO exDao = new exchgreqstDAO();
                    List<exchgreqstVO> isactivereq = exDao.activeRequestCheck(curr.getSid(), curr);
                    System.out.println("actovereq :: "+isactivereq.size());
                    if(isactivereq.size() >= 1) {
                        session.setAttribute("currRequest", isactivereq.get(0));
                    }
                } catch (Exception e) {
                    System.out.println("error :: "+e);
                }



                session.removeAttribute("updateRoomRequest");
                response.sendRedirect("studentProfile.jsp");

            } else {
                System.out.println("false");
                response.sendRedirect("roomExchangeForm.jsp");
            }

        } else {
            // Insert request in database;
            exchgreqstVO excobj = new exchgreqstVO();
            studentDetailsVO sdvo = (studentDetailsVO) session.getAttribute("currStudent");
            excobj.setComment(request.getParameter("comment"));
            excobj.setFrom_SID(sdvo.getSid());
            excobj.setRequest_status("Active");
            Date currTime = new Date();
            excobj.setRequest_date(currTime);
            exchgreqstDAO obj = new exchgreqstDAO();

            hostelRoomVO hrv = new hostelRoomVO();
            int v = Integer.parseInt(request.getParameter("roomNumber"));
            hrv.setRno(v);

            excobj.setHost_room(hrv);




            if(obj.insertIntoDatabase(excobj)) {
                studentDetailsVO curr = (studentDetailsVO) session.getAttribute("currStudent");
                studentDetailsDAO sdda = new studentDetailsDAO();
                sdda.RefreshIt(curr);



                exchgreqstDAO excdao = new exchgreqstDAO();
                List<exchgreqstVO> isactivereq = excdao.activeRequestCheck(curr.getSid(), sdvo);
                if(isactivereq.size() >= 1) {
                    session.setAttribute("currRequest", isactivereq.get(0));
                }
                response.sendRedirect("studentProfile.jsp");
            } else {
                response.sendRedirect("roomExchangeForm.jsp");
            }


        }
    }

    public void destroy() {
    }
}
