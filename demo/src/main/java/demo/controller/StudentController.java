package demo.controller;

import com.codegym.c0225g1.entity.Student;
import com.codegym.c0225g1.service.IStudentService;
import com.codegym.c0225g1.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/student")
public class StudentController extends HttpServlet {
    private IStudentService studentService = new StudentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentService.findAll();
        req.setAttribute("students", students);
        req.getRequestDispatcher("/WEB-INF/student/list.jsp").forward(req, resp);
    }
}
