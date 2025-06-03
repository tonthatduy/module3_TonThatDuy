package com.example.ung_dung_calculator;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/calculator")
public class Calculator extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double first = Double.parseDouble(req.getParameter("first"));
        double second = Double.parseDouble(req.getParameter("second"));
        double result;
        String operator = req.getParameter("operator");
        if (operator.equals("cong")) {
            result = first + second;
        } else if (operator.equals("tru")) {
            result = first - second;
        } else if (operator.equals("nhan")) {
            result = first * second;
        } else {
            result = first / second;
        }
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Result:</h2>");
        writer.println(result);
    }
}
