package com.example.SampleTry.servlets;



import com.example.SampleTry.dao.studentDAO;
import com.example.SampleTry.models.studentVO;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.sql.*;

@WebServlet(name = "helloServlet", value = "/loginUser")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        System.out.println(id);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);


        studentVO haha = new studentVO();
        haha.setId(id);
        haha.setFirstName(firstName);
        haha.setLastName(lastName);
        haha.setEmail(email);

        studentDAO lol = new studentDAO();

        int isValid = lol.authenticate(haha);

        HttpSession session = request.getSession();

        if(isValid == 1) {
            session.setAttribute("firstName",firstName);
            response.sendRedirect("viewFiles.jsp");
        } else {

        }

    }

    public void destroy() {
    }
}