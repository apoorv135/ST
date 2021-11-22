package com.example.SampleTry.servlets;

import com.example.SampleTry.dao.exchgreqstDAO;
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
import java.util.List;

@WebServlet(name = "loginStudentServlet", value = "/User/loginUserStudent")
public class loginStudentUser extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String param = request.getParameter("flag");
        System.out.println("doGet loginUserStudent :: " + param);
        HttpSession session = request.getSession();
        if(param.equals("logout")) {
            session.invalidate();
            response.sendRedirect("loginUser.jsp");
        } else if (param.equals("refreshProfile")) {
            studentDetailsDAO sdd = new studentDetailsDAO();
            studentDetailsVO currStudent = (studentDetailsVO)session.getAttribute("currStudent");
            sdd.RefreshIt(currStudent);
            session.setAttribute("currStudent", currStudent);

            exchgreqstDAO exDao = new exchgreqstDAO();
            List<exchgreqstVO> isactivereq = exDao.activeRequestCheck(currStudent.getSid(), currStudent);
            System.out.println("actovereq :: "+isactivereq.size());
            if(isactivereq.size() >= 1) {
                session.setAttribute("currRequest", isactivereq.get(0));
            } else {
                session.removeAttribute("currRequest");
            }

            response.sendRedirect("studentProfile.jsp");


        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        System.out.println("loginStudentUser servlet\n===========");
        String sid = request.getParameter("sid");
        System.out.println(sid);
        String password = request.getParameter("password");
        System.out.println(password + "\n============");
        studentDetailsDAO studLogin = new studentDetailsDAO();

        List<studentDetailsVO> currStudent = studLogin.authenticate(sid, password);

        HttpSession session = request.getSession();

        if(currStudent.size() >= 1) {
            System.out.println("success");
            System.out.println(currStudent.get(0).getFirstName());

            session.setAttribute("currStudent", currStudent.get(0));
            session.setAttribute("userName",
                    currStudent.get(0).getFirstName() + " " + currStudent.get(0).getLastName());

            studentDetailsVO sdvo = currStudent.get(0);
            exchgreqstDAO excdao = new exchgreqstDAO();
            List<exchgreqstVO> isactivereq = excdao.activeRequestCheck(sdvo.getSid(), sdvo);

            if(isactivereq.size() >= 1) {
                session.setAttribute("currRequest", isactivereq.get(0));
            }

            response.sendRedirect("studentProfile.jsp");
        } else {
            System.out.println("fail");
            session.setAttribute("studentLoginError", "Invalid username/password");
            response.sendRedirect("loginUser.jsp");
        }

    }

    public void destroy() {
    }
}
