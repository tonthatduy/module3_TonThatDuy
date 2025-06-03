package com.example.product_discount_calculator;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/display_discount")
public class DiscountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String description = req.getParameter("description");
        double list_price = Double.parseDouble(req.getParameter("list_price"));
        double discount = Double.parseDouble(req.getParameter("discount"));
        double discount_amount = list_price * discount * 0.01;
        double discount_price = list_price - discount_amount;

        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<h1>Description: " + description + "</h1>");
        writer.println("<h1>Discount Amount: " + discount_amount + "</h1>");
        writer.println("<h1>Discount Price: " + discount_price + "</h1>");
        writer.println("</html>");
    }
}
