package com.example.mvc_2dt.controller;

import com.example.mvc_2dt.dto.StudentDtoReponse;
import com.example.mvc_2dt.entity.Class;
import com.example.mvc_2dt.service.classs.ClassService;
import com.example.mvc_2dt.service.student.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {
    private StudentService studentService = new StudentService();
    private ClassService classService = new ClassService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null){
            action = "list";
        }
        switch (action){
            case "create":
                break;
            case "update":
                break;
            case "delete":
                break;
            case "list":
            default:
                listStudent(req,resp);
                break;
        }
    }

    private void listStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentDtoReponse> studentDtoReponses = studentService.findAll();
        List<Class> classList = classService.findAll();
        req.setAttribute("classlist",classList);
        req.setAttribute("studentlist",studentDtoReponses);
        RequestDispatcher dispatcher = req.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
